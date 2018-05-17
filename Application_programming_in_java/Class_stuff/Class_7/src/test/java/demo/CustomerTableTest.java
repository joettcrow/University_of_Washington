package demo;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.Address;
import util.CleanPlayground;
import util.DBConstants;
import util.Name;

public class CustomerTableTest
{
    private static final String DB_URL  = DBConstants.DB_URL;
    private final Address[]   addresses   =
    {
        new Address(
            "1313 Mockingbird Ln",
            "#2B",
            "London",
            "AL",
            "11111"
        ),
        new Address(
            "4200 Yellowbrick Rd",
            null,
            "Fantasy",
            "CA",
            "22222"
        ),
        new Address(
            "118 Washington Ave.",
            null,
            "Patchogue",
            "NY",
            "33333"
        )
    };

    @Before
    public void setUp() throws Exception
    {
        CleanPlayground.main( null );
    }

    @Test
    public void testInsert() throws SQLException
    {
        CustomerTable table = CustomerTable.getInstance("", "", DB_URL );
        List<Customer>  list    = new ArrayList<>();
        for ( int inx = 0 ; inx < addresses.length ; ++inx )
        {
            Name    name    = 
                new Name( "" + inx, "" + (inx + 1), "" + (inx + 2) );
            list.add( new Customer( name, addresses[inx] ) );
        }
        
        for ( Customer customer : list )
            table.insert( customer );
        
        List<Customer>  actuals = table.selectAll();
        assertEquals( list.size(), actuals.size() );
        for ( Customer customer : actuals )
            assertTrue( list.contains( customer ) );
    }
    
    @Test
    public void testSelectStringString()
        throws SQLException
    {
        CustomerTable   table   = CustomerTable.getInstance( "", "", DB_URL );
        List<Customer>  list    = new ArrayList<>();
        for ( int inx = 0 ; inx < addresses.length ; ++inx )
        {
            Name    name    = 
                new Name( "" + inx, "" + (inx + 1), "" + (inx + 2) );
            list.add( new Customer( name, addresses[inx] ) );
        }
        
        for ( Customer customer : list )
            table.insert( customer );
        
        for ( Customer customer : list )
        {
            Name            name    = customer.getName();
            List<Customer>  actuals = table.select( name.last, name.first );
            assertEquals( 1, actuals.size() );
            assertTrue( list.contains( actuals.get( 0 ) ) );
        }
       
        Customer    customer    = list.get( 0 );
        Name        name        = customer.getName();
        table.insert( customer ); // One customer is in there twice
        List<Customer>  actuals = table.select( name.last, name.first );
        
        assertEquals( 2, actuals.size() );
        assertTrue( actuals.contains( customer ) );
    }
    
    @Test
    public void testWithCCList() throws SQLException
    {
        int             ccNum   = 100000000;
        int             cVV     = 100;
        char            ccName  = 'A';
        LocalDate       date    = LocalDate.of( 2016,  5,  22 );
        CustomerTable   table   = CustomerTable.getInstance( "", "", DB_URL );
        List<Customer>  list    = new ArrayList<>();
        for ( int inx = 0 ; inx < addresses.length ; ++inx )
        {
            Name    name    = 
                new Name( "" + inx, "" + (inx + 1), "" + (inx + 2) );
            Address addr    = addresses[inx];
            Customer    customer    = new Customer( name, addr );
            String      sNum        = "" + ccNum++;
            String      sCVV        = "" + cVV++;
            String      sName       = "" + ccName++;
            CreditCard  card        =
                new CreditCard( sName, addr, sNum, sCVV, date );
            
            customer.addCreditCard( card );
            customer.addCreditCard( card );
            list.add( customer );
            table.insert( customer );
        }
        
        List<Customer>  actuals = table.selectAll();
        assertEquals( list.size(), actuals.size() );
        for ( Customer actual : actuals )
            assertTrue( list.contains( actual ) );
    }
}
