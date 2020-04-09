package msharp.Nodes;


public class partDclNode extends node {
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
        return null;
    }
}