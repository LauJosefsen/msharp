package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class playNode implements node {
    public stmtList stmts = new stmtList();

    @Override
    public String toString() {
        return("main = " + stmts.toString() + "\n");
    }

    @Override
    public Graph toGraph() {
        Node play = node("play").with(Color.RED).with(Label.html("<b>play</b>"));

        Graph g = graph("play").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));

        List<MutableNode> partGraphs = new ArrayList<>();
        for(stmtNode stmt : stmts){
            partGraphs.add(stmt.toGraph().toMutable().rootNodes().iterator().next());
        }

        g = g.with(play.link(partGraphs));

        return g;
    }
}
