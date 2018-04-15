package cp120.class4.downloadedFiles;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class InstanceOfDemo
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

    public void actionPerformed( ActionEvent evt )
    {
        Object  src = evt.getSource();
        if ( src instanceof JButton )
        {
            JButton button  = (JButton)src;
            button.setBackground( Color.RED );
        }
    }
}
