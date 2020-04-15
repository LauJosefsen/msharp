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

public class instruNode implements stmtNode{
    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    private String instrument;

    public instruNode(String instrument) {
        this.instrument = instrument;
    }

    @Override
    public String toString() {
        return instrument + ":";
    }

    @Override
    public Graph toGraph() {
        Node instru = node("instru"+ UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>INSTRUMENT</b><br/>"+this.instrument));

        Graph g = graph("instru").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(instru);

        return g;
    }

    @Override
    public List<FinalNote> accept(NotePopulation visitor, nodeContext ctx ) {
        return visitor.visit(this, ctx);
    }
}
