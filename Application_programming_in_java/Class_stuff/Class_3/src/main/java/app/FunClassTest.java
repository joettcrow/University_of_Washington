package app;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class FunClassTest {

    @Test
    public void test() throws IOException{
        ByteArrayOutputStream baStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(baStream);
        PrintStream stdOut = System.out;

        System.setOut(writer);

        FunClass.main(null);
        writer.close();
        System.setOut(stdOut);

        System.out.println("*******");
        System.out.println(writer);

        byte[] arr = baStream.toByteArray();

        ByteArrayInputStream inStream = new ByteArrayInputStream(arr);
        InputStreamReader reader = new InputStreamReader(inStream);
        BufferedReader bReader = new BufferedReader(reader);

        String line = bReader.readLine();
        assertEquals("A", line);

        File file = new File("treehouse");
    }
}
