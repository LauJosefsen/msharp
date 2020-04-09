package msharp.Nodes;

public class andNode implements stmtNode{
    private stmtNode left;
    private stmtNode right;

    public andNode(stmtNode left, stmtNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " & " + right.toString() + ")";
    }
}
