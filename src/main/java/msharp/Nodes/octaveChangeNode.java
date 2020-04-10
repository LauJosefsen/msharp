package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class octaveChangeNode implements stmtNode{
    private int deltaOctave;

    public octaveChangeNode(int deltaOctave) {
        this.deltaOctave = deltaOctave;
    }

    @Override
    public String toString() {
        String str;

        if (deltaOctave <= 0)
            str = "/" + deltaOctave;
        else
            str = "\\" + deltaOctave;

        return str;
    }

    @Override
    public Graph toGraph() {
        Node octaveChange = node("octaveChange"+ UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>OctChange</b><br>"+this.deltaOctave));

        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(octaveChange);

        return g;
    }
}