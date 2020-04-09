package msharp.Nodes;

import java.util.ArrayList;

public class stmtList extends ArrayList<stmtNode> implements stmtNode {
    @Override
    public boolean add(stmtNode stmt) {
        if(stmt != null)
            return super.add(stmt);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        for(stmtNode node : this){
            sb.append(node.toString());
            sb.append(" ");
        }

        sb.append(")");
        return sb.toString();
    }
}
