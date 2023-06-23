package cz.dejfcold.cisco_demo.implementations;

import cz.dejfcold.cisco_demo.preconditions.GNode;
import cz.dejfcold.cisco_demo.preconditions.GraphWalker;

import java.util.*;

public class GraphWalkerImpl implements GraphWalker {
    @Override
    public List<GNode> walkGraph(GNode node) {
        Objects.requireNonNull(node);

        var nodeSet = new HashSet<GNode>();

        var nodesToVisit = new Stack<GNode>();
        nodesToVisit.push(node);

        while (!nodesToVisit.isEmpty()) {
            var currentNode = nodesToVisit.pop();
            nodesToVisit.addAll(currentNode.getChildren());
            nodeSet.add(currentNode);
        }

        return new ArrayList<>(nodeSet);
    }
}
