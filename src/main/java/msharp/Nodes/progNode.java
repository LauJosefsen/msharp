package msharp.Nodes;

import java.util.ArrayList;
import java.util.List;

public class progNode implements node{
    public List<partDclNode> parts = new ArrayList<>();
    public playNode main;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(partDclNode part : parts){
            sb.append(part.toString());
            sb.append("\n");
        }
        sb.append(main.toString());
        sb.append("\n");
        return sb.toString();
    }
}