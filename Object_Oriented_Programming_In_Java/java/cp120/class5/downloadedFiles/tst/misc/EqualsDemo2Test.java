package misc;

import static org.junit.Assert.*;

import org.junit.Test;

public class EqualsDemo2Test
{
    @Test
    public void test()
    {
        testAccessors();
        testEquals();
    }
    
    private void testAccessors()
    {
        EqualsDemo2 demo        = new EqualsDemo2();
        int         nextIVar1   = 101;
        int         nextIVar2   = 97;
        String      nextSVar    = "lassie";
        
        demo.setIVar1( nextIVar1 );
        demo.setIVar2( nextIVar2 );
        demo.setSVar( nextSVar );
        
        assertEquals( nextIVar1, demo.getIVar1() );
        assertEquals( nextIVar2, demo.getIVar2() );
        assertEquals( nextSVar, demo.getSVar() );
    }
    
    private void testEquals()
    {
        int         iVar1       = 101;
        int         iVar2       = 99;
        String      sVar        = "lassie";
        EqualsDemo2 demo        = getEqualsDemo2( iVar1, iVar2, sVar );
        EqualsDemo2 notDemo1    = getEqualsDemo2( iVar1 + 1, iVar2, sVar );
        EqualsDemo2 notDemo2    = getEqualsDemo2( iVar1, iVar2 + 1, sVar );
        EqualsDemo2 notDemo3    = getEqualsDemo2( iVar1, iVar2, sVar + 1 );
        EqualsDemo2 isDemo      = getEqualsDemo2( iVar1, iVar2, sVar );
        
        assertFalse( demo.equals( null ) );
        assertTrue( demo.equals( demo ) );
        assertFalse( demo.equals( new Object() ) );
        assertFalse( demo.equals( notDemo1 ) );
        assertFalse( demo.equals( notDemo2 ) );
        assertFalse( demo.equals( notDemo3 ) );
        assertTrue( demo.equals( isDemo ) );
    }
    
    private EqualsDemo2 getEqualsDemo2( int iVar1, int iVar2, String sVar )
    {
        EqualsDemo2 demo        = new EqualsDemo2();
        demo.setIVar1( iVar1 );
        demo.setIVar2( iVar2 );
        demo.setSVar( sVar );
        return demo;
    }
}
