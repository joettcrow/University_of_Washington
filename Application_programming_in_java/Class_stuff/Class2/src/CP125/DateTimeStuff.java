package CP125;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jcrowley
 */

public class DateTimeStuff {
    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        StringBuilder bldr = new StringBuilder();
        DateTimeFormatter fmtr = DateTimeFormatter.ofPattern("uuu");
//        String
    }
}
