package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DBConstants;

public class RSUpdateExample
{
    private static final Logger log =
        LoggerFactory.getLogger( RSUpdateExample.class );
                                        
    private final String connName       = null;
    private final String connPassword   = null;
    private final String connURL        = DBConstants.DB_URL;
    
    public static void main( String[] args )
    {
        try
        {
            new RSUpdateExample().execute();
            DumpAccount.main( args );
        }
        catch ( SQLException exc )
        {
            log.error( "Account dump failure", exc );
        }
    }

    public void execute()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            execute( conn );
        }
    }
    
    private void execute( Connection conn )
        throws SQLException
    {
        final String sql        = "SELECT * FROM account";
        int         type        = ResultSet.TYPE_SCROLL_SENSITIVE;
        int         curr        = ResultSet.CONCUR_UPDATABLE;
        Statement   statement   = conn.createStatement( type, curr );
        statement.executeQuery( sql );
        ResultSet   resultSet   = statement.getResultSet();
        
        while ( resultSet.next() )
        {
            String  first   = resultSet.getString( "first_name" );
            String  last    = resultSet.getString( "last_name" );
            String  info    = "Updating " + first + " " + last;
            log.info( info );
            
            int     credits = resultSet.getInt( "credits" );
            resultSet.updateInt( "credits", ++credits );
            resultSet.updateRow();
        }
        
        statement.close();
    }

    public void dumpAccount( Connection conn )
        throws SQLException
    {
        Statement   statement   = conn.createStatement();
        statement.executeQuery( "SELECT * FROM account" );
        ResultSet   resultSet   = statement.getResultSet();
        
        System.out.println( "BEGIN DUMP OF ACCOUNT TABLE" );
        while ( resultSet.next() )
        {
            String          first   = resultSet.getString( "first_name" );
            String          last    = resultSet.getString( "last_name" );
            int             credits = resultSet.getInt( "credits" );
            boolean         blocked = resultSet.getBoolean( "blocked" );

            StringBuilder   bldr    = new StringBuilder();
            bldr.append( last ).append( ", " ).append( first ).append( ": " );
            bldr.append( credits ).append( "/" ).append( blocked );
            System.out.println( bldr );
        }
        System.out.println( "END DUMP OF ACCOUNT TABLE" );
    }

    private Connection getConnection()
        throws SQLException
    {
        Connection conn = 
            DriverManager.getConnection( connURL, connName, connPassword );
        return conn;
    }
}
