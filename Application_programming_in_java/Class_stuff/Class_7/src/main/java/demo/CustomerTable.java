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
import util.Name;

public class CustomerTable
{
    private static CustomerTable  instance;
    
    private final String connName;
    private final String connPassword;
    private final String connURL;
    
    private static final String CREATE_TABLE_SQL    =
        "CREATE TABLE customer ( "
        + "ident INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
        + "addr_id INTEGER NOT NULL REFERENCES addresses( ident ), "
        + "first_name VARCHAR( 30 ), "
        + "last_name VARCHAR( 30 ) NOT NULL, "
        + "middle_name VARCHAR( 30 ), "
        + "PRIMARY KEY ( ident )"
        + ")";

    private static final String DROP_TABLE_SQL  = "DROP TABLE customer";
    
    private static final String TABLE           = "customer";
    
    private static final String INSERT_SQL  =
        "INSERT INTO customer ( "
        +   "addr_id, "
        +   "first_name, "
        +   "last_name, "
        +   "middle_name "
        + ") "
        + "VALUES ( ?, ?, ?, ? )";
    
    private static final String SELECT_ALL_SQL  = "SELECT * FROM customer";
    
    private static final String SELECT_BY_NAME_SQL  =
        "SELECT * FROM customer "
        + "WHERE last_name = ? AND first_name = ?";
    
    // Updates all fields EXCEPT credit cards
    private static final String UPDATE_SQL  =
        "UPDATE customer SET "
        +   "addr_id, "
        +   "first_name = ?, "
        +   "last_name = ?, "
        +   "middle_name = ? "
        + "WHERE ident = ?";
    
    private 
    CustomerTable( String connName, String connPassword, String connURL )
    {
        this.connName = connName;
        this.connPassword = connPassword;
        this.connURL = connURL;
    }
    
    public static CustomerTable 
    getInstance( String connName, String connPassword, String connURL )
    {
        if ( instance == null )
            instance = new CustomerTable( connName, connPassword, connURL );
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
        ResultSet           resultSet   =
        metaData.getTables( null, null, "CUSTOMER", null );
        
        boolean rval = resultSet.next();
        resultSet.close();
        
        return rval;
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
    
    public void insert( Customer customer )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            insert( conn, customer );
        }
    }
    
    private void insert( Connection conn, Customer customer )
        throws SQLException
    {
        Address             addr    = customer.getAddress();
        AddressTable        aTable  = 
            AddressTable.getInstance( "", "", connURL );
        aTable.insert( conn, addr );
        
        Name        name    = customer.getName();
        String      first   = name.first;
        String      last    = name.last;
        String      middle  = name.middle;
        int         addrID  = addr.ident;
        
        int                 flags       = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement   statement   = 
            conn.prepareStatement( INSERT_SQL, flags );
        int inx = 1;
        statement.setInt( inx++, addrID );
        statement.setString( inx++, first );
        statement.setString( inx++, last );
        statement.setString( inx++, middle );
        statement.executeUpdate();

        ResultSet   results = statement.getGeneratedKeys();
        if ( !results.next() )
            throw new Error( "update customer malfunction" );
        customer.setIdent( results.getInt( 1 ) );
        statement.close();
        
        CreditCardTable cardTable   = 
            CreditCardTable.getInstance( connName, connPassword, connURL );
        cardTable.insert( conn, customer );
    }
    
    public List<Customer> selectAll()
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            List<Customer>  list    = selectAll( conn );
            return list;
        }
    }
    
    public List<Customer> selectAll( Connection conn )
        throws SQLException
    {
        PreparedStatement   statement   =
            conn.prepareStatement( SELECT_ALL_SQL );
        ResultSet           results     = statement.executeQuery();
        
        List<Customer>      customers   = buildCustomerList( conn, results );
        statement.close();
        
        return customers;
    }
    
    public List<Customer> select( String last, String first )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            List<Customer>  list    = select( conn, last, first );
            return list;
        }
    }
    
    private List<Customer>
    select( Connection conn, String last, String first )
        throws SQLException
    {
        PreparedStatement   statement   =
            conn.prepareStatement( SELECT_BY_NAME_SQL );
        statement.setString( 1, last );
        statement.setString( 2, first );
        ResultSet           results     =
            statement.executeQuery();
        
        List<Customer>  customers   = buildCustomerList( conn, results );
        return customers;
    }
    
    private List<Customer> 
    buildCustomerList( Connection conn, ResultSet results )
        throws SQLException
    {
        List<Customer>  customers   = new ArrayList<>();
        AddressTable    aTable      = 
            AddressTable.getInstance( "", "", connURL );
        CreditCardTable ccTable     = 
            CreditCardTable.getInstance( "", "", connURL );
        
        while ( results.next() )
        {
            int             inx     = 1;
            int             ident   = results.getInt( inx++ );
            int             aID     = results.getInt( inx++ );
            String          lastN   = results.getString( inx++ );
            String          firstN  = results.getString( inx++ );
            String          middleN = results.getString( inx++ );
            Name            name    = new Name( lastN, firstN, middleN );
            Address         address     = aTable.get( conn, aID );
            Customer        customer    = new Customer( name, address );
            customer.setIdent( ident );
            ccTable.selectAll( conn, customer );

            customers.add( customer );
        }
        
        return customers;
    }
    
//    private List<Customer>
//    buildCustomerList( Connection conn, ResultSet results )
//        throws SQLException
//    {
//        CreditCardTable     ccTable     = 
//            CreditCardTable.getInstance( connName, connPassword, connURL );
//        List<Customer>      list        = new ArrayList<>();
//        
//        while ( results.next() )
//        {
//            int     ident   = results.getInt( "ident" );
//            String  first   = results.getString( "first_name" );
//            String  last    = results.getString( "last_name" );
//            String  middle  = results.getString( "middle_name" );
//            Name    name    = new Name( first, last, middle );
//            
//            String  addr1   = results.getString( "address1" );
//            String  addr2   = results.getString( "address2" );
//            String  city    = results.getString( "city" );
//            String  state   = results.getString( "state" );
//            String  zip     = results.getString( "zip" );
//            Address addr    = new Address( addr1, addr2, city, state, zip );
//            
//            Customer            customer    = 
//                new Customer( name, addr, ident );            
//            
//            list.add( customer );
//        }
//        
//        return list;
//    }
    
    public void update( Customer customer )
        throws SQLException
    {
        try ( Connection conn = getConnection() )
        {
            update( conn, customer );
        }
    }
    
    private void update( Connection conn, Customer customer )
        throws SQLException
    {
        PreparedStatement   statement   = conn.prepareStatement( UPDATE_SQL );
        Name                name        = customer.getName();
        Address             addr        = customer.getAddress();
        int                 recID       = customer.getIdent();
        
        statement.setString( 1, name.first );
        statement.setString( 2, name.last );
        statement.setString( 3, name.middle );
        statement.setString( 4, addr.address1 );
        statement.setString( 5, addr.address2 );
        statement.setString( 6, addr.city );
        statement.setString( 7, addr.state );
        statement.setString( 8, addr.zip_code );
        statement.setInt( 9, recID );
        statement.executeUpdate();
        
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
