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
import static guru.nidi.graphviz.model.Link.to;

public class andNode implements stmtNode{
    private stmtNode left;
    private stmtNode right;

    public andNode(stmtNode left, stmtNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " & " + right.toString() + ")";
    }

    @Override
    public Graph toGraph() {
        Node and = node("and"+ UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>AND</b>"));

        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(and.link(to(left.toGraph().toMutable().rootNodes().iterator().next()), to(right.toGraph().toMutable().rootNodes().iterator().next())));

        return g;
    }
}
