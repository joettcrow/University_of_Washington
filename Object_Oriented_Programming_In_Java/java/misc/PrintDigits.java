package misc;

public class PrintDigits {
    public static void main(String[] args) {

//        printDigits( 53892);

//        int inx = 5;
//        int jnx = ++inx *3;
//        System.out.println( inx + ", " + jnx );

        byte j = (byte) 0b010011;
        byte k = (byte) 0b010011;

        System.out.println( (byte)j );

    }

    private static void printDigits( int param ){
        int temp = param;

        while ( temp != 0){
            int digit = temp % 10;
            temp = temp/10;
            System.out.println( digit );
        }
    }
}
