package msharp.NotePopulation;

public class Bpm implements Cloneable {
    int bpm;
    Tempo tempo;
    
    public Bpm (int bpm, Tempo tempo)
    {
        this.bpm = bpm;
        this.tempo = tempo;
    }
    
    @Override
    protected Object clone () throws CloneNotSupportedException
    {
        Bpm bpm = (Bpm) super.clone();
        bpm.tempo = (Tempo) tempo.clone();
        return bpm;
    }
}
