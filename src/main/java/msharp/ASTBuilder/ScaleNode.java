package msharp.ASTBuilder;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;
import msharp.NotePopulation.ToneEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;

public class ScaleNode implements StmtNode{
    private final List<ToneEnum> inScale;
    private final boolean up;

    public ScaleNode (List<ToneEnum> inScale, boolean up)
    {
        this.inScale = inScale;
        this.up = up;
    }
    // copy constructor
    public ScaleNode(ScaleNode toBeCopied){
        this.inScale = new ArrayList<>(toBeCopied.getInScale());
        this.up = toBeCopied.up;
    }
    
    
    public List<ToneEnum> getInScale ()
    {
        return inScale;
    }
    
    public boolean isUp ()
    {
        return up;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }

    @Override
    public Graph toGraph ()
    {
        guru.nidi.graphviz.model.Node scaleNode =
                node("scaleNode" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>SCALE</b><br/>" + inScale.toString() + (up ? "^" : "_")));
        
    
        Graph g = graph("scaleNode").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(scaleNode);
    
        return g;
    }
    
    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaleNode scaleNode = (ScaleNode) o;
        return up == scaleNode.up &&
                Objects.equals(inScale, scaleNode.inScale);
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(inScale, up);
    }
}
