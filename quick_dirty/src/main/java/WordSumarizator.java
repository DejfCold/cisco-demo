import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class WordSumarizator {
    private final String path;

    WordSumarizator(String path) {
        this.path = path;
    }

    public HashMap<String, Integer> process() throws IOException {
        var tokenMap = new HashMap<String, Integer>();

        try (var reader = new FileReader(path)) {
            var bufferedReader = new BufferedReader(reader);
            var streamTokenizer = new StreamTokenizer(bufferedReader);
            streamTokenizer.lowerCaseMode(true);
            streamTokenizer.whitespaceChars('.', '.');


            var currentToken = streamTokenizer.nextToken();
            while (currentToken != StreamTokenizer.TT_EOF) {
                if (streamTokenizer.ttype != StreamTokenizer.TT_WORD) {
                    currentToken = streamTokenizer.nextToken();
                    continue;
                }

                var word = streamTokenizer.sval;
                tokenMap.putIfAbsent(word, 0);
                tokenMap.put(word, tokenMap.get(word) + 1);

                currentToken = streamTokenizer.nextToken();
            }

        }

        return tokenMap;
    }

    public static void main(String[] args) throws IOException {
        var sumarizator = new WordSumarizator(args[0]);
        var result = sumarizator.process();
        for (var word : result.entrySet()) {
            System.out.println(word.getValue() + " " + word.getKey());
        }
    }
}
