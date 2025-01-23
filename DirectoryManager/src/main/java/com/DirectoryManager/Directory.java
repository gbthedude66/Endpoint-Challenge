package com.DirectoryManager;

import java.util.*;

public class Directory extends DirNode {
    private Map<String, DirNode> children;

    public Directory(String name) {
        super(name);
        this.children = new HashMap<>();
    }

    public void addChild(DirNode node) {
        children.put(node.getName(), node);
        node.setParent(this);
    }

    public DirNode removeChild(String name) {
        return children.remove(name);
    }

    public DirNode getChild(String name) {
        return children.get(name);
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    @Override
    void print(StringBuilder sb, String indent) {
        if (!indent.isEmpty()) {
            sb.append(indent).append(name).append("\n");
        } else {
            sb.append(name).append("\n");
        }

        List<DirNode> sortedChildren = new ArrayList<>(children.values());
        sortedChildren.sort((a, b) -> a.getName().compareTo(b.getName()));

        for (DirNode child : sortedChildren) {
            child.print(sb, indent.isEmpty() ? " " : indent + " ");
        }
    }

}
