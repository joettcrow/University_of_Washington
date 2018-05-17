package demo;

import static util.DBConstants.DB_URL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Contact;
import util.Name;

public class ContactTable
{
    private static final String CONTACT = "contact";
    
    public void createContacts()
        throws SQLException
    {
        try ( Connection conn = getConnection(); )
        {
            if ( !hasTable( conn, CONTACT ) )
                createContacts( conn );
        }
    }
    
    public void truncateTable()
        throws SQLException
    {
        try ( Connection conn = getConnection(); )
        {
            truncateTable( conn );
        }
    }
    
    public void insertContact( Contact contact)
        throws SQLException
    {
        try ( Connection conn = getConnection(); )
        {
            insertContact( conn, contact );
        }
    }
    
    public List<Contact> getAllContacts()
        throws SQLException
    {
        List<Contact>   list    = null;
        try ( Connection conn = getConnection(); )
        {
            list = getAllContacts( conn );
        }
        return list;
    }
    
    public List<Contact> getByPhoneNumber( String phone )
        throws SQLException
    {
        List<Contact>   list    = null;
        try ( Connection conn = getConnection(); )
        {
            list = getByPhoneNumber( conn, phone );
        }
        return list;
    }
    
    public Contact getContact( String last, String first )
        throws SQLException
    {
        Contact contact = null;
        try ( Connection conn = getConnection(); )
        {
            contact = getContact( conn, last, first );
        }
        return contact;
    }
    
    public List<Name> getAllNames()
        throws SQLException 
    {
        List<Name>  list = null;
        try ( Connection conn = getConnection(); )
        {
            list = getAllNames( conn );
        }
        return list;
    }
    
    public List<Name> getAllNames( Connection conn )
        throws SQLException 
    {
        String      sql     = 
            "SELECT first_name, last_name FROM " + CONTACT;
        List<Name>  list        = new ArrayList<>();
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( sql );
        while ( results.next() )
        {
            String  first   = results.getString( 1 );
            String  last    = results.getString( 2 );
            Name    name    = new Name( first, last );
            list.add( name );
        }
        
        return list;
    }    
    
    private Connection getConnection()
        throws SQLException
    {
        Connection  conn    =
            DriverManager.getConnection( DB_URL );
        return conn;
    }
    
    public boolean hasTable( Connection conn, String table )
        throws SQLException
    {
        // Important: Derby stores table names in upper case
        String  tableUC = table.toUpperCase();
        DatabaseMetaData    metaData    = conn.getMetaData();
        ResultSet           resultSet   =
            metaData.getTables( null, null, tableUC, null );
        
        boolean rval = resultSet.next();
        resultSet.close();
        
        return rval;
    }
    
    public void truncateTable( Connection conn )
        throws SQLException
    {
        if ( !hasTable( conn, CONTACT ) )
            createContacts( conn );
        else
        {
            final String    truncate    = 
                "DELETE FROM " + CONTACT + " WHERE 1=1";
            Statement       statement   = conn.createStatement();
            statement.executeUpdate( truncate );
        }
    }
    
    public void createContacts( Connection conn )
        throws SQLException
    {
        final String createTableSQL =
            "CREATE TABLE " + CONTACT + "  ( "
            + "contact_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "last_name VARCHAR(30), "
            + "first_name VARCHAR(30) NOT NULL, "
            + "phone VARCHAR(15), "
            + "e_mail VARCHAR(60), "
            + "PRIMARY KEY ( contact_id )"
            + ")";
        Statement   statement   = conn.createStatement();
        statement.executeUpdate( createTableSQL );
        statement.close();
    }
    
    public void insertContact( Connection conn, Contact contact)
        throws SQLException
    {
        final String insertSQL  =
            "INSERT INTO " + CONTACT + "  ("
            +    "last_name, "
            +    "first_name, "
            +    "phone, "
            +    "e_mail "
            +    ")"
            +    "VALUES ( ?, ?, ?, ? )";
        PreparedStatement   statement   = conn.prepareStatement( insertSQL );
        int                 inx         = 1;
        statement.setString( inx++, contact.getLastName() );
        statement.setString( inx++, contact.getFirstName() );
        statement.setString( inx++, contact.getPhone() );
        statement.setString( inx++, contact.getEMail() );
        statement.executeUpdate();
    }
    
    public List<Contact> getByPhoneNumber( Connection conn, String number )
        throws SQLException
    {
        String          sql         =
            "SELECT * FROM contact WHERE phone = '" + number + "'";
        List<Contact>   list        = new ArrayList<>();
        Statement       statement   = conn.createStatement();
        ResultSet       results     = statement.executeQuery( sql );
        System.out.println( sql );
        while ( results.next() )
        {
            String  last    = results.getString( "last_name" );
            String  first   = results.getString( "first_name" );
            String  phone   = results.getString( "phone" );
            String  eMail   = results.getString( "e_mail" );
            Contact contact = new Contact( last, first, phone, eMail );
            list.add( contact );
        }
        
        return list;
    }
    
    public List<Contact> getAllContacts( Connection conn )
        throws SQLException
    {
        final String selectAllSQL  =
            "SELECT * FROM  " + CONTACT
           + " ORDER BY last_name, first_name";
        Statement   statement   = conn.createStatement();
        ResultSet   results     = statement.executeQuery( selectAllSQL );

        List<Contact>   list    = new ArrayList<>();
        while ( results.next() )
        {
            String  last    = results.getString( "last_name" );
            String  first   = results.getString( "first_name" );
            String  phone   = results.getString( "phone" );
            String  eMail   = results.getString( "e_mail" );
            Contact contact = new Contact( last, first, phone, eMail );
            list.add( contact );
        }
        
        return list;
    }
    
    public Contact 
    getContact( Connection conn, String lastName, String firstName )
        throws SQLException
    {
        final String        getContactSQL   =
            "SELECT * FROM " + CONTACT
            + " WHERE last_name = ? AND first_name = ?";
        PreparedStatement   statement   =
            conn.prepareStatement( getContactSQL );
        statement.setString( 1, lastName );
        statement.setString( 2, firstName );
        ResultSet   results = statement.executeQuery();
        
        Contact contact = null;
        if ( results.next() )
        {
            String  last    = results.getString( "last_name" );
            String  first   = results.getString( "first_name" );
            String  phone   = results.getString( "phone" );
            String  eMail   = results.getString( "e_mail" );
            contact = new Contact( last, first, phone, eMail );
        }
        
        return contact;
    }
}
