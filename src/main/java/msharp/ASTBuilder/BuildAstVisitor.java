package msharp.ASTBuilder;

import antlr4.MsharpBaseVisitor;
import antlr4.MsharpParser;
import msharp.ASTBuilder.*;
import msharp.NotePopulation.ToneEnum;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class BuildAstVisitor extends MsharpBaseVisitor<Node> {
    
    @Override
    public StmtNode visitPbodyTone (MsharpParser.PbodyToneContext ctx)
    {
        OperandInterface octave = null;
        if (ctx.Digs() != null)
            octave = new NumberNode(Integer.parseInt(ctx.Digs().getText()));
        if (ctx.numberExpr() != null) {
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
        
        List<NumDeclNode> globalVariables = new ArrayList<>();
        for (ParseTree pt : ctx.assignNumVariable())
            globalVariables.add((NumDeclNode) visit(pt));
        
        List<PartDclNode> parts = new ArrayList<>();
        for (ParseTree pt : ctx.partDcl())
            parts.add((PartDclNode) visit(pt));
        
        
        return new ProgNode(globalVariables, parts, (PlayNode) visit(ctx.playDcl()));
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
    public Node visitDigsOrNumberExprInParenthesis (MsharpParser.DigsOrNumberExprInParenthesisContext ctx)
    {
        
        //either digs or number expr
        if (ctx.Digs() != null)
            return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
        return visit(ctx.numberExpr());
    }
    
    @Override
    public Node visitStmtOps (MsharpParser.StmtOpsContext ctx)
    {
        return visit(ctx.ops());
    }
    
    
    // Methods added after second iteration
    /*
    @Override
    public Node visitNumberExprActualExpression (MsharpParser.NumberExprActualExpressionContext ctx)
    {
        return new ExprNode((OperandInterface) visit(ctx.numberExpr(0)),
                (OperandInterface) visit(ctx.numberExpr(1)),
                ExprOpEnum.fromString(ctx.numberOp().getText()));
    }*/
    
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
        for (TerminalNode tone : ctx.Tone()) {
            tones.add(ToneEnum.fromLetter(tone.getText().charAt(0)));
        }
        boolean up = ctx.TransposeUp() != null;
        
        return new ScaleNode(tones, up);
    }
    
    @Override
    public Node visitMultStmtNL (MsharpParser.MultStmtNLContext ctx)
    {
        return super.visitMultStmtNL(ctx);
    }
    
    
    @Override
    public Node visitExprOp (MsharpParser.ExprOpContext ctx)
    {
        if (ctx.Plus() != null) {
            // Plus node
            return new ExprNode(
                    (OperandInterface) visit(ctx.numberExpr())
                    , (OperandInterface) visit(ctx.numberTerm())
                    , ExprOpEnum.ADD);
        } else {
            // Minus node
            return new ExprNode(
                    (OperandInterface) visit(ctx.numberExpr())
                    , (OperandInterface) visit(ctx.numberTerm())
                    , ExprOpEnum.SUBTRACT);
        }
    }
    
    @Override
    public Node visitTermOp (MsharpParser.TermOpContext ctx)
    {
        ExprOpEnum op = ExprOpEnum.MODULO;
        
        if (ctx.Repeat() != null) {
            // Multiply
            op = ExprOpEnum.MULTIPLY;
        }
        if (ctx.OctaveUp() != null) {
            // Divide
            op = ExprOpEnum.DIVIDE;
        }
        
        return new ExprNode(
                (OperandInterface) visit(ctx.numberTerm())
                , (OperandInterface) visit(ctx.numberFactor())
                , op);
    }
    
    
    @Override
    public Node visitFactorParens (MsharpParser.FactorParensContext ctx)
    {
        return visit(ctx.numberExpr());
    }
    
    @Override
    public Node visitExprValue (MsharpParser.ExprValueContext ctx)
    {
        return visit(ctx.numberTerm());
    }
    
    @Override
    public Node visitFactorDigs (MsharpParser.FactorDigsContext ctx)
    {
        return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
    }
    
    @Override
    public Node visitTermValue (MsharpParser.TermValueContext ctx)
    {
        return visit(ctx.numberFactor());
    }
    
    @Override
    public Node visitFactorId (MsharpParser.FactorIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    @Override
    public Node visitPbodyOperators (MsharpParser.PbodyOperatorsContext ctx)
    {
        StmtNode child = (StmtNode) visit(ctx.partBody());
        
        PartBodyOperator operatorNotFinished = (PartBodyOperator) visit(ctx.partAfter());
        
        return operatorNotFinished.setLeftOperand(child);
    }
    
    @Override
    public Node visitAndOperator (MsharpParser.AndOperatorContext ctx)
    {
        return new AndNode(null, (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public Node visitRepeatOperator (MsharpParser.RepeatOperatorContext ctx)
    {
        return new RepeatNode((OperandInterface) visit(ctx.numberExpr()), null);
    }
    
    @Override
    public Node visitTransposeOperator (MsharpParser.TransposeOperatorContext ctx)
    {
        
        if (ctx.numberExpr() == null) {
            int transposeAmount = -1;
            if(ctx.TransposeDown() == null) transposeAmount = 1;
            return new TransposeNode(new NumberNode(transposeAmount), null);
        }
        
        
        
        return new TransposeNode((OperandInterface) visit(ctx.numberExpr()), null);
    }
}

































