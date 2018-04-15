package cp120.class7;

/**
 * @author jcrowley
 */

public class CCC extends BBB{
    public static void main(String[] args) {
        new CCC();
        new CCC(12);
    }

    public CCC(){
        System.out.println("constructing CCC");
    }

    public CCC(int param){
        System.out.println("CCC " + param );
    }
}
