package msharp.Nodes;

import guru.nidi.graphviz.model.Graph;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;

public class ExprNode implements StmtNode, OperandInterface {
    private final OperandInterface left;
    private final OperandInterface right;
    private final ExprOpEnum operator;

    public ExprNode (OperandInterface left, OperandInterface right, ExprOpEnum operator)
    {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Graph toGraph ()
    {
        return null;
    }

    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }

}
