package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class EveryNode implements StmtNode {
    //something to store condition
    private int amount;
    private StmtNode trueCase;
    private StmtNode elseCase;
    
    public EveryNode (int amount)
    {
        this.amount = amount;
    }
    
    public int getAmount ()
    {
        return amount;
    }
    
    public void setAmount (int amount)
    {
        this.amount = amount;
    }
    
    public StmtNode getTrueCase ()
    {
        return trueCase;
    }
    
    public void setTrueCase (StmtNode trueCase)
    {
        this.trueCase = trueCase;
    }
    
    public StmtNode getElseCase ()
    {
        return elseCase;
    }
    
    public void setElseCase (StmtNode elseCase)
    {
        this.elseCase = elseCase;
    }
    
    @Override
    public String toString ()
    {
        
        
        return "EVERY (" + amount + ") {" + trueCase.toString() + "} ELSE {" + elseCase.toString() + "}";
        
        
    }
    
    @Override
    public Graph toGraph ()
    {
        Node every = node("every" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>EVERY</b><br/>" + amount));
        
        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(every.link(
                to(trueCase.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("TRUE")),
                to(elseCase.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("ELSE"))));
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
