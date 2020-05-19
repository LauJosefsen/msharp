//package msharp.ASTBuilder;
//
//import antlr4.MsharpLexer;
//import antlr4.MsharpParser;
//import msharp.Compiler.IllegalCompilerAction;
//import org.antlr.v4.runtime.tree.TerminalNode;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class BuildAstVisitorTests {
//    BuildAstVisitor visitor = new BuildAstVisitor();
//
//    @Test
//    void visitPbodyToneTrivialCase ()
//    {
//        //Arrange
//        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs()).thenReturn(null);
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//        Mockito.when(ctx.Tone().getText()).thenReturn("d");
//
//        //Act
//        NoteNode result = visitor.visitPbodyTone(ctx);
//
//        //Assert
//        Assertions.assertEquals('d', result.getLetter());
//        Assertions.assertNull(result.getOctave());
//
//    }
//
//    @Test
//    void visitPbodyToneShouldThrowIlegalCompilerActionOnHugeInt ()
//    {
//        //Arrange
//        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("12310230120359081313284813");
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//        Mockito.when(ctx.Tone().getText()).thenReturn("d");
//
//        //Act / Assert
//        Assertions.assertThrows(IllegalCompilerAction.class, () -> visitor.visitPbodyTone(ctx));
//
//
//    }
//
//    @Test
//    void visitPbodyToneShouldSetOctaveOnPositiveInt ()
//    {
//        //Arrange
//        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("123");
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//        Mockito.when(ctx.Tone().getText()).thenReturn("d");
//
//        //Act
//        NoteNode result = visitor.visitPbodyTone(ctx);
//
//        //Assert
//        Assertions.assertEquals(123, ((NumberNode) result.getOctave()).getN());
//    }
//
//    @Test
//    void visitPbodyToneShouldSetOctaveOnNumberExpression ()
//    {
//        //Arrange
//        MsharpParser.PbodyToneContext ctx = Mockito.mock(MsharpParser.PbodyToneContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs()).thenReturn(null);
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(123));
//        Mockito.when(ctx.Tone().getText()).thenReturn("d");
//
//        //Act
//        NoteNode result = visitor.visitPbodyTone(ctx);
//
//        //Assert
//        Assertions.assertEquals(123, ((NumberNode) result.getOctave()).getN());
//    }
//
//    @Test
//    void visitPbodyId ()
//    {
//        //Arrange
//        MsharpParser.PbodyIdContext ctx = Mockito.mock(MsharpParser.PbodyIdContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Id().getText()).thenReturn("SomeText");
//
//        //Act
//        IdNode result = visitor.visitPbodyId(ctx);
//
//        //Assert
//        Assertions.assertEquals("SomeText", result.getId());
//    }
//
//    @Test
//    void visitPbodyPause ()
//    {
//        //Arrange
//        MsharpParser.PbodyPauseContext ctx = Mockito.mock(MsharpParser.PbodyPauseContext.class, Mockito.RETURNS_DEEP_STUBS);
//
//        //Act
//        NoteNode result = visitor.visitPbodyPause(ctx);
//
//        //Assert
//        Assertions.assertEquals('-', result.getLetter());
//    }
//
//    @Test
//    void visitPbodyParen ()
//    {
//        //Arrange
//        MsharpParser.StmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
//
//        MsharpParser.PbodyParenContext ctx = Mockito.mock(MsharpParser.PbodyParenContext.class, Mockito.RETURNS_DEEP_STUBS);
//        List<MsharpParser.StmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//        Mockito.when(ctx.stmt()).thenReturn(list);
//
//
//        //Act
//        StmtList result = visitor.visitPbodyParen(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, result.size());
//        Assertions.assertSame(result.get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("Awd", ((IdNode) result.get(0)).getId());
//    }
//
//    @Test
//    void visitOpsIntru ()
//    {
//        //Arrange
//        MsharpParser.OpsIntruContext ctx = Mockito.mock(MsharpParser.OpsIntruContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Instrument().getText()).thenReturn("BANJO:");
//
//
//        //Act
//        InstruNode result = visitor.visitOpsIntru(ctx);
//
//        //Assert
//        Assertions.assertEquals("BANJO", result.getInstrument());
//    }
//
//    @Test
//    void visitOpsOctDown ()
//    {
//        //Arrange
//        MsharpParser.OpsOctDownContext ctx = Mockito.mock(MsharpParser.OpsOctDownContext.class, Mockito.RETURNS_DEEP_STUBS);
//
//        //Act
//        OctaveChangeNode result = visitor.visitOpsOctDown(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getDeltaOctave(), -1);
//    }
//
//    @Test
//    void visitOpsOctUp ()
//    {
//        //Arrange
//        MsharpParser.OpsOctUpContext ctx = Mockito.mock(MsharpParser.OpsOctUpContext.class, Mockito.RETURNS_DEEP_STUBS);
//
//        //Act
//        OctaveChangeNode result = visitor.visitOpsOctUp(ctx);
//
//        //Assert
//        Assertions.assertEquals(1, result.getDeltaOctave());
//    }
//
//    @Test
//    void visitOpsTempOp ()
//    {
//        //Arrange
//        MsharpParser.OpsTempOpContext ctx = Mockito.mock(MsharpParser.OpsTempOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.tempoOp().accept(visitor))
//                .thenReturn(new TempoChangeNode(new NumberNode(10), new NumberNode(10)));
//
//        //Act
//        NodeInterface result = visitor.visitOpsTempOp(ctx);
//
//        //Assert
//        Assertions.assertSame(result.getClass(), TempoChangeNode.class);
//        Assertions.assertEquals(10, ((NumberNode) ((TempoChangeNode) result).getNumerator()).getN());
//        Assertions.assertEquals(10, ((NumberNode) ((TempoChangeNode) result).getDenominator()).getN());
//    }
//
//    @Test
//    void visitTempoOp ()
//    {
//        //Arrange
//        MsharpParser.TempoOpContext ctx = Mockito.mock(MsharpParser.TempoOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.digsOrNumberExprInParenthesis(0).accept(visitor))
//                .thenReturn(new NumberNode(10));
//        Mockito.when(ctx.digsOrNumberExprInParenthesis(1).accept(visitor))
//                .thenReturn(new NumberNode(10));
//
//        //Act
//        TempoChangeNode result = visitor.visitTempoOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(10, ((NumberNode) (result).getNumerator()).getN());
//        Assertions.assertEquals(10, ((NumberNode) (result).getDenominator()).getN());
//    }
//
//    @Test
//    void visitOpsBpmDcl ()
//    {
//        //Arrange
//        MsharpParser.OpsBpmDclContext ctx = Mockito.mock(MsharpParser.OpsBpmDclContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.numberExpr().accept(visitor))
//                .thenReturn(new NumberNode(100));
//        Mockito.when(ctx.tempoOp().accept(visitor))
//                .thenReturn(new TempoChangeNode(new NumberNode(10), new NumberNode(11)));
//
//        //Act
//        BpmDclNode result = visitor.visitOpsBpmDcl(ctx);
//
//        //Assert
//        Assertions.assertEquals(100, ((NumberNode) result.getBpm()).getN());
//        Assertions.assertEquals(11, ((NumberNode) result.getTempo().getDenominator()).getN());
//        Assertions.assertEquals(10, ((NumberNode) result.getTempo().getNumerator()).getN());
//    }
//
//    @Test
//    void visitMultStmtMultRepeat ()
//    {
//        //Arrange
//        MsharpParser.MultStmtMultRepeatContext ctx = Mockito.mock(MsharpParser.MultStmtMultRepeatContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.multilineRepeat().accept(visitor)).thenReturn(new StmtList());
//
//        //Act
//        NodeInterface result = visitor.visitMultStmtMultRepeat(ctx);
//
//        //Assert
//        Assertions.assertSame(result.getClass(), StmtList.class);
//    }
//
//    @Test
//    void visitMultilineRepeat ()
//    {
//        //Arrange
//        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
//
//        MsharpParser.MultilineRepeatContext ctx = Mockito.mock(MsharpParser.MultilineRepeatContext.class, Mockito.RETURNS_DEEP_STUBS);
//        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(123));
//
//
//        //Act
//        RepeatNode result = visitor.visitMultilineRepeat(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, ((StmtList) result.getStmts()).size());
//        Assertions.assertSame(((StmtList) result.getStmts()).get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("Awd", ((IdNode) ((StmtList) result.getStmts()).get(0)).getId());
//        Assertions.assertEquals(123, ((NumberNode) result.getAmount()).getN());
//    }
//
//    @Test
//    void visitEveryStmt ()
//    {
//        //Arrange
//        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
//
//
//        MsharpParser.EveryStmtContext ctx = Mockito.mock(MsharpParser.EveryStmtContext.class, Mockito.RETURNS_DEEP_STUBS);
//        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
//
//        IdNode pbodyIdContextElse = new IdNode("Awd2");
//        StmtList listElse = new StmtList();
//        for (int i = 0; i < 4; i++) {
//            listElse.add(pbodyIdContextElse);
//        }
//        Mockito.when(ctx.elseStmt().accept(visitor)).thenReturn(listElse);
//
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new NumberNode(12345));
//
//
//        //Act
//        EveryNode result = visitor.visitEveryStmt(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, ((StmtList) result.getTrueCase()).size());
//        Assertions.assertSame(((StmtList) result.getTrueCase()).get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("Awd", ((IdNode) ((StmtList) result.getTrueCase()).get(0)).getId());
//
//        Assertions.assertEquals(4, ((StmtList) result.getElseCase()).size());
//        Assertions.assertSame(((StmtList) result.getElseCase()).get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("Awd2", ((IdNode) ((StmtList) result.getElseCase()).get(0)).getId());
//
//        Assertions.assertEquals(12345, ((NumberNode) result.getAmount()).getN());
//    }
//
//    @Test
//    void visitElseStmt ()
//    {
//        //Arrange
//        MsharpParser.StmtOrEveryStmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtOrEveryStmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("SomeIdNode"));
//        List<MsharpParser.StmtOrEveryStmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//
//        MsharpParser.ElseStmtContext ctx = Mockito.mock(MsharpParser.ElseStmtContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.stmtOrEveryStmt()).thenReturn(list);
//
//
//        //Act
//        StmtList result = visitor.visitElseStmt(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, result.size());
//        Assertions.assertSame(result.get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("SomeIdNode", ((IdNode) result.get(0)).getId());
//    }
//
//    @Test
//    void visitProg ()
//    {
//        // lets check that both part declarations, number declarations and play body are visited.
//        //Arrange
//        MsharpParser.AssignNumVariableContext numAssignMock = Mockito.mock(MsharpParser.AssignNumVariableContext.class);
//        Mockito.when(numAssignMock.accept(visitor)).thenReturn(null);
//        List<MsharpParser.AssignNumVariableContext> listNumVariable = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            listNumVariable.add(numAssignMock);
//        }
//
//        MsharpParser.PartDclContext partDclMock = Mockito.mock(MsharpParser.PartDclContext.class);
//        Mockito.when(numAssignMock.accept(visitor)).thenReturn(null);
//        List<MsharpParser.PartDclContext> listParts = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            listParts.add(partDclMock);
//        }
//
//
//        MsharpParser.ProgContext ctx = Mockito.mock(MsharpParser.ProgContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.assignNumVariable()).thenReturn(listNumVariable);
//        Mockito.when(ctx.partDcl()).thenReturn(listParts);
//
//
//        //Act
//        ProgNode result = visitor.visitProg(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, result.getParts().size());
//        Assertions.assertSame(4,result.getGlobalVariables().size());
//    }
//
//    @Test
//    void visitPlayDcl ()
//    {
//        // basically test if all stmts get added.
//        //Arrange
//        MsharpParser.StmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("Awd"));
//
//        MsharpParser.PlayDclContext ctx = Mockito.mock(MsharpParser.PlayDclContext.class, Mockito.RETURNS_DEEP_STUBS);
//        List<MsharpParser.StmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//        Mockito.when(ctx.stmt()).thenReturn(list);
//
//
//        //Act
//        PlayNode result = visitor.visitPlayDcl(ctx);
//
//        //Assert
//        Assertions.assertEquals(3, result.getStmts().size());
//        Assertions.assertSame(result.getStmts().get(0).getClass(), IdNode.class);
//        Assertions.assertEquals("Awd", ((IdNode) result.getStmts().get(0)).getId());
//    }
//
//
//    @Test
//    void visitDigsOrNumberExprInParenthesisShouldThrowIlegalCompilerActionWithHugeInt ()
//    {
//        //Arrange
//        MsharpParser.DigsOrNumberExprInParenthesisContext ctx = Mockito.mock(MsharpParser.DigsOrNumberExprInParenthesisContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("12310230120359081313284813");
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//
//        //Act / Assert
//        Assertions.assertThrows(IllegalCompilerAction.class, () -> visitor.visitDigsOrNumberExprInParenthesis(ctx));
//    }
//
//    @Test
//    void visitDigsOrNumberExprInParenthesisShouldReturnCorrectInt ()
//    {
//        //Arrange
//        MsharpParser.DigsOrNumberExprInParenthesisContext ctx = Mockito.mock(MsharpParser.DigsOrNumberExprInParenthesisContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("123");
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//
//        //Act
//        NodeInterface result = visitor.visitDigsOrNumberExprInParenthesis(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getClass(), NumberNode.class);
//        Assertions.assertEquals(123, ((NumberNode) result).getN());
//    }
//
//    @Test
//    void visitDigsOrNumberExprInParenthesisShouldReturnCorrectNumberExpr ()
//    {
//        //Arrange
//        MsharpParser.DigsOrNumberExprInParenthesisContext ctx = Mockito.mock(MsharpParser.DigsOrNumberExprInParenthesisContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs()).thenReturn(null);
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new ExprNode(new NumberNode(123), new NumberNode(123), ExprOpEnum.SUBTRACT));
//
//        //Act
//        NodeInterface result = visitor.visitDigsOrNumberExprInParenthesis(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getClass(), ExprNode.class);
//    }
//
//    @Test
//    void visitOpsScaleUp ()
//    {
//        // lets test a scale up, scale down and an empty scale.
//        //Arrange
//        MsharpParser.OpsScaleContext ctx = Mockito.mock(MsharpParser.OpsScaleContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.TransposeUp()).thenReturn(Mockito.mock(TerminalNode.class)); // just need to be not-null
//        Mockito.when(ctx.TransposeDown()).thenReturn(null); // just need to be not-null
//
//        TerminalNode pbodyIdContext = Mockito.mock(TerminalNode.class);
//        Mockito.when(pbodyIdContext.getText()).thenReturn("c");
//
//        List<TerminalNode> list = new ArrayList<>();
//        list.add(pbodyIdContext);
//        Mockito.when(ctx.Tone()).thenReturn(list);
//
//        //Act
//        ScaleNode result = visitor.visitOpsScale(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getInScale().size(), 1);
//        Assertions.assertTrue(result.isUp());
//    }
//
//    @Test
//    void visitOpsScaleDown ()
//    {
//        // lets test a scale up, scale down and an empty scale.
//        //Arrange
//        MsharpParser.OpsScaleContext ctx = Mockito.mock(MsharpParser.OpsScaleContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.TransposeUp()).thenReturn(null); // just need to be not-null
//        Mockito.when(ctx.TransposeDown()).thenReturn(Mockito.mock(TerminalNode.class)); // just need to be not-null
//
//        TerminalNode pbodyIdContext = Mockito.mock(TerminalNode.class);
//        Mockito.when(pbodyIdContext.getText()).thenReturn("c");
//
//        List<TerminalNode> list = new ArrayList<>();
//        list.add(pbodyIdContext);
//        Mockito.when(ctx.Tone()).thenReturn(list);
//
//        //Act
//        ScaleNode result = visitor.visitOpsScale(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getInScale().size(), 1);
//        Assertions.assertFalse(result.isUp());
//    }
//
//    @Test
//    void visitOpsScaleEmpty ()
//    {
//        // lets test a scale up, scale down and an empty scale.
//        //Arrange
//        MsharpParser.OpsScaleContext ctx = Mockito.mock(MsharpParser.OpsScaleContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Tone()).thenReturn(new ArrayList<>());
//        Mockito.when(ctx.TransposeUp().accept(visitor)).thenReturn(new ExprNode(new NumberNode(123), new NumberNode(123), ExprOpEnum.SUBTRACT));
//
//        //Act
//        ScaleNode result = visitor.visitOpsScale(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getInScale().size(), 0);
//        // we dont care if its up or down if the list is empty.
//    }
//
//    @Test
//    void visitExprOpPlus ()
//    {
//        // plus
//        //Arrange
//        MsharpParser.ExprOpContext ctx = Mockito.mock(MsharpParser.ExprOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Plus()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.Pause()).thenReturn(null);
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(null);
//        Mockito.when(ctx.numberTerm().accept(visitor)).thenReturn(null);
//
//        //Act
//        ExprNode result = visitor.visitExprOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getOperator(), ExprOpEnum.ADD);
//    }
//    @Test
//    void visitExprOpSubtract ()
//    {
//        // subtract.
//        //Arrange
//        MsharpParser.ExprOpContext ctx = Mockito.mock(MsharpParser.ExprOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Plus()).thenReturn(null);
//        Mockito.when(ctx.Pause()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(null);
//        Mockito.when(ctx.numberTerm().accept(visitor)).thenReturn(null);
//
//        //Act
//        ExprNode result = visitor.visitExprOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getOperator(), ExprOpEnum.SUBTRACT);
//    }
//
//    @Test
//    void visitTermOpModulo ()
//    {
//        // modulo
//        //Arrange
//        MsharpParser.TermOpContext ctx = Mockito.mock(MsharpParser.TermOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Repeat()).thenReturn(null);
//        Mockito.when(ctx.OctaveUp()).thenReturn(null);
//        Mockito.when(ctx.Percent()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.numberFactor().accept(visitor)).thenReturn(null);
//        Mockito.when(ctx.numberTerm().accept(visitor)).thenReturn(null);
//
//        //Act
//        ExprNode result = visitor.visitTermOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getOperator(), ExprOpEnum.MODULO);
//    }
//    @Test
//    void visitTermOpMultiply ()
//    {
//        // multiply
//        //Arrange
//        MsharpParser.TermOpContext ctx = Mockito.mock(MsharpParser.TermOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Repeat()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.OctaveUp()).thenReturn(null);
//        Mockito.when(ctx.Percent()).thenReturn(null);
//        Mockito.when(ctx.numberFactor().accept(visitor)).thenReturn(null);
//        Mockito.when(ctx.numberTerm().accept(visitor)).thenReturn(null);
//
//        //Act
//        ExprNode result = visitor.visitTermOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getOperator(), ExprOpEnum.MULTIPLY);
//    }
//    @Test
//    void visitTermOpDivide ()
//    {
//        // divide
//        //Arrange
//        MsharpParser.TermOpContext ctx = Mockito.mock(MsharpParser.TermOpContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Repeat()).thenReturn(null);
//        Mockito.when(ctx.OctaveUp()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.Percent()).thenReturn(null);
//        Mockito.when(ctx.numberFactor().accept(visitor)).thenReturn(null);
//        Mockito.when(ctx.numberTerm().accept(visitor)).thenReturn(null);
//
//        //Act
//        ExprNode result = visitor.visitTermOp(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getOperator(), ExprOpEnum.DIVIDE);
//    }
//
//    @Test
//    void visitFactorDigsNegative()
//    {
//        // test both negative, 0, positive and extremely huge nubmers.
//        //Arrange
//        MsharpParser.FactorDigsContext ctx = Mockito.mock(MsharpParser.FactorDigsContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("-123");
//
//        //Act
//        NumberNode result = visitor.visitFactorDigs(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getN(),-123);
//    }
//
//    @Test
//    void visitFactorDigsZero ()
//    {
//        // test both negative, 0, positive and extremely huge nubmers.
//        //Arrange
//        MsharpParser.FactorDigsContext ctx = Mockito.mock(MsharpParser.FactorDigsContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("0");
//
//        //Act
//        NumberNode result = visitor.visitFactorDigs(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getN(),0);
//    }
//
//    @Test
//    void visitFactorDigsPositive ()
//    {
//        // test both negative, 0, positive and extremely huge nubmers.
//        //Arrange
//        MsharpParser.FactorDigsContext ctx = Mockito.mock(MsharpParser.FactorDigsContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("123");
//
//        //Act
//        NumberNode result = visitor.visitFactorDigs(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getN(),123);
//    }
//
//    @Test
//    void visitFactorDigsExtremeInt ()
//    {
//        // test both negative, 0, positive and extremely huge nubmers.
//        //Arrange
//        MsharpParser.FactorDigsContext ctx = Mockito.mock(MsharpParser.FactorDigsContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.Digs().getText()).thenReturn("12310230120359081313284813");
//
//        //Act / Assert
//        Assertions.assertThrows(IllegalCompilerAction.class, () -> visitor.visitFactorDigs(ctx));
//    }
//
//    @Test
//    void visitTransposeOperatorWithoutNumberExprDown ()
//    {
//        // test without a numberexpr.
//        //Arrange
//        MsharpParser.TransposeOperatorContext ctx = Mockito.mock(MsharpParser.TransposeOperatorContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//        Mockito.when(ctx.TransposeDown()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.TransposeUp()).thenReturn(null);
//
//        //Act
//        TransposeNode result = visitor.visitTransposeOperator(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getDeltaTonation().getClass(),NumberNode.class);
//        Assertions.assertEquals(((NumberNode)result.getDeltaTonation()).getN(),-1);
//    }
//    @Test
//    void visitTransposeOperatorWithoutNumberExprUp ()
//    {
//        // test without a numberexpr.
//        //Arrange
//        MsharpParser.TransposeOperatorContext ctx = Mockito.mock(MsharpParser.TransposeOperatorContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.numberExpr()).thenReturn(null);
//        Mockito.when(ctx.TransposeUp()).thenReturn(Mockito.mock(TerminalNode.class));
//        Mockito.when(ctx.TransposeDown()).thenReturn(null);
//
//        //Act
//        TransposeNode result = visitor.visitTransposeOperator(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getDeltaTonation().getClass(),NumberNode.class);
//        Assertions.assertEquals(((NumberNode)result.getDeltaTonation()).getN(),1);
//    }
//    @Test
//    void visitTransposeOperatorWithNumberExpr ()
//    {
//        // test without a numberexpr.
//        //Arrange
//        MsharpParser.TransposeOperatorContext ctx = Mockito.mock(MsharpParser.TransposeOperatorContext.class, Mockito.RETURNS_DEEP_STUBS);
//
//        Mockito.when(ctx.numberExpr().accept(visitor)).thenReturn(new ExprNode(null,null,ExprOpEnum.ADD));
//        Mockito.when(ctx.TransposeUp()).thenReturn(null);
//        Mockito.when(ctx.TransposeDown()).thenReturn(null);
//
//        //Act
//        TransposeNode result = visitor.visitTransposeOperator(ctx);
//
//        //Assert
//        Assertions.assertEquals(result.getDeltaTonation().getClass(),ExprNode.class);
//    }
//
//    @Test
//    void visitPartDcl ()
//    {
//        // confirm that all are added and id is assigned.
//        //Arrange
//        MsharpParser.StmtContext pbodyIdContext = Mockito.mock(MsharpParser.StmtContext.class);
//        Mockito.when(pbodyIdContext.accept(visitor)).thenReturn(new IdNode("SomeIdNode"));
//        List<MsharpParser.StmtContext> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(pbodyIdContext);
//        }
//
//        MsharpParser.PartDclContext ctx = Mockito.mock(MsharpParser.PartDclContext.class, Mockito.RETURNS_DEEP_STUBS);
//        Mockito.when(ctx.stmt()).thenReturn(list);
//        Mockito.when(ctx.Id().getText()).thenReturn("NameOfPart");
//
//
//        //Act
//        PartDclNode result = visitor.visitPartDcl(ctx);
//
//        //Assert
//        Assertions.assertEquals("NameOfPart", result.getId());
//        Assertions.assertEquals(result.getStmts().size(), 3);
//    }
//}