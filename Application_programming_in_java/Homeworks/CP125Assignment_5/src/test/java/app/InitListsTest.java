package app;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class InitListsTest {

    @Test
    public void mainTest() {
        try {
            InitLists.main(null);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}