package msharp.Compiler;

public class Symbol<T>{
    public T value;
    
    Symbol(String id, T value){
        this.value = value;
    }
}
