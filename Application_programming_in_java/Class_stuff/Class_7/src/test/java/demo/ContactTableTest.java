package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.Contact;
import util.Name;

public class ContactTableTest
{
    private static final Contact[]   contacts    =
    {
        new Contact( "Smith", "John", "1111111", "partyAnimal" ),
        new Contact( "Jones", "Jessica", "2222222", "deepThinker" ),
        new Contact( "Jackson", "Janet", "3333333", "litigator" ),
        new Contact( "Kelly", "Gene", "4444444", "prancer" ),
    };
    
    @Before
    public void setUp() throws Exception
    {
        ContactTable   table    = new ContactTable();
        table.truncateTable();
    }

    @Test
    public void testInsert() throws SQLException
    {
        List<Contact>   expList = new ArrayList<>();
        ContactTable   table    = new ContactTable();
        for ( Contact contact : contacts )
        {
            table.insertContact( contact );
            expList.add( contact );
        }
        
        List<Contact>   actList = table.getAllContacts();
        assertEquals( expList.size(), actList.size() );
        for ( Contact contact : actList )
            assertTrue( expList.contains( contact ) );
    }

    @Test
    public void testGetAllNames() throws SQLException
    {
        List<Name>      expList = new ArrayList<>();
        ContactTable   table    = new ContactTable();
        for ( Contact contact : contacts )
        {
            table.insertContact( contact );
            String  last    = contact.getLastName();
            String  first   = contact.getFirstName();
            Name    name    = new Name( first, last );
            expList.add( name );
        }
        
        List<Name>  actList = table.getAllNames();
        assertEquals( expList.size(), actList.size() );
        for ( Name act : actList )
            assertTrue( expList.contains( act ) );
    }

    @Test
    public void testGetByPhoneNumber() throws SQLException
    {
        List<Contact>   expList = new ArrayList<>();
        ContactTable   table    = new ContactTable();
        for ( Contact contact : contacts )
        {
            table.insertContact( contact );
            expList.add( contact );
        }
        
        for ( Contact contact : expList )
        {
            String          phone   = contact.getPhone();
            List<Contact>   actList = table.getByPhoneNumber( phone );
            assertEquals( 1, actList.size() );
            Contact         act     = actList.get( 0 );
            System.out.println( actList.get( 0 ) );
            assertTrue( expList.contains( act ) );
        }
    }
}
