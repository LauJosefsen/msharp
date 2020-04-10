package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class transposeNode implements stmtNode{
    private int deltaTonation;
    private stmtNode toBeTransposed;


    public transposeNode(int deltaTonation, stmtNode toBeTransposed) {
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
}
