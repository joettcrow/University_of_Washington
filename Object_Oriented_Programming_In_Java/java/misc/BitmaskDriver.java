package misc;
import cp120.assignments.assignment002.BitMasks;

/***
*
* Demonstration driver for assignment 2, BitMasks.
*
* @author Jack
*/


public class BitmaskDriver {
    /***  Program entry point. Prints the values returned by calls
     * to BitMasks.decode().
     *
     * @param args not used
     * */
    public static void main( String[] args ){
        System.out.println( BitMasks.decode( 0b0101110110011010 ) );
        System.out.println( BitMasks.decode( 0b1100011101001011 ) );
        System.out.println( BitMasks.decode( 0b1001000101101101 ) );
        System.out.println( BitMasks.decode( 0b0111101111000110 ) );
    }
}

