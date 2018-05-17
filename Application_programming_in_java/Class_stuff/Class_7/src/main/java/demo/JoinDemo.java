package demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import util.Address;
import util.CleanPlayground;
import util.DBConstants;
import util.Vendor;

public class JoinDemo
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
            "1600 Pennsylvania Ave. NW",
            "#12",
            "Fort Meyers",
            "ID",
            "33333"
        ),
        new Address(
            "4200 Yellowbrick Rd",
            null,
            "Fantasy",
            "CA",
            "22222"
        ),
        null,
        null
    };
    
    // These are addresses with no related vendor
    private Address[]   unmatchedAddresses  =
    {
        new Address(
            "47 Newport Heights",
            "#118",
            "Bellevue",
            "WA",
            "44444"
        ),
        new Address(
            "Arroz con Pollo",
            "#3",
            "Quogue",
            "NY",
            "55555"
        ),
    };
    
    private String[] names    =
    {
        "Last Straw Feed and Grain",
        "Peter's Party Palace",
        "nIce Skate",
        // the last two vendors won't have addresses
        "Socrates's Diner",
        "Plato's Paper Plates"
    };
    
    public static void main(String[] args)
    {
        try
        {
            JoinDemo   demo    = new JoinDemo();
            demo.init();
            demo.dump();
            System.out.println( "***** Inner Join" );
            demo.innerJoin();
            System.out.println( "***** Left Join" );
            demo.leftJoin();
            System.out.println( "***** Right Join" );
            demo.rightJoin();
            System.out.println( "***** Full Join" );
            demo.fullJoin();
        }
        catch ( Exception exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void innerJoin()
    {
        try ( Connection conn = 
            DriverManager.getConnection( DB_URL, "", "" );
        )
        {
            String      sql = 
                "SELECT "
                +    "v.name, "
                +    "v.last_call, "
                +    "a.city, "
                +    "a.state "
                +    "FROM vendors v INNER JOIN addresses a "
                +        "ON v.addr_id = a.ident ";
            join( conn, sql );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void leftJoin()
    {
        try ( Connection conn = 
            DriverManager.getConnection( DB_URL, "", "" );
        )
        {
            String      sql = 
                "SELECT "
                +    "v.name, "
                +    "v.last_call, "
                +    "a.city, "
                +    "a.state "
                +    "FROM vendors v LEFT JOIN addresses a "
                +        "ON v.addr_id = a.ident ";
            join( conn, sql );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void rightJoin()
    {
        try ( Connection conn = 
            DriverManager.getConnection( DB_URL, "", "" );
        )
        {
            String      sql = 
                "SELECT "
                +    "v.name, "
                +    "v.last_call, "
                +    "a.city, "
                +    "a.state "
                +    "FROM vendors v RIGHT JOIN addresses a "
                +        "ON v.addr_id = a.ident ";
            join( conn, sql );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void fullJoin()
    {
        try ( Connection conn = 
            DriverManager.getConnection( DB_URL, "", "" );
        )
        {
            String      sql = 
                "SELECT "
                +    "v.name, "
                +    "v.last_call, "
                +    "a.city, "
                +    "a.state "
                +    "FROM vendors v JOIN addresses a "
                +        "ON v.addr_id = a.ident ";
            join( conn, sql );
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void join( Connection conn, String sql ) throws SQLException
    {
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( sql );
        dumpResults( results, "Inner join", 1, 2, 3, 4 );
    }
    
    private void dumpResults(
        ResultSet results, 
        String label, 
        int nameInx, 
        int dateInx, 
        int cityInx,
        int stateInx
    ) throws SQLException
    {
        String      fmt         = "%-26s %s %s, %s%n";
        System.out.println( "================" );
        while ( results.next() )
        {
            String      name    = results.getString( nameInx );
            Date        date    = results.getDate( dateInx );
            String      city    = results.getString( cityInx );
            String      state   = results.getString( stateInx );
            
            System.out.printf( fmt, name, date, city, state );
        }
        System.out.println( "================" );
    }
    
    private void init() throws SQLException
    {
        CleanPlayground.main( null );
        VendorTable vTable      = 
            VendorTable.getInstance( "", "", DB_URL );
        LocalDate   nextDate    = LocalDate.of( 2015, 3, 30 );
        
        for ( int inx = 0 ; inx < names.length ; ++inx )
        {
            String  name    = names[inx];
            Address addr    = addresses[inx];
            Vendor  vendor  = new Vendor( name, addr, nextDate );
            vTable.insert( vendor );
            nextDate = nextDate.plusDays( 40 );
        }
        
        AddressTable    aTable      = 
            AddressTable.getInstance( "", "", DB_URL );
        for ( Address addr : unmatchedAddresses )
            aTable.insert( addr );
    }
    
    private void dump() throws SQLException
    {
        VendorTable table   = 
            VendorTable.getInstance( "", "", DB_URL );
        List<Vendor>    list    = table.selectAll();
        String          fmt     = "%-26s %s %s, %s%n";
        System.out.println( "*************************" );
        for ( Vendor vendor : list )
        {
            String      name    = vendor.getName();
            LocalDate   date    = vendor.getLastContact();
            Address     addr    = vendor.getAddr();
            String      city    = null;
            String      state   = null;
            if ( addr != null )
            {
                city = addr.city;
                state = addr.state;
            }
            System.out.printf( fmt, name, date, city, state );
        }
        System.out.println( "*************************" );
    }
}
