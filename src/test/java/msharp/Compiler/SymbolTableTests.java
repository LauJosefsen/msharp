package msharp.Compiler;

import msharp.ASTBuilder.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SymbolTableTests {
    
    SymbolTable symbolTable;
    
    @BeforeEach
    void beforeEach(){
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
    }
    
    @Test
    void openScope ()
    {
    }
    
    @Test
    void closeScope ()
    {
    }
    
    @Test
    void enterSymbol_trivialCase ()
    {
        symbolTable.enterSymbol("Test", new Object());
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol != null);
    }
    
    @Test
    void enterSymbol_shouldBeFoundAfterOpeningAndClosingScope ()
    {
        symbolTable.enterSymbol("Test", new Object());
        symbolTable.openScope();
        symbolTable.closeScope();
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol != null);
    }
    
    @Test
    void enterSymbol_shouldNotBeFoundAfterClosingScope ()
    {
        symbolTable.enterSymbol("Test", new Object());
        symbolTable.closeScope();
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol == null);
    }
    
    @Test
    void enterSymbol_shouldBeFoundAfterOpeningScope ()
    {
        symbolTable.openScope();
        symbolTable.enterSymbol("Test", new Object());
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol != null);
    }
    
    @Test
    void enterSymbol_shouldBeFoundEvenWhenANewScopeIsOpened()
    {
        symbolTable.enterSymbol("Test", new Object());
        symbolTable.openScope();
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol != null);
    }
    
    @Test
    void enterSymbol_shouldBeFoundEvenWhenANewScopeIsOpenedAndThenClosed()
    {
        symbolTable.enterSymbol("Test", new Object());
        symbolTable.openScope();
        symbolTable.closeScope();
        Symbol symbol =  symbolTable.retrieveSymbol("Test");
        assertTrue(symbol != null);
    }
    
    @Test
    void retrieveSymbol_CanRetriveGlobalSymbolPart ()
    {
        Symbol returnedValue = symbolTable.retrieveSymbol("part1");
        
        assertTrue(returnedValue != null);
    }
    
    @Test
    void retrieveSymbol_CanRetriveGlobalSymbolInt ()
    {
        Symbol returnedValue = symbolTable.retrieveSymbol("int1");
        
        assertTrue(returnedValue != null);
    }
    
    @Test
    void retrieveSymbol_OnlyReturnsIfItemExist ()
    {
        Symbol returnedValue = symbolTable.retrieveSymbol("ThisKeyIsNotInTheSymbolTable");
        assertTrue(returnedValue == null);
    }

}