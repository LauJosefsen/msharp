package msharp.ASTBuilder;

import guru.nidi.graphviz.model.Graph;

public interface Node {
    String toString ();
    
    Graph toGraph ();
}

