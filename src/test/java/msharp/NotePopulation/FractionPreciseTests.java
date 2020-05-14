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
}