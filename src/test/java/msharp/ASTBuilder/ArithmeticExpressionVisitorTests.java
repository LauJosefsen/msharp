package msharp.ASTBuilder;

import msharp.Compiler.IllegalCompilerAction;
import msharp.Compiler.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionVisitorTests {
    
    SymbolTable symbolTable;
    ArithmeticExpressionVisitor arithmeticExpressionVisitor;
    
    @BeforeEach
    void beforeEach () {
        //Creating global variables: Int's
        List<NumDeclNode> globalNumDeclNodesList = new ArrayList<NumDeclNode>();
        NumDeclNode numDeclNode = new NumDeclNode("int1", new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.ADD));
        globalNumDeclNodesList.add(numDeclNode);
    
        //Creating global variables: Parts.
        List<PartDclNode> partDclNodesList = new ArrayList<PartDclNode>();
        PartDclNode partDclNode = new PartDclNode("part1", new StmtList());
        partDclNodesList.add(partDclNode);
    
        //Creating playNode
        StmtList statementList = new StmtList();
        PlayNode playNode = new PlayNode(statementList);
    
        //Creating progNode
        ProgNode progNode = new ProgNode(globalNumDeclNodesList, partDclNodesList, playNode);
    
        symbolTable = new SymbolTable(progNode);
        arithmeticExpressionVisitor = new ArithmeticExpressionVisitor();
    }
    
    @Test
    void visitIdNode_ShouldReturnValue ()
    {
        IdNode idNode = new IdNode("int1");
        int returnedValue = arithmeticExpressionVisitor.visit(idNode, symbolTable);
        assertEquals(2, returnedValue);
    }
    
    @Test
    void visitIdNode_ShouldThrowIllegalCompilerAction ()
    {
        IdNode idNode = new IdNode("DoesntExist");
        assertThrows(new IllegalCompilerAction("Name \""+idNode.getId()+"\", was not declared in current scope.").getClass(),
                () -> arithmeticExpressionVisitor.visit(idNode, symbolTable));
    }
    
    @Test
    void visitIdNode_ShouldThrowIllegalCompilerAction2 ()
    {
        IdNode idNode = new IdNode("part1");
        assertThrows(new IllegalCompilerAction("Name \""+idNode.getId()+"\", was declared in current scope, but is not integer.").getClass(),
                () -> arithmeticExpressionVisitor.visit(idNode, symbolTable));
    }
    
    @Test
    void visitNumberNode ()
    {
        NumberNode numberNode = new NumberNode(5);
        int returnedValue = arithmeticExpressionVisitor.visit(numberNode, symbolTable);
        assertEquals(5, returnedValue);
    }
    
    @Test
    void visitExprNode_ADD ()
    {
        ExprNode exprNode = new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.ADD);
        int actualResult = arithmeticExpressionVisitor.visit(exprNode, symbolTable);
        assertEquals(2, actualResult);
    }
    
    @Test
    void visitExprNode_SUBTRACT ()
    {
        ExprNode exprNode = new ExprNode(new NumberNode(2), new NumberNode(1), ExprOpEnum.SUBTRACT);
        int actualResult = arithmeticExpressionVisitor.visit(exprNode, symbolTable);
        assertEquals(1, actualResult);
    }
    
    @Test
    void visitExprNode_MULTIPLY ()
    {
        ExprNode exprNode = new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.MULTIPLY);
        int actualResult = arithmeticExpressionVisitor.visit(exprNode, symbolTable);
        assertEquals(1, actualResult);
    }
    
    @Test
    void visitExprNode_DIVIDE ()
    {
        ExprNode exprNode = new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.DIVIDE);
        int actualResult = arithmeticExpressionVisitor.visit(exprNode, symbolTable);
        assertEquals(1, actualResult);
    }
    
    @Test
    void visitExprNode_MODULO ()
    {
        ExprNode exprNode = new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.MODULO);
        int actualResult = arithmeticExpressionVisitor.visit(exprNode, symbolTable);
        assertEquals(0, actualResult);
    }
    
    
}