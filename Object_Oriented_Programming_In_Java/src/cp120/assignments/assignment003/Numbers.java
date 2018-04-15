package cp120.assignments.assignment003;

import java.lang.reflect.Array;
import java.math.BigInteger;
import  java.util.*;

/**
 * Collection of math functions
 *
 * @author jcrowley
 */
public class Numbers {
    /**
     * Calculates if the number provided is prime and returns true or false
     * @param num Non-negative integer number
     * @return  True if number is prime, false if number is not prime
     */
    public static boolean isPrime(int num){
        if (num == 0 || num == 1){
            return false;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Finds lowest common multiple for two numbers
     * @param param1 First non-negative integer number
     * @param param2 Second non-negative integer number
     * @return The lowest common multiple integer of the provided numbers
     */
    public static int lcm( int param1, int param2 ){
        if (param1 == 0 || param2 == 0){
            return 0;
        }
        for (int i = 1; i < param2; i++) {
            if ((param1 * i) % param2 == 0){
                return param1 * i;
            }
        }
        return param1 * param2;
    }

    /**
     * Finds the greatest common factor of two numbers
     * @param param1 First non-negative integer number
     * @param param2 Second non-negative integer number
     * @return The greatest common factor integer of the provided numbers
     */
    public static int gcf( int param1, int param2 ){
        if (param1 == 0 || param2 == 0){
            return 0;
        }
        for (int i = param2; i > 1; i--){
            if ((param1 % i == 0) && (param2 % i == 0)) {
                return i;
            }
        }
        return 1;
    }

    /**
     * Sums together all the digits in a number
     * @param num Integer number with digits to sum
     * @return Integer number that is the sume of digits
     */
    public static int digitSum(int num){
        int x = 0;
        for (String i : String.valueOf(num).split("(?!^)")) {
            x = x + Integer.parseInt(i);
        }
        return x;
    }

    /**
     * Calculates the mean of an array of numbers
     * @param arr Array of double values to find the mean of
     * @return The mean as a double value
     */
    public static double mean(double[] arr){
        double x = 0;
        int count = 0;
        for (double i: arr) {
            x += i;
            count +=1;
        }
        return x/count;
    }

    /**
     * Finds the median for a given array
     * @param arr Arrayed list of double we want the median of
     * @return The median value as a double
     */
    public static double median(double[] arr){
        Arrays.sort(arr);
        double intermediate1 = Array.getLength(arr) + 1;
        double intermediate2 = intermediate1/2;
        double aboveMiddle = Math.ceil(intermediate2);
        double belowMiddle = Math.floor(intermediate2);
        double medianSum = arr[(int) (aboveMiddle-1)] + arr[(int) (belowMiddle-1)];
        return medianSum/2;
    }

    /**
     * Calculates the factorial for an integer
     * @param num Integer to calculate factorial for
     * @return The factorial as a big integer
     */
    public static BigInteger factorial( int num ){
        if (num == 0){
            return BigInteger.valueOf(1);
        }
        BigInteger x = BigInteger.valueOf(1);
        for (int i = 1; i <= num; i++) {
            x = x.multiply(BigInteger.valueOf(i));
        }
        return x;
    }

    /**
     * Calculates the factorial for a big integer
     * @param num Big integer to calculate factorial for
     * @return The factorial as a big integer
     */
    public static BigInteger factorial( BigInteger num ){
        if (num.equals(BigInteger.ZERO)){
            return BigInteger.valueOf(1);
        }
        BigInteger x = BigInteger.ONE;
        while (num.compareTo(BigInteger.ZERO) > 0){
            x = x.multiply(num);
            num = num.subtract(BigInteger.ONE);
        }
        return x;
    }


}
