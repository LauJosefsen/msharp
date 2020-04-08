package msharp.Nodes;

public class andNode extends stmtNode{
    private stmtNode left;
    private stmtNode right;

    @Override
    public String toString() {
        return left.toString() + " & " + right.toString();
    }
}
