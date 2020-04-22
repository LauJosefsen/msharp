package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;

import java.util.Objects;
import java.util.Stack;

public class NodeContext implements Cloneable {
    public int octave = 0;
    public Instrument instrument = Instrument.HARP;
    public Bpm bpm = new Bpm(150, new Tempo(1, 4));
    public Tempo tempo = new Tempo(1, 4);
    public Fraction timing = new Fraction(0, 1);
    
    public Stack<IntByReference> repeatIterationStack = new Stack<>();
    
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
    
    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeContext that = (NodeContext) o;
        return octave == that.octave &&
                instrument == that.instrument &&
                Objects.equals(bpm, that.bpm) &&
                Objects.equals(tempo, that.tempo) &&
                Objects.equals(timing, that.timing) &&
                Objects.equals(repeatIterationStack, that.repeatIterationStack);
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(octave, instrument, bpm, tempo, timing, repeatIterationStack);
    }
}
