package msharp.Nodes;

import guru.nidi.graphviz.model.Graph;

public interface Node {
    String toString ();
    
    Graph toGraph ();
}

