package demo;

import java.sql.SQLException;

import util.Contact;

public class SimpleQuery2
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
            System.out.println( getContact( mgr, "Kelly", "Gene" ) );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }

    private static Contact getContact( ContactTable mgr, String last, String first )
        throws SQLException
    {
        Contact contact = mgr.getContact( last, first );
        return contact;
    }
}
