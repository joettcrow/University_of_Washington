package cp120.class3;

import javax.swing.*;

public class ShowMessageDialogDemo1 {
    public static void main(String[] args) {
        String msg = "Jobby is the best and marketing loves him!";
//        JOptionPane.showMessageDialog( null, msg);
//        JOptionPane.showMessageDialog(
//                null,
//                msg,
//                "Demo",
//                JOptionPane.WARNING_MESSAGE
//        );
//        System.out.println("Next line of code");
//
//        int rcode = JOptionPane.showConfirmDialog(null, msg);
//        System.out.println(rcode);
//
//        if (rcode == JOptionPane.YES_OPTION) {
//            System.out.println("yes");
//        }
//        else if (rcode == JOptionPane.NO_OPTION) {
//            System.out.println("no");
//        }
        String prompt = "Enter your name";
        String result = null;
        String name = JOptionPane.showInputDialog(null, prompt);
        if (name == null){
            result = "Operation was Cancelled";
        }
        else if (name.isEmpty()){
            result = "What's wrong?  Forgot your name? ";
        }
        else {
            result = "Your name is " + name;
        }
        JOptionPane.showMessageDialog( null, result);
    }
}
