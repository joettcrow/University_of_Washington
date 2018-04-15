package cp120.class4.downloadedFiles;

public class FormattingDemo3
{

    public static void main(String[] args)
    {
        int     iVar1   = 27;
        int     iVar2   = 3449;
        int     iVar3   = 451;
        
        String  sVar1   = String.format( "Daily total: %4d", iVar1 );
        String  sVar2   = String.format( "Daily total: %4d", iVar2 );
        String  sVar3   = String.format( "Daily total: %4d", iVar3 );
        
        System.out.println( sVar1 );
        System.out.println( sVar2 );
        System.out.println( sVar3 );
    }

}
