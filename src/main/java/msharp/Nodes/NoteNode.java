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

public class NoteNode implements StmtNode {
    public char getLetter ()
    {
        return letter;
    }
    
    public int getOctave ()
    {
        return octave;
    }
    
    private char letter;
    public int octave;
    
    public NoteNode (char letter, int octave)
    {
        this.letter = letter;
        this.octave = octave;
    }
    
    @Override
    public String toString ()
    {
        String o;
        
        if (octave == -1)
            o = "";
        else
            o = String.valueOf(octave);
        return letter + o;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node note = node("note" + UUID.randomUUID().toString()).with(Color.YELLOW).with(Label.html("<b>NOTE</b><br/>" + this.toString()));
        
        Graph g = graph("note").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(note);
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
