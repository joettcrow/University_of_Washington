package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.DBConstants.DB_URL;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TransactionTableTest
{
    private static LocalDateTime    baseDate;
    static
    {
        // SQL doesn't store time in millis, so these have to be snipped
        baseDate = LocalDateTime.now();
        int nanos   = baseDate.getNano();
        baseDate = baseDate.minusNanos( nanos );
    }
    
    private final Transaction[] transactions    =
    {
        new Transaction( 
            "Hedy Lamarr", 
            baseDate.plusDays( 1 ),
            new BigDecimal( "132.15" ).setScale( 2 )
        ),
        new Transaction( 
            "Miss Havisham", 
            baseDate.plusDays( 2 ),
            new BigDecimal( "41.11" ).setScale( 2 )
        ),
        new Transaction( 
            "Ishmael", 
            baseDate.plusDays( 3 ),
            new BigDecimal( "92.93" ).setScale( 2 )
        ),
    };

    @Before
    public void setUp() throws Exception
    {
        TransactionTable.getInstance( "", "", DB_URL ).truncateTable();
//        CleanPlayground.purify();
    }

    @Test
    public void testInsert() throws SQLException
    {
        TransactionTable    table   = 
            TransactionTable.getInstance( "", "", DB_URL );
        List<Transaction>   expList = new ArrayList<>();
        for ( Transaction trans : transactions )
        {
            table.insert( trans );
            expList.add( trans );
        }
        
        List<Transaction>   actuals = table.getAll();
        assertEquals( expList.size(), actuals.size() );
        for ( Transaction trans : actuals )
            assertTrue( actuals.contains( trans ) );
    }
    
    @Test
    public void testUpdate() throws SQLException
    {
        TransactionTable    table   = 
            TransactionTable.getInstance( "", "", DB_URL );
        for ( Transaction trans : transactions )
        {
            table.insert( trans );
            System.out.println( trans );
        }
        
        List<Transaction>   actList = table.getAll();
        assertEquals( transactions.length, actList.size() );
        actList.forEach( System.out::println );
        for ( Transaction trans : transactions )
            assertTrue( actList.contains( trans ) );
        
        List<Transaction>   expList = new ArrayList<>();
        int limit   = transactions.length;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Transaction     trans       = transactions[inx];
            int             ident       = trans.getIdent();
            String          name        = trans.getName() + inx;
            LocalDateTime   time        = trans.getTime().plusDays( inx );
            BigDecimal      diff        = new BigDecimal( "" + inx );
            BigDecimal      amount      = trans.getAmount().add( diff );
            
            Transaction     nextTrans   = 
                new Transaction( name, time, amount );
            nextTrans.setIdent( ident );
            expList.add( nextTrans );
            table.update( trans );
        }
        actList = table.getAll();
        assertEquals( transactions.length, actList.size() );
        for ( Transaction trans : transactions )
            assertTrue( actList.contains( trans ) );
    }
}
