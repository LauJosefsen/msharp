package msharp.ASTBuilder;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import msharp.Compiler.IllegalCompilerAction;
import msharp.Compiler.SymbolTable;

import java.util.UUID;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class ExprNode implements ArithmeticExpressionNodeInterface {
    private final ArithmeticExpressionNodeInterface left;
    private final ArithmeticExpressionNodeInterface right;
    private final ExprOpEnum operator;

    public ExprNode (ArithmeticExpressionNodeInterface left, ArithmeticExpressionNodeInterface right, ExprOpEnum operator)
    {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    
    public ArithmeticExpressionNodeInterface getLeft ()
    {
        return left;
    }
    
    public ArithmeticExpressionNodeInterface getRight ()
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
    public int accept (ArithmeticExpressionVisitor visitor, SymbolTable symbolTable)
    {
        return visitor.visit(this, symbolTable);
    }

}
