package msharp.Compiler;

import msharp.ASTBuilder.*;

import java.util.*;

public class SymbolTable {
    public SymbolTable getAsOnlyGlobalScope(){
        Stack<Scope> scopeStack = new Stack<>();
        scopeStack.push(this.scopes.get(0));
        return new SymbolTable(scopeStack);
    }
    
    public SymbolTable (Stack<Scope> scopes)
    {
        this.scopes = scopes;
    }
    
    static class Scope{
        Map<String, Symbol> localSymbols = new HashMap<>();
    }
    
    Stack<Scope> scopes = new Stack<>();
    
    public void openScope(){
        scopes.push(new Scope());
    }
    
    public void closeScope(){
        scopes.pop();
    }
    
    public void  enterSymbol(String key, Object value)
    {
        Symbol symbol = retrieveSymbol(key);
        if(symbol == null){
            // not in symboltable, add to symboltable
            scopes.peek().localSymbols.put(key,new Symbol(key,value));
        }
        else symbol.value = value;
    }
    
    public Symbol retrieveSymbol(String key)
    {
        // take from top to stack to bottom of stack, search every scope if they have this variable.
        Symbol found = null;
        
        // the loop direction (bottom-top or top-bottom) on the stack doesnt matter,
        // when we dont allow multiple variables in different scopes with the same name.
        for (Iterator<Scope> it = scopes.iterator(); it.hasNext() && found == null; ) {
            Scope scope = it.next();
            
            if(scope.localSymbols.containsKey(key))
                found = scope.localSymbols.get(key);
        }
        return found;
    }
    
    
    public SymbolTable (ProgNode ast)
    {
        this.openScope();
    
        ArithmeticExpressionVisitor visitor = new ArithmeticExpressionVisitor();
        for(NumDeclNode numNode : ast.getGlobalVariables()){
            this.enterSymbol(numNode.getId(), numNode.getValue().accept(visitor,this));
        }
        for(PartDclNode partNode : ast.getParts()){
            this.enterSymbol(partNode.getId(), partNode.getStmts());
        }
        
    }
}
