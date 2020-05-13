package msharp.ASTBuilder;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.Compiler.IllegalCompilerAction;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class NoteNode implements StmtNode {
    private final char letter;
    private final ArithmeticExpressionNodeInterface octave;
    
    public char getLetter ()
    {
        return letter;
    }
    
    public ArithmeticExpressionNodeInterface getOctave ()
    {
        return octave;
    }
    
    public NoteNode (char letter, ArithmeticExpressionNodeInterface octave)
    {
        this.letter = letter;
        this.octave = octave;
    }
    
    @Override
    public String toString ()
    {
        return ""+letter;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node note = node("note" + UUID.randomUUID().toString()).with(Color.YELLOW).with(Label.html("<b>NOTE</b><br/>" + this.toString()));
        
        Graph g = graph("note").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        if(octave == null){
            return g.with(note);
        }
        return g.with(note.link(to(octave.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("OCTAVE"))));
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
