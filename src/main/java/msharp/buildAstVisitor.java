package msharp;
import msharp.Nodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class buildAstVisitor extends cfgBaseVisitor<node> {
    // Can be used for errorhandling
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PartBody ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public stmtNode visitPbodyId(cfgParser.PbodyIdContext ctx) {
        return new idNode(ctx.Id().getText());
    }

    @Override
    public stmtNode visitPbodyPause(cfgParser.PbodyPauseContext ctx) {
        return new noteNode('-',-1);
    }

    @Override
    public stmtNode visitPbodyParen(cfgParser.PbodyParenContext ctx) {
        parenNode node = new parenNode();

        node.stmts = new ArrayList<>();
        for(msharp.cfgParser.StmtContext stmt : ctx.stmt()){
            node.stmts.add((stmtNode) visit(stmt));
        }
        return node;
    }

    @Override
    public stmtNode visitPbodyTransUp(cfgParser.PbodyTransUpContext ctx) {
        return new transposeNode(Integer.parseInt(ctx.Digs().getText()), (stmtNode) visit(ctx.partBody()));
    }

    @Override
    public stmtNode visitPbodyTransDown(cfgParser.PbodyTransDownContext ctx) {
        return new transposeNode(
                - Integer.parseInt(ctx.Digs().getText()),
                (stmtNode) visit(ctx.partBody())
        );
    }

    @Override
    public stmtNode visitPbodyAnd(cfgParser.PbodyAndContext ctx) {
        return new andNode((stmtNode) visit(ctx.partBody(0)), (stmtNode) visit(ctx.partBody(1)));
    }

    @Override
    public node visitPbodySingleLRepeat(cfgParser.PbodySingleLRepeatContext ctx) {
        return new repeatNode(Integer.parseInt(
                ctx.Digs().getText())
                , (stmtNode) visit(ctx.partBody()));
    }






    // SKAL UDFYLDES
    @Override
    public stmtNode visitStmtPBody(cfgParser.StmtPBodyContext ctx) {

        return null;
    }

    @Override
    public stmtNode visitStmtOps(cfgParser.StmtOpsContext ctx) {
        return null;
    }
}

































