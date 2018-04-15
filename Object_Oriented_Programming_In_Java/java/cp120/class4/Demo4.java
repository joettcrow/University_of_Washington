package cp120.class4;

/**
 * @author jcrowley
 */

public class Demo4 {
    public static void main(String[] args) {
        try{
            int num = Integer.parseInt("100q");
            System.out.println(num);
        }
        catch ( NumberFormatException exc )
        {
            System.out.println("That was not an integer");
        }
    }
}
