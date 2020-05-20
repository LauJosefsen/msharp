package msharp.ASTBuilder;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.Compiler.IllegalCompilerAction;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class TransposeNode implements StmtNode {
    private final ArithmeticExpressionNodeInterface deltaTonation;
    private final StmtNode toBeTransposed;
    
    public ArithmeticExpressionNodeInterface getDeltaTonation ()
    {
        return deltaTonation;
    }
    
    public StmtNode getToBeTransposed ()
    {
        return toBeTransposed;
    }
    
    
    
    public TransposeNode (ArithmeticExpressionNodeInterface deltaTonation, StmtNode toBeTransposed)
    {
        this.deltaTonation = deltaTonation;
        this.toBeTransposed = toBeTransposed;
    }
    
    @Override
    public String toString ()
    {
        return this.toBeTransposed + "^" + this.deltaTonation;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node transpose = node("transpose" + UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>TRANSPOSE</b><br/>" + this.deltaTonation));
        Graph g = graph("transpose").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(transpose.link(toBeTransposed.toGraph().toMutable().rootNodes().iterator().next()));
    
        g = g.with(transpose.link(
                to(deltaTonation.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("AMOUNT")),
                to(toBeTransposed.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("BODY"))));
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }

    public TransposeNode setLeftOperand (StmtNode left)
    {
        return new TransposeNode(this.deltaTonation,left);
    }
}
