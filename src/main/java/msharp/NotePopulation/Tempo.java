package msharp.NotePopulation;

public class Tempo extends Fraction {


    public Tempo(long numerator, long denominator) {
        super(numerator, denominator);
    }

    public Tempo(Fraction numerator, Fraction denominator) {
        super(numerator, denominator);
    }

    //    public float toFraction(){
//        return (float) ((1.0 *numerator)/denominator);
//    }
    public Fraction toFraction(){
        return new Fraction(numerator,denominator);
    }
}
