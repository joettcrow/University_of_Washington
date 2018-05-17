package demo;

import java.sql.SQLException;
import java.util.List;

import util.Contact;

public class SimpleQuery1
{

    public static void main(String[] args)
    {
        Contact[]   contacts    =
        {
            new Contact( "Smith", "John", "1111111", "partyAnimal" ),
            new Contact( "Jones", "Jessica", "2222222", "deepThinker" ),
            new Contact( "Jackson", "Janet", "3333333", "litigator" ),
            new Contact( "Kelly", "Gene", "4444444", "prancer" ),
        };
        
        try
        {
            ContactTable   mgr = new ContactTable();
            mgr.truncateTable();
            for ( Contact contact : contacts )
                mgr.insertContact( contact );
            dumpContacts( mgr );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }

    private static void dumpContacts( ContactTable mgr )
        throws SQLException
    {
        List<Contact>   list    = mgr.getAllContacts();
        list.forEach( System.out::println );
    }
}
