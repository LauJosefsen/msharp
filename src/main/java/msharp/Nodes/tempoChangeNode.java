package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;

import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;

public class tempoChangeNode implements stmtNode{
    private int numerator;
    private int denomniator;

    public tempoChangeNode(int numerator, int denomniator) {
        this.numerator = numerator;
        this.denomniator = denomniator;
    }

    @Override
    public String toString() {
        return numerator + "%" + denomniator;
    }

    @Override
    public Graph toGraph() {
        Node tempoChange = node("tempoChange"+ UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>TEMPO</b><br/>"+this.toString()));

        Graph g = graph("tempoChange").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(tempoChange);

        return g;
    }
}
