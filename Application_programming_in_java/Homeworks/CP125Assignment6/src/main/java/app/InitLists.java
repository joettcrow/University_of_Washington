package app;

import com.scg.domain.ClientAccount;
import com.scg.domain.TimeCard;

import java.io.*;
import java.util.List;

import javax.swing.JFileChooser;

/**
 * This application class will generate a list of client accounts
 * and a list of time cards.
 * The lists must then be serialized to ClientList.ser and TimeCardList.ser,
 * respectively.
 * @author jcrowley
 */
public class InitLists implements Serializable{

    private static void serializeTimeCards(List<TimeCard> timeCardList){
        try ( FileOutputStream fStream = new FileOutputStream( "TimeCardList.ser" );
              ObjectOutputStream oStream = new ObjectOutputStream( fStream )
        )
        {
            oStream.writeObject( timeCardList );
            oStream.close();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }

    private static void serializeClientLIst(List<ClientAccount> clientList){
        try ( FileOutputStream fStream = new FileOutputStream( "ClientList.ser" );
              ObjectOutputStream oStream = new ObjectOutputStream( fStream )
        )
        {
            oStream.writeObject( clientList );
            oStream.close();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }

    /**
     * Main method for the serializer
     * Serializes the ClientAccounts and TImecards
     * @param args args to pass
     * @throws FileNotFoundException if the files are not found
     * @throws IOException if the files are not writtable
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SCGDriver driver = new SCGDriver();
        List<TimeCard> timeCardList = driver.getTimeCards();
        List<ClientAccount> clientList = driver.getClientAccounts();

        serializeTimeCards(timeCardList);
        serializeClientLIst(clientList);
    }
}
