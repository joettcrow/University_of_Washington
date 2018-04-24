package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jcrowley
 */

public class ReadFromConsole {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = null;
        while ( !testEnd(line = bufferedReader.readLine())){
            StringTokenizer tizer = new StringTokenizer(line);
            while (tizer.hasMoreTokens()){
                System.out.println(tizer.nextToken());
            }

        }
    }

    protected static boolean testEnd(String line ){
        String temp = line.toUpperCase();
        boolean rval = temp.equals("END");
        return rval;
    }
}
