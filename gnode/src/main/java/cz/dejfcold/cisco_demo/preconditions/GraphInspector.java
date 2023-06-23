package cz.dejfcold.cisco_demo.preconditions;

import java.util.List;

public interface GraphInspector {
    /**
     * Returns a List of Lists, representing all possible paths through the graph starting at 'node'.
     * The List returned can be thought of as a List of
     * paths, where each path is represented as a List of GNodes.
     *
     * @param node
     * @return
     */
    List<List<GNode>> paths(GNode node);
}
