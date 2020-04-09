package msharp;
import msharp.Nodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class buildAstVisitor extends cfgBaseVisitor<node> {
    public List<String> getSemanticErrors() {
        return semanticErrors;
    }

    // Can be used for errorhandling
    //private List<String> vars;
    private List<String> semanticErrors = new ArrayList<>();

    private Map<String, Object> symbolTable = new HashMap<>();
    // Add to symboltable
    // Dawd35j
    // $instrument , GUITAR
    // $octave     , 3
    // $bpm        ,
    // $tempo


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

        for(cfgParser.StmtContext stmt : ctx.stmt()){
            node.add((stmtNode) visit(stmt));
        }
        return node;
    }

    @Override
    public stmtNode visitPbodyTransUp(cfgParser.PbodyTransUpContext ctx) {
        if(ctx.Digs() == null)
            return new transposeNode(1, (stmtNode) visit(ctx.partBody()));
        return new transposeNode(Integer.parseInt(ctx.Digs().getText()), (stmtNode) visit(ctx.partBody()));
    }

    @Override
    public stmtNode visitPbodyTransDown(cfgParser.PbodyTransDownContext ctx) {
        if(ctx.Digs() == null)
            return new transposeNode(-1, (stmtNode) visit(ctx.partBody()));

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
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Operators
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public node visitOpsIntru(cfgParser.OpsIntruContext ctx) {
        return new instruNode(ctx.Instrument().getText());
    }
    
    @Override
    public node visitOpsOctDown(cfgParser.OpsOctDownContext ctx){
        return new octaveChangeNode(-1);
    }

    @Override
    public node visitOpsOctUp(cfgParser.OpsOctUpContext ctx) {
        return new octaveChangeNode(1);
    }

    @Override
    public node visitOpsTempOp(cfgParser.OpsTempOpContext ctx){
        return visit(ctx.tempoOp());
    }

    @Override
    public tempoChangeNode visitTempoOp(cfgParser.TempoOpContext ctx){
        return new tempoChangeNode(
                Integer.parseInt(ctx.Digs(0).getText()),
                Integer.parseInt(ctx.Digs(1).getText())
        );
    }

    @Override
    public node visitOpsBpmDcl(cfgParser.OpsBpmDclContext ctx) {
        return new bpmDclNode(Integer.parseInt(ctx.Digs().getText()),
                (tempoChangeNode) visit(ctx.tempoOp()));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MultStmt
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public node visitMultStmtStmt(cfgParser.MultStmtStmtContext ctx){
        return visit(ctx.stmt());
    }

    @Override
    public node visitMultStmtNL(cfgParser.MultStmtNLContext ctx){
        return null; //todo
    }

    @Override
    public node visitMultStmtMultRepeat(cfgParser.MultStmtMultRepeatContext ctx) {
        return visit(ctx.multilineRepeat());
    }

    @Override
    public node visitMultilineRepeat(cfgParser.MultilineRepeatContext ctx) {
        repeatNode node = new repeatNode(Integer.parseInt(ctx.Digs().getText()));

        if(ctx.multStmtOrEveryStmt().size() == 1){
            node.setStmts((stmtNode) visit(ctx.multStmtOrEveryStmt(0)));
        }
        else{
            parenNode stmts = new parenNode();
            for(ParseTree parseTree : ctx.multStmtOrEveryStmt()){       // For-each
                stmts.add((stmtNode) visit(parseTree));
            }
            node.setStmts(stmts);
        }

        return node;
    }

    @Override
    public node visitEveryStmt(cfgParser.EveryStmtContext ctx) {
        everyNode node = new everyNode(Integer.parseInt((ctx.Digs().getText())));

        if(ctx.multStmtOrEveryStmt().size() == 1){
            node.setTrueCase((stmtNode) visit(ctx.multStmtOrEveryStmt(0)));
        }
        else{
            parenNode stmts = new parenNode();
            for(ParseTree parseTree : ctx.multStmtOrEveryStmt()){       // For-each
                stmts.add((stmtNode) visit(parseTree));
            }
            node.setTrueCase(stmts);
        }

        if(ctx.elseStmt() != null){
            node.setElseCase((stmtNode) visit(ctx.elseStmt()));
        }
        return node;
    }

    @Override
    public node visitElseStmt(cfgParser.ElseStmtContext ctx) {
        if(ctx.multStmtOrEveryStmt().size() == 1){
            return visit(ctx.multStmtOrEveryStmt(0));
        }
        else{
            parenNode stmts = new parenNode();
            for(ParseTree parseTree : ctx.multStmtOrEveryStmt()){       // For-each
                stmts.add((stmtNode) visit(parseTree));
            }
            return stmts;
        }
    }

    @Override
    public node visitMultStmtOrEveryStmtMultStmt(cfgParser.MultStmtOrEveryStmtMultStmtContext ctx) {
        return visit(ctx.multStmt());
    }

    @Override
    public node visitMultStmtOrEveryStmtEveryStmt(cfgParser.MultStmtOrEveryStmtEveryStmtContext ctx) {
        return visit(ctx.everyStmt());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // declarations and main
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public node visitProg(cfgParser.ProgContext ctx) {
        progNode prog = new progNode();

        for(ParseTree pt : ctx.partDcl()){
            prog.parts.add((partDclNode) visit(pt));
        }

        prog.main = (playNode) visit(ctx.playDcl());

        return prog;
    }

    @Override
    public node visitPartDclSingleLine(cfgParser.PartDclSingleLineContext ctx) {
        partDclNode part = new partDclNode(ctx.Id().getText());

        for(ParseTree pt : ctx.stmt()){
            part.stmts.add((stmtNode) visit(pt));
        }

        if(symbolTable.containsKey(part.getId())) {
            semanticErrors.add(part.getId() + " part name is already defined. (At line " + ctx.Id().getSymbol().getLine() + ")");
        }
        else{
            symbolTable.putIfAbsent(part.id, part.stmts);
        }

        return part;
    }

    @Override
    public node visitPartDclMultiLine(cfgParser.PartDclMultiLineContext ctx) {
        return super.visitPartDclMultiLine(ctx);
    }

    @Override
    public node visitPlayDcl(cfgParser.PlayDclContext ctx) {
        playNode node = new playNode();

        for(ParseTree pt : ctx.multStmt()){
            node.stmts.add((stmtNode) visit(pt));
        }

        return new playNode();
    }

    @Override
    public node visitStmtPBody(cfgParser.StmtPBodyContext ctx) {
        return visit(ctx.partBody());
    }

    @Override
    public node visitStmtOps(cfgParser.StmtOpsContext ctx) {
        return visit(ctx.ops());
    }
}

































