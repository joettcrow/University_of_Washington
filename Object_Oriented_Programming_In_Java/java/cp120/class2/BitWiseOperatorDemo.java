package cp120.class2;

public class BitWiseOperatorDemo {
    public static short buildAddressRegister(
            int drive, int cylinder, int head, int sector
    ){
        short address = (short)sector;

        address = (short)(address | head << 4);
        address = (short)(address | cylinder << 5);
        address = (short)(address | drive << 13);

        return address;
    }

    private static void parseAddressRegister( short reg){
        int temp = reg;
        int sector = temp & 0xf;

        temp = temp >>> 4;
        int head = temp & 1;

        temp = temp >>> 1;
        int cylinder = temp & 0xff;

        temp = temp>>> 8;
        int drive = temp & 7;

        String str = "Drive # = " + drive + ", Cylinder = " + cylinder + ", Head = " + head + ", " +
                "Sector = " + sector;
        System.out.println( str);
    }

    public static void main(String[] args) {
        short test = buildAddressRegister( 3, 28, 1, 15 );
        parseAddressRegister( test );
    }
}
