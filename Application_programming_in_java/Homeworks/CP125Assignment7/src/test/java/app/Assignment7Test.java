package app;

import com.scg.persistent.initDb;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class Assignment7Test {

    @Test
    public void mainTest() {
        String[] args = {"one", "two", "three"};
        initDb.main(args);
        Assignment7.main(args);
    }
}