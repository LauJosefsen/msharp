package msharp.Nodes;

public class repeatNode implements stmtNode{
    int amount;

    public stmtNode getStmts() {
        return stmts;
    }

    public void setStmts(stmtNode stmts) {
        this.stmts = stmts;
    }

    public repeatNode(int amount) {
        this.amount = amount;
    }

    stmtNode stmts;

    public repeatNode(int amount, stmtNode stmts) {
        this.amount = amount;
        this.stmts = stmts;
    }

    @Override
    public String toString() {



        return "REPEAT (" + amount + ") {" + stmts.toString() + "}";
    }
}
