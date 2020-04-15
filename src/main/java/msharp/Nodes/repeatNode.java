package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NotePopulation;
import msharp.NotePopulation.nodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class repeatNode implements stmtNode{
    private int amount;

    public stmtNode getStmts() {
        return stmts;
    }

    public void setStmts(stmtNode stmts) {
        this.stmts = stmts;
    }

    public repeatNode(int amount) {
        this.amount = amount;
    }

    private stmtNode stmts;

    public repeatNode(int amount, stmtNode stmts) {
        this.amount = amount;
        this.stmts = stmts;
    }

    @Override
    public String toString() {



        return "REPEAT (" + amount + ") {" + stmts.toString() + "}";
    }

    @Override
    public Graph toGraph() {
        Node repeat = node("repeat"+ UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>REPEAT</b><br/>"+amount + "times"));

        Graph g = graph("repeat").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(repeat.link(stmts.toGraph().toMutable().rootNodes().iterator().next()));

        return g;
    }

    @Override
    public List<FinalNote> accept(NotePopulation visitor, nodeContext ctx ) {
        return visitor.visit(this, ctx);
    }

    public int getAmount() {
        return amount;
    }
}
