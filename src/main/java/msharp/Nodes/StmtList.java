package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class StmtList extends ArrayList<StmtNode> implements StmtNode {
    @Override
    public boolean add (StmtNode stmt)
    {
        if (stmt != null)
            return super.add(stmt);
        return false;
    }

    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        for (StmtNode node : this) {
            sb.append(node.toString());
            sb.append(" ");
        }

        sb.append(")");
        return sb.toString();
    }

    @Override
    public Graph toGraph ()
    {
        Node stmts = node("stmts" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>stmts</b>"));

        Graph g = graph("stmts").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));

        List<MutableNode> partGraphs = new ArrayList<>();
        for (StmtNode stmt : this) {
            partGraphs.add(stmt.toGraph().toMutable().rootNodes().iterator().next());
        }


        g = g.with(stmts.link(partGraphs));

        return g;
    }
    
    @Override
    public List<FinalNote> accept(AstVisitorInterface visitor, NodeContext ctx)
    {
        return visitor.visit(this, ctx);
    }
}
