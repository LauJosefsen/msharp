package msharp.Nodes;

import java.util.ArrayList;

public class stmtList extends ArrayList<stmtNode> {
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
