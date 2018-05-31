package threads;

public class WaitingDemo
{
    private static final Object flourMonitor    = new Object();
    private static final Object masterMonitor   = new Object();
    
    private static volatile boolean isRunning   = true;
    
    private int                 flourGrams      = 0;
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

    public int getFlourGrams()
    {
        return flourGrams;
    }
    
    public void setFlourGrams( int flourGrams )
    {
        this.flourGrams = flourGrams;
    }
    
    public int removeFlour( int grams )
    {
        int rval    = 0;
        if ( grams < flourGrams )
        {
            rval = grams;
            flourGrams -= grams;
        }
        return rval;
    }
    
    public void addFlour( int grams )
    {
        flourGrams += grams;
    }
    
    private class PieMan implements Runnable
    {
        public void run()
        {
            getFlour();
            bake();
        }
        
        private void getFlour()
        {
            int grams   = 0;
            while ( isRunning && (grams= removeFlour( 3 )) == 0 )
            {
                synchronized ( flourMonitor )
                {
                    if ( (grams = removeFlour( 3 )) == 0 )
                    {
                        try
                        {
                            flourMonitor.wait();
                        }
                        catch ( InterruptedException exc )
                        {
                        }
                    }
                }
            }
        }
        
        private void bake()
        {
            synchronized ( masterMonitor )
            {
                try
                {
                    masterMonitor.wait( 50 );
                }
                catch ( InterruptedException exc )
                {
                }
            }
        }
    }
    

}
