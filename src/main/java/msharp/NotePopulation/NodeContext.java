package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;
import msharp.ASTBuilder.ScaleNode;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class NodeContext  {
    public int octave = 0;
    public Instrument instrument = Instrument.HARP;
    public Bpm bpm = new Bpm(150, new Fraction(1, 4));
    public Fraction tempo = new Fraction(1, 4);
    public FractionPrecise timing = new FractionPrecise(0, 1);
    public ScaleNode scale = new ScaleNode(new ArrayList<>(),false);
    public Stack<IntByReference> repeatIterationStack = new Stack<>();
    
    public NodeContext(){}

    // copy constructor
    public NodeContext(NodeContext toBeCloned)
    {
            octave = toBeCloned.octave;
            instrument = toBeCloned.instrument;
            bpm = new Bpm(toBeCloned.bpm);
            tempo = new Fraction(toBeCloned.tempo);
            timing = new FractionPrecise(toBeCloned.timing);
            scale = new ScaleNode(toBeCloned.scale);
            repeatIterationStack = toBeCloned.repeatIterationStack; // doesnt need to be cloned.
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
                Objects.equals(repeatIterationStack, that.repeatIterationStack) &&
                Objects.equals(scale, that.scale);
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(octave, instrument, bpm, tempo, timing, repeatIterationStack, scale);
    }
}
