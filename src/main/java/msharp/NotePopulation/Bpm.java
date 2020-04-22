package msharp.NotePopulation;

import java.util.Objects;

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
    
    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bpm bpm1 = (Bpm) o;
        return bpm == bpm1.bpm &&
                Objects.equals(tempo, bpm1.tempo);
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(bpm, tempo);
    }
}
