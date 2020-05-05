package msharp.Nodes;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.NotePopulation.BuildNoteListVisitor;
import msharp.NotePopulation.FinalNote;
import msharp.NotePopulation.NodeContext;
import msharp.NumberExpressionVisitor;
import msharp.SymbolTable;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class NumberNode implements OperandInterface {
    private final int n;

    public NumberNode (int n)
    {
        this.n = n;
    }

    public int getN ()
    {
        return n;
    }

    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return null; //todo same as in exprNode, fix interfaces pls
    }

    @Override
    public String toString ()
    {
        return String.valueOf(n);
    }

    @Override
    public Graph toGraph ()
    {
        Node number = node("number" + UUID.randomUUID().toString()).with(Color.YELLOW).with(Label.html("<b>NUMBER</b><br/>" + this.toString()));
    
        Graph g = graph("note").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        return g.with(number);
    }
    
    @Override
    public int accept (NumberExpressionVisitor visitor, SymbolTable symbolTable)
    {
        return visitor.visit(this, symbolTable);
    }
}
