package command;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class AlmostCommandDemo implements Runnable
{
    private JFrame      frame       = new JFrame( "Not Command Demo" );
    
    private OpenItem    openItem    = new OpenItem();
    private ExitItem    exitItem    = new ExitItem();
    
    private ColorButton redButton   = new ColorButton( "red", Color.RED );
    private ColorButton grayButton  = new ColorButton( "Gray", Color.GRAY );
    private ColorButton greenButton = new ColorButton( "Green", Color.GREEN );
    
    private Canvas      canvas      = new Canvas();
    private File        currDir     = null;
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater( new AlmostCommandDemo() );
    }

    @Override
    public void run()
    {
        Container   pane    = frame.getContentPane();
        JMenuBar    menuBar = new JMenuBar();
        JMenu       menu    = new JMenu( "File" );
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        pane.setLayout( new BorderLayout() );
        
        menu.add( openItem );
        menu.add( exitItem );
        menuBar.add( menu );
        frame.setJMenuBar( menuBar );
        
        JPanel  buttonPanel = new JPanel( new GridBagLayout() );
        GridBagConstraints  gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        
        buttonPanel.add( redButton, gbc );
        gbc.gridy++;
        buttonPanel.add( grayButton, gbc );
        gbc.gridy++;
        buttonPanel.add( greenButton, gbc );
        
        pane.add( canvas, BorderLayout.CENTER );
        pane.add( buttonPanel, BorderLayout.WEST );
        
        CallBack    callBack    = new CallBack();
        redButton.addActionListener( callBack );
        grayButton.addActionListener( callBack );
        greenButton.addActionListener( callBack );
        openItem.addActionListener( callBack );
        exitItem.addActionListener( callBack );
        
        frame.pack();
        frame.setVisible( true );
    }
    
    private class CallBack implements ActionListener
    {
        public void actionPerformed( ActionEvent evt )
        {
            Object  src = evt.getSource();
            if ( src instanceof Command )
                ((Command)src).execute();
            canvas.repaint();
        }
    }
    
    private void openImage()
    {
        Image           image   = null;
        JFileChooser    chooser = new JFileChooser( currDir );
        int             rVal    = chooser.showOpenDialog( frame );
        if ( rVal == JFileChooser.APPROVE_OPTION )
        {
            try
            {
                File    file    = chooser.getSelectedFile();
                image = ImageIO.read( file );
                canvas.setImage( image );
                currDir = file.getParentFile();
            }
            catch ( IOException exc )
            {
                JOptionPane.showMessageDialog( frame, exc.getMessage() );
            }
        }
    }
    
    @SuppressWarnings("serial")
    private class Canvas extends JPanel
    {
        private Color   background  = Color.RED;
        private Image   image       = null;
        
        public Canvas()
        {
            Dimension   dim = new Dimension( 700, 500 );
            setPreferredSize( dim );
            LineBorder  border  = new LineBorder( Color.BLACK, 3 );
            setBorder( border );
        }
        
        public void setImage( Image image )
        {
            this.image = image;
        }
        
        public Image getImage()
        {
            return image;
        }
        
        public void setBackgroundColor( Color color )
        {
            background = color;
        }
        
        public Color getBackgroundColor()
        {
            return background;
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            Graphics2D  gtx     = (Graphics2D)graphics.create();
            int         width   = getWidth();
            int         height  = getHeight();
            gtx.setColor( background );
            gtx.fillRect( 0,  0,  width,  height );
            
            if ( image != null )
            {
                int iWidth  = image.getWidth( this );
                int iHeight = image.getHeight( this );
                gtx.drawImage( image, 0, 0, iWidth, iHeight, this );
            }
        }
    }
    
    private interface Command
    {
        public void execute();
    }
    
    private class ColorButton extends JButton
        implements Command
    {
        private final Color color;
        
        public ColorButton( String text, Color color )
        {
            super( text );
            this.color = color;
        }
        
        public void execute()
        {
            canvas.setBackgroundColor( color );
        }
    }
    
    private class ExitItem extends JMenuItem
        implements Command
    {
        public ExitItem()
        {
            super( "Exit" );
        }
        
        public void execute()
        {
            System.exit( 0 );
        }
    }
    
    private class OpenItem extends JMenuItem
        implements Command
    {
        public OpenItem()
        {
            super( "Open" );
        }
        
        public void execute()
        {
            openImage();
        }
    }
}
