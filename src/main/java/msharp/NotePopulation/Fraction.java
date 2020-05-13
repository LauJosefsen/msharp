package msharp.NotePopulation;

import java.util.Objects;

public class Fraction implements Comparable<Fraction>, Cloneable {
    public final long numerator;
    public final long denominator;
    
    public Fraction (long numerator, long denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public Fraction (Fraction numerator, Fraction denominator)
    {
        // this is basically dividing by fraction.
        this.numerator = Math.multiplyExact(numerator.numerator, denominator.denominator);
        this.denominator = Math.multiplyExact(numerator.denominator, denominator.numerator);
    }
    
    public long getNumerator ()
    {
        return numerator;
    }
    
    public long getDenominator ()
    {
        return denominator;
    }
    
    public double toDouble ()
    {
        return (1.0 * numerator) / denominator;
    }
    
    public Fraction add (Fraction fraction)
    {
        // hvis den nye fractions denominator går op i vores fractions denominator, så bare tilføj numerator (men skaleret)
        // ellers skal vi ændre på denominator.
        
        // Example:
        // 1/4 + 1/3
        // De kan ikke bare ligges sammen
        // 3/12 + 1/3
        // bliver til (3 fra this.numerator)+(1 fra denominator)*(12/4) (denominators divideret)
        // = 7/12
        
        long numerator = this.numerator, denominator = this.denominator;
        
        if (denominator % fraction.denominator != 0) {    // If they are
            denominator = Math.multiplyExact(denominator, fraction.denominator);
            numerator = Math.multiplyExact(numerator, fraction.denominator);
        }
        
        numerator = Math.addExact(numerator, Math.multiplyExact(fraction.numerator, (denominator / fraction.denominator)));
        
        return (new Fraction(numerator,denominator)).abbreviate();
    }
    
    private Fraction abbreviate(){
        long gcd = gcd(numerator,denominator);
        return new Fraction(numerator/gcd,denominator/gcd);
    }
    
    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        
        // since a fraction can be equal with a different denominator, we first check that the biggest denominator is a integer scalar of the smallest denominator
        if (Math.max(Math.abs(this.denominator), Math.abs(fraction.denominator)) %
                Math.min(Math.abs(this.denominator), Math.abs(fraction.denominator)) != 0) {
            return false;
            // is not a integer scalar, and thus must be different fractions.
        }
        
        // if the max numerator is equal to the min numerator * scalar the fractions are equal
        long scalar =
                Math.max(Math.abs(this.denominator), Math.abs(fraction.denominator)) /
                        Math.min(Math.abs(this.denominator), Math.abs(fraction.denominator));
        return Math.min(Math.abs(this.numerator), Math.abs(fraction.numerator)) * scalar ==
                Math.max(Math.abs(this.numerator), Math.abs(fraction.numerator));
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(numerator, denominator);
    }
    
    @Override
    public int compareTo (Fraction fraction)
    {
        return Double.compare(this.toDouble(), fraction.toDouble());
    }
    
    @Override
    protected Object clone () throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
