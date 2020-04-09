package msharp.Nodes;

import java.util.List;

public class parenNode implements stmtNode {
    public stmtList stmts = new stmtList();

    public void add(stmtNode stmt){
        if(stmt != null)
            stmts.add(stmt);
    }

    @Override
    public String toString() {
        return "(" + stmts.toString() + ")";
    }
}