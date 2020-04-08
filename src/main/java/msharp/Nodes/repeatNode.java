package msharp.Nodes;

public class repeatNode extends stmtNode{
    int amount;
    stmtNode stmts;

    public repeatNode(int amount, stmtNode stmts) {
        this.amount = amount;
        this.stmts = stmts;
    }

    @Override
    public String toString() {
        return null;
    }
}
