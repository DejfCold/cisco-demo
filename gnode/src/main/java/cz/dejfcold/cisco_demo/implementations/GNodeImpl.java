package cz.dejfcold.cisco_demo.implementations;

import cz.dejfcold.cisco_demo.preconditions.GNode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GNodeImpl implements GNode {
    private final String name;
    private final List<GNode> children;

    public GNodeImpl(String name, GNode... children) {
        this.name = name;
        this.children = Arrays.asList(children);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<GNode> getChildren() {
        return this.children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GNodeImpl gNode = (GNodeImpl) o;
        return Objects.equals(name, gNode.name) && Objects.equals(children, gNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }
}
