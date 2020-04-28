package msharp.Nodes;

import guru.nidi.graphviz.model.Graph;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public class NumberNode implements StmtNode, OperandInterface {
    private final int n;

    public NumberNode (int n)
    {
        this.n = n;
    }

    public int getN ()
    {
        return n;
    }

    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }

    @Override
    public String toString ()
    {
        return null;
    }

    @Override
    public Graph toGraph ()
    {
        return null;
    }
}
