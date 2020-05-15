package msharp.NotePopulation;

import msharp.ASTBuilder.*;
import msharp.Compiler.Symbol;
import msharp.Compiler.SymbolTable;
import msharp.MinecraftClasses.Instrument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildNoteListVisitorTests {
    
    NodeContext initialCtx;
    NodeContext ctxToSend;
    BuildNoteListVisitor visitor;
    SymbolTable symbolTable;
    ProgNode progNode;
    PlayNode playNode;
    List<NumDeclNode> globalNumDeclNodesList;
    NumDeclNode numDeclNode;
    
    
    @BeforeEach
    void setUp ()
    {
        List<NodeContext> nodeContexts = new ArrayList<NodeContext>();
        nodeContexts.add(initialCtx);
        nodeContexts.add(ctxToSend);
        
        for (NodeContext nodeContext : nodeContexts) {
            nodeContext= new NodeContext();
            nodeContext.bpm = new Bpm(150, new Fraction(1, 4));
            nodeContext.tempo = new Fraction(1, 8);
            nodeContext.instrument = Instrument.HARP;
            nodeContext.octave = 8;
        }
        createSymbolTable();
        visitor = new BuildNoteListVisitor(symbolTable);
    }
    
    void createSymbolTable() {
        //Creating global variables: Int's
        globalNumDeclNodesList = new ArrayList<NumDeclNode>();
        numDeclNode = new NumDeclNode("int1", new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.ADD));
        globalNumDeclNodesList.add(numDeclNode);
    
        //Creating global variables: Parts.
        List<PartDclNode> partDclNodesList = new ArrayList<PartDclNode>();
        PartDclNode partDclNode = new PartDclNode("part1", new StmtList());
        partDclNodesList.add(partDclNode);
    
        //Creating playNode
        StmtList statementList = new StmtList();
        statementList.add(new NoteNode('c', new NumberNode(initialCtx.octave)));
        playNode = new PlayNode(statementList);
    
        //Creating progNode
        progNode = new ProgNode(globalNumDeclNodesList, partDclNodesList, playNode);
    
        symbolTable = new SymbolTable(progNode);
    }
    
    @Test
    void testVisitProgNode ()
    {
        List<FinalNote> finalNoteList = visitor.visit(progNode);
        assertEquals(1, finalNoteList.size());
        assertEquals(Instrument.HARP, finalNoteList.iterator().next().instrument);
        assertEquals(ToneEnum.C, finalNoteList.iterator().next().tone);
        assertEquals(new FractionPrecise(1, 8), finalNoteList.iterator().next().timing);
        assertEquals(8, finalNoteList.iterator().next().octave);
        
    }
//
//    @Test
//    void testVisit1AndNode ()
//    {
//    }
//
//    @Test
//    void testVisitBpmDclNode ()
//    {
//    }
//
//    @Test
//    void testVisitEveryNode ()
//    {
//    }
//
//    @Test
//    void testVisitIdNode ()
//    {
//    }
//
//    @Test
//    void testVisitInstruNode ()
//    {
//    }
//
//    @Test
//    void testVisitNoteNode ()
//    {
//    }
//
//    @Test
//    void testVisitNodeContext ()
//    {
//    }
//
//    @Test
//    void testVisitOctaveChangeNode ()
//    {
//    }
//
//    @Test
//    void testVisitRepeatNode ()
//    {
//    }
//
//    @Test
//    void testVisitStmtList ()
//    {
//    }
//
//    @Test
//    void testVisitTempoChangeNode ()
//    {
//    }
//
//    @Test
//    void testVisitTransposeNode ()
//    {
//    }
//
//    @Test
//    void testVisitScaleNode ()
//    {
//    }
//
//    @Test
//    void testVisitNumDeclNode ()
//    {
//    }
}