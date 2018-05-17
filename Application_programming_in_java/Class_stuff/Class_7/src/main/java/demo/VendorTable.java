package demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import util.Address;
import util.Vendor;

public class VendorTable
{
    private static final String TABLE   = "vendors";
    
    private static Optional<VendorTable>   instance    = 
        Optional.empty();
    
    private final String connName;
    private final String connPassword;
    private final String connURL;
    
    private VendorTable( String cName, String cPassword, String cURL )
    {
        connName = cName;
        connPassword = cPassword;
        connURL = cURL;
    }
    
    public static VendorTable 
    getInstance( String connName, String connPassword, String connURL )
    {
        VendorTable    transaction = 
            instance.orElse( 
                new VendorTable( connName, connPassword, connURL )
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
            + "last_call DATE, "
            + "addr_id INTEGER, "
            + "PRIMARY KEY ( ident ), "
            + "FOREIGN KEY ( addr_id ) "
            +     "REFERENCES addresses ( ident ) "
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
    
    public void insert( Vendor vendor ) throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            insert( conn, vendor );
        }
    }
    
    public void insert( Connection conn, Vendor vendor )
        throws SQLException
    {
        final String    sql =
            "INSERT INTO " + TABLE + " ("
                +   "name, "
                +   "last_call, "
                +   "addr_id"
                + ")"
                + "VALUES ( ?, ?, ? )";
        
        String          name    = vendor.getName();
        Date            date    = Date.valueOf( vendor.getLastContact() );
        Address         addr    = vendor.getAddr();
        int             flags   = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement   statement   = 
            conn.prepareStatement( sql, flags );
        
        int inx = 1;
        statement.setString( inx++, name );
        statement.setDate( inx++, date );
        if ( addr != null )
        {
            AddressTable    aTable  = 
                AddressTable.getInstance( connName, connPassword, connURL );
            aTable.insert( addr );
            statement.setInt( inx++, addr.ident );
        }
        else
            statement.setNull( inx++, Types.INTEGER );
        statement.executeUpdate();
        
        ResultSet   results = statement.getGeneratedKeys();
        if ( !results.next() )
            throw new SQLException( "get generated keys failure" );
        vendor.setIdent( results.getInt( 1 ) );
    }
    
    public List<Vendor> selectAll() throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            List<Vendor> list = selectAll( conn );
            return list;
        }
    }
    
    private List<Vendor> selectAll( Connection conn )
        throws SQLException
    {
        final String    sql         = "SELECT * FROM " + TABLE;
        Statement       statement   = conn.createStatement();
        ResultSet       results     = statement.executeQuery( sql );
        List<Vendor>    list        = new ArrayList<>();
        
        while ( results.next() )
        {
            int         inx         = 1;
            int         ident       = results.getInt( inx++ );
            String      name        = results.getString( inx++ );
            Date        date        = results.getDate( inx++ );
            int         addrID      = results.getInt( inx++ );
            Address     addr        = null;
            if ( addrID != 0 )
            {
                AddressTable    aTable      = 
                    AddressTable.getInstance( connName, connPassword, connURL );
                addr = aTable.get( addrID );
            }
            Vendor  vendor  = new Vendor( name, addr, date.toLocalDate() );
            vendor.setIdent( ident );
            list.add( vendor );
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
