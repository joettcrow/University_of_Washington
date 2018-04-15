package misc;

public class TwoDArrayDemo1
{

    public static void main(String[] args)
    {
        String[][] demo    = new String[10][15];
        for ( int inx = 0 ; inx < demo.length ; ++inx )
            for ( int jnx = 0 ; jnx < demo[inx].length ; ++jnx )
                demo[inx][jnx] = "Row " + inx +", Col " + jnx;

        for ( int inx = 0 ; inx < demo.length ; ++inx )
            for ( int jnx = 0 ; jnx < demo[inx].length ; ++jnx )
                System.out.println(  demo[inx][jnx] ); 
    }

}
