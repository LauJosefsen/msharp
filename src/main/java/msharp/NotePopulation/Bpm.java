package msharp.NotePopulation;

import java.util.Objects;

public class Bpm {
    int bpm;
    Fraction tempo;

    public Bpm (int bpm, Fraction tempo)
    {
        this.bpm = bpm;
        this.tempo = tempo;
    }
    
    //copy constructor
    public Bpm (Bpm toBeCopied){
        this.bpm = toBeCopied.bpm;
        this.tempo = new Fraction(toBeCopied.tempo);
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
