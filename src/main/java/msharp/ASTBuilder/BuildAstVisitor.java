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
    public NoteNode visitPbodyTone (MsharpParser.PbodyToneContext ctx)
    {
        ArithmeticExpressionNodeInterface octave = null;
        if (ctx.Digs() != null) {
            try {
                octave = new NumberNode(Integer.parseInt(ctx.Digs().getText()));
            } catch (NumberFormatException e){
                throw new IllegalCompilerAction(e.toString());
            }
        }
        if (ctx.numberExpr() != null) {
            octave = (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr());
        }
        
        return new NoteNode(ctx.Tone().getText().charAt(0), octave);
    }
    
    @Override
    public IdNode visitPbodyId (MsharpParser.PbodyIdContext ctx)
    {
        return new IdNode(ctx.Id().getText());
    }
    
    @Override
    public NoteNode visitPbodyPause (MsharpParser.PbodyPauseContext ctx)
    {
        return new NoteNode('-', null);
    }
    
    @Override
    public StmtList visitPbodyParen (MsharpParser.PbodyParenContext ctx)
    {
        StmtList node = new StmtList();
        
        for (MsharpParser.StmtContext stmt : ctx.stmt()) {
            node.add((StmtNode) visit(stmt));
        }
        return node;
    }
    
    
    @Override
    public InstruNode visitOpsIntru (MsharpParser.OpsIntruContext ctx)
    {
        return new InstruNode(ctx.Instrument().getText().replace(":", ""));
    }
    
    @Override
    public OctaveChangeNode visitOpsOctDown (MsharpParser.OpsOctDownContext ctx)
    {
        return new OctaveChangeNode(-1);
    }
    
    @Override
    public OctaveChangeNode visitOpsOctUp (MsharpParser.OpsOctUpContext ctx)
    {
        return new OctaveChangeNode(1);
    }
    
    @Override
    public NodeInterface visitOpsTempOp (MsharpParser.OpsTempOpContext ctx)
    {
        return visit(ctx.tempoOp());
    }
    
    @Override
    public TempoChangeNode visitTempoOp (MsharpParser.TempoOpContext ctx)
    {
        return new TempoChangeNode(
                (ArithmeticExpressionNodeInterface) visit(ctx.digsOrNumberExprInParenthesis(0)),
                (ArithmeticExpressionNodeInterface) visit(ctx.digsOrNumberExprInParenthesis(1)));
    }
    
    @Override
    public BpmDclNode visitOpsBpmDcl (MsharpParser.OpsBpmDclContext ctx)
    {
        return new BpmDclNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()),
                (TempoChangeNode) visit(ctx.tempoOp()));
    }
    
    @Override
    public NodeInterface visitMultStmtMultRepeat (MsharpParser.MultStmtMultRepeatContext ctx)
    {
        return visit(ctx.multilineRepeat());
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
    public NodeInterface visitMultStmtOrEveryStmtMultStmt (MsharpParser.MultStmtOrEveryStmtMultStmtContext ctx)
    {
        return visit(ctx.stmt());
    }
    
    @Override
    public NodeInterface visitMultStmtOrEveryStmtEveryStmt (MsharpParser.MultStmtOrEveryStmtEveryStmtContext ctx)
    {
        return visit(ctx.everyStmt());
    }
    
    @Override
    public NodeInterface visitProg (MsharpParser.ProgContext ctx)
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
    public NodeInterface visitPlayDcl (MsharpParser.PlayDclContext ctx)
    {
        StmtList stmts = new StmtList();
        
        for (ParseTree pt : ctx.stmt()) {
            stmts.add((StmtNode) visit(pt));
        }
        
        return new PlayNode(stmts);
    }
    
    @Override
    public NodeInterface visitStmtPBody (MsharpParser.StmtPBodyContext ctx)
    {
        return visit(ctx.partBody());
    }
    
    @Override
    public NodeInterface visitDigsOrNumberExprInParenthesis (MsharpParser.DigsOrNumberExprInParenthesisContext ctx)
    {
        
        //either digs or number expr
        if (ctx.Digs() != null)
            try {
                return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
            } catch (NumberFormatException e) {
                throw new IllegalCompilerAction(e.toString());
            }
        return visit(ctx.numberExpr());
    }
    
    @Override
    public NodeInterface visitStmtOps (MsharpParser.StmtOpsContext ctx)
    {
        return visit(ctx.ops());
    }
    
    
    @Override
    public NodeInterface visitAssignNumVariable (MsharpParser.AssignNumVariableContext ctx)
    {
        return new NumDeclNode(ctx.Id().getText(), (ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()));
    }
    
    @Override
    public NodeInterface visitMultStmtAssignNum (MsharpParser.MultStmtAssignNumContext ctx)
    {
        return visit(ctx.assignNumVariable());
    }
    
    @Override
    public NodeInterface visitOpsScale (MsharpParser.OpsScaleContext ctx)
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
    public NodeInterface visitMultStmtNL (MsharpParser.MultStmtNLContext ctx)
    {
        return super.visitMultStmtNL(ctx);
    }
    
    
    @Override
    public NodeInterface visitExprOp (MsharpParser.ExprOpContext ctx)
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
    public NodeInterface visitTermOp (MsharpParser.TermOpContext ctx)
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
    public NodeInterface visitFactorDigs (MsharpParser.FactorDigsContext ctx)
    {
        try {
            return new NumberNode(Integer.parseInt(ctx.Digs().getText()));
        }
        catch(NumberFormatException e){
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
    public NodeInterface visitPbodyOperators (MsharpParser.PbodyOperatorsContext ctx)
    {
        StmtNode child = (StmtNode) visit(ctx.partBody());
        
        PartBodyOperatorInterface operatorNotFinished = (PartBodyOperatorInterface) visit(ctx.partAfter());
        
        return operatorNotFinished.setLeftOperand(child);
    }
    
    @Override
    public NodeInterface visitAndOperator (MsharpParser.AndOperatorContext ctx)
    {
        return new AndNode(null, (StmtNode) visit(ctx.partBody()));
    }
    
    @Override
    public NodeInterface visitRepeatOperator (MsharpParser.RepeatOperatorContext ctx)
    {
        return new RepeatNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()), null);
    }
    
    @Override
    public NodeInterface visitTransposeOperator (MsharpParser.TransposeOperatorContext ctx)
    {
        
        if (ctx.numberExpr() == null) {
            int transposeAmount = -1;
            if(ctx.TransposeDown() == null) transposeAmount = 1;
            return new TransposeNode(new NumberNode(transposeAmount), null);
        }
        
        
        
        return new TransposeNode((ArithmeticExpressionNodeInterface) visit(ctx.numberExpr()), null);
    }
    
    @Override
    public NodeInterface visitPartDcl (MsharpParser.PartDclContext ctx)
    {
        StmtList stmts = new StmtList();
    
    
        for (ParseTree pt : ctx.stmt()) {
            stmts.add((StmtNode) visit(pt));
        }
    
        return new PartDclNode(ctx.Id().getText(), stmts);
    }
}