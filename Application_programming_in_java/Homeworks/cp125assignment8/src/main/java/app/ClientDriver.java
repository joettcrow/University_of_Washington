package app;

import edu.uweo.java2.assignment8.*;
import edu.uweo.java2.assignment8.client.Client;

import java.io.IOException;
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

    public static void main(String[] args) {
        Client client = new Client(4885);
//        Server server = new Server(4885);



        while ( true )
        {
            try
            {
                Thread thread = new Thread(new Server(4885));
                thread.start();
//                server.execute();
                client.execute(addCommand);
                pause( 125 );
//                client.execute(subCommand);
//                pause( 125 );
//                client.execute(mulCommand);
//                pause( 125 );
//                client.execute(divCommand);
//                pause( 125 );
            }
            catch ( IOException exc )
            {
                exc.printStackTrace();
                System.exit( 1 );
            }
        }
    }

    private static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
        }
    }



}
