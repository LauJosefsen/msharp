package msharp.Nodes;

import guru.nidi.graphviz.model.Graph;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;
import msharp.NotePopulation.ToneEnum;

import java.util.List;

public class ScaleNode implements StmtNode{
    private final List<ToneEnum> inScale;
    private final boolean up;

    public ScaleNode (List<ToneEnum> inScale, boolean up)
    {
        this.inScale = inScale;
        this.up = up;
    }

    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }

    @Override
    public Graph toGraph ()
    {
        return null;
    }
}
