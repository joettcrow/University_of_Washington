package demo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionTable
{
    private static final String TABLE   = "transactions";
    
    private static Optional<TransactionTable>   instance    = 
        Optional.empty();
    
    private final String connName;
    private final String connPassword;
    private final String connURL;
    
    private TransactionTable( String cName, String cPassword, String cURL )
    {
        connName = cName;
        connPassword = cPassword;
        connURL = cURL;
    }
    
    public static TransactionTable 
    getInstance( String connName, String connPassword, String connURL )
    {
        TransactionTable    transaction = 
            instance.orElse( 
                new TransactionTable( connName, connPassword, connURL )
        );
            
        return transaction;
    }
    
    public boolean exists()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            boolean rcode   = exists( conn );
            return rcode;
        }
    }
    
    public boolean exists( Connection conn )
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

    public void dropTable()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            dropTable( conn );
        }
    }
    
    public void dropTable( Connection conn )
        throws SQLException
    {
        final String sql  = "DROP TABLE " + TABLE;
        if ( exists() )
        {
            Statement   statement   = conn.createStatement();
            statement.executeUpdate( sql );
            statement.close();
        }
    }
    
    public void createTable()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            createTable( conn );
        }
    }

    public void createTable( Connection conn )        
        throws SQLException
    {
        final String sql    =
            "CREATE TABLE " + TABLE + " ( "
            + "ident INTEGER NOT NULL GENERATED "
            +     "ALWAYS AS IDENTITY, "
            + "name VARCHAR( 30 ) NOT NULL, "
            + "date DATE, "
            + "time TIME, "
            + "amount DECIMAL( 20, 2 ), "
            + "PRIMARY KEY ( ident )"
            + ")";

        if ( !exists( conn ) )
        {
            Statement   statement   = conn.createStatement();
            statement.executeUpdate( sql );
            statement.close();
        }
    }
    
    public void truncateTable() throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            if ( !exists() )
                createTable( conn );
            else
                truncateTable( conn );
        }
    }
    
    public void truncateTable( Connection conn ) throws SQLException
    {
        String      sql         = "DELETE FROM " + TABLE + " WHERE 1=1";
        Statement   statement   = conn.createStatement();
        statement.executeUpdate( sql );
    }
    
    public void insert( Transaction trans ) throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            insert( conn, trans );
        }
    }
    
    public void insert( Connection conn, Transaction trans )
        throws SQLException
    {
        final String    sql =
            "INSERT INTO " + TABLE + " ("
                +   "name, "
                +   "date, "
                +   "time, "
                +   "amount"
                + ")"
                + "VALUES ( ?, ?, ?, ? )";
        
        LocalDateTime   dateTime    = trans.getTime();
        LocalDate       datePart    = dateTime.toLocalDate();
        LocalTime       timePart    = dateTime.toLocalTime();
        String          name        = trans.getName();
        Date            date        = Date.valueOf( datePart );
        Time            time        = Time.valueOf( timePart );
        BigDecimal      amount      = trans.getAmount();

        int             flags   = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement   statement   = 
            conn.prepareStatement( sql, flags );
        
        int inx = 1;
        statement.setString( inx++, name );
        statement.setDate( inx++, date );
        statement.setTime( inx++, time );
        statement.setBigDecimal(inx++, amount );
        statement.executeUpdate();
        
        ResultSet   results = statement.getGeneratedKeys();
        if ( !results.next() )
            throw new SQLException( "get generated keys failure" );
        trans.setIdent( results.getInt( 1 ) );
    }
    
    public List<Transaction> getAll() throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            List<Transaction>   list    = getAll( conn );
            return list;
        }
    }
    
    public List<Transaction> getAll( Connection conn ) 
        throws SQLException
    {
        final String        sql         = "SELECT * FROM " + TABLE;
        List<Transaction>   list        = new ArrayList<>();
        Statement           statement   = conn.createStatement();
        ResultSet           results     = statement.executeQuery( sql );
        
        while ( results.next() )
        {
            int             ident       = results.getInt( "ident" );
            String          name        = results.getString( "name" );
            Date            date        = results.getDate( "date" );
            Time            time        = results.getTime( "time" );
            BigDecimal      amount      = 
                results.getBigDecimal( "amount" ).setScale( 2 );
            LocalDate       lDate       = date.toLocalDate();
            LocalTime       lTime       = time.toLocalTime();
            LocalDateTime   dateTime    = LocalDateTime.of( lDate, lTime );
            
            Transaction     transaction =
                new Transaction( name, dateTime, amount );
            transaction.setIdent( ident );
            list.add( transaction );
        }
        
        return list;
    }
    
    public void update( Transaction transaction ) throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            if ( transaction.getIdent() == null )
                throw new SQLException( "Record ID missing" );
            update( conn, transaction );
        }
    }
    
    public void update( Connection conn, Transaction trans )
        throws SQLException
    {
        final String    sql =
            "UPDATE " + TABLE + " SET "
                +   "name = ?, "
                +   "date = ?, "
                +   "time = ?, "
                +   "amount = ? "
                + " WHERE ident = ?";
        
        LocalDateTime   dateTime    = trans.getTime();
        LocalDate       datePart    = dateTime.toLocalDate();
        LocalTime       timePart    = dateTime.toLocalTime();
        String          name        = trans.getName();
        Date            date        = Date.valueOf( datePart );
        Time            time        = Time.valueOf( timePart );
        BigDecimal      amount      = trans.getAmount();
        int             ident       = trans.getIdent();
        
        PreparedStatement   statement   = conn.prepareStatement( sql );
        int                 inx         = 1;
        statement.setString( inx++, name );
        statement.setDate( inx++, date );
        statement.setTime( inx++, time );
        statement.setBigDecimal( inx++, amount );
        statement.setInt( inx++, ident );
        statement.executeUpdate();
    }
    
    private Connection getConnection()
        throws SQLException
    {
        Connection conn = 
            DriverManager.getConnection( connURL, connName, connPassword );
        return conn;
    }
}
