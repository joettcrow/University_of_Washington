package misc;

public class SwitchDemo1
{
    public static void main(String[] args)
    {
        for ( int inx = 1 ; inx <= 13 ; ++inx )
        {
            String  month   = getMonth( inx );
            int     num     = getMonth( month );
            System.out.printf( "%2d = %s\n", num, month );
        }
    }

    private static String getMonth( int num )
    {
        String  month   = null;
        
        switch ( num )
        {
        case 1:
            month = "January";
            break;
        case 2:
            month = "February";
            break;
        case 3:
            month = "March";
            break;
        case 4:
            month = "April";
            break;
        case 5:
            month = "May";
            break;
        case 6:
            month = "June";
            break;
        case 7:
            month = "July";
            break;
        case 8:
            month = "August";
            break;
        case 9:
            month = "September";
            break;
        case 10:
            month = "October";
            break;
        case 11:
            month = "November";
            break;
        case 12:
            month = "December";
            break;
        default:
            month = "invalid month: " + num;
            break;
        }
        
        return month;
    }
    
    private static int getMonth( String month )
    {
        int     num     = -1;
        
        switch ( month.toUpperCase() )
        {
        case "JANUARY":
            num = 1;
            break;
        case "FEBRUARY":
            num = 2;
            break;
        case "MARCH":
            num = 3;
            break;
        case "APRIL":
            num = 4;
            break;
        case "MAY":
            num = 5;
            break;
        case "JUNE":
            num = 6;
            break;
        case "JULY":
            num = 7;
            break;
        case "AUGUST":
            num = 8;
            break;
        case "SEPTEMBER":
            num = 9;
            break;
        case "OCTOBER":
            num = 10;
            break;
        case "NOVEMBER":
            num = 11;
            break;
        case "DECEMBER":
            num = 12;
            break;
        default:
            num = -1;
            break;
        }
        
        return num;
    }
}
