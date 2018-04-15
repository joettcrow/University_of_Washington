package cp120.assignments.assignment003;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class NumbersTest {

    @Test
    public void testIsPrimeNormal(){
        assertTrue(Numbers.isPrime(7));
        assertFalse(Numbers.isPrime(4));
    }

    @Test
    public void testIsPrimeOneZero(){
        assertFalse(Numbers.isPrime(1));
        assertFalse(Numbers.isPrime(0));
    }

    @Test
    public void testLcmZero(){
        int param1 = 0;
        int param2 = 5;
        int leastMultiple = 0;
        int lcm = Numbers.lcm(param1, param2);
        assertEquals(lcm, leastMultiple);
    }

    @Test
    public void testLcmMultiply(){
        int param1 = 7;
        int param2 = 29;
        int leastMultiple = 203;
        int lcm = Numbers.lcm(param1, param2);
        assertEquals(lcm, leastMultiple);
    }

    @Test
    public void testLcmOnes(){
        int param1 = 1;
        int param2 = 1;
        int leastMultiple = 1;
        int lcm = Numbers.lcm(param1, param2);
        assertEquals(lcm, leastMultiple);
    }

    @Test
    public void testLcmSubset(){
        int param1 = 9;
        int param2 = 18;
        int leastMultiple = 18;
        int lcm = Numbers.lcm(param1, param2);
        assertEquals(lcm, leastMultiple);
    }

    @Test
    public void testGcfZero(){
        int param1 = 0;
        int param2 = 16;
        int greatestFactor = 0;
        int gcf = Numbers.gcf(param1,param2);
        assertEquals(gcf, greatestFactor);
    }

    @Test
    public void testGcfFullSubset(){
        int param1 = 8;
        int param2 = 16;
        int greatestFactor = 8;
        int gcf = Numbers.gcf(param1,param2);
        assertEquals(gcf, greatestFactor);
    }

    @Test
    public void testGcfFullOne(){
        int param1 = 7;
        int param2 = 9;
        int greatestFactor = 1;
        int gcf = Numbers.gcf(param1,param2);
        assertEquals(gcf, greatestFactor);
    }

    @Test
    public void testDigitSumSingle(){
        int num = 7;
        assertEquals(Numbers.digitSum(num), num);
    }

    @Test
    public void testDigitSumLarge(){
        int num = 123456789;
        int sum = 45;
        assertEquals(Numbers.digitSum(num), sum);
    }

    @Test
    public void testMeanSame(){
        double[] arr = { 1, 1, 1 };
        double mean = 1;
        assertEquals(Numbers.mean(arr), mean, 0.0);
    }

    @Test
    public void testMeanZero(){
        double[] arr = {-999999, 999999};
        double mean = 0;
        assertEquals(Numbers.mean(arr), mean, 0.0);
    }

    @Test
    public void testMeanNormal(){
        double[] arr = {3,4,5};
        double mean = 4;
        assertEquals(Numbers.mean(arr), mean, 0.0);
    }

    @Test
    public void testMedianSingle(){
        double[] arr = {99};
        double median = 99;
        assertEquals(Numbers.median(arr), median, 0.0);
    }

    @Test
    public void testMedianAvgMiddle(){
        double[] arr = {1,1,2,2};
        double median = 1.5;
        assertEquals(Numbers.median(arr), median, 0.0);
    }

    @Test
    public void testMedianMiddle(){
        double[] arr = {1,2,3};
        double median = 2;
        assertEquals(Numbers.median(arr), median, 0.0);
    }

    @Test
    public void testFactorialIntZero(){
        int num = 0;
        BigInteger fact = BigInteger.ONE;
        assertEquals(fact, Numbers.factorial(num));
    }

    @Test
    public void testFactorialIntNormal(){
        int num = 5;
        BigInteger fact = BigInteger.valueOf(120);
        assertEquals(fact, Numbers.factorial(num));
    }

    @Test
    public void testFactorialBigZero(){
        BigInteger num = BigInteger.ZERO;
        BigInteger fact = BigInteger.ONE;
        assertEquals(fact, Numbers.factorial(num));
    }

    @Test
    public void testFactorialBigNormal(){
        BigInteger num = BigInteger.valueOf(5);
        BigInteger fact = BigInteger.valueOf(120);
        assertEquals(fact, Numbers.factorial(num));
    }

    @Test
    public void testFactorialBigHuge(){
        BigInteger num = BigInteger.valueOf(12);
        BigInteger fact = BigInteger.valueOf(479001600);
        assertEquals(fact, Numbers.factorial(num));
    }


}