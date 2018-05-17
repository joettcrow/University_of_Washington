package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DBConstants;

public class DeleteExample
{
    private static final Logger log     =
        LoggerFactory.getLogger( DeleteExample.class );
    private static final String DB_URL  = DBConstants.DB_URL;
    
    private final LocalDateTime baseTime        = LocalDateTime.now();
    private final LocalDateTime intervalStart   = baseTime.plusDays( 3 );
    private final LocalDateTime intervalEnd     = intervalStart.plusDays( 4 );
    private final LocalDateTime timeEnd         = intervalEnd.plusDays( 3 );
    
    private int                 mNextName       = 0;
    private final String[]      allNames        =
    {
        "Fred",
        "Wilma",
        "Barney",
        "Betty",
        "Manny",
        "Moe",
        "Maxine",
        "Ally",
        "Jared",
        "Confucius",
        "Juno",
        "Aphrodite"
    };
    
    public static void main( String[] args )
    {
        TransactionTable    table   = 
            TransactionTable.getInstance( "", "", DBConstants.DB_URL ); 
        DeleteExample       demo    = new DeleteExample();
        try
        {
            log.info( "truncating transaction table" );
            table.truncateTable();

            log.info( "populating transaction table" );
            demo.populate( table );

            log.info( "dumping initial transaction table" );
            demo.dump( table );
            
            LocalDate   start   = demo.intervalStart.toLocalDate();
            LocalDate   end     = demo.intervalEnd.toLocalDate();
            log.info( "deleting transactions BETWEEN {} AND {}", start, end );
            demo.execute();
            
            log.info( "dumping modified transaction table" );
            demo.dump( table );

            log.info( "demo complete" );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void execute() throws SQLException
    {
        try ( Connection conn = 
            DriverManager.getConnection( DB_URL, "", "" );
        )
        {
            execute( conn );
        }
    }
    
    private void execute( Connection conn )
        throws SQLException
    {
        final String sql   = 
            "DELETE FROM transactions "
            +    "WHERE date BETWEEN ? AND ?";
        Date    after   = Date.valueOf( intervalStart.toLocalDate() );
        Date    before  = Date.valueOf( intervalEnd.toLocalDate() );
        PreparedStatement   statement   = conn.prepareStatement( sql );
        statement.setDate( 1, after );
        statement.setDate( 2, before );
        statement.executeUpdate();
    }
    
    private void populate( TransactionTable table )
        throws SQLException
    {
        LocalDateTime   time    = baseTime;
        RoundingMode    mode    = RoundingMode.HALF_UP;
        
        while ( time.isBefore( timeEnd ) )
        {
            double      temp    = Math.random() * 100;
            BigDecimal  amount  = new BigDecimal( temp ).setScale( 2, mode );
            String      name    = allNames[mNextName++];
            Transaction trans   = new Transaction( name, time, amount );
            time = time.plusDays( 1 );
            table.insert( trans );
        }
    }
    
    private void dump( TransactionTable table )
        throws SQLException
    {
        List<Transaction>   list    = table.getAll();
        System.out.println( "***** Dump Transaction Table" );
        
        String  fmt = "%-10s %s %6s%n";
        for ( Transaction trans : list )
        {
            LocalDate   date    = trans.getTime().toLocalDate();
            String      name    = trans.getName();
            String      amount  = trans.getAmount().toString();
            System.out.printf( fmt, name, date, amount );
        }
        System.out.println( "****************************" );
    }
}
