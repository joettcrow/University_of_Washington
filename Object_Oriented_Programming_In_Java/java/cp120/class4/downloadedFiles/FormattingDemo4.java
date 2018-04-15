package cp120.class4.downloadedFiles;

public class FormattingDemo4
{
    public static void main(String[] args)
    {
        double  radius  = 21.1;
        double  area    = Math.PI * radius * radius;
        double  circum  = Math.PI * radius * 2;
        String  sVarA   = String.format( "%6s = %6.3f", "Area", area );
        String  sVarC   = String.format( "%6s = %6.3f", "Circum", circum );
        
        System.out.println( sVarA );
        System.out.println( sVarC );
    }
}
