package app;

import com.scg.domain.ClientAccount;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Runner for assignment 5.  It deserializes the created files
 * @author jcrowley
 */
public class Assignment05 {
    private static List<ClientAccount> readClientList() {
        List<ClientAccount> clientAccountList = new ArrayList<>();

        try(
                // Reading the object from a file
                InputStream file = new FileInputStream("ClientList.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInputStream in = new ObjectInputStream(buffer);
        )
        {
            // Method for deserialization of object
            clientAccountList = (List<ClientAccount>)in.readObject();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return clientAccountList;
    }

    private static List<TimeCard> readTimeCardList(){
        List<TimeCard> timeCardList = new ArrayList<>();

        try(
                // Reading the object from a file
                InputStream file = new FileInputStream("TimeCardList.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInputStream in = new ObjectInputStream(buffer);
        )
        {
            // Method for deserialization of object
            timeCardList = (List<TimeCard>) in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return timeCardList;
    }

    /**
     * Main method that deserializes TimeCardList.ser and ClientList.ser
     * Then creates invoices for each company.
     * @param args
     */
    public static void main(String[] args) {

        List<ClientAccount> clientAccounts = readClientList();
        List<TimeCard> timeCards = readTimeCardList();

        System.out.println( "          ********************" );
        System.out.println( "          **** Time Cards ****" );
        System.out.println( "          ********************" );
        for (TimeCard card : timeCards){
            System.out.println(card.toReportString());
        }


        System.out.println();
        System.out.println( "          ********************" );
        System.out.println( "          ***** Invoices *****" );
        System.out.println( "          ********************" );
        for (ClientAccount clientAccount: clientAccounts) {
            Invoice invoice = new Invoice(clientAccount, Month.APRIL, 2004);
            for (TimeCard timeCard : timeCards) {
                invoice.extractLineItems(timeCard);
            }
            System.out.println(invoice.toReportString());
        }
    }
}
