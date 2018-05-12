package app;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class Assignment05Test {

    @Test
    public void mainTest() {
        try {
            InitLists.main(null);
            Assignment05.main(null);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}