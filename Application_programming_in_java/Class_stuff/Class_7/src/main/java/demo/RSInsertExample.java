package demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DBConstants;

public class RSInsertExample
{
    private static final Logger log =
        LoggerFactory.getLogger( RSInsertExample.class );
    
    private static final String TABLE           = "account";
    
    private final String connName       = null;
    private final String connPassword   = null;
    private final String connURL        = DBConstants.DB_URL;
    
    private final String[]  firstNames  =
        { "jane", "manny", "sally", "moe", "curly" };
    
    private final String[]  lastNames   =
        { "brady", "pep1", "brady", "pep2", "stooge" };

    public static void main(String[] args)
    {
        try
        {
            RSInsertExample demo    = new RSInsertExample();
            demo.createTable();
            demo.execute();
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
        
        resultSet.moveToInsertRow();
        for ( int inx = 0 ; inx < firstNames.length ; ++inx )
        {
            String  first   = firstNames[inx];
            String  last    = lastNames[inx];
            String  info    = "Adding " + first + " " + last;
            log.info( info );
            
            resultSet.updateString( "first_name", first );
            resultSet.updateString( "last_name", last );
            resultSet.updateInt( "credits", 1 );
            resultSet.updateBoolean( "blocked", false );
            resultSet.insertRow();
        }
        
        dumpAccount( conn );
        statement.close();
    }

    public void dumpAccount( Connection conn )
        throws SQLException
    {
        Statement   statement   = conn.createStatement();
        statement.executeQuery( "SELECT * FROM " + TABLE );
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
    
    private boolean accountExists( Connection conn )
        throws SQLException
    {
        DatabaseMetaData    metaData    = conn.getMetaData();
        String              ucTable     = TABLE.toUpperCase();
        ResultSet           resultSet   =
            metaData.getTables( null, null, ucTable, null );
        
        boolean rval = resultSet.next();
        resultSet.close();
        
        return rval;
    }
    
    public void createTable() throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            createTable( conn );
        }
    }
    
    private void createTable( Connection conn )        
        throws SQLException
    {
        final String createSQL  =
            "CREATE TABLE " + TABLE + " ( "
            + "ident INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "first_name VARCHAR( 30 ) NOT NULL, "
            + "last_name VARCHAR( 30 ) NOT NULL, "
            + "credits INTEGER, "
            + "blocked BOOLEAN, "
            + "PRIMARY KEY ( ident )"
            + ")";
        
        final String truncateSQL    = "DELETE FROM account WHERE 1=1";
        
        String      sql         = 
            accountExists( conn ) ? truncateSQL : createSQL;
        Statement   statement   = conn.createStatement();
        statement.executeUpdate( sql );
        statement.close();
    }
    
    private Connection getConnection()
        throws SQLException
    {
        Connection conn = 
            DriverManager.getConnection( connURL, connName, connPassword );
        return conn;
    }
}
