package cp120.class5;

/**
 * @author jcrowley
 */

public class TripleDemo {

    public static void main(String[] args) {
        int num1 = 47;
        int num2 = 99;
        num1 ^= num2;
        num2 ^= num1;
        num1 ^=num2;

        System.out.println(num1 + ", " + num2);
    }
}
