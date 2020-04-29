package msharp;

import msharp.Nodes.ProgNode;

import java.util.*;

public class SymbolTable {
    class Symbol<T>{
        String id;
        T value;

        Symbol(String id, T value){
            this.id = id;
            this.value = value;
        }


    }
    class Scope{
        Map<String,Symbol> localSymbols = new HashMap<>();
    }
    
    Stack<Scope> scopes = new Stack<>();
    
    void openScope(){
        scopes.push(new Scope());
    }
    
    void closeScope(){
        scopes.pop();
    }
    
    <T> void  enterSymbol(String key, T value){
        if(retrieveSymbol(key) == null)
            scopes.peek().localSymbols.put(key,new Symbol(key,value));
        else{
            //todo change existing symbol
        }
    }
    
    Symbol retrieveSymbol(String key){
        // take from top to stack to bottom of stack, search every scope if they have this variable. todo
        return null;
    }
    
    
    public SymbolTable (ProgNode ast)
    {
        //do some visitor thing here todo
    }
}
