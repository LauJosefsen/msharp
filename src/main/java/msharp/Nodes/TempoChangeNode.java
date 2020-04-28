package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.Fraction;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class TempoChangeNode implements StmtNode {
    private final OperandInterface numerator;
    private final OperandInterface denominator;

    public TempoChangeNode (OperandInterface numerator, OperandInterface denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    @Override
    public String toString ()
    {
        return numerator + "%" + denominator;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node tempoChange = node("tempoChange" + UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>TEMPO</b><br/>" + this.toString()));
        
        Graph g = graph("tempoChange").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(tempoChange);
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
