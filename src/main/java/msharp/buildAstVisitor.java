/*
import Nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class buildAstVisitor extends cfgBaseVisitor<node> {
    // Part table
    Map<String, part> data = new HashMap<String, part>();

    @Override
    public node visitProg(cfgParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public node visitPartDcl(cfgParser.PartDclContext ctx) {
        part node = new part();
        node.toneList = new ArrayList<tone>();

        node.id = ctx.Id().getText();
        for (int i=0; i < 3; i++)
        {
            node.toneList.add((tone) visit(ctx.partBody(i)));
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
        tone node = new tone();
        node.letter = "b";
        node.octave = 1;
        return node;
        //return super.visitPBodyId(ctx)
    }

    @Override
    public node visitTone(cfgParser.ToneContext ctx) {
        // Returns a tone with octave
        tone node = new tone();

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
        play node = new play();
        node.partList = new ArrayList<part>();

        for (int i=0; i<2; i++){
            String id = ctx.Id(i).getText();

            node.partList.add(data.get(id));
        }

        return node;
    }
}




*/


































