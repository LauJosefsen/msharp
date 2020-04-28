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

public class IdNode implements StmtNode,OperandInterface{
    private final String id;
    
    public String getId ()
    {
        return id;
    }
    
    public IdNode (String id)
    {
        this.id = id;
    }
    
    @Override
    public String toString ()
    {
        return id;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node id = node("id" + UUID.randomUUID().toString()).with(Color.GREEN).with(Label.html("<b>ID</b><br/>" + this.id));
        
        Graph g = graph("and").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(id);
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
