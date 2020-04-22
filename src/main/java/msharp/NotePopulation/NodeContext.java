package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;

import java.util.Stack;

public class NodeContext implements Cloneable {
    int octave = 0;
    Instrument instrument = Instrument.HARP;
    Bpm bpm = new Bpm(150, new Tempo(1, 4));
    Tempo tempo = new Tempo(1, 4);
    Fraction timing = new Fraction(0, 1);
    
    Stack<IntByReference> repeatIterationStack = new Stack<>();
    
    public NodeContext clone ()
    {
        NodeContext nodeContext = null;
        try {
            nodeContext = (NodeContext) super.clone();
            
            //literals will be copied, but bpm, tempo and fraction needs to be copied.
            nodeContext.tempo = (Tempo) tempo.clone();
            nodeContext.bpm = (Bpm) bpm.clone();
            nodeContext.timing = (Fraction) timing.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(); //should never happen.
        }
        
        return nodeContext;
    }
}
