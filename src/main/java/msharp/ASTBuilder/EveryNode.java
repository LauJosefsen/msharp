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

public class EveryNode implements StmtNode {
    public EveryNode (ArithmeticExpressionNodeInterface amount, StmtNode trueCase, StmtNode elseCase)
    {
        this.amount = amount;
        this.trueCase = trueCase;
        this.elseCase = elseCase;
    }

    //something to store condition
    private final ArithmeticExpressionNodeInterface amount;
    private final StmtNode trueCase;
    private final StmtNode elseCase;

    public ArithmeticExpressionNodeInterface getAmount ()
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
                to(amount.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("AMOUNT")),
                to(amount.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("AMOUNT")),
                to(trueCase.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("TRUE")),
                to(elseCase.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("ELSE"))));

            return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx) throws IllegalCompilerAction
    {
        return visitor.visit(this, ctx);
    }
}
