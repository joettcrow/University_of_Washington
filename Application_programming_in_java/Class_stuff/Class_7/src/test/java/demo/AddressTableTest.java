package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.DBConstants.DB_URL;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.Address;
import util.CleanPlayground;

public class AddressTableTest
{
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
        AddressTable    table   = 
            AddressTable.getInstance( "", "", DB_URL );
        for ( Address address : addresses )
            table.insert( address );
        
        List<Address>   actuals = table.getAll();
        assertEquals( addresses.length, actuals.size() );
        for ( Address addr : addresses )
            assertTrue( actuals.contains( addr ) );
        
        Address address = actuals.get( 0 );
        address.city = "Tangiers";
        table.update( address );
        
        actuals = table.getAll();
        assertTrue( actuals.contains( address ) );
    }
}
