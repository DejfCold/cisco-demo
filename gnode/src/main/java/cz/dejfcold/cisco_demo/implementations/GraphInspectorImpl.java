package cz.dejfcold.cisco_demo.implementations;

import cz.dejfcold.cisco_demo.preconditions.GNode;
import cz.dejfcold.cisco_demo.preconditions.GraphInspector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class GraphInspectorImpl implements GraphInspector {
    @Override
    public List<List<GNode>> paths(GNode node) {
        Objects.requireNonNull(node);

        var paths = new ArrayList<List<GNode>>();

        var nodesToVisit = new Stack<NodeWithPath>();
        nodesToVisit.push(new NodeWithPath(node, List.of()));

        while (!nodesToVisit.isEmpty()) {
            var currentNodeWithPath = nodesToVisit.pop();

            var currentNode = currentNodeWithPath.getNode();
            var currentPath = currentNodeWithPath.getPath();

            var absolutePath = new ArrayList<>(currentPath);
            absolutePath.add(currentNode);

            if(currentNode.getChildren().isEmpty()) {
                paths.add(absolutePath);
            }

            for (var child : currentNode.getChildren()) {
                nodesToVisit.push(new NodeWithPath(child, absolutePath));
            }
        }

        return paths;
    }

    private static class NodeWithPath {
        private final GNode node;
        private final List<GNode> path;

        public NodeWithPath(GNode node, List<GNode> path) {
            this.node = node;
            this.path = path;
        }

        public GNode getNode() {
            return this.node;
        }

        public List<GNode> getPath() {
            return this.path;
        }
    }
}
