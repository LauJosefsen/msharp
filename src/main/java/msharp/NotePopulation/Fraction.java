package msharp.NotePopulation;

import java.util.Objects;

public class Fraction implements Comparable<Fraction>, Cloneable {
    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(Fraction numerator, Fraction denominator) {
        // this is basically dividing by fraction.
        this.numerator = Math.multiplyExact(numerator.numerator,denominator.denominator);
        this.denominator = Math.multiplyExact(numerator.denominator,denominator.numerator);
    }

    public long numerator;
    public long denominator;

    public double toDouble(){
        return (1.0*numerator)/denominator;
    }

    public void add(Fraction fraction){
        // hvis den nye fractions denominator går op i vores fractions denominator, så bare tilføj numerator (men skaleret)
        // ellers skal vi ændre på denominator.

        // Example:
        // 1/4 + 1/3
        // De kan ikke bare ligges sammen
        // 3/12 + 1/3
        // bliver til (3 fra this.numerator)+(1 fra denominator)*(12/4) (denominators divideret)
        // = 7/12

        if(this.denominator % fraction.denominator != 0){    // If they are
            this.denominator = Math.multiplyExact(this.denominator, fraction.denominator);
            this.numerator = Math.multiplyExact(this.numerator,fraction.denominator);
        }

        this.numerator = Math.addExact(this.numerator, Math.multiplyExact(fraction.numerator,(this.denominator/fraction.denominator)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;

        // since a fraction can be equal with a different denominator, we first check that the biggest denominator is a integer scalar of the smallest denominator
        if(Math.max(Math.abs(this.denominator),Math.abs(fraction.denominator)) %
                Math.min(Math.abs(this.denominator),Math.abs(fraction.denominator)) != 0){
            return false;
            // is not a integer scalar, and thus must be different fractions.
        }

        // if the max numerator is equal to the min numerator * scalar the fractions are equal
        long scalar =
                Math.max(Math.abs(this.denominator),Math.abs(fraction.denominator)) /
                Math.min(Math.abs(this.denominator),Math.abs(fraction.denominator));
          return Math.min(Math.abs(this.numerator), Math.abs(fraction.numerator)) * scalar ==
                Math.max(Math.abs(this.numerator), Math.abs(fraction.numerator));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public int compareTo(Fraction fraction) {
        return Double.compare(this.toDouble(),fraction.toDouble());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
