package cp120.class4;

/**
 * @author jcrowley
 */

public class Demo3 {

    public static void main(String[] args) {
        System.out.println("Zebra".compareTo("Aardvark"));
        System.out.println("Zulu".compareTo("anteater"));
        System.out.println("market".compareTo("Market"));
        System.out.println("200".compareTo("30"));
        System.out.println("100".compareTo("-100"));
        String str = "something goes here";
        System.out.println(str.substring(4,5));
    }
}
