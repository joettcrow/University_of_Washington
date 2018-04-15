package classtime.CP125;

/**
 * @author jcrowley
 */

public class Temp {
    public static void main(String[] args) {
        EnumDemo1 constants[] = EnumDemo1.values();
        for (EnumDemo1 constatnt: constants)
            System.out.println(constatnt);
        eatMe(EnumDemo1.ORANGE);
        EnumDemo1.BANANA.whoAreYou();

        System.out.println(EnumDemo1.ORANGE.getRating());
        System.out.println( EnumDemo1.APPLE.name());
    }

    public static void eatMe( EnumDemo1 val){
        if (val == EnumDemo1.ORANGE);
        if (val == EnumDemo1.BANANA);


    }
}
