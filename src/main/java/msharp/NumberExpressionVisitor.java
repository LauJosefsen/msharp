package msharp;

import msharp.Nodes.ExprNode;
import msharp.Nodes.IdNode;
import msharp.Nodes.NumberNode;

public class NumberExpressionVisitor {
    public int visit(ExprNode exprNode, SymbolTable symbolTable) throws IllegalCompilerAction
    {
        int left = exprNode.getLeft().accept(this,symbolTable);
        int right = exprNode.getRight().accept(this,symbolTable);
        switch (exprNode.getOperator()){
            case ADD: return left + right;
            case DIVIDE: return left / right;
            case MODULO: return left % right;
            case MULTIPLY: return left * right;
            case SUBTRACT: return left - right;
        }
        throw new IllegalArgumentException("Did not know how to compute operation: "+exprNode.getOperator());
    }
    public int visit(NumberNode numberNode, SymbolTable symbolTable){
        return numberNode.getN();
    }
    public int visit(IdNode idNode, SymbolTable symbolTable) throws IllegalCompilerAction
    {
        // type check and check scope
        SymbolTable.Symbol symbol = symbolTable.retrieveSymbol(idNode.getId());
        
        //scope checking
        if(symbol == null){
            // make some error noise
            throw new IllegalArgumentException("Name \""+idNode.getId()+"\", was not declared in current scope.");
        }
        
        //type checking
        if(symbol.value.getClass() != Integer.class){
            // make some error noise
            throw new IllegalArgumentException("Name \""+idNode.getId()+"\", was declared in current scope, but is not integer.");
        }
        
        return (int) symbol.value;
    }
}
