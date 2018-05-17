package demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.Address;

public class CreditCardTable
{
    private static CreditCardTable  instance;
    private static final String TABLE   = "credit_card";
    
    private final String connName;
    private final String connPassword;
    private final String connURL;
    
    private static final String CREATE_TABLE_SQL    =
        "CREATE TABLE credit_card ( "
        + "ident INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
        + "cust_id INTEGER, "
        + "addr_id INTEGER, "
        + "name VARCHAR(45), "
        + "number VARCHAR(25) NOT NULL, "
        + "cvv VARCHAR(5) NOT NULL, "
        + "expiration_date DATE NOT NULL, "
        + "PRIMARY KEY ( ident ), "
        + "FOREIGN KEY ( cust_id ) "
        +     " REFERENCES customer ( ident ), "
        + "FOREIGN KEY ( addr_id ) "
        +     " REFERENCES addresses ( ident )"
        + ")";
    
    private static final String INSERT_SQL  =
        "INSERT INTO credit_card ("
        +   "cust_id, "
        +   "addr_id, "
        +   "name, "
        +   "number, "
        +   "cvv, "
        +   "expiration_date "
        + ")"
        + "VALUES ( ?, ?, ?, ?, ?, ? )";

    private static final String DROP_TABLE_SQL    =
        "DROP TABLE credit_card";

    private static final String DELETE_ROW_SQL    =
        "DELETE FROM TABLE credit_card "
        + "WHERE ident = ?";
    
    private 
    CreditCardTable( String connName, String connPassword, String connURL )
    {
        this.connName = connName;
        this.connPassword = connPassword;
        this.connURL = connURL;
    }
    
    public static CreditCardTable 
    getInstance( String connName, String connPassword, String connURL )
    {
        if ( instance == null )
            instance = new CreditCardTable( connName, connPassword, connURL );
        return instance;
    }
    
    public boolean exists()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            boolean rval    = exists( conn );
            return rval;
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
    
    public void dropTable()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            dropTable( conn );
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
    
    public void insert( Customer customer )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            insert( conn, customer );
        }
    }
    
    public void insert( Connection conn, Customer customer )
        throws SQLException
    {
        AddressTable        aTable      = 
            AddressTable.getInstance( "", "", connURL );
        int                 customerID  = customer.getIdent();
        List<CreditCard>    cards       = customer.getCreditCards();
        int                 flags       = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement   statement   = 
            conn.prepareStatement( INSERT_SQL, flags );
        for ( CreditCard card : cards )
        {
            String  name    = card.getName();
            Address addr    = card.getAddress();
            String  number  = card.getNumber();
            String  cvv     = card.getCvv();
            Date    expiry  = Date.valueOf( card.getExpiry() );
            
            aTable.insert( addr );
            int     addrID  = addr.ident;

            int     inx     = 1;
            statement.setInt( inx++, customerID );
            statement.setInt( inx++, addrID );
            statement.setString( inx++, name );
            statement.setString( inx++, number );
            statement.setString( inx++, cvv );
            statement.setDate( inx++, expiry );
            
            statement.executeUpdate();
            ResultSet   keys    = statement.getGeneratedKeys();
            if ( !keys.next() )
                throw new SQLException( "get generated keys failure" );
            card.setIdent( keys.getInt( 1 ) );
        }
    }
    
    public void deleteRow( CreditCard card )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            deleteRow( conn, card );
        }
    }
    
    private void deleteRow( Connection conn, CreditCard card )
        throws SQLException
    {
        Integer ident   = card.getIdent();
        if ( ident == null )
            throw new IllegalArgumentException();
        PreparedStatement   statement   = 
            conn.prepareStatement( DELETE_ROW_SQL );
        statement.setInt( 1,  ident );
        statement.executeUpdate();
    }
    
    private void createTable( Connection conn )        
        throws SQLException
    {
        if ( !exists() )
        {
            Statement   statement   = conn.createStatement();
            statement.executeUpdate( CREATE_TABLE_SQL );
            statement.close();
        }
    }
    
    private void dropTable( Connection conn )        
        throws SQLException
    {
        if ( exists() )
        {
            Statement   statement   = conn.createStatement();
            statement.executeUpdate( DROP_TABLE_SQL );
            statement.close();
        }
    }
    
    private boolean exists( Connection conn )
        throws SQLException
    {
        DatabaseMetaData    metaData    = conn.getMetaData();
        ResultSet           resultSet   =
            metaData.getTables( null, null, "CREDIT_CARD", null );
        
        boolean rval = resultSet.next();
        resultSet.close();
        
        return rval;
    }
    
    public List<CreditCard> selectAll()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            List<CreditCard>    list    = selectAll( conn );
            return list;
        }
    }
        
    public List<CreditCard> selectAll( Connection conn )
        throws SQLException
    {
        String      sql         = "SELECT * FROM credit_card";
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( sql );
        
        AddressTable        aTable  =
            AddressTable.getInstance("", "", connURL );
        List<CreditCard>    cards   = new ArrayList<>();
        while ( results.next() )
        {
            int         inx     = 1;
            int         ident   = results.getInt( inx++ );
            int         custID  = results.getInt( inx++ );
            int         addrID  = results.getInt( inx++ );
            String      name    = results.getString( inx++ );
            String      number  = results.getString( inx++ );
            String      cvv     = results.getString( inx++ );
            Date        date    = results.getDate( inx++ );
            Address     addr    = aTable.get( addrID );
            LocalDate   lDate   = date.toLocalDate();
            CreditCard  card    =
                new CreditCard( name, addr, number, cvv, lDate );
            card.setIdent( ident );
            card.setCustID( custID );
            cards.add( card );
        }
        
        return cards;
    }
    
    public void selectAll( Customer customer )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            selectAll( conn, customer );
        }
    }
    
    public void selectAll( Connection conn, Customer customer )
        throws SQLException
    {
        String selectAllSQL  = "SELECT * FROM credit_card WHERE cust_id = ?";
        PreparedStatement   statement   = 
            conn.prepareStatement( selectAllSQL );
        statement.setInt( 1, customer.getIdent() );
        ResultSet           results     = statement.executeQuery();
        
        AddressTable    aTable  = AddressTable.getInstance("", "", connURL );
        while ( results.next() )
        {
            int         ident       = results.getInt( "ident" );
            int         custID      = results.getInt( "cust_id" );
            int         addrID      = results.getInt( "addr_id" );
            String      name        = results.getString( "name" );
            String      number      = results.getString( "number" );
            String      cvv         = results.getString( "cvv" );
            Date        expiry      = results.getDate( "expiration_date" );
            Address     addr        = aTable.get( addrID );
            LocalDate   lDate       = expiry.toLocalDate();
            CreditCard  card        = 
                new CreditCard( name, addr, number, cvv, lDate );
            card.setIdent( ident );
            card.setCustID( custID );
            customer.addCreditCard( card );
        }
    }
    
    private Connection getConnection()
        throws SQLException
    {
        Connection conn = 
            DriverManager.getConnection( connURL, connName, connPassword );
        return conn;
    }
    
}
