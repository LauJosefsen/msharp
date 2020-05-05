package msharp.Nodes;

import guru.nidi.graphviz.attribute.GraphAttr;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class ProgNode implements Node {
    private final List<NumDeclNode> globalVariables;
    private final List<PartDclNode> parts;
    private final PlayNode main;
    
    
    public ProgNode (List<NumDeclNode> globalVariables, List<PartDclNode> parts, PlayNode main)
    {
        this.globalVariables = globalVariables;
        this.parts = parts;
        this.main = main;
    }
    
    public PlayNode getMain ()
    {
        return main;
    }
    
    public List<NumDeclNode> getGlobalVariables ()
    {
        return globalVariables;
    }
    
    public List<PartDclNode> getParts ()
    {
        return parts;
    }
    
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (PartDclNode part : parts) {
            sb.append(part.toString());
            sb.append("\n");
        }
        sb.append(main.toString());
        sb.append("\n");
        return sb.toString();
    }
    
    @Override
    public Graph toGraph ()
    {
        
        guru.nidi.graphviz.model.Node program = node("program");
        Graph g = graph("program").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .graphAttr().with(GraphAttr.splines(GraphAttr.SplineMode.ORTHO))
                .graphAttr().with("concentrate", false)
                .graphAttr().with("compound", false);
        
        List<MutableNode> partGraphs = new ArrayList<>();
        for (NumDeclNode node : globalVariables) {
            partGraphs.add(node.toGraph().toMutable().rootNodes().iterator().next());
        }
        
        for (PartDclNode partDcl : parts) {
            partGraphs.add(partDcl.toGraph().toMutable().rootNodes().iterator().next());
        }
        partGraphs.add(main.toGraph().toMutable().rootNodes().iterator().next());
        
        
        g = g.with(program.link(partGraphs));
        
        return g;
    }
}