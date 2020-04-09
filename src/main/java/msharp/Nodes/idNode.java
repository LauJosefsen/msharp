package msharp.Nodes;

public class idNode implements stmtNode{
    private String id;

    public idNode(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
