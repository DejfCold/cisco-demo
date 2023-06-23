package cz.dejfcold.cisco_demo.preconditions;

import java.util.List;

public interface GraphWalker {

    /**
     * returns a List containing every GNode in the graph. Each node appears in the List exactly once (i.e. no duplicates).
     *
     * @param node
     * @return
     */
    List<GNode> walkGraph(GNode node);
}
