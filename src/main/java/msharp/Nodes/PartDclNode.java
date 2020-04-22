package msharp.Nodes;


import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class PartDclNode implements Node {
    private final String id;
    private final StmtList stmts;
    
    public PartDclNode (String id, StmtList stmts)
    {
        this.id = id;
        this.stmts = stmts;
    }
    
    public StmtList getStmts () {return stmts;}
    
    public String getId ()
    {
        return id;
    }
    
    
    @Override
    public String toString ()
    {
        return id + ": " + stmts.toString();
    }
    
    @Override
    public Graph toGraph ()
    {
        
        guru.nidi.graphviz.model.Node partDcl = node("partDcl" + UUID.randomUUID().toString()).with(Color.RED).with(Label.html("<b>partDcl</b><br/>" + this.id));
        
        Graph g = graph("partDcl");
        
        List<MutableNode> partGraphs = new ArrayList<>();
        for (StmtNode stmt : stmts) {
            partGraphs.add(stmt.toGraph().toMutable().rootNodes().iterator().next());
        }
        
        g = g.with(partDcl.link(partGraphs));
        
        return g;
    }
}