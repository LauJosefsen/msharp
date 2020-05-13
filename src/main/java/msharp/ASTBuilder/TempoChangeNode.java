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

public class TempoChangeNode implements StmtNode {
    private final ArithmeticExpressionNodeInterface numerator;
    private final ArithmeticExpressionNodeInterface denominator;

    public TempoChangeNode (ArithmeticExpressionNodeInterface numerator, ArithmeticExpressionNodeInterface denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public ArithmeticExpressionNodeInterface getNumerator ()
    {
        return numerator;
    }
    
    public ArithmeticExpressionNodeInterface getDenominator ()
    {
        return denominator;
    }
    
    @Override
    public String toString ()
    {
        return numerator + "%" + denominator;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node tempoChange = node("tempoChange" + UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>TEMPO</b><br/>"));
        
        Graph g = graph("tempoChange").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
    
        g = g.with(tempoChange.link(
                to(numerator.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("NUMERATOR")),
                to(denominator.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("DENOMINATOR"))));
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
