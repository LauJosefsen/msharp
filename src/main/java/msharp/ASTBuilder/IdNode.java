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
import msharp.Compiler.SymbolTable;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class IdNode implements StmtNode, ArithmeticExpressionNodeInterface {
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
        
        Graph g = graph("id").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(id);
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx) throws IllegalCompilerAction
    {
        return visitor.visit(this, ctx);
    }
    
    @Override
    public int accept (ArithmeticExpressionVisitor visitor, SymbolTable symbolTable) throws IllegalCompilerAction
    {
        return visitor.visit(this, symbolTable);
    }
}
