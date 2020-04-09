package msharp.Nodes;

public class everyNode extends stmtNode {
    //something to store condition
    private int amount;
    private stmtNode trueCase;
    private stmtNode elseCase;

    public everyNode(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public stmtNode getTrueCase() {
        return trueCase;
    }

    public void setTrueCase(stmtNode trueCase) {
        this.trueCase = trueCase;
    }

    public stmtNode getElseCase() {
        return elseCase;
    }

    public void setElseCase(stmtNode elseCase) {
        this.elseCase = elseCase;
    }

    @Override
    public String toString() {

    return null;
    }
}
