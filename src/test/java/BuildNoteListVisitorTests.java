import org.junit.jupiter.api.Test;

public class BuildNoteListVisitorTests {
    
    // when we visit prognode and playnode, we just want to initialize the list and context, and then visit the main method/stmts in the main method
    // The methods are really trivial and are therefore not tested.
    
    @Test
    public void VisitAndNodeTest ()
    {
        // TWO important things to assure about the AND-node
        // 1. The changed context in EITHER side must NOT affect the context before the AND-NODE is callled, except for timing progression.
        // 2. The progression of timing should be equal to the LEFT-SIDE of the AND-NODE
    }
    
    @Test
    public void VisitBpmDclNodeTest () {}
    
    @Test
    public void VisitEveryNodeTest () {}
    
    @Test
    public void VisitIdNodeTest () {}
    
    @Test
    public void VisitInstruNodeTest () {}
    
    @Test
    public void VisitNoteNodeTest () {}
    
    @Test
    public void MoveTimerByNoteDurationTest () {}
    
    @Test
    public void VisitOctaveChangeNodeTest () {}
    
    @Test
    public void VisitRepeatNodeTest () {}
    
    @Test
    public void VisitStmtListNodeTest () {}
    
    @Test
    public void VisitTempoChangeNodeTest () {}
    
    @Test
    public void VisitTransposeNodeTest () {}
}
