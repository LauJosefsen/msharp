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
import static guru.nidi.graphviz.model.Link.to;

public class NumDeclNode implements StmtNode {
    private final String id;
    private final OperandInterface value;

    public NumDeclNode (String id, OperandInterface value)
    {
        this.id = id;
        this.value = value;
    }

    @Override
    public List<FinalNote> accept (AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
    
    @Override
    public Graph toGraph ()
    {
        Node numDcl = node("numDcl" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>NUMBER DECL</b><br/>" + id));
        
        Graph g = graph("numDcl").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(numDcl.link(to(value.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("VALUE"))));
        
        return g;
    }
}
