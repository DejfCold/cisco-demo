package implementations;

import cz.dejfcold.cisco_demo.implementations.GNodeImpl;
import cz.dejfcold.cisco_demo.implementations.GraphWalkerImpl;
import cz.dejfcold.cisco_demo.preconditions.GNode;
import cz.dejfcold.cisco_demo.preconditions.GraphWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;


class GraphWalkerImplTest {
    private final GraphWalker graphWalker = new GraphWalkerImpl();

    @ParameterizedTest
    @MethodSource("validInputData")
    void should_pass_when_validInput(GNode graphNode, String[] solution) {
        var traversedGraph = graphWalker.walkGraph(graphNode);

        var traversedGraphStringArray = traversedGraph.stream()
                .map(GNode::getName)
                .toArray(String[]::new);

        Arrays.sort(solution);
        Arrays.sort(traversedGraphStringArray);

        Assertions.assertArrayEquals(traversedGraphStringArray, solution);
    }

    @Test
    void should_throwNPE_when_null() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            graphWalker.walkGraph(null);
        });
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
        String[] sampleGraphSolution = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        var singleNodeGraph = new GNodeImpl("Single");
        String[] singleNodeGraphSolution = {"Single"};

        var mergePoint = new GNodeImpl("Merge");
        var mergingGraph = new GNodeImpl("A",
                new GNodeImpl("B", mergePoint),
                new GNodeImpl("C", mergePoint)
        );
        String[] mergingGraphSolution = {"A", "B", "C", "Merge"};

        return new Object[][]{
                {sampleGraph, sampleGraphSolution},
                {singleNodeGraph, singleNodeGraphSolution},
                {mergingGraph, mergingGraphSolution}
        };
    }
}