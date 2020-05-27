package msharp.ASTBuilder;

import antlr4.MsharpBaseVisitor;
import antlr4.MsharpParser;
import msharp.Compiler.IllegalCompilerAction;
import msharp.NotePopulation.ToneEnum;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class BuildAstVisitor extends MsharpBaseVisitor<NodeInterface> {
    @Override
    public NodeInterface visitStatementOrEveryStatementEveryStatement (MsharpParser.StatementOrEveryStatementEveryStatementContext ctx)
    {
        return visit(ctx.everyStmt());
    }
    
    @Override
    public NodeInterface visitStatementMultilineRepeat (MsharpParser.StatementMultilineRepeatContext ctx)
    {
        return visit(ctx.multilineRepeat());
    }
    
    @Override
    public NodeInterface visitStatementAssignNumber (MsharpParser.StatementAssignNumberContext ctx)
    {
        return visit(ctx.assignNumVariable());
    }
    
    @Override
    public NodeInterface visitPartTermPartFactor (MsharpParser.PartTermPartFactorContext ctx)
    {
        return visit(ctx.partFactor());
    }
    
    @Override
    public NodeInterface visitStatementOrEveryStatementStatement (MsharpParser.StatementOrEveryStatementStatementContext ctx)
    {
        return visit(ctx.statement());
    }
    
    @Override
    public NodeInterface visitPartExprPartTerm (MsharpParser.PartExprPartTermContext ctx)
    {
        return visit(ctx.partTerm());
    }
    
    @Override
    public NodeInterface visitStatementPartExpr (MsharpParser.StatementPartExprContext ctx)
    {
        return visit(ctx.partExpr());
    }
    
    @Override
    public NodeInterface visitStatementOctaveDown (MsharpParser.StatementOctaveDownContext ctx)
    {
        return new OctaveChangeNode(-1);
    }
    
    @Override
    public NodeInterface visitPartFactorId (MsharpParser.PartFactorIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    
    @Override
    public NodeInterface visitPartTermRepeatOperator (MsharpParser.PartTermRepeatOperatorContext ctx)
    {
        return new RepeatNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()), (StmtNode) visit(ctx.partTerm()));
    }
    
    @Override
    public NodeInterface visitPartFactorParenthesis (MsharpParser.PartFactorParenthesisContext ctx)
    {
        StmtList node = new StmtList();
        
        for (MsharpParser.StatementContext stmt : ctx.statement()) {
            node.add((StmtNode) visit(stmt));
        }
        return node;
    }
    
    @Override
    public NodeInterface visitStatementChangeBPM (MsharpParser.StatementChangeBPMContext ctx)
    {
        return new BpmDclNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()),
                (TempoChangeNode) visit(ctx.tempotStmt()));
    }
    
    
    @Override
    public NodeInterface visitStatementInstrument (MsharpParser.StatementInstrumentContext ctx)
    {
        return new InstruNode(ctx.Instrument().getText().replace(":", ""));
    }
    
    @Override
    public NodeInterface visitStatementChangeTempo (MsharpParser.StatementChangeTempoContext ctx)
    {
        return visit(ctx.tempotStmt());
    }
    
    @Override
    public NodeInterface visitStatementChangeScale (MsharpParser.StatementChangeScaleContext ctx)
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
    public NodeInterface visitPartFactorTone (MsharpParser.PartFactorToneContext ctx)
    {
        ArithmeticExpressionNodeInterface octave = null;
        if (ctx.Digs() != null) {
            try {
                octave = new NumberNode(Integer.parseInt(ctx.Digs().getText()));
            } catch (NumberFormatException e) {
                throw new IllegalCompilerAction(e.toString());
            }
        }
        if (ctx.numberExpr() != null) {
            octave = (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr());
        }
        
        return new NoteNode(ctx.Tone().getText().charAt(0), octave);
    }
    
    @Override
    public NodeInterface visitPartFactorPause (MsharpParser.PartFactorPauseContext ctx)
    {
        return new NoteNode('-', null);
    }
    
    @Override
    public NodeInterface visitStatementOctaveUp (MsharpParser.StatementOctaveUpContext ctx)
    {
        return new OctaveChangeNode(1);
    }
    
    @Override
    public NodeInterface visitPartTermTranspose (MsharpParser.PartTermTransposeContext ctx)
    {
        TransposeNode node = (TransposeNode) visit(ctx.transpose());
        node = node.setLeftOperand((StmtNode) visit(ctx.partTerm()));
        return node;
    }
    
    @Override
    public NodeInterface visitTranspose (MsharpParser.TransposeContext ctx)
    {
        if (ctx.digsOrExpressionInParenthesis() == null) {
            int transposeAmount = -1;
            if (ctx.TransposeDown() == null) transposeAmount = 1;
            return new TransposeNode(new NumberNode(transposeAmount), null);
        }
        return new TransposeNode((ArithmeticExpressionNodeInterface) visit(ctx.digsOrExpressionInParenthesis()), null);
    }
    
    
    @Override
    public NodeInterface visitPartExprAndOperator (MsharpParser.PartExprAndOperatorContext ctx)
    {
        return new AndNode((StmtNode) visit(ctx.partExpr()), (StmtNode) visit(ctx.partTerm()));
    }
    
    
    @Override
    public NodeInterface visitDigsOrExpressionInParenthesis (MsharpParser.DigsOrExpressionInParenthesisContext ctx)
    {
        if(ctx.Digs() != null) {
            try {
                return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
            } catch (NumberFormatException e) {
                throw new IllegalCompilerAction(e.toString());
            }
        }
        return visit(ctx.numberExpr());
    }
    
    @Override
    public TempoChangeNode visitTempotStmt (MsharpParser.TempotStmtContext ctx)
    {
        return new TempoChangeNode(
                (ArithmeticExpressionNodeInterface) visit(ctx.digsOrExpressionInParenthesis(0)),
                (ArithmeticExpressionNodeInterface) visit(ctx.digsOrExpressionInParenthesis(1)));
    }
    
    @Override
    public RepeatNode visitMultilineRepeat (MsharpParser.MultilineRepeatContext ctx)
    {
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.stmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        
        return new RepeatNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()), stmts);
    }
    
    @Override
    public EveryNode visitEveryStmt (MsharpParser.EveryStmtContext ctx)
    {
        StmtList trueCase = new StmtList();
        for (ParseTree parseTree : ctx.stmtOrEveryStmt()) {
            trueCase.add((StmtNode) visit(parseTree));
        }
        
        StmtNode elseCase = new StmtList();
        if (ctx.elseStmt() != null) {
            elseCase = (StmtNode) visit(ctx.elseStmt());
        }
        return new EveryNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()), trueCase, elseCase);
    }
    
    @Override
    public StmtList visitElseStmt (MsharpParser.ElseStmtContext ctx)
    {
        
        StmtList stmts = new StmtList();
        for (ParseTree parseTree : ctx.stmtOrEveryStmt()) {       // For-each
            stmts.add((StmtNode) visit(parseTree));
        }
        return stmts;
    }
    
    @Override
    public ProgNode visitProg (MsharpParser.ProgContext ctx)
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
    public PlayNode visitPlayDcl (MsharpParser.PlayDclContext ctx)
    {
        StmtList stmts = new StmtList();
        
        for (ParseTree pt : ctx.statement()) {
            stmts.add((StmtNode) visit(pt));
        }
        
        return new PlayNode(stmts);
    }
    
    @Override
    public NodeInterface visitAssignNumVariable (MsharpParser.AssignNumVariableContext ctx)
    {
        return new NumDeclNode(ctx.Id().getText(), (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()));
    }
    
    
    @Override
    public ExprNode visitExprOp (MsharpParser.ExprOpContext ctx)
    {
        if (ctx.Plus() != null) {
            // Plus node
            return new ExprNode(
                    (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr())
                    , (ArithmeticExpressionNodeInterface) visit(ctx.numberTerm())
                    , ExprOpEnum.ADD);
        } else {
            // Minus node
            return new ExprNode(
                    (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr())
                    , (ArithmeticExpressionNodeInterface) visit(ctx.numberTerm())
                    , ExprOpEnum.SUBTRACT);
        }
    }
    
    @Override
    public ExprNode visitTermOp (MsharpParser.TermOpContext ctx)
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
                (ArithmeticExpressionNodeInterface) visit(ctx.numberTerm())
                , (ArithmeticExpressionNodeInterface) visit(ctx.numberFactor())
                , op);
    }
    
    
    @Override
    public NodeInterface visitFactorParens (MsharpParser.FactorParensContext ctx)
    {
        return visit(ctx.numberExpr());
    }
    
    @Override
    public NodeInterface visitExprValue (MsharpParser.ExprValueContext ctx)
    {
        return visit(ctx.numberTerm());
    }
    
    @Override
    public NumberNode visitFactorDigs (MsharpParser.FactorDigsContext ctx)
    {
        try {
            return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
        } catch (NumberFormatException e) {
            throw new IllegalCompilerAction(e.toString());
        }
    }
    
    @Override
    public NodeInterface visitTermValue (MsharpParser.TermValueContext ctx)
    {
        return visit(ctx.numberFactor());
    }
    
    @Override
    public NodeInterface visitFactorId (MsharpParser.FactorIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    
    
    @Override
    public PartDclNode visitPartDcl (MsharpParser.PartDclContext ctx)
    {
        StmtList stmts = new StmtList();
        
        for (ParseTree pt : ctx.statement()) {
            stmts.add((StmtNode) visit(pt));
        }
        
        return new PartDclNode(ctx.Id().getText(), stmts);
    }
}