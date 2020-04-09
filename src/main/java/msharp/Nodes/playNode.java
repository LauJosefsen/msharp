package msharp.Nodes;

import java.util.ArrayList;
import java.util.List;

public class playNode implements node {
    public stmtList stmts = new stmtList();

    @Override
    public String toString() {
        return("main = " + stmts.toString() + "\n");
    }
}
