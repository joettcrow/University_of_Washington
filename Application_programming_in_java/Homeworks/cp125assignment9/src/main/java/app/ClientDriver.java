package app;

import edu.uweo.java2.assignment9.*;
import edu.uweo.java2.assignment9.client.Client;

import java.math.BigDecimal;

/**
 * This application will use the Client class to send four commands to the server:
 * @author jcrowley
 */
public class ClientDriver {
    private final static BigDecimal add1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal add2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal sub1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal sub2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal mul1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal mul2 = BigDecimal.valueOf(2.0);
    private final static BigDecimal div1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal div2 = BigDecimal.valueOf(3.0);
    private final static AddCommand addCommand = new AddCommand(add1,add2);
    private final static SubCommand subCommand = new SubCommand(sub1,sub2);
    private final static MulCommand mulCommand = new MulCommand(mul1,mul2);
    private final static DivCommand divCommand = new DivCommand(div1,div2);

    /**
     * Main method for the driver
     * @param args can be empty
     */
    public static void main(String[] args) {
        Client client = new Client(4885);
        client.execute(addCommand);
        client.execute(subCommand);
        client.execute(mulCommand);
        client.execute(divCommand);
    }
}
