package msharp;

import msharp.Nodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildAstVisitor extends cfgBaseVisitor<Node> {
    List<String> getSemanticErrors ()
    {
        return semanticErrors;
    }
    
    // Can be used for errorhandling
    //private List<String> vars;
    private List<String> semanticErrors = new ArrayList<>();
    
    public Map<String, Object> symbolTable = new HashMap<>();
    // Add to symboltable
    // Dawd35j
    // $instrument , GUITAR
    // $octave     , 3
    // $Bpm        ,
    // $Tempo
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PartBody ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public StmtNode visitPbodyTone (cfgParser.PbodyToneContext ctx)
    {
        NoteNode node = new NoteNode(ctx.Tone().getText().charAt(0), -1);
        
        if (ctx.Digs() != null) {
            node.octave = Integer.parseInt(ctx.Digs().getText());
        }
        return node;
    }
    
    @Override
    public StmtNode visitPbodyId (cfgParser.PbodyIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    
    @Override
    public StmtNode visitPbodyPause (cfgParser.PbodyPauseContext ctx)
    {
        return new NoteNode('-', -1);
    }
    
    @Override
    public Node visitPbodyParen (cfgParser.PbodyParenContext ctx)
    {
        StmtList node = new StmtList();
        
        for (cfgParser.StmtContext stmt : ctx.stmt()) {
            node.add((StmtNode) visit(stmt));
        }
        return node;
    }
    
    @Override
    public StmtNode visitPbodyTransUp (cfgParser.PbodyTransUpContext ctx)
    {
        if (ctx.Digs() == null)
            return new TransposeNode(1, (StmtNode) visit(ctx.partBody()));
        return new TransposeNode(Integer.parseInt(ctx.Digs().getText()), (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public StmtNode visitPbodyTransDown (cfgParser.PbodyTransDownContext ctx)
    {
        if (ctx.Digs() == null)
            return new TransposeNode(-1, (StmtNode) visit(ctx.partBody()));
        
        return new TransposeNode(
                -Integer.parseInt(ctx.Digs().getText()),
                (StmtNode) visit(ctx.partBody())
        );
    }
    
    @Override
    public StmtNode visitPbodyAnd (cfgParser.PbodyAndContext ctx)
    {
        return new AndNode((StmtNode) visit(ctx.partBody(0)), (StmtNode) visit(ctx.partBody(1)));
    }
    
    @Override
    public Node visitPbodySingleLRepeat (cfgParser.PbodySingleLRepeatContext ctx)
    {
        return new RepeatNode(Integer.parseInt(
                ctx.Digs().getText())
                , (StmtNode) visit(ctx.partBody()));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Operators
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitOpsIntru (cfgParser.OpsIntruContext ctx)
    {
        return new InstruNode(ctx.Instrument().getText().replace(":", ""));
    }
    
    @Override
    public Node visitOpsOctDown (cfgParser.OpsOctDownContext ctx)
    {
        return new OctaveChangeNode(-1);
    }
    
    @Override
    public Node visitOpsOctUp (cfgParser.OpsOctUpContext ctx)
    {
        return new OctaveChangeNode(1);
    }
    
    @Override
    public Node visitOpsTempOp (cfgParser.OpsTempOpContext ctx)
    {
        return visit(ctx.tempoOp());
    }
    
    @Override
    public TempoChangeNode visitTempoOp (cfgParser.TempoOpContext ctx)
    {
        return new TempoChangeNode(
                Integer.parseInt(ctx.Digs(0).getText()),
                Integer.parseInt(ctx.Digs(1).getText())
        );
    }
    
    @Override
    public Node visitOpsBpmDcl (cfgParser.OpsBpmDclContext ctx)
    {
        return new BpmDclNode(Integer.parseInt(ctx.Digs().getText()),
                (TempoChangeNode) visit(ctx.tempoOp()));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MultStmt
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitMultStmtStmt (cfgParser.MultStmtStmtContext ctx)
    {
        return visit(ctx.stmt());
    }
    
    //    @Override
    //    public Node visitMultStmtNL(cfgParser.MultStmtNLContext ctx){
    //        return null;
    //    }
    
    @Override
    public Node visitMultStmtMultRepeat (cfgParser.MultStmtMultRepeatContext ctx)
    {
        return visit(ctx.multilineRepeat());
    }
    
    @Override
    public Node visitMultilineRepeat (cfgParser.MultilineRepeatContext ctx)
    {
        RepeatNode node = new RepeatNode(Integer.parseInt(ctx.Digs().getText()));
        
        
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.multStmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        node.setStmts(stmts);
        
        if (stmts.size() == 1) {
            node.setStmts(stmts.get(0));
        }
        
        return node;
    }
    
    @Override
    public Node visitEveryStmt (cfgParser.EveryStmtContext ctx)
    {
        EveryNode node = new EveryNode(Integer.parseInt((ctx.Digs().getText())));
        
        
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.multStmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        node.setTrueCase(stmts);
        
        if (stmts.size() == 1) {
            node.setTrueCase(stmts.get(0));
        }
        
        if (ctx.elseStmt() != null) {
            node.setElseCase((StmtNode) visit(ctx.elseStmt()));
        }
        return node;
    }
    
    @Override
    public Node visitElseStmt (cfgParser.ElseStmtContext ctx)
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
    public Node visitMultStmtOrEveryStmtMultStmt (cfgParser.MultStmtOrEveryStmtMultStmtContext ctx)
    {
        return visit(ctx.multStmt());
    }
    
    @Override
    public Node visitMultStmtOrEveryStmtEveryStmt (cfgParser.MultStmtOrEveryStmtEveryStmtContext ctx)
    {
        return visit(ctx.everyStmt());
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // declarations and main
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Node visitProg (cfgParser.ProgContext ctx)
    {
        ProgNode prog = new ProgNode();
        
        for (ParseTree pt : ctx.partDcl()) {
            prog.parts.add((PartDclNode) visit(pt));
        }
        
        prog.main = (PlayNode) visit(ctx.playDcl());
        
        return prog;
    }
    
    @Override
    public Node visitPartDclSingleLine (cfgParser.PartDclSingleLineContext ctx)
    {
        PartDclNode part = new PartDclNode(ctx.Id().getText());
        
        for (ParseTree pt : ctx.stmt()) {
            part.stmts.add((StmtNode) visit(pt));
        }
        
        if (symbolTable.containsKey(part.getId())) {
            semanticErrors.add(part.getId() + " part name is already defined. (At line " + ctx.Id().getSymbol().getLine() + ")");
        } else {
            symbolTable.put(part.getId(), part.stmts);
        }
        
        return part;
    }
    
    @Override
    public Node visitPartDclMultiLine (cfgParser.PartDclMultiLineContext ctx)
    {
        PartDclNode part = new PartDclNode(ctx.Id().getText());
        
        for (ParseTree pt : ctx.multStmt()) {
            part.stmts.add((StmtNode) visit(pt));
        }
        
        if (symbolTable.containsKey(part.getId())) {
            semanticErrors.add(part.getId() + " part name is already defined. (At line " + ctx.Id().getSymbol().getLine() + ")");
        } else {
            symbolTable.putIfAbsent(part.id, part.stmts);
        }
        
        return part;
    }
    
    @Override
    public Node visitPlayDcl (cfgParser.PlayDclContext ctx)
    {
        PlayNode node = new PlayNode();
        
        for (ParseTree pt : ctx.multStmt()) {
            node.stmts.add((StmtNode) visit(pt));
        }
        
        return node;
    }
    
    @Override
    public Node visitStmtPBody (cfgParser.StmtPBodyContext ctx)
    {
        return visit(ctx.partBody());
    }
    
    @Override
    public Node visitStmtOps (cfgParser.StmtOpsContext ctx)
    {
        return visit(ctx.ops());
    }
}

































