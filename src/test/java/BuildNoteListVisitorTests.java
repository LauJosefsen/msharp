//import msharp.MinecraftClasses.Instrument;
//import msharp.Nodes.*;
//import msharp.NotePopulation.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class BuildNoteListVisitorTests {
//
//    // when we visit prognode and playnode, we just want to initialize the list and context, and then visit the main method/stmts in the main method
//    // The methods are really trivial and are therefore not tested.
//    // TransposeNode is trivial as well, as we have already tested FinalNote.transpose method. Also ctx is handled safe by its children (due to all the other tests in here)
//
//    NodeContext initialCtx;
//    NodeContext ctxToSend;
//    BuildNoteListVisitor visitor;
//
//
//    @BeforeEach
//    void setUp ()
//    {
//        initialCtx= new NodeContext();
//        initialCtx.bpm = new Bpm(150,new Tempo(1,4));
//        initialCtx.tempo = new Tempo(1,8);
//        initialCtx.instrument = Instrument.HARP;
//        initialCtx.octave = 8;
//
//        ctxToSend = initialCtx.clone();
//
//        visitor = new BuildNoteListVisitor(new HashMap<>());
//    }
//
//    @Test
//    public void VisitAndNodeTestNoContextChangeButTiming ()
//    {
//        // TWO important things to assure about the AND-node
//        // 1. The changed context in EITHER side must NOT affect the context before the AND-NODE is callled, except for timing progression.
//        // 2. The progression of timing should be equal to the LEFT-SIDE of the AND-NODE
//        // Check the notes are played on the same time.
//
//
//        // Arrange
//        StmtList stmts = new StmtList();
//        stmts.add(new BpmDclNode(60,new TempoChangeNode(1,100)));
//        stmts.add(new TempoChangeNode(1,100));
//        stmts.add(new InstruNode("GUITAR"));
//        stmts.add(new OctaveChangeNode(1));
//        stmts.add(new NoteNode('c',5));
//        AndNode node = new AndNode(stmts,stmts);
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(initialCtx.bpm,ctxToSend.bpm);
//        assertEquals(initialCtx.tempo,ctxToSend.tempo);
//        assertEquals(initialCtx.instrument,ctxToSend.instrument);
//        assertEquals(initialCtx.octave,ctxToSend.octave);
//        assertEquals(ctxToSend.timing,new Fraction(1,1)); // playing a 1/100th node with bpm of 60 at 100th note should return 1 second.
//        assertEquals(2, result.size());
//        assertEquals(result.get(0).getTiming(), new Fraction(0,1));
//        assertEquals(result.get(0).getTiming(), new Fraction(0,1));
//    }
//
//    @Test
//    public void VisitBpmDclNodeTest () {
//        //Arrange
//        BpmDclNode node = new BpmDclNode(100,new TempoChangeNode(1,100));
//
//
//        //Act
//        node.accept(visitor, ctxToSend);
//
//        //Assert
//        assertEquals(ctxToSend.bpm, new Bpm(100,new Tempo(1,100)));
//
//        // if we change bpm back, ctx should still be identical
//        ctxToSend.bpm = initialCtx.bpm;
//        assertEquals(ctxToSend,initialCtx);
//    }
//
//
//    @Test
//    public void VisitIdNodeWithoutDeclaration () {
//        // Arrange
//        IdNode idNode = new IdNode("SomethingId");
//
//        //Act
//        List<FinalNote> result = idNode.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(visitor.getErrors().size(),1);
//        assertEquals(result.size(),0);
//    }
//
//    @Test
//    public void VisitIdNodeTest () {
//        //primarily we just have to ensure that context is contained, as the rest of functionality relies on other visitors.
//        //Arrange
//        IdNode idNode = new IdNode("SomethingId");
//        // We have to create a listener with an actual symboltable
//
//        Map<String, Object> symbolTable = new HashMap<>();
//
//        StmtList stmtListNode = new StmtList();
//        stmtListNode.add(new BpmDclNode(60,new TempoChangeNode(1,100)));
//        stmtListNode.add(new TempoChangeNode(1,100));
//        stmtListNode.add(new InstruNode("GUITAR"));
//        stmtListNode.add(new OctaveChangeNode(1));
//        stmtListNode.add(new NoteNode('c',-1));
//
//        symbolTable.put("SomethingId",stmtListNode);
//        BuildNoteListVisitor visitor = new BuildNoteListVisitor(symbolTable);
//
//        //Act
//        List<FinalNote> result = idNode.accept(visitor,ctxToSend);
//
//        //Assert
//        // same as from stmtList, as that is an inlinePart more or less
//        assertEquals(ctxToSend.timing,new Fraction(1,1)); // playing a 1/100th node with bpm of 60 at 100th note should return 1 second.
//        // if we set timing to the original, the rest of the context should be the same
//        ctxToSend.timing = initialCtx.timing;
//        assertEquals(ctxToSend,initialCtx);
//
//        assertEquals(1, result.size());
//        assertEquals(result.get(0), new FinalNote(Instrument.GUITAR, ToneEnum.C,initialCtx.octave+1,new Fraction(0,1)));
//    }
//
//    @Test
//    public void VisitInstruNodeTest () {
//        //Arrange
//        InstruNode node = new InstruNode("HAT");
//
//
//        //Act
//        node.accept(visitor, ctxToSend);
//
//        //Assert
//        assertEquals(ctxToSend.instrument, Instrument.HAT);
//
//        // if we change instrument back, ctx should still be identical
//        ctxToSend.instrument = initialCtx.instrument;
//        assertEquals(ctxToSend,initialCtx);
//    }
//
//    @Test
//    public void VisitNoteNodeTestPause () {
//        // test if note is a pause '-'
//        // Arrange
//        NoteNode node = new NoteNode('-',-1);
//
//        // Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        // Context should have changed except timing.
//        assertEquals(ctxToSend.timing,new Fraction(1,5));
//        ctxToSend.timing = initialCtx.timing;
//        assertEquals(ctxToSend,initialCtx);
//
//        assertEquals(result.size(),0);
//    }
//
//    @Test
//    public void VisitNoteNodeTestWithOctave () {
//        // test if a node contains octave
//        // Arrange
//        NoteNode node = new NoteNode('c',500);
//
//        // Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        // Context should have changed except timing and octave.
//        assertEquals(ctxToSend.timing,new Fraction(1,5));
//        assertEquals(ctxToSend.octave, 500);
//        ctxToSend.timing = initialCtx.timing;
//        ctxToSend.octave = initialCtx.octave;
//        assertEquals(ctxToSend,initialCtx);
//
//        assertEquals(result.size(),1);
//        assertEquals(result.get(0),new FinalNote(Instrument.HARP,ToneEnum.C,500,new Fraction(0,1)));
//    }
//
//    @Test
//    public void VisitNoteNodeTestWithoutOctave () {
//        // test if a note does not contain octave.
//        // Arrange
//        NoteNode node = new NoteNode('c',-1);
//
//        // Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        // Context should have changed except timing
//        assertEquals(ctxToSend.timing,new Fraction(1,5));
//        ctxToSend.timing = initialCtx.timing;
//        assertEquals(ctxToSend,initialCtx);
//
//        assertEquals(result.size(),1);
//        assertEquals(result.get(0),new FinalNote(Instrument.HARP,ToneEnum.C,initialCtx.octave,new Fraction(0,1)));
//    }
//
//    @Test
//    public void VisitOctaveChangeNodeTest () {
//        //Arrange
//        OctaveChangeNode node = new OctaveChangeNode(100);
//
//
//        //Act
//        node.accept(visitor, ctxToSend);
//
//        //Assert
//        assertEquals(ctxToSend.octave, initialCtx.octave+100);
//
//        // if we change instrument back, ctx should still be identical
//        ctxToSend.octave = initialCtx.octave;
//        assertEquals(ctxToSend,initialCtx);
//    }
//
//    @Test
//    public void VisitRepeatNodeTest () {
//        // we need to assert that the repeatIterationStack is probably maintained.
//        // the context is maintained by trusting the stmts inside the repeat stmt is probably maintaining the context.
//        // Arrange
//        StmtList stmtList = new StmtList();
//        stmtList.add(new BpmDclNode(60,new TempoChangeNode(1,100)));
//        stmtList.add(new TempoChangeNode(1,100));
//        stmtList.add(new InstruNode("GUITAR"));
//        stmtList.add(new OctaveChangeNode(1));
//        stmtList.add(new NoteNode('c',-1));
//
//
//        RepeatNode node = new RepeatNode(2,stmtList);
//        // Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        // Assert
//        assertEquals(result.size(),2);
//        assertEquals(result.get(0).getTiming(),new Fraction(0,1));
//        assertEquals(result.get(1).getTiming(),new Fraction(1,1));
//
//        assertEquals(ctxToSend.timing, new Fraction(2,1));
//    }
//
//    @Test
//    public void VisitEveryNodeNoExecuteTest () {
//        // Arrange
//        StmtList trueCase = new StmtList();
//        trueCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,trueCase,new StmtList());
//        ctxToSend.repeatIterationStack.push(new IntByReference(3));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),0);
//    }
//
//    @Test
//    public void VisitEveryNodeWithExecuteTest () {
//        // Arrange
//        StmtList trueCase = new StmtList();
//        trueCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,trueCase,new StmtList());
//        ctxToSend.repeatIterationStack.push(new IntByReference(4));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),1);
//    }
//
//    @Test
//    public void VisitEveryNodeWithElseCaseNoExecuteTest () {
//        // Arrange
//        StmtList elseCase = new StmtList();
//        elseCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,new StmtList(),elseCase);
//        ctxToSend.repeatIterationStack.push(new IntByReference(3));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),1);
//    }
//
//    @Test
//    public void VisitEveryNodeWithElseCaseWithExecuteTest () {
//        // Arrange
//        StmtList elseCase = new StmtList();
//        elseCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,new StmtList(),elseCase);
//        ctxToSend.repeatIterationStack.push(new IntByReference(4));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),0);
//    }
//
//    @Test
//    public void VisitEveryNodeWithinTwoRepeatWithExecuteTest () {
//        // Arrange
//        StmtList trueCase = new StmtList();
//        trueCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,trueCase,new StmtList());
//        ctxToSend.repeatIterationStack.push(new IntByReference(2));
//        ctxToSend.repeatIterationStack.push(new IntByReference(4));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),1);
//    }
//
//    @Test
//    public void VisitEveryNodeWithinTwoRepeatWithNoExecuteTest () {
//        // Arrange
//        StmtList trueCase = new StmtList();
//        trueCase.add(new NoteNode('c',-1));
//        EveryNode node = new EveryNode(4,trueCase,new StmtList());
//        ctxToSend.repeatIterationStack.push(new IntByReference(4));
//        ctxToSend.repeatIterationStack.push(new IntByReference(3));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(result.size(),0);
//    }
//
//    @Test
//    public void VisitStmtListNodeTest () {
//        // context changes inside stmt list should NOT be visible outside
//        // lets just check with the example used in the AND-visitor test
//        // Arrange
//        StmtList node = new StmtList();
//        node.add(new BpmDclNode(60,new TempoChangeNode(1,100)));
//        node.add(new TempoChangeNode(1,100));
//        node.add(new InstruNode("GUITAR"));
//        node.add(new OctaveChangeNode(1));
//        node.add(new NoteNode('c',-1));
//
//        //Act
//        List<FinalNote> result = node.accept(visitor,ctxToSend);
//
//        //Assert
//        assertEquals(ctxToSend.timing,new Fraction(1,1)); // playing a 1/100th node with bpm of 60 at 100th note should return 1 second.
//        // if we set timing to the original, the rest of the context should be the same
//        ctxToSend.timing = initialCtx.timing;
//        assertEquals(ctxToSend,initialCtx);
//
//        assertEquals(1, result.size());
//        assertEquals(result.get(0), new FinalNote(Instrument.GUITAR, ToneEnum.C,initialCtx.octave+1,new Fraction(0,1)));
//    }
//
//    @Test
//    public void VisitTempoChangeNodeTest () {
//        //Arrange
//        TempoChangeNode node = new TempoChangeNode(3,5);
//
//
//        //Act
//        node.accept(visitor, ctxToSend);
//
//        //Assert
//        assertEquals(ctxToSend.tempo, new Tempo(3,5));
//
//        // if we change instrument back, ctx should still be identical
//        ctxToSend.tempo = initialCtx.tempo;
//        assertEquals(ctxToSend,initialCtx);
//    }
//
//}
