package msharp;

import antlr4.MsharpBaseVisitor;
import antlr4.MsharpParser;
import msharp.Nodes.*;
import msharp.NotePopulation.ToneEnum;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class BuildAstVisitor extends MsharpBaseVisitor<Node> {
    List<String> getSemanticErrors ()
    {
        return semanticErrors;
    }
    
    // Can be used for errorhandling
    //private List<String> vars;
    private final List<String> semanticErrors = new ArrayList<>();
    
    @Override
    public StmtNode visitPbodyTone (MsharpParser.PbodyToneContext ctx)
    {
        OperandInterface octave = null;
        if (ctx.Digs() != null)
            octave = new NumberNode(Integer.parseInt(ctx.Digs().getText()));
        if(ctx.numberExpr() != null){
            octave = (OperandInterface) visit(ctx.numberExpr());
        }
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
        return new NoteNode('-', null);
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
        if (ctx.numberExpr() == null)
            return new TransposeNode(new NumberNode(1), (StmtNode) visit(ctx.partBody()));
        return new TransposeNode((OperandInterface) visit(ctx.numberExpr()), (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public StmtNode visitPbodyTransDown (MsharpParser.PbodyTransDownContext ctx)
    {
        if (ctx.numberExpr() == null)
            return new TransposeNode(new NumberNode(-1), (StmtNode) visit(ctx.partBody()));
        
        return new TransposeNode((OperandInterface) visit(ctx.numberExpr()), (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public StmtNode visitPbodyAnd (MsharpParser.PbodyAndContext ctx)
    {
        return new AndNode((StmtNode) visit(ctx.partBody(0)), (StmtNode) visit(ctx.partBody(1)));
    }
    
    @Override
    public Node visitPbodySingleLRepeat (MsharpParser.PbodySingleLRepeatContext ctx)
    {
        return new RepeatNode((OperandInterface) visit(ctx.numberExpr()), (StmtNode) visit(ctx.partBody()));
    }
    
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
                (OperandInterface) visit(ctx.digsOrNumberExprInParenthesis(0)),
                (OperandInterface) visit(ctx.digsOrNumberExprInParenthesis(1)));
    }
    
    @Override
    public Node visitOpsBpmDcl (MsharpParser.OpsBpmDclContext ctx)
    {
        return new BpmDclNode((OperandInterface) visit(ctx.numberExpr()),
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
        
        return new RepeatNode((OperandInterface) visit(ctx.numberExpr()), stmts);
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
        return new EveryNode((OperandInterface) visit(ctx.numberExpr()), trueCase, elseCase);
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

        return new PartDclNode(ctx.Id().getText(), stmts);
    }
    
    @Override
    public Node visitPartDclMultiLine (MsharpParser.PartDclMultiLineContext ctx)
    {
        StmtList stmts = new StmtList();
        
        
        for (ParseTree pt : ctx.multStmt()) {
            stmts.add((StmtNode) visit(pt));
        }

        return new PartDclNode(ctx.Id().getText(), stmts);
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


    // Methods added after second iteration

    @Override
    public Node visitNumberExprActualExpression (MsharpParser.NumberExprActualExpressionContext ctx)
    {
        return new ExprNode((OperandInterface) visit(ctx.numberExpr(0)),
                (OperandInterface) visit(ctx.numberExpr(1)),
                ExprOpEnum.fromString(ctx.numberOp().getText()));
    }

    @Override
    public Node visitAssignNumVariable (MsharpParser.AssignNumVariableContext ctx)
    {
        return new NumDeclNode(ctx.Id().getText(), (OperandInterface) visit(ctx.numberExpr()));
    }

    @Override
    public Node visitMultStmtAssignNum (MsharpParser.MultStmtAssignNumContext ctx)
    {
        return visit(ctx.assignNumVariable());
    }

    @Override
    public Node visitOpsScale (MsharpParser.OpsScaleContext ctx)
    {
        // If the Scale is empty, it will transpose down an empty list, which is okay
        List<ToneEnum> tones = new ArrayList<>();
        for(TerminalNode tone : ctx.Tone()){
            tones.add(ToneEnum.fromLetter(tone.getText().charAt(0)));
        }
        boolean up = ctx.TransposeUp() != null;

        return new ScaleNode(tones, up);
    }

    @Override
    public Node visitNumberExprParens (MsharpParser.NumberExprParensContext ctx)
    {
        return visit(ctx.numberExpr());
    }

    @Override
    public Node visitNumberExprId (MsharpParser.NumberExprIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }

    @Override
    public Node visitNumberExprDigs (MsharpParser.NumberExprDigsContext ctx)
    {
        return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
    }
}

































