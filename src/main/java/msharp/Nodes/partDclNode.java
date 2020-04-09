package msharp.Nodes;


public class partDclNode implements node {
    public partDclNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String id;
    public stmtList stmts = new stmtList();

    @Override
    public String toString() {
        return id + ": "+stmts.toString();
    }
}