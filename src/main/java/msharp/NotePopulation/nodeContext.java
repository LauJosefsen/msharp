package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;

import java.util.Stack;

public class nodeContext implements Cloneable{
    int octave = 0;
    Instrument instrument = Instrument.HARP;
    bpm bpm = new bpm(150, new tempo(1,4));
    tempo tempo = new tempo(1,4);
    float timing = 0;

    Stack<IntByReference> repeatIterationStack = new Stack<>();

    public nodeContext clone()
    {
        try {
            return (nodeContext) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
