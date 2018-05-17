package demo;

import static org.junit.Assert.*;
import static util.DBConstants.DB_URL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import util.Address;
import util.CleanPlayground;
import util.Name;

public class CreditCardTableTest
{
    private final Address   address     =
        new Address( "725 Fifth Ave", "#1", "New York", "NY", "10022" );
    private final Name        name       = new Name( "Khan", "Genghis" );
    private final Customer    customer   = new Customer( name, address );
    
    private final Address   addr1   = 
        new Address( "111", "TTT1", "CCC1", "SSS1", "11111" );
    private final Address   addr2   = 
        new Address( "222", "TTT2", "CCC2", "SSS2", "22222" );
    private final Address   addr3   = 
        new Address( "333", "TTT3", "CCC3", "SSS3", "33333" );
    
    private final LocalDate baseDate    = LocalDate.of( 2016, 11, 23 );
    
    private final CreditCard[]  creditCards =
    {
        new CreditCard( "A", addr1, "11111", "111", baseDate.plusDays( 1 ) ),
        new CreditCard( "B", addr2, "22222", "222", baseDate.plusDays( 2 ) ),
        new CreditCard( "C", addr3, "33333", "333", baseDate.plusDays( 3 ) ),
    };
    
    @Before
    public void setUp() throws Exception
    {
        CleanPlayground.main( null );
        CustomerTable   table   = CustomerTable.getInstance( "", "", DB_URL );
        table.insert( customer );
    }

    @Test
    public void test() throws SQLException
    {
        CreditCardTable table   = CreditCardTable.getInstance("", "", DB_URL );
        Arrays.stream( creditCards ).forEach( customer::addCreditCard );
        table.insert( customer );
        
        List<CreditCard>    exp     = 
            Arrays.stream( creditCards ).collect( Collectors.toList() );
        List<CreditCard>    act     = table.selectAll();
        assertEquals( exp.size(), act.size() );
        for ( CreditCard card : act )
            assertTrue( exp.contains( card ) );
    }
}
