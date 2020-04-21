import msharp.NotePopulation.Fraction;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTests {

    // basically we just need to test that
    // fraction.add works
    // and the constructor taking 2 fractions as input works.
    // assert that overflow is thrown in both of these functions.
    // assert that the equals function works, since the other tests wont work right then.

    @Test
    public void FractionEqualsTest(){
        class TestCase{
            Fraction a, b;
            boolean result;

            public TestCase(Fraction a, Fraction b, boolean result) {
                this.a = a;
                this.b = b;
                this.result = result;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        // positive trivial test case
        testCases.add(new TestCase(
                new Fraction(1,4),
                new Fraction(1,4),
                true
        ));

        //numerator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,4),
                new Fraction(-1,4),
                true
        ));

        //denominator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(1,-4),
                new Fraction(1,-4),
                true
        ));

        // negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,-4),
                new Fraction(-1,-4),
                true
        ));

        // same but with non integer scalars determinators.

        // positive trivial test case
        testCases.add(new TestCase(
                new Fraction(1,4),
                new Fraction(1,3),
                false
        ));

        //numerator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,4),
                new Fraction(-1,5),
                false
        ));

        //denominator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(1,-10),
                new Fraction(1,-4),
                false
        ));

        // negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,-4),
                new Fraction(-1,-15),
                false
        ));

        // same but with integer scalars determinators, but still false

        // positive trivial test case
        testCases.add(new TestCase(
                new Fraction(1,4),
                new Fraction(3,8),
                false
        ));

        //numerator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,4),
                new Fraction(-3,16),
                false
        ));

        //denominator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(1,-2),
                new Fraction(5,-4),
                false
        ));

        // negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-1,-4),
                new Fraction(-1,-20),
                false
        ));

        // same but with different denominators, but same fraction, should return true

        // positive trivial test case
        testCases.add(new TestCase(
                new Fraction(1,4),
                new Fraction(3,12),
                true
        ));

        //numerator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-4,16),
                new Fraction(-1,4),
                true
        ));

        //denominator negative trivial test case
        testCases.add(new TestCase(
                new Fraction(1,-4),
                new Fraction(15,-60),
                true
        ));

        // negative trivial test case
        testCases.add(new TestCase(
                new Fraction(-7,-28),
                new Fraction(-1,-4),
                true
        ));



        for(TestCase testCase : testCases){
            //Act
            boolean result = testCase.a.equals(testCase.b);

            //Assert
            assertEquals(result, testCase.result);
        }

    }

    @Test
    public void FractionAddTest(){
        // Arrange
        class FractionTestCase{
            Fraction fractionToAddTo, fractionToAdd, fractionResult;

            public FractionTestCase(Fraction fractionToAddTo, Fraction fractionToAdd, Fraction fractionResult) {
                this.fractionToAddTo = fractionToAddTo;
                this.fractionToAdd = fractionToAdd;
                this.fractionResult = fractionResult;
            }
        }

        List<FractionTestCase> testCases = new ArrayList<>();

        // trivial test case
        testCases.add(new FractionTestCase(
                new Fraction(1,4),
                new Fraction(1,4),
                new Fraction(2,4)
        ));

        // test case where we need to change denominator
        testCases.add(new FractionTestCase(
                new Fraction(1,3),
                new Fraction(1,8),
                new Fraction(11,24)
        ));

        // negative test case
        testCases.add(new FractionTestCase(
                new Fraction(-4,3),
                new Fraction(1,8),
                new Fraction(-29,24)
        ));

        // tests 0.1 decimal, as that cant be represented without fractions as double.
        testCases.add(new FractionTestCase(
                new Fraction(1,20),
                new Fraction(1,20),
                new Fraction(1,10)
        ));

        for(FractionTestCase testCase : testCases){
            //Act
            testCase.fractionToAddTo.add(testCase.fractionToAdd);

            //Assert
            assertEquals(testCase.fractionToAddTo, testCase.fractionResult);
        }
    }

    @Test
    public void FractionAddOverflowIsThrownDenominator(){
        // we need to make sure that we never have a overflow without noticing it.
        // Arrange
        Fraction a = new Fraction(1,Long.MAX_VALUE);
        Fraction b = new Fraction(1, Long.MAX_VALUE-1);

        // Act&Assert
        assertThrows(ArithmeticException.class, () -> a.add(b));
    }
    @Test
    public void FractionAddOverflowIsThrownNumerator(){
        // we need to make sure that we never have a overflow without noticing it.
        // Arrange
        Fraction a = new Fraction(Long.MAX_VALUE,1);
        Fraction b = new Fraction(Long.MAX_VALUE,1);

        // Act&Assert
        assertThrows(ArithmeticException.class, () -> a.add(b));
    }

    @Test
    public void FractionFractionsInConstructor(){
        // Arrange
        class FractionTestCase{
            Fraction numerator, denominator, result;

            public FractionTestCase(Fraction numerator, Fraction denominator, Fraction result) {
                this.numerator = numerator;
                this.denominator = denominator;
                this.result = result;
            }
        }

        List<FractionTestCase> testCases = new ArrayList<>();

        // same test cases (without the 0.1 test) as in add test, with different results though.

        // trivial test case
        testCases.add(new FractionTestCase(
                new Fraction(1,4),
                new Fraction(1,4),
                new Fraction(1,1)
        ));

        // test case where we need to change denominator
        testCases.add(new FractionTestCase(
                new Fraction(1,3),
                new Fraction(1,8),
                new Fraction(8,3)
        ));

        // negative test case
        testCases.add(new FractionTestCase(
                new Fraction(-4,3),
                new Fraction(1,8),
                new Fraction(-32,3)
        ));





        for(FractionTestCase testCase : testCases){
            //Act
            Fraction result = new Fraction(testCase.numerator,testCase.denominator);

            //Assert
            assertEquals(result, testCase.result);
        }
    }

    @Test
    public void FractionFractionsInConstructorOverflowIsThrownDenominator(){
        // we need to make sure that we never have a overflow without noticing it.
        // Arrange
        Fraction a = new Fraction(1,Long.MAX_VALUE);
        Fraction b = new Fraction(Long.MAX_VALUE, 1);

        // Act&Assert
        assertThrows(ArithmeticException.class, () -> new Fraction(a,b));
    }
    @Test
    public void FractionFractionsInConstructorOverflowIsThrownNumerator(){
        // we need to make sure that we never have a overflow without noticing it.
        // Arrange
        Fraction a = new Fraction(Long.MAX_VALUE,1);
        Fraction b = new Fraction(1,Long.MAX_VALUE);

        // Act&Assert
        assertThrows(ArithmeticException.class, () -> new Fraction(a,b));
    }
}
