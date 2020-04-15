package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.GraphAttr;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.*;

import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class progNode implements node{
    public List<partDclNode> parts = new ArrayList<>();
    public playNode main;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(partDclNode part : parts){
            sb.append(part.toString());
            sb.append("\n");
        }
        sb.append(main.toString());
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public Graph toGraph() {

        Node program = node("program");
        Graph g = graph("program").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .graphAttr().with(GraphAttr.splines(GraphAttr.SplineMode.ORTHO))
                .graphAttr().with("concentrate",false)
                .graphAttr().with("compound",false);

        List<MutableNode> partGraphs = new ArrayList<>();
        for(partDclNode partDcl : parts){
            partGraphs.add(partDcl.toGraph().toMutable().rootNodes().iterator().next());
        }
        partGraphs.add(main.toGraph().toMutable().rootNodes().iterator().next());

        g = g.with(program.link(partGraphs));

        return g;
    }
}