package util;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.AddressTable;
import demo.CreditCardTable;
import demo.CustomerTable;
import demo.TransactionTable;
import demo.VendorTable;

public class CleanPlayground
{
    private static final Logger log =
        LoggerFactory.getLogger( CleanPlayground.class );
                            
    private static final String connName        = null;
    private static final String connPassword    = null;
    private static final String connURL         = DBConstants.DB_URL;

    public static void main( String[] args )
    {
        try
        {
            VendorTable     vendTable   =
                VendorTable.getInstance( connName, connPassword, connURL );
            CustomerTable   custTable   = 
                CustomerTable.getInstance( connName, connPassword, connURL );
            CreditCardTable ccTable     =
                CreditCardTable.getInstance( connName, connPassword, connURL );
            AddressTable    addrTable   =
                AddressTable.getInstance( connName, connPassword, connURL );
            TransactionTable transTable =
                TransactionTable.getInstance( connName, connPassword, connURL );
            
            log.info( "truncating vendor table" );
            vendTable.truncateTable();
            log.info( "truncating credit_card table" );
            ccTable.truncateTable();
            log.info( "truncating customer table" );
            custTable.truncateTable();
            log.info( "truncating address table" );
            addrTable.truncateTable();
            log.info( "truncating transaction table" );
            transTable.truncateTable();
        }
        catch ( SQLException exc )
        {
            log.error( "", exc );
        }
    }
    
    public static void purify()
    {
        try
        {
            VendorTable     vendTable   =
                VendorTable.getInstance( connName, connPassword, connURL );
            CustomerTable   custTable   = 
                CustomerTable.getInstance( connName, connPassword, connURL );
            CreditCardTable ccTable     =
                CreditCardTable.getInstance( connName, connPassword, connURL );
            AddressTable    addrTable   =
                AddressTable.getInstance( connName, connPassword, connURL );
            TransactionTable transTable =
                TransactionTable.getInstance( connName, connPassword, connURL );
            
            log.info( "dropping vendor table" );
            vendTable.dropTable();
            log.info( "dropping vendor table" );
            ccTable.dropTable();
            log.info( "dropping customer table" );
            custTable.dropTable();
            log.info( "dropping address table" );
            addrTable.dropTable();
            log.info( "dropping transaction table" );
            transTable.dropTable();
            
            log.info( "creating vendor table" );
            vendTable.createTable();
            log.info( "creating credit_card table" );
            ccTable.createTable();
            log.info( "creating customer table" );
            custTable.createTable();
            log.info( "creating address table" );
            addrTable.createTable();
            log.info( "creating transaction table" );
            transTable.createTable();
        }
        catch ( SQLException exc )
        {
            log.error( "", exc );
        }
    }
}
 