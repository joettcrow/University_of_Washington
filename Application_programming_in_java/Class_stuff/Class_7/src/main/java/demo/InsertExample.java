package demo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DBConstants;

public class InsertExample
{
    private static final Logger log =
        LoggerFactory.getLogger( InsertExample.class );
    
    public static void main( String[] args )
    {
        BigDecimal      amount  = new BigDecimal( 43.85 );
        LocalDateTime   time    = LocalDateTime.now();
        Transaction     trans   = new Transaction( "Dr. Smith", time, amount );
        
        TransactionTable    table   = 
            TransactionTable.getInstance( "", "", DBConstants.DB_URL );        
        try
        {
            log.info( "truncating transaction table" );
            table.truncateTable();

            log.info( "inserting transaction" );
            table.insert( trans );

            log.info( "insert complete" );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
}
