package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

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
        Node bpmDcl = node("bpmDcl" + UUID.randomUUID().toString()).with(Color.BLUE).with(Label.html("<b>BPM</b><br/>" + bpm));
        
        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(bpmDcl.link(tempo.toGraph()));
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
