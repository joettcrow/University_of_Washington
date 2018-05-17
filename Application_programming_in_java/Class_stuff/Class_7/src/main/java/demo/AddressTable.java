package demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Address;

public class AddressTable
{
    private static final String TABLE   = "addresses";
    
    private static AddressTable  instance;
    
    private final String connName;
    private final String connPassword;
    private final String connURL;
    
    private static final String CREATE_TABLE_SQL    =
        "CREATE TABLE " + TABLE + " ( "
        + "ident INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
        + "address1 VARCHAR( 30 ) NOT NULL, "
        + "address2 VARCHAR( 30 ), "
        + "city VARCHAR( 30 ), "
        + "state VARCHAR( 30 ), "
        + "zip VARCHAR( 10 ), "
        + "PRIMARY KEY ( ident )"
        + ")";

    private static final String DROP_TABLE_SQL  = "DROP TABLE " + TABLE;
    
    private static final String INSERT_SQL  =
        "INSERT INTO " + TABLE + " ("
        +   "address1, "
        +   "address2, "
        +   "city, "
        +   "state, "
        +   "zip"
        + ")"
        + "VALUES ( ?, ?, ?, ?, ? )";
    
    // Updates all fields EXCEPT credit cards
    private static final String UPDATE_SQL  =
        "UPDATE " + TABLE + " SET "
        +   "address1 = ?, "
        +   "address2 = ?, "
        +   "city = ?, "
        +   "state = ?, "
        +   "zip = ? "
        + "WHERE ident = ?";
    
    private 
    AddressTable( String connName, String connPassword, String connURL )
    {
        this.connName = connName;
        this.connPassword = connPassword;
        this.connURL = connURL;
    }
    
    public static AddressTable 
    getInstance( String connName, String connPassword, String connURL )
    {
        if ( instance == null )
            instance = new AddressTable( connName, connPassword, connURL );
        return instance;
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
    
    private boolean exists( Connection conn )
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
    
    public void createTable()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            createTable( conn );
        }
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
    
    public void insert( Address address )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            insert( conn, address );
        }
    }
    
    public void insert( Connection conn, Address addr )
        throws SQLException
    {
        String              addr1   = addr.address1;
        String              addr2   = addr.address2;
        String              city    = addr.city;
        String              state   = addr.state;
        String              zip     = addr.zip_code;
        int                 flags   = Statement.RETURN_GENERATED_KEYS;
        
        PreparedStatement   statement   = 
            conn.prepareStatement( INSERT_SQL, flags );
        int inx = 1;
        statement.setString( inx++, addr1 );
        statement.setString( inx++, addr2 );
        statement.setString( inx++, city );
        statement.setString( inx++, state );
        statement.setString( inx++, zip );
        statement.executeUpdate();
        ResultSet results = statement.getGeneratedKeys();
        
        if ( !results.next() )
            throw new SQLException( "get generated keys failurt" );
        addr.ident = results.getInt( 1 );

        statement.close();
    }
    
    public void update( Address address )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            update( conn, address );
        }
    }
    
    private void update( Connection conn, Address addr )
        throws SQLException
    {
        if ( addr.ident == null )
            throw new IllegalArgumentException( "must read before update" );
        PreparedStatement   statement   = conn.prepareStatement( UPDATE_SQL );
        
        int inx = 1;
        statement.setString( inx++, addr.address1 );
        statement.setString( inx++, addr.address2 );
        statement.setString( inx++, addr.city );
        statement.setString( inx++, addr.state );
        statement.setString( inx++, addr.zip_code );
        statement.setInt( inx++, addr.ident );
        statement.executeUpdate();
        
        statement.close();
    }
    
    public Address get( int ident )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            Address address = get( conn, ident );
            return address;
        }
    }
    
    public Address get( Connection conn, int ident )
        throws SQLException
    {
        final String getSQL =
            "SELECT * FROM " + TABLE
            + " WHERE ident = " + ident;
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( getSQL );
        
        if ( !results.next() )
            throw new SQLException( "address not found" );
        String  addr1   = results.getString( "address1" );
        String  addr2   = results.getString( "address2" );
        String  city    = results.getString( "city" );
        String  state   = results.getString( "state" );
        String  zip     = results.getString( "zip" );
        Address address = new Address( addr1, addr2, city, state, zip );
        return address;
    }
    
    public List<Address> getAll()
        throws SQLException
    {
        List<Address>   list    = null;
        try ( Connection conn = getConnection() )
        {
            list = getAll( conn );
        }
        return list;
    }
    
    private List<Address> getAll( Connection conn )
        throws SQLException
    {
        String      getAllSQL   = "SELECT * FROM " + TABLE;
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( getAllSQL );
        
        List<Address>   list    = new ArrayList<>();
        while ( results.next() )
        {
            int     ident   = results.getInt( "ident" );
            String  addr1   = results.getString( "address1" );
            String  addr2   = results.getString( "address2" );
            String  city    = results.getString( "city" );
            String  state   = results.getString( "state" );
            String  zip     = results.getString( "zip" );
            Address addr    = new Address( addr1, addr2, city, state, zip );
            addr.ident = ident;
            list.add( addr );
        }
        
        return list;
    }
    
    private Connection getConnection()
        throws SQLException
    {
        Connection conn = 
            DriverManager.getConnection( connURL, connName, connPassword );
        return conn;
    }
}
