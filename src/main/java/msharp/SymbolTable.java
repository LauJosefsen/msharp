package msharp;

import msharp.Nodes.ProgNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int scopeId;
        Scope parent; //null if top/global
        Map<String,Symbol> localSymbols = new HashMap<>();
        
        public Scope (int scopeId, Scope parent)
        {
            this.scopeId = scopeId;
            this.parent = parent;
        }
        
        Symbol findSymbol(String key){
            if(localSymbols.containsKey(key))
                return localSymbols.get(key);
            if(parent == null)
                return null;
            return parent.findSymbol(key);
        }
    }
    
    
    private int scopeCounter = 0;
    Scope currentScope = null;
    
    List<Scope> scopes = new ArrayList<>();
    
    void openScope(){
        Scope newScope = new Scope(scopeCounter++,currentScope);
        scopes.add(newScope);
        currentScope = newScope;
    }
    
    void closeScope(){
        currentScope = currentScope.parent;
    }
    
    <T> void  enterSymbol(String key, T value){
        if(currentScope.findSymbol(key) == null){
            Symbol<T> toAdd = new Symbol<>(key,value);
            currentScope.localSymbols.put(key,toAdd);
        }
    }
    
    Symbol retrieveSymbol(String key){
        return currentScope.findSymbol(key);
    }
    
    
    public SymbolTable (ProgNode ast)
    {
        //do some visitor thing here
    }
}
