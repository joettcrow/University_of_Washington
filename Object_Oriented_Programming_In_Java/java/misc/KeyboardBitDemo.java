package misc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class KeyboardBitDemo implements Runnable
{
    private JFrame  frame   = new JFrame( "Keyboard Bit Demo" );
    
    public static void main(String[] args)
    {
        KeyboardBitDemo demo    = new KeyboardBitDemo();
        SwingUtilities.invokeLater( demo );
    }
    
    public void run()
    {
        frame.setDefaultCloseOperation(  JFrame.EXIT_ON_CLOSE  );
        frame.setContentPane(  new MainPanel() );
        frame.pack();
        frame.setVisible( true );
    }

    @SuppressWarnings( "serial" )
    private class MainPanel 
        extends JPanel
        implements KeyListener
    {
        private int         cols        = 60;
        private JTextArea   textArea    = new JTextArea( 24, 60 );
        private String      newl        = System.lineSeparator();
        
        public MainPanel()
        {
            super( new BorderLayout( 5, 5 ) );
            JLabel  label   = new JLabel( "TYPE INTO THE BOX" );
            Font    newFont = label.getFont().deriveFont(  20f );
            label.setFont(  newFont );
            
            JTextField  textBox = new JTextField( cols );
            textBox.addKeyListener(  this  );
            
            textArea.setEditable(  false );
            
            add( label, BorderLayout.NORTH );
            add( textArea, BorderLayout.SOUTH );
            add( textBox, BorderLayout.CENTER );
            
            Border  border  =
                BorderFactory.createEmptyBorder( 10, 10, 10, 10 );            
            setBorder( border );
        }

        @Override
        public void keyPressed( KeyEvent evt )
        {
            int             mods    = evt.getModifiers();
            int             keyCode = evt.getKeyCode();
            String          keyText = KeyEvent.getKeyText( keyCode );
            StringBuilder   bldr    = new StringBuilder();
            bldr.append(  keyText ).append(  ": " );
            bldr.append(  getLabel( "SHIFT", mods, KeyEvent.SHIFT_MASK) );
            bldr.append(  getLabel( "CONTROL", mods, KeyEvent.CTRL_MASK) );
            bldr.append(  getLabel( "ALT", mods, KeyEvent.ALT_MASK ) );
            bldr.append(  getLabel( "META", mods, KeyEvent.META_MASK ) );
            bldr.append(  newl );
            textArea.append(  bldr.toString() );
        }

        @Override
        public void keyReleased(KeyEvent evt)
        {
            // required to satisfy KeyEvent implementation
            // not used
        }

        @Override
        public void keyTyped( KeyEvent evt )
        {
            int mods   = evt.getModifiers();
            if ( (mods & KeyEvent.SHIFT_MASK) != 0 )
                System.out.println(  "shift key down" );
            if ( (mods & KeyEvent.CTRL_MASK) != 0 )
                System.out.println(  "control key down" );
        }

        private String getLabel( String label, int modifiers, int mask )
        {
            StringBuilder   bldr        = new StringBuilder();
            String          downOrUp    =
                (modifiers & mask) == 0 ? "up, " : "down, ";
            bldr.append(  label ).append(  "->" ).append(  downOrUp );
            return bldr.toString();
        }       
    }
}
