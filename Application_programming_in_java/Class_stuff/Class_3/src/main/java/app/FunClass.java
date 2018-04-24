package app;

import java.io.File;

/**
 * @author jcrowley
 */

public class FunClass {
    public static void main(String[] args) {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");

        File file = new File("treehouse");
        System.out.println(file.exists());
    }
}
