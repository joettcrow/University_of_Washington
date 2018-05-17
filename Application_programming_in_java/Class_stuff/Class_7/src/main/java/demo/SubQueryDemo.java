package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import util.Address;
import util.CleanPlayground;
import util.DBConstants;
import util.Name;

public class SubQueryDemo
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
    
    private Name[]  names   =
    {
        new Name( "Henny", "Youngman" ),
        new Name( "Groucho", "Marx" ),
        new Name( "Jerry", "Lewis" )
    };

    public static void main(String[] args)
    {
        SubQueryDemo    demo    = new SubQueryDemo();
        try
        {
            demo.init();
            demo.execute();
        }
        catch( SQLException exc )
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
            "SELECT credit_card.name, "
            +    "credit_card.number, "
            +    "credit_card.expiration_date "
            + "FROM credit_card "
            + "WHERE cust_id = "
            +     "( SELECT ident FROM  customer "
            +         "WHERE first_name = ? "
            +         "AND last_name = ? )";
    
        PreparedStatement   statement = 
            conn.prepareStatement( sql );
        String  first   = names[0].first;
        String  last    = names[0].last;
        statement.setString( 1, names[0].first );
        statement.setString( 2, names[0].last );
        ResultSet           results = statement.executeQuery();
        
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "All credit cards for " ).append( last );
        bldr.append( " " ).append( first );
        System.out.println( bldr.toString() );
        
        while ( results.next() )
        {
            int     inx     = 1;
            String  name    = results.getString( inx++ );
            String  number  = results.getString( inx++ );
            String  expiry  = results.getString( inx++ );
            bldr = new StringBuilder( "    " );
            bldr.append( "\"" ).append( name ).append( "\" " );
            bldr.append( number ).append( " " );
            bldr.append( "expires " ).append( expiry );
            System.out.println( ": " + bldr.toString() );
        }
        
        statement.close();
    }
    
    private void init() throws SQLException
    {
        CleanPlayground.main( null );
        int             ccNum   = 100000000;
        int             cVV     = 100;
        char            ccName  = 'A';
        LocalDate       date    = LocalDate.of( 2016,  5,  22 );
        CustomerTable   table   = CustomerTable.getInstance( "", "", DB_URL );
        for ( int inx = 0 ; inx < addresses.length ; ++inx )
        {
            Name    name    = names[inx];
            Address addr    = addresses[inx];
            Customer    customer    = new Customer( name, addr );
            ccNum += 1345;
            String      sNum        = "" + ccNum;
            String      sCVV        = "" + cVV++;
            String      sName       = "" + ccName++;
            CreditCard  card        =
                new CreditCard( sName, addr, sNum, sCVV, date );         
            customer.addCreditCard( card );
            
            ccNum += 1345;
            date = date.plusDays( 134 );
            sNum        = "" + ccNum;
            sCVV        = "" + cVV++;
            sName       = "" + ccName++;
            card        =
                new CreditCard( sName, addr, sNum, sCVV, date );         
            customer.addCreditCard( card );
            table.insert( customer );
        }
    }
}
