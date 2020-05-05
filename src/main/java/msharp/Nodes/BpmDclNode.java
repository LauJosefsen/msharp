package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.IllegalCompilerAction;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class BpmDclNode implements StmtNode {
    private final TempoChangeNode tempo;
    private final OperandInterface bpm;
    
    public TempoChangeNode getTempo ()
    {
        return tempo;
    }
    
    public OperandInterface getBpm () { return bpm; }
    
    public BpmDclNode (OperandInterface bpm, TempoChangeNode tempo)
    {
        this.tempo = tempo;
        this.bpm = bpm;
    }
    
    @Override
    public String toString ()
    {
        return "BPM(" + bpm + "," + tempo.toString() + ")";
    }
    
    @Override
    public Graph toGraph ()
    {
        Node bpmDcl = node("bpmDcl" + UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>BPM</b>"));
        
        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(bpmDcl.link(
                to(bpm.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("BPM")),
                to(tempo.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("TEMPO"))));
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx) throws IllegalCompilerAction
    {
        return visitor.visit(this, ctx);
    }
}
