package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinalNoteTests {
    
    @Test
    public void TestFinalNoteTranspose ()
    {
        //Arrange
        class TestCase {
            final FinalNote a;
            final FinalNote result;
            final int amount;
        
            public TestCase (FinalNote a, int amount, FinalNote result)
            {
                this.a = a;
                this.result = result;
                this.amount = amount;
            }
        }
        List<TestCase> testCases = new ArrayList<>();
    
        // trivial test case - no transpose.
        testCases.add(new TestCase(
                new FinalNote(Instrument.HARP, ToneEnum.E, 5, new FractionPrecise(0, 1)),
                0,
                new FinalNote(Instrument.HARP, ToneEnum.E, 5, new FractionPrecise(0, 1))
        ));
    
        // edge test case, if transpose is going to lower the octave:
        testCases.add(new TestCase(
                new FinalNote(Instrument.HARP, ToneEnum.C, 5, new FractionPrecise(0, 1)),
                -1,
                new FinalNote(Instrument.HARP, ToneEnum.B, 4, new FractionPrecise(0, 1))
        ));
    
        // edge test case, if transpose is going to lower the octave:
        testCases.add(new TestCase(
                new FinalNote(Instrument.HARP, ToneEnum.B, 4, new FractionPrecise(0, 1)),
                1,
                new FinalNote(Instrument.HARP, ToneEnum.C, 5, new FractionPrecise(0, 1))
        ));
    
        // normal test case, if transpose is happening in the same octave
        testCases.add(new TestCase(
                new FinalNote(Instrument.HARP, ToneEnum.C, 5, new FractionPrecise(0, 1)),
                1,
                new FinalNote(Instrument.HARP, ToneEnum.C_SHARP, 5, new FractionPrecise(0, 1))
        ));
    
        // normal test case, if transpose is happening in the same octave
        testCases.add(new TestCase(
                new FinalNote(Instrument.HARP, ToneEnum.C_SHARP, 5, new FractionPrecise(0, 1)),
                -1,
                new FinalNote(Instrument.HARP, ToneEnum.C, 5, new FractionPrecise(0, 1))
        ));
    
    
        for (TestCase testCase : testCases) {
            //Act
            testCase.a.transpose(testCase.amount);
        
            //Assert
            assertEquals(testCase.a, testCase.result);
        }
    }
}