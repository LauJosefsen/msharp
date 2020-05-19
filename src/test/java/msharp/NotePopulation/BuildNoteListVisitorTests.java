package msharp.NotePopulation;

import msharp.ASTBuilder.*;
import msharp.Compiler.IllegalCompilerAction;
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
        initialCtx = new NodeContext();
        ctxToSend = new NodeContext();
        nodeContexts.add(initialCtx);
        nodeContexts.add(ctxToSend);
        
        for (NodeContext nodeContext : nodeContexts) {
            nodeContext = new NodeContext();
            nodeContext.bpm = new Bpm(150, new Fraction(1, 4));
            nodeContext.tempo = new Fraction(1, 8);
            nodeContext.instrument = Instrument.HARP;
            nodeContext.octave = 8;
        }
        createSymbolTable();
        visitor = new BuildNoteListVisitor(symbolTable);
    }
    
    void createSymbolTable ()
    {
        //Creating global variables: Int's
        globalNumDeclNodesList = new ArrayList<NumDeclNode>();
        numDeclNode = new NumDeclNode("int1", new ExprNode(new NumberNode(1), new NumberNode(1), ExprOpEnum.ADD));
        
        //Creating global variables: Parts.
        List<PartDclNode> partDclNodesList = new ArrayList<PartDclNode>();
        StmtList stmtListForThePartNode = new StmtList();
        stmtListForThePartNode.add(new NoteNode('c', new NumberNode(1)));
        PartDclNode partDclNode = new PartDclNode("part1", stmtListForThePartNode);
        partDclNodesList.add(partDclNode);
        
        
        //Creating playNode
        StmtList statementList = new StmtList();
        statementList.add(numDeclNode);
        playNode = new PlayNode(statementList);
        
        //Creating progNode
        progNode = new ProgNode(globalNumDeclNodesList, partDclNodesList, playNode);
        
        symbolTable = new SymbolTable(progNode);
    }
    
    @Test
    void testVisitPlayNode ()
    {
        visitor.visit(progNode);
        assertNull(symbolTable.retrieveSymbol("int1"));
    }
    
    @Test
    void testVisitAnd ()
    {
        NodeContext expectedNodeContext = new NodeContext(initialCtx);
        NodeContext leftContext = new NodeContext(initialCtx);
        AndNode andNode = new AndNode(new NoteNode('c', new NumberNode(3)),
                new TempoChangeNode(new NumberNode(81), new NumberNode(51)));
        visitor.visit(andNode, initialCtx);
        visitor.visit(new NoteNode('c', new NumberNode(3)), leftContext);
        
        expectedNodeContext.timing = leftContext.timing;
        assertEquals(expectedNodeContext, initialCtx);
    }
    
    @Test
    void testEvery_TrueCase ()
    {
        initialCtx.repeatIterationStack.push(new IntByReference(1));
        StmtList stmtListA = new StmtList();
        stmtListA.add(new NoteNode('c', new NumberNode(1)));
        StmtList stmtListB = new StmtList();
        EveryNode everyNode = new EveryNode(new NumberNode(1), stmtListA, stmtListB);
        
        List<FinalNote> expectedList = visitor.visit(stmtListA, initialCtx);
        List<FinalNote> actualList = visitor.visit(everyNode, initialCtx);
        assertEquals(expectedList.size(), actualList.size());
    }
    
    @Test
    void testEvery_FalseCase ()
    {
        initialCtx.repeatIterationStack.push(new IntByReference(5));
        StmtList stmtListA = new StmtList();
        stmtListA.add(new NoteNode('c', new NumberNode(1)));
        StmtList stmtListB = new StmtList();
        EveryNode everyNode = new EveryNode(new NumberNode(4), stmtListA, stmtListB);
        
        List<FinalNote> expectedList = visitor.visit(stmtListB, initialCtx);
        List<FinalNote> actualList = visitor.visit(everyNode, initialCtx);
        assertEquals(expectedList.size(), actualList.size());
    }
    
    @Test
    void testIdNode_ShouldThrowIllegalCompilerAction ()
    {
        IdNode idNode = new IdNode("DoesntExist");
        assertThrows(new IllegalCompilerAction("Name \""+idNode.getId()+"\", was not declared in current scope.").getClass(),
                () -> visitor.visit(idNode, initialCtx));
    }
    
    @Test
    void testIdNode_ShouldThrowIllegalCompilerAction2 ()
    {
        IdNode idNode = new IdNode("int1");
        assertThrows(new IllegalCompilerAction("Name \""+idNode.getId()+"\", was declared in current scope, but is not a part.").getClass(),
                () -> visitor.visit(idNode, initialCtx));
    }
    
    @Test
    void testIdNode_ShouldReturnFinalNotes ()
    {
        IdNode idNode = new IdNode("part1");
        List<FinalNote> finalNoteList = visitor.visit(idNode, initialCtx);
        assertNotNull(finalNoteList);
    }
    
    @Test
    void testNote_WithOctave()
    {
        NoteNode noteNode = new NoteNode('c', new NumberNode(1000));
        visitor.visit(noteNode, initialCtx);
        assertEquals(1000, initialCtx.octave);
    }
    
    @Test
    void testNote_WithoutOctave()
    {
        NoteNode noteNode = new NoteNode('c', null);
        int initialOctave = initialCtx.octave;
        visitor.visit(noteNode, initialCtx);
        assertEquals(initialOctave, initialCtx.octave);
    }
    
    @Test
    void testNote_Pause()
    {
        NoteNode noteNode = new NoteNode('-', null);
        NodeContext context = new NodeContext(initialCtx);
        visitor.visit(noteNode, initialCtx);
        assertNotEquals(context.timing, initialCtx.timing);
        assertEquals(context.octave, initialCtx.octave);
    }
    
    @Test
    void testNote_Note()
    {
        NoteNode noteNode = new NoteNode('c', null);
        NodeContext context = new NodeContext(initialCtx);
        visitor.visit(noteNode, initialCtx);
        assertNotEquals(context.timing, initialCtx.timing);
    }
    
    @Test
    void testRepeat_positiveAmount()
    {
        StmtList stmtList = new StmtList();
        stmtList.add(new NoteNode('c', null));
        RepeatNode repeatNode = new RepeatNode(new NumberNode(5), stmtList);
        
        List<FinalNote> actualFinalNotes = visitor.visit(repeatNode, initialCtx);
        assertEquals(5, actualFinalNotes.size());
    }
    
    @Test
    void testRepeat_negativeAmount()
    {
        StmtList stmtList = new StmtList();
        stmtList.add(new NoteNode('c', null));
        RepeatNode repeatNode = new RepeatNode(new NumberNode(-5), stmtList);
        
        List<FinalNote> actualFinalNotes = visitor.visit(repeatNode, initialCtx);
        assertEquals(0, actualFinalNotes.size());
    }
    
    @Test
    void testRepeat_0Repititions()
    {
        StmtList stmtList = new StmtList();
        stmtList.add(new NoteNode('c', null));
        RepeatNode repeatNode = new RepeatNode(new NumberNode(0), stmtList);
        
        List<FinalNote> actualFinalNotes = visitor.visit(repeatNode, initialCtx);
        assertEquals(0, actualFinalNotes.size());
    }
    
    @Test
    void testStmtList()
    {
        NodeContext expectedNodeContext = new NodeContext(initialCtx);
        StmtList stmtList = new StmtList();
        NoteNode notenode = new NoteNode('c', null);
        TempoChangeNode tempoChangeNode = new TempoChangeNode(new NumberNode(1), new NumberNode(57));
        InstruNode instruNode = new InstruNode("GUITAR");
        stmtList.add(notenode);
        stmtList.add(tempoChangeNode);
        stmtList.add(instruNode);
        
        visitor.visit(stmtList, initialCtx);
        
        assertEquals(expectedNodeContext.tempo, initialCtx.tempo);
        assertEquals(expectedNodeContext.instrument, initialCtx.instrument);
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