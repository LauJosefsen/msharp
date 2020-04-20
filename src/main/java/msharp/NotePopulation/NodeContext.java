package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;

import java.util.Stack;

public class NodeContext implements Cloneable{
    int octave = 0;
    Instrument instrument = Instrument.HARP;
    Bpm bpm = new Bpm(150, new Tempo(1,4));
    Tempo tempo = new Tempo(1,4);
    float timing = 0;

    Stack<IntByReference> repeatIterationStack = new Stack<>();

    public NodeContext clone()
    {
        try {
            return (NodeContext) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
