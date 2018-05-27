package app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SCGDriverTest
{
    @Test
    public void testMain()
    {
        SCGDriver.main( null );
    }

    @Test
    public void testGetLists()
    {
        SCGDriver   driver  = new SCGDriver();
        driver.getClientAccounts();
        driver.getTimeCards();
    }

    @Test
    public void testReset()
    {
        SCGDriver.UniquePersonalName.reset();
        SCGDriver.UniqueClient.reset();
        SCGDriver.UniqueAddress.reset();
    }
}