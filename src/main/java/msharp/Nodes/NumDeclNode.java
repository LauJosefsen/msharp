package msharp.Nodes;

import guru.nidi.graphviz.model.Graph;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public class NumDeclNode implements StmtNode {
    private final String id;
    private final OperandInterface value;

    public NumDeclNode (String id, OperandInterface value)
    {
        this.id = id;
        this.value = value;
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
