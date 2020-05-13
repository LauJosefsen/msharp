package msharp.NotePopulation;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FractionPreciseTests {
    
    
    @Test
    void toDouble ()
    {
        //FractionPrecise fractionPrecise = new FractionPrecise(1, 10);
        //double fractionPreciseDouble = fractionPrecise.toDouble();
        //assert(Math.abs(fractionPreciseDouble - 0.1) <= 0.001);
    
        // Arrange
        class FractionTestCase {
            FractionPrecise fraction;
            double expectedResult;
        
            public FractionTestCase (FractionPrecise fraction, double expectedResult)
            {
                this.fraction = fraction;
                this.expectedResult = expectedResult;
            }
        }
    
        List<FractionTestCase> testCases = new ArrayList<>();
    
        // Trivial case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 1),
                1.0
        ));
    
        // Numerator is the biggest number
        testCases.add(new FractionTestCase(
                new FractionPrecise(50, 2),
                25.0
        ));
    
        // Denominator is the biggest number
        testCases.add(new FractionTestCase(
                new FractionPrecise(2, 50),
                2.0/50
        ));
    
    
        // Very large numbers
        testCases.add(new FractionTestCase(
                new FractionPrecise(new BigInteger("1000111222333444555666777888999000111222333444555666777888999") , new BigInteger("1000111222333444555666777888999000111222333444555666777888999")),
                1.0
        ));
    
        // Numerator is negative
        testCases.add(new FractionTestCase(
                new FractionPrecise(-20, 10),
                -20.0/10
        ));
    
        // Denominator is negative
        testCases.add(new FractionTestCase(
                new FractionPrecise(20, -10),
                20.0/-10
        ));
    
        // Both are negative
        testCases.add(new FractionTestCase(
                new FractionPrecise(-20, -10),
                -20.0/-10
        ));
        
        for (FractionTestCase testcase : testCases) {
            // Act
            double actualResult = testcase.fraction.toDouble();
            // Assert
            assertTrue(Math.abs(actualResult - testcase.expectedResult) <= 0.001);
        }
    }
    
    @Test
    void add ()
    {
        // Arrange
        class FractionTestCase {
            FractionPrecise fraction;
            FractionPrecise fractionToAdd;
            FractionPrecise expectedResult;
        
            public FractionTestCase (FractionPrecise fraction, FractionPrecise fractionToAdd, FractionPrecise expectedResult)
            {
                this.fraction = fraction;
                this.fractionToAdd = fractionToAdd;
                this.expectedResult = expectedResult;
            }
        }
    
        List<FractionTestCase> testCases = new ArrayList<>();
    
        // Trivial case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 4),
                new FractionPrecise(1, 4),
                new FractionPrecise(2, 4)
        ));
    
    
    
        for (FractionTestCase testcase : testCases) {
            // Act
            FractionPrecise fractionResult = testcase.fraction.add(testcase.fractionToAdd);
            BigInteger bigIntegerResult = fractionResult.numerator.divide(fractionResult.denominator);
            double actualResult = bigIntegerResult.doubleValue();
            // Assert
            assertEquals(testcase.expectedResult, fractionResult);
        }
    }
    
    @Test
    void compareTo ()
    {
        // Arrange
        class FractionTestCase {
            FractionPrecise fraction;
            FractionPrecise fractionToCompare;
            int expectedResult;
        
            public FractionTestCase (FractionPrecise fraction, FractionPrecise fractionToCompare, int expectedResult)
            {
                this.fraction = fraction;
                this.fractionToCompare = fractionToCompare;
                this.expectedResult = expectedResult;
            }
        }
    
        List<FractionTestCase> testCases = new ArrayList<>();
    
        // Equal - Trivial case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 1),
                new FractionPrecise(1, 1),
                Double.compare(1.0/1.0, 1.0/1.0)
        ));
    
        // Not equal - Trivial case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 2),
                new FractionPrecise(1, 1),
                Double.compare(1.0/2.0, 1.0/1.0)
        ));
    
        // Equal - Large numbers
        testCases.add(new FractionTestCase(
                new FractionPrecise(new BigInteger("1000111222333444555666777888999000111222333444555666777888999"), new BigInteger("1000111222333444555666777888999000111222333444555666777888999")),
                new FractionPrecise(new BigInteger("1000111222333444555666777888999000111222333444555666777888999") , new BigInteger("1000111222333444555666777888999000111222333444555666777888999") ),
                Double.compare(new BigInteger("1000111222333444555666777888999000111222333444555666777888999").doubleValue(), new BigInteger(
                        "1000111222333444555666777888999000111222333444555666777888999").doubleValue())
        ));
    
        // Not equal - Large numbers
        testCases.add(new FractionTestCase(
                new FractionPrecise(new BigInteger("1000111222333444555666777888999000111222333444555666777888999"), new BigInteger("1000111222333444555666777888999000111222333444555666777888999")),
                new FractionPrecise(new BigInteger("1000111222333444555666777888999000111222333444555666777888999") , new BigInteger(
                        "1000111222333444555666777888999000111222333444555666777888990") ),
                Double.compare(new BigInteger("1000111222333444555666777888999000111222333444555666777888999").doubleValue(), new BigInteger(
                        "1000111222333444555666777888999000111222333444555666777888990").doubleValue())
        ));
    
    
    
        //numerator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-1, 4),
                new FractionPrecise(-1, 4),
                Double.compare(-1.0/4.0, -1.0/4.0)
        ));
    
        //denominator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, -4),
                new FractionPrecise(1, -4),
                Double.compare(1.0/-4.0, 1.0/-4.0)
        ));
    
        // negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-1, -4),
                new FractionPrecise(-1, -4),
                Double.compare(-41.0/-4.0, -1.0/-4.0)
        ));
    
        // same but with non integer scalars determinators.
    
        // positive trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 4),
                new FractionPrecise(1, 3),
                Double.compare(1.0/4.0, 1.0/3.0)
        ));
    
        //numerator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-1, 4),
                new FractionPrecise(-1, 5),
                Double.compare(-1.0/4.0, -1.0/5.0)
        ));
    
        //denominator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, -10),
                new FractionPrecise(1, -4),
                Double.compare(1.0/-10.0, 1.0/-4.0)
        ));
    
        // negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-1, -4),
                new FractionPrecise(-1, -15),
                Double.compare(-1.0/-4.0, -1.0/-15.0)
        ));
    
        // same but with integer scalars determinators, but still false
    
        // positive trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 4),
                new FractionPrecise(3, 8),
                Double.compare(1.0/4.0, 3.0/8.0)
        ));
    
        //denominator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, -2),
                new FractionPrecise(5, -4),
                Double.compare(1.0/-2.0, 5.0/-4.0)
        ));
    //DSADASDASDASDASDJIOASDJIOASD
        // negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-1, -4),
                new FractionPrecise(-1, -20),
                Double.compare(-1.0/-4.0, -1.0/-20.0)
        ));
    
        // same but with different denominators, but same fraction, should return true
    
        // positive trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, 4),
                new FractionPrecise(3, 12),
                Double.compare(1.0/4.0, 3.0/12.0)
        ));
    
        //numerator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-4, 16),
                new FractionPrecise(-1, 4),
                Double.compare(-4.0/16.0, -1.0/4.0)
        ));
    // jioajioawefDASIOJASDJIOASDJIOASDASDJIOASDJIOASDJIOASDJIOASDJIOASDJIOASDJIOASDJIOASDJIOASDJIOASD
        //denominator negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(1, -4),
                new FractionPrecise(15, -60),
                Double.compare(1.0/-4.0, 15.0/-60.0)
        ));
    
        // negative trivial test case
        testCases.add(new FractionTestCase(
                new FractionPrecise(-7, -28),
                new FractionPrecise(-1, -4),
                Double.compare(-7.0/-28.0, -1.0/-4.0)
        ));
        
    
        for (FractionTestCase testcase : testCases) {
            // Act
            int actualResult = testcase.fraction.compareTo(testcase.fractionToCompare);
            // Assert
            assertEquals(testcase.expectedResult, actualResult);
        }
    }
    
    @Test
    void cloneTest ()
    {
    }
}