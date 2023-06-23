package implementations;

import cz.dejfcold.cisco_demo.implementations.GNodeImpl;
import cz.dejfcold.cisco_demo.implementations.GraphInspectorImpl;
import cz.dejfcold.cisco_demo.preconditions.GNode;
import cz.dejfcold.cisco_demo.preconditions.GraphInspector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;

class GraphInspectorImplTest {

    private final GraphInspector graphInspector = new GraphInspectorImpl();

    @ParameterizedTest
    @MethodSource("validInputData")
    void should_pass_when_validInput(GNode node, String[][] solution) {
        var paths = graphInspector.paths(node);

        var stringPaths = paths.stream()
                .map(it -> it.stream()
                        .map(GNode::getName)
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        Arrays.sort(solution, Comparator.comparing(Arrays::hashCode));
        Arrays.sort(stringPaths, Comparator.comparing(Arrays::hashCode));

        Assertions.assertArrayEquals(solution, stringPaths);

    }


    @Test
    void should_throwNPE_when_null() {
        Assertions.assertThrows(NullPointerException.class, () -> graphInspector.paths(null));
    }


    private static Object[][] validInputData() {
        var sampleGraph =
                new GNodeImpl("A",
                        new GNodeImpl("B",
                                new GNodeImpl("E"),
                                new GNodeImpl("F")),
                        new GNodeImpl("C",
                                new GNodeImpl("G"),
                                new GNodeImpl("H"),
                                new GNodeImpl("I")),
                        new GNodeImpl("D",
                                new GNodeImpl("J")
                        )
                );
        String[][] sampleGraphSolution = {
                {"A", "B", "E"},
                {"A", "B", "F"},
                {"A", "C", "G"},
                {"A", "C", "H"},
                {"A", "C", "I"},
                {"A", "D", "J"}
        };

        var singleNodeGraph = new GNodeImpl("Single");
        String[][] singleNodeGraphSolution = {{"Single"}};

        var mergePoint = new GNodeImpl("Merge");
        var mergingGraph = new GNodeImpl("A",
                new GNodeImpl("B", mergePoint),
                new GNodeImpl("C", mergePoint)
        );

        String[][] mergingGraphSolution = {
                {"A", "B", "Merge"},
                {"A", "C", "Merge"}
        };

        return new Object[][]{
                new Object[]{sampleGraph, sampleGraphSolution},
                new Object[]{singleNodeGraph, singleNodeGraphSolution},
                new Object[]{mergingGraph, mergingGraphSolution}
        };
    }

}