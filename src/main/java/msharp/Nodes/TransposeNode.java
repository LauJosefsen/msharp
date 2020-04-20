package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class TransposeNode implements StmtNode {
    private int deltaTonation;

    public int getDeltaTonation() {
        return deltaTonation;
    }

    public StmtNode getToBeTransposed() {
        return toBeTransposed;
    }

    private StmtNode toBeTransposed;


    public TransposeNode(int deltaTonation, StmtNode toBeTransposed) {
        this.deltaTonation = deltaTonation;
        this.toBeTransposed = toBeTransposed;
    }

    @Override
    public String toString() {
        return this.toBeTransposed + "^" + this.deltaTonation;
    }

    @Override
    public Graph toGraph() {
        Node transpose = node("transpose"+UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>TRANSPOSE</b><br/>"+this.deltaTonation));
        Graph g = graph("transpose").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(transpose.link(toBeTransposed.toGraph().toMutable().rootNodes().iterator().next()));

        return g;
    }
    @Override
    public List<FinalNote> accept(BuildNoteListVisitor visitor, NodeContext ctx ) {
        return visitor.visit(this, ctx);
    }
}
