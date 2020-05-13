package msharp.ASTBuilder;

import msharp.ASTBuilder.ExprNode;
import msharp.ASTBuilder.IdNode;
import msharp.ASTBuilder.NumberNode;
import msharp.Compiler.IllegalCompilerAction;
import msharp.Compiler.Symbol;
import msharp.Compiler.SymbolTable;

public class ArithmeticExpressionVisitor {
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
        throw new IllegalCompilerAction("Did not know how to compute operation: "+exprNode.getOperator());
    }
    public int visit(NumberNode numberNode, SymbolTable symbolTable){
        return numberNode.getN();
    }
    public int visit(IdNode idNode, SymbolTable symbolTable) throws IllegalCompilerAction
    {
        // type check and check scope
        Symbol symbol = symbolTable.retrieveSymbol(idNode.getId());
        
        //scope checking
        if(symbol == null){
            // make some error noise
            throw new IllegalCompilerAction("Name \""+idNode.getId()+"\", was not declared in current scope.");
        }
        
        //type checking
        if(symbol.value.getClass() != Integer.class){
            // make some error noise
            throw new IllegalCompilerAction("Name \""+idNode.getId()+"\", was declared in current scope, but is not integer.");
        }
        
        return (int) symbol.value;
    }
}
