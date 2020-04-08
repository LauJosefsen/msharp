package msharp;
import msharp.Nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class buildAstVisitor extends cfgBaseVisitor<node> {
    private List<String> vars;
    private List<String> semanticErrors;

    @Override
    public stmtNode visitPbodyTone(cfgParser.PbodyToneContext ctx) {
        noteNode node = new noteNode(ctx.Tone().getText().charAt(0),-1);

        if(ctx.Digs() != null){
            node.octave = Integer.parseInt(ctx.Digs().getText());
        }

        return node;
    }

    @Override
    public node visitPbodyPause(cfgParser.PbodyPauseContext ctx) {
        return new noteNode('-',-1);
    }



    /*
    // Part table
    Map<String, partDclNode> data = new HashMap<String, partDclNode>();

    @Override
    public node visitProg(cfgParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public node visitPartDcl(cfgParser.PartDclContext ctx) {
        partDclNode node = new partDclNode();
        node.toneNodeList = new ArrayList<noteNode>();

        node.id = ctx.Id().getText();
        for (int i=0; i < 3; i++)
        {
            node.toneNodeList.add((noteNode) visit(ctx.partBody(i)));
        }

        data.put(node.id, node);

        return node;
    }

    @Override
    public node visitPBodyTone(cfgParser.PBodyToneContext ctx) {
        // Returns the tone node
        return visit(ctx.tone());
    }

    @Override
    public node visitPBodyId(cfgParser.PBodyIdContext ctx) {
        // Not implemented

        // Idk det gør ingen forskel
        noteNode node = new noteNode();
        node.letter = "b";
        node.octave = 1;
        return node;
        //return super.visitPBodyId(ctx)
    }

    @Override
    public node visitTone(cfgParser.ToneContext ctx) {
        // Returns a tone with octave
        noteNode node = new noteNode();

        node.letter = ctx.Tone().getText();
        try {
            node.octave = Integer.parseInt(ctx.Dig().getText());
        }
        catch (Exception e) {
            node.octave = -1;
        }

        // Debug
        System.out.println("[" + node.letter + node.octave + "]");

        return node;
    }

    @Override
    public node visitPlayDcl(cfgParser.PlayDclContext ctx) {
        playNode node = new playNode();
        node.partList = new ArrayList<partDclNode>();

        for (int i=0; i<2; i++){
            String id = ctx.Id(i).getText();

            node.partList.add(data.get(id));
        }

        return node;
    }
    */
}

































