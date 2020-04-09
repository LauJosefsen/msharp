package msharp.Nodes;

import java.util.List;

public class parenNode extends stmtNode {
    public stmtList stmts = new stmtList();

    public void add(stmtNode stmt){
        stmts.add(stmt);
    }

    @Override
    public String toString() {
        return null;
    }
}