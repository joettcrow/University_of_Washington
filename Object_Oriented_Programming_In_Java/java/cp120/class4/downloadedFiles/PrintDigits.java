package cp120.class4.downloadedFiles;

public class PrintDigits
{
    public static void main(String[] args)
    {
        printDigits( -4530 );
    }

    private static void printDigits( int num )
    {
        printTime( 5, 63, 75 );
    }
    
private static void printTime( int hours, int mins, int secs )
{
    int nSecs   = secs;
    int nMins   = mins;
    int nHours  = hours;
    
    nMins += nSecs / 60;
    nSecs %= 60;
    nHours += nMins / 60;
    nMins %= 60;
    nHours %= 24;
    String  time    =
        nHours + ":" + nMins + ":" + nSecs;
    System.out.println(  time );      
}
}
