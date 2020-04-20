package msharp.NotePopulation;

public class Tempo {
    int numerator;
    int denominator;

    public Tempo(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public float toFraction(){
        return (float) ((1.0 *numerator)/denominator);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}
