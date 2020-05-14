package msharp.ASTBuilder;

import antlr4.MsharpLexer;
import antlr4.MsharpParser;
import msharp.Compiler.IllegalCompilerAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class BuildAstVisitorTests {
    BuildAstVisitor visitor = new BuildAstVisitor();
    
    @Test
    void visitPbodyToneTrivialCase ()
    {
        //Arrange
        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Digs()).thenReturn(null);
        Mockito.when(ctx.numberExpr()).thenReturn(null);
        Mockito.when(ctx.Tone().getText()).thenReturn("d");
        
        //Act
        NoteNode result = visitor.visitPbodyTone(ctx);
        
        //Assert
        Assertions.assertEquals('d', result.getLetter());
        Assertions.assertNull(result.getOctave());
        
    }
    
    @Test
    void visitPbodyToneShouldThrowIlegalCompilerActionOnHugeInt ()
    {
        //Arrange
        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Digs().getText()).thenReturn("12310230120359081313284813");
        Mockito.when(ctx.numberExpr()).thenReturn(null);
        Mockito.when(ctx.Tone().getText()).thenReturn("d");
        
        //Act / Assert
        Assertions.assertThrows(IllegalCompilerAction.class, () -> visitor.visitPbodyTone(ctx));
        
        
    }
    
    @Test
    void visitPbodyToneShouldSetOctaveOnPositiveInt ()
    {
        //Arrange
        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Digs().getText()).thenReturn("123");
        Mockito.when(ctx.numberExpr()).thenReturn(null);
        Mockito.when(ctx.Tone().getText()).thenReturn("d");
        
        //Act
        NoteNode result = visitor.visitPbodyTone(ctx);
        
        //Assert
        Assertions.assertEquals(123, ((NumberNode) result.getOctave()).getN());
    }
    
    @Test
    void visitPbodyToneShouldSetOctaveOnNumberExpression ()
    {
        //Arrange
        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Digs()).thenReturn(null);
        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(123));
        Mockito.when(ctx.Tone().getText()).thenReturn("d");
        
        //Act
        NoteNode result = visitor.visitPbodyTone(ctx);
        
        //Assert
        Assertions.assertEquals(123, ((NumberNode) result.getOctave()).getN());
    }
    
    @Test
    void visitPbodyId ()
    {
        //Arrange
        MsharpParser.PbodyIdContext ctx = Mockito.mock(MsharpParser.PbodyIdContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Id().getText()).thenReturn("SomeText");
        
        //Act
        IdNode result = visitor.visitPbodyId(ctx);
        
        //Assert
        Assertions.assertEquals("SomeText", result.getId());
    }
    
    @Test
    void visitPbodyPause ()
    {
        //Arrange
        MsharpParser.PbodyPauseContext ctx = Mockito.mock(MsharpParser.PbodyPauseContext.class, Mockito.RETURNS_DEEP_STUBS);
        
        //Act
        NoteNode result = visitor.visitPbodyPause(ctx);
        
        //Assert
        Assertions.assertEquals('-', result.getLetter());
    }
    
    @Test
    void visitPbodyParen ()
    {
        //Arrange
        MsharpParser.StmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtContext.class);
        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
        
        MsharpParser.PbodyParenContext ctx = Mockito.mock(MsharpParser.PbodyParenContext.class, Mockito.RETURNS_DEEP_STUBS);
        List<MsharpParser.StmtContext> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(pbodyIdContext);
        }
        Mockito.when(ctx.stmt()).thenReturn(list);
        
        
        //Act
        StmtList result = visitor.visitPbodyParen(ctx);
        
        //Assert
        Assertions.assertEquals(3, result.size());
        Assertions.assertSame(result.get(0).getClass(), IdNode.class);
        Assertions.assertEquals("Awd", ((IdNode) result.get(0)).getId());
    }
    
    @Test
    void visitOpsIntru ()
    {
        //Arrange
        MsharpParser.OpsIntruContext ctx = Mockito.mock(MsharpParser.OpsIntruContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.Instrument().getText()).thenReturn("BANJO:");
        
        
        //Act
        InstruNode result = visitor.visitOpsIntru(ctx);
        
        //Assert
        Assertions.assertEquals("BANJO", result.getInstrument());
    }
    
    @Test
    void visitOpsOctDown ()
    {
        //Arrange
        MsharpParser.OpsOctDownContext ctx = Mockito.mock(MsharpParser.OpsOctDownContext.class, Mockito.RETURNS_DEEP_STUBS);
        
        //Act
        OctaveChangeNode result = visitor.visitOpsOctDown(ctx);
        
        //Assert
        Assertions.assertEquals(result.getDeltaOctave(), -1);
    }
    
    @Test
    void visitOpsOctUp ()
    {
        //Arrange
        MsharpParser.OpsOctUpContext ctx = Mockito.mock(MsharpParser.OpsOctUpContext.class, Mockito.RETURNS_DEEP_STUBS);
        
        //Act
        OctaveChangeNode result = visitor.visitOpsOctUp(ctx);
        
        //Assert
        Assertions.assertEquals(1, result.getDeltaOctave());
    }
    
    @Test
    void visitOpsTempOp ()
    {
        //Arrange
        MsharpParser.OpsTempOpContext ctx = Mockito.mock(MsharpParser.OpsTempOpContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.tempoOp().accept(visitor))
                .thenReturn(new TempoChangeNode(new NumberNode(10), new NumberNode(10)));
        
        //Act
        NodeInterface result = visitor.visitOpsTempOp(ctx);
        
        //Assert
        Assertions.assertSame(result.getClass(), TempoChangeNode.class);
        Assertions.assertEquals(10, ((NumberNode) ((TempoChangeNode) result).getNumerator()).getN());
        Assertions.assertEquals(10, ((NumberNode) ((TempoChangeNode) result).getDenominator()).getN());
    }
    
    @Test
    void visitTempoOp ()
    {
        //Arrange
        MsharpParser.TempoOpContext ctx = Mockito.mock(MsharpParser.TempoOpContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.digsOrNumberExprInParenthesis(0).accept(visitor))
                .thenReturn(new NumberNode(10));
        Mockito.when(ctx.digsOrNumberExprInParenthesis(1).accept(visitor))
                .thenReturn(new NumberNode(10));
        
        //Act
        TempoChangeNode result = visitor.visitTempoOp(ctx);
        
        //Assert
        Assertions.assertEquals(10, ((NumberNode) (result).getNumerator()).getN());
        Assertions.assertEquals(10, ((NumberNode) (result).getDenominator()).getN());
    }
    
    @Test
    void visitOpsBpmDcl ()
    {
        //Arrange
        MsharpParser.OpsBpmDclContext ctx = Mockito.mock(MsharpParser.OpsBpmDclContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.numberExpr().accept(visitor))
                .thenReturn(new NumberNode(100));
        Mockito.when(ctx.tempoOp().accept(visitor))
                .thenReturn(new TempoChangeNode(new NumberNode(10), new NumberNode(11)));
        
        //Act
        BpmDclNode result = visitor.visitOpsBpmDcl(ctx);
        
        //Assert
        Assertions.assertEquals(100, ((NumberNode) result.getBpm()).getN());
        Assertions.assertEquals(11, ((NumberNode) result.getTempo().getDenominator()).getN());
        Assertions.assertEquals(10, ((NumberNode) result.getTempo().getNumerator()).getN());
    }
    
    @Test
    void visitMultStmtMultRepeat ()
    {
        //Arrange
        MsharpParser.MultStmtMultRepeatContext ctx = Mockito.mock(MsharpParser.MultStmtMultRepeatContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.multilineRepeat().accept(visitor)).thenReturn(new StmtList());
        
        //Act
        NodeInterface result = visitor.visitMultStmtMultRepeat(ctx);
        
        //Assert
        Assertions.assertSame(result.getClass(), StmtList.class);
    }
    
    @Test
    void visitMultilineRepeat ()
    {
        //Arrange
        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
        
        MsharpParser.MultilineRepeatContext ctx = Mockito.mock(MsharpParser.MultilineRepeatContext.class, Mockito.RETURNS_DEEP_STUBS);
        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(pbodyIdContext);
        }
        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(123));
        
        
        //Act
        RepeatNode result = visitor.visitMultilineRepeat(ctx);
        
        //Assert
        Assertions.assertEquals(3, ((StmtList) result.getStmts()).size());
        Assertions.assertSame(((StmtList) result.getStmts()).get(0).getClass(), IdNode.class);
        Assertions.assertEquals("Awd", ((IdNode) ((StmtList) result.getStmts()).get(0)).getId());
        Assertions.assertEquals(123, ((NumberNode) result.getAmount()).getN());
    }
    
    @Test
    void visitEveryStmt ()
    {
        //Arrange
        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
        
        
        MsharpParser.EveryStmtContext ctx = Mockito.mock(MsharpParser.EveryStmtContext.class, Mockito.RETURNS_DEEP_STUBS);
        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(pbodyIdContext);
        }
        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
        
        IdNode pbodyIdContextElse = new IdNode("Awd2");
        StmtList listElse = new StmtList();
        for (int i = 0; i < 4; i++) {
            listElse.add(pbodyIdContextElse);
        }
        Mockito.when(ctx.elseStmt().accept(visitor)).thenReturn(listElse);
        
        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(12345));
        
        
        //Act
        EveryNode result = visitor.visitEveryStmt(ctx);
        
        //Assert
        Assertions.assertEquals(3, ((StmtList) result.getTrueCase()).size());
        Assertions.assertSame(((StmtList) result.getTrueCase()).get(0).getClass(), IdNode.class);
        Assertions.assertEquals("Awd", ((IdNode) ((StmtList) result.getTrueCase()).get(0)).getId());
        
        Assertions.assertEquals(4, ((StmtList) result.getElseCase()).size());
        Assertions.assertSame(((StmtList) result.getElseCase()).get(0).getClass(), IdNode.class);
        Assertions.assertEquals("Awd2", ((IdNode) ((StmtList) result.getElseCase()).get(0)).getId());
        
        Assertions.assertEquals(12345, ((NumberNode) result.getAmount()).getN());
    }
    
    @Test
    void visitElseStmt ()
    {
        //Arrange
        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("SomeIdNode"));
        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(pbodyIdContext);
        }
    
        MsharpParser.ElseStmtContext ctx = Mockito.mock(MsharpParser.ElseStmtContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
    
    
        //Act
        StmtList result = visitor.visitElseStmt(ctx);
    
        //Assert
        Assertions.assertEquals(3, result.size());
        Assertions.assertSame(result.get(0).getClass(), IdNode.class);
        Assertions.assertEquals("SomeIdNode", ((IdNode) result.get(0)).getId());
    }
    
    @Test
    void visitMultStmtOrEveryStmtMultStmt ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitMultStmtOrEveryStmtEveryStmt ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitProg ()
    {
        //todo
    }
    
    @Test
    void visitPlayDcl ()
    {
        //todo
    }
    
    @Test
    void visitStmtPBody ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitDigsOrNumberExprInParenthesis ()
    {
        //todo
    }
    
    @Test
    void visitStmtOps ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitAssignNumVariable ()
    {
        //todo
    }
    
    @Test
    void visitMultStmtAssignNum ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitOpsScale ()
    {
        //todo
    }
    
    @Test
    void visitMultStmtNL ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitExprOp ()
    {
        //todo
    }
    
    @Test
    void visitTermOp ()
    {
        //todo
    }
    
    @Test
    void visitFactorParens ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitExprValue ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitFactorDigs ()
    {
        //todo
    }
    
    @Test
    void visitTermValue ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitFactorId ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitPbodyOperators ()
    {
        //todo
    }
    
    @Test
    void visitAndOperator ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitRepeatOperator ()
    {
        // very trivial, won't test
    }
    
    @Test
    void visitTransposeOperator ()
    {
        //todo
    }
    
    @Test
    void visitPartDcl ()
    {
        //todo
    }
}