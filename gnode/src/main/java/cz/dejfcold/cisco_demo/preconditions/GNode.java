package cz.dejfcold.cisco_demo.preconditions;

import java.util.List;

public interface GNode {
    String getName();

    List<GNode> getChildren();
}
