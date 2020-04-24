package msharp;

import antlr4.MsharpBaseVisitor;
import antlr4.MsharpParser;
import msharp.Nodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildAstVisitor extends MsharpBaseVisitor<Node> {
    List<String> getSemanticErrors ()
    {
        return semanticErrors;
    }
    
    // Can be used for errorhandling
    //private List<String> vars;
    private final List<String> semanticErrors = new ArrayList<>();
    
    public Map<String, Object> symbolTable = new HashMap<>();
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PartBody ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public StmtNode visitPbodyTone (MsharpParser.PbodyToneContext ctx)
    {
        int octave = -1;
        if (ctx.Digs() != null)
            octave = Integer.parseInt(ctx.Digs().getText());
        return new NoteNode(ctx.Tone().getText().charAt(0), octave);
    }
    
    @Override
    public StmtNode visitPbodyId (MsharpParser.PbodyIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    
    @Override
    public StmtNode visitPbodyPause (MsharpParser.PbodyPauseContext ctx)
    {
        return new NoteNode('-', -1);
    }
    
    @Override
    public Node visitPbodyParen (MsharpParser.PbodyParenContext ctx)
    {
        StmtList node = new StmtList();
        
        for (MsharpParser.StmtContext stmt : ctx.stmt()) {
            node.add((StmtNode) visit(stmt));
        }
        return node;
    }
    
    @Override
    public StmtNode visitPbodyTransUp (MsharpParser.PbodyTransUpContext ctx)
    {
        if (ctx.Digs() == null)
            return new TransposeNode(1, (StmtNode) visit(ctx.partBody()));
        return new TransposeNode(Integer.parseInt(ctx.Digs().getText()), (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public StmtNode visitPbodyTransDown (MsharpParser.PbodyTransDownContext ctx)
    {
        if (ctx.Digs() == null)
            return new TransposeNode(-1, (StmtNode) visit(ctx.partBody()));
        
        return new TransposeNode(
                -Integer.parseInt(ctx.Digs().getText()),
                (StmtNode) visit(ctx.partBody())
        );
    }
    
    @Override
    public StmtNode visitPbodyAnd (MsharpParser.PbodyAndContext ctx)
    {
        return new AndNode((StmtNode) visit(ctx.partBody(0)), (StmtNode) visit(ctx.partBody(1)));
    }
    
    @Override
    public Node visitPbodySingleLRepeat (MsharpParser.PbodySingleLRepeatContext ctx)
    {
        return new RepeatNode(Integer.parseInt(
                ctx.Digs().getText())
                , (StmtNode) visit(ctx.partBody()));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Operators
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitOpsIntru (MsharpParser.OpsIntruContext ctx)
    {
        return new InstruNode(ctx.Instrument().getText().replace(":", ""));
    }
    
    @Override
    public Node visitOpsOctDown (MsharpParser.OpsOctDownContext ctx)
    {
        return new OctaveChangeNode(-1);
    }
    
    @Override
    public Node visitOpsOctUp (MsharpParser.OpsOctUpContext ctx)
    {
        return new OctaveChangeNode(1);
    }
    
    @Override
    public Node visitOpsTempOp (MsharpParser.OpsTempOpContext ctx)
    {
        return visit(ctx.tempoOp());
    }
    
    @Override
    public TempoChangeNode visitTempoOp (MsharpParser.TempoOpContext ctx)
    {
        return new TempoChangeNode(
                Integer.parseInt(ctx.Digs(0).getText()),
                Integer.parseInt(ctx.Digs(1).getText())
        );
    }
    
    @Override
    public Node visitOpsBpmDcl (MsharpParser.OpsBpmDclContext ctx)
    {
        return new BpmDclNode(Integer.parseInt(ctx.Digs().getText()),
                (TempoChangeNode) visit(ctx.tempoOp()));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MultStmt
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitMultStmtStmt (MsharpParser.MultStmtStmtContext ctx)
    {
        return visit(ctx.stmt());
    }
    
    //    @Override
    //    public Node visitMultStmtNL(cfgParser.MultStmtNLContext ctx){
    //        return null;
    //    }
    
    @Override
    public Node visitMultStmtMultRepeat (MsharpParser.MultStmtMultRepeatContext ctx)
    {
        return visit(ctx.multilineRepeat());
    }
    
    @Override
    public Node visitMultilineRepeat (MsharpParser.MultilineRepeatContext ctx)
    {
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.multStmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        
        return new RepeatNode(Integer.parseInt(ctx.Digs().getText()),stmts);
    }
    
    @Override
    public Node visitEveryStmt (MsharpParser.EveryStmtContext ctx)
    {
        StmtList trueCase = new StmtList();
        for (ParseTree parseTree : ctx.multStmtOrEveryStmt()) {
            trueCase.add((StmtNode) visit(parseTree));
        }
        
        StmtNode elseCase = new StmtList();
        if (ctx.elseStmt() != null) {
            elseCase = (StmtNode) visit(ctx.elseStmt());
        }
        return new EveryNode(Integer.parseInt((ctx.Digs().getText())), trueCase, elseCase);
    }
    
    @Override
    public Node visitElseStmt (MsharpParser.ElseStmtContext ctx)
    {
        
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.multStmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        
        if (stmts.size() == 1) {
            return stmts.get(0);
        }
        
        return stmts;
        
    }
    
    @Override
    public Node visitMultStmtOrEveryStmtMultStmt (MsharpParser.MultStmtOrEveryStmtMultStmtContext ctx)
    {
        return visit(ctx.multStmt());
    }
    
    @Override
    public Node visitMultStmtOrEveryStmtEveryStmt (MsharpParser.MultStmtOrEveryStmtEveryStmtContext ctx)
    {
        return visit(ctx.everyStmt());
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // declarations and main
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitProg (MsharpParser.ProgContext ctx)
    {
        List<PartDclNode> parts = new ArrayList<>();
        for (ParseTree pt : ctx.partDcl())
            parts.add((PartDclNode) visit(pt));
        
        return new ProgNode(parts, (PlayNode) visit(ctx.playDcl()));
    }
    
    @Override
    public Node visitPartDclSingleLine (MsharpParser.PartDclSingleLineContext ctx)
    {
        StmtList stmts = new StmtList();
        
        for (ParseTree pt : ctx.stmt()) {
            stmts.add((StmtNode) visit(pt));
        }
        
        PartDclNode part = new PartDclNode(ctx.Id().getText(), stmts);
        
        if (symbolTable.containsKey(part.getId())) {
            semanticErrors.add(part.getId() + " part name is already defined. (At line " + ctx.Id().getSymbol().getLine() + ")");
        } else {
            symbolTable.put(part.getId(), part.getStmts());
        }
        
        return part;
    }
    
    @Override
    public Node visitPartDclMultiLine (MsharpParser.PartDclMultiLineContext ctx)
    {
        StmtList stmts = new StmtList();
        
        
        for (ParseTree pt : ctx.multStmt()) {
            stmts.add((StmtNode) visit(pt));
        }
        PartDclNode part = new PartDclNode(ctx.Id().getText(), stmts);
        
        if (symbolTable.containsKey(part.getId())) {
            semanticErrors.add(part.getId() + " part name is already defined. (At line " + ctx.Id().getSymbol().getLine() + ")");
        } else {
            symbolTable.putIfAbsent(part.getId(), part.getStmts());
        }
        
        return part;
    }
    
    @Override
    public Node visitPlayDcl (MsharpParser.PlayDclContext ctx)
    {
        StmtList stmts = new StmtList();
        
        for (ParseTree pt : ctx.multStmt()) {
            stmts.add((StmtNode) visit(pt));
        }
        
        return new PlayNode(stmts);
    }
    
    @Override
    public Node visitStmtPBody (MsharpParser.StmtPBodyContext ctx)
    {
        return visit(ctx.partBody());
    }
    
    @Override
    public Node visitStmtOps (MsharpParser.StmtOpsContext ctx)
    {
        return visit(ctx.ops());
    }
}

































