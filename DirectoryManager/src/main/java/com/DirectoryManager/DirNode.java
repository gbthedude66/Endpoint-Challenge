package com.DirectoryManager;

abstract class DirNode {
    public DirNode parent;
    public String name;

    public DirNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(DirNode parent) {
        this.parent = parent;
    }

    public DirNode getParent() {
        return parent;
    }

    public String getPath() {
        if (parent == null) {
            return name;
        }
        return parent.getPath() + "/" + name;
    }

    abstract void print(StringBuilder sb, String indent);
}
