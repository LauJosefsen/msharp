package msharp.Nodes;

public class transposeNode implements stmtNode{
    private int deltaTonation;
    private stmtNode toBeTransposed;


    public transposeNode(int deltaTonation, stmtNode toBeTransposed) {
        this.deltaTonation = deltaTonation;
        this.toBeTransposed = toBeTransposed;
    }

    @Override
    public String toString() {
        return this.toBeTransposed + "^" + this.deltaTonation;
    }
}
