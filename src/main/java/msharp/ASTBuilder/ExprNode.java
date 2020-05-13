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
import msharp.Compiler.NumberExpressionVisitor;
import msharp.Compiler.SymbolTable;

import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class ExprNode implements OperandInterface {
    private final OperandInterface left;
    private final OperandInterface right;
    private final ExprOpEnum operator;

    public ExprNode (OperandInterface left, OperandInterface right, ExprOpEnum operator)
    {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    
    public OperandInterface getLeft ()
    {
        return left;
    }
    
    public OperandInterface getRight ()
    {
        return right;
    }
    
    public ExprOpEnum getOperator ()
    {
        return operator;
    }
    
    @Override
    public Graph toGraph ()
    {
        Node expr = node("expr" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>EXPRESSION</b><br/>" + operator));
        
        Graph g = graph("expr").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
        g = g.with(expr.link(
                to(left.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("LEFT")),
                to(right.toGraph().toMutable().rootNodes().iterator().next()).with(Label.of("RIGHT"))));
        
        return g;
    }
    
    @Override
    public List<FinalNote> accept (BuildNoteListVisitor visitor, NodeContext ctx)
    {
        return null; // this doesnt get visited by this visitor. //todo fix interfaces
    }
    @Override
    public int accept (NumberExpressionVisitor visitor, SymbolTable symbolTable) throws IllegalCompilerAction
    {
        return visitor.visit(this, symbolTable);
    }

}
