package msharp.NotePopulation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class FractionPrecise implements Comparable<FractionPrecise>, Cloneable {
    
    public final BigInteger numerator;
    public final BigInteger denominator;
    
    public FractionPrecise (long numerator, long denominator)
    {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
    }
    
    public FractionPrecise (BigInteger numerator, BigInteger denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public FractionPrecise (FractionPrecise numerator, FractionPrecise denominator)
    {
        // this is basically dividing by fraction.
        this.numerator = numerator.numerator.multiply(denominator.denominator);
        this.denominator = numerator.denominator.multiply(denominator.numerator);
    }
    
    public FractionPrecise (Fraction numerator, Fraction denominator)
    {
        // this is basically dividing by fraction.
        this.numerator = BigInteger.valueOf(numerator.numerator).multiply(BigInteger.valueOf(denominator.denominator));
        this.denominator = BigInteger.valueOf(numerator.denominator).multiply(BigInteger.valueOf(denominator.numerator));
    }
    
    public BigInteger getNumerator ()
    {
        return numerator;
    }
    
    public BigInteger getDenominator ()
    {
        return denominator;
    }
    
    public double toDouble ()
    {
        // precision of 0.001 is fine
        BigInteger tempNumerator = numerator.multiply(BigInteger.valueOf(1000));
        
        BigInteger fractionValue = tempNumerator.divide(denominator);
        
        return fractionValue.doubleValue() / 1000;
    }
    
    public FractionPrecise add (FractionPrecise fraction)
    {
        // hvis den nye fractions denominator går op i vores fractions denominator, så bare tilføj numerator (men skaleret)
        // ellers skal vi ændre på denominator.
        
        // Example:
        // 1/4 + 1/3
        // De kan ikke bare ligges sammen
        // 3/12 + 1/3
        // bliver til (3 fra this.numerator)+(1 fra denominator)*(12/4) (denominators divideret)
        // = 7/12
        
        BigInteger numerator = this.numerator, denominator = this.denominator;
        if (!denominator.mod(fraction.denominator).equals(BigInteger.ZERO)) {    // If they are
            denominator = denominator.multiply(fraction.denominator);
            numerator = numerator.multiply(fraction.denominator);
        }
        
        numerator = numerator.add(
                fraction.numerator.multiply(
                        denominator.divide(fraction.denominator)));
        
        return (new FractionPrecise(numerator, denominator)).abbreviate();
    }
    
    private FractionPrecise abbreviate ()
    {
        BigInteger gcd = gcd(numerator, denominator);
        return new FractionPrecise(numerator.divide(gcd), denominator.divide(gcd));
    }
    
    //        @Override
    //        public boolean equals (Object o)
    //        {
    //            if (this == o) return true;
    //            if (o == null || getClass() != o.getClass()) return false;
    //            msharp.NotePopulation.Fraction fraction = (msharp.NotePopulation.Fraction) o;
    //
    //            // since a fraction can be equal with a different denominator, we first check that the biggest denominator is a integer scalar of the smallest denominator
    //            if (Math.max(Math.abs(this.denominator), Math.abs(fraction.denominator)) %
    //                    Math.min(Math.abs(this.denominator), Math.abs(fraction.denominator)) != 0) {
    //                return false;
    //                // is not a integer scalar, and thus must be different fractions.
    //            }
    //
    //            // if the max numerator is equal to the min numerator * scalar the fractions are equal
    //            long scalar =
    //                    Math.max(Math.abs(this.denominator), Math.abs(fraction.denominator)) /
    //                            Math.min(Math.abs(this.denominator), Math.abs(fraction.denominator));
    //            return Math.min(Math.abs(this.numerator), Math.abs(fraction.numerator)) * scalar ==
    //                    Math.max(Math.abs(this.numerator), Math.abs(fraction.numerator));
    //        }
    
    //        @Override
    //        public int hashCode ()
    //        {
    //            return Objects.hash(numerator, denominator);
    //        }
    
    @Override
    public int compareTo (FractionPrecise fraction)
    {
        return Double.compare(this.toDouble(), fraction.toDouble());
    }
    
    @Override
    protected Object clone () throws CloneNotSupportedException
    {
        // todo test if denominator/numerator gets cloned or only references gets cloned.
        return super.clone();
    }
    
    private static BigInteger gcd (BigInteger a, BigInteger b)
    {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        }
        return gcd(b, a.mod(b));
    }
}
