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
    public EveryNode (OperandInterface amount, StmtNode trueCase, StmtNode elseCase)
    {
        this.amount = amount;
        this.trueCase = trueCase;
        this.elseCase = elseCase;
    }

    //something to store condition
    private final OperandInterface amount;
    private final StmtNode trueCase;
    private final StmtNode elseCase;

    public OperandInterface getAmount ()
    {
        return amount;
    }

    public StmtNode getTrueCase ()
    {
        return trueCase;
    }

    public StmtNode getElseCase ()
    {
        return elseCase;
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
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
