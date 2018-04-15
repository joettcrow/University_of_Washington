package cp120.class4;


import javax.swing.*;

public class InputDemo1 {

    public static void main(String[] args) {

        String str = "jim" + "bob" + "alice";
        try {
            String string = getStr();
            System.out.println(string);
        } catch (OpCanceledException e) {
            System.out.println( "Operation Cancelled");
        }

    }

    private static String getStr()
        throws OpCanceledException
    {
        String str =
                JOptionPane.showInputDialog(null, "Enter a string");
        if (str == null)
            throw new OpCanceledException();
        return str;
    }

}
