package networking;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class URLExample3
{
    public static void main(String[] args)
    {
        new URLExample3().execute();
    }

    private void execute()
    {
        try
        {
            String  str1        =
                "http://faculty.washington.edu/jstraub/backg.jpg";
            String  str2         =
                "https://img1.wsimg.com/isteam/ip/5a3a197c-d244-4fc5"
                + "-8713-52789b4185d1/Loper%20.jpg/:/rs=w:1400,"
                +  "h:600,cg:true,m/cr=w:1400,h:600,a:cc";
            URL     url1        = new URL( str1 );
            URL     url2        = new URL( str2 );
            Image   image1      = ImageIO.read( url1 );
            Image   image2      = ImageIO.read( url2 );
            new ShowOff( image1 ).execute();
            new ShowOff( image2 ).execute();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private class ShowOff 
        extends JPanel
        implements Runnable
    {
        private static final long serialVersionUID = 2306599905594496914L;
        
        private final Image image;
        
        public ShowOff( Image image )
        {
            this.image = image;
            setPreferredSize( new Dimension( 700, 500 ) );
        }
        
        public void execute()
        {
            new Thread( this, "ImageThread" ).start();
        }
        
        public void run()
        {
            JFrame  frame   = new JFrame( "Display Image from Web" );
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            frame.setContentPane( this );
            frame.pack();
            frame.setVisible( true );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            Graphics2D  gtx     = (Graphics2D)graphics;
            int         width   = getWidth();
            int         height  = getHeight();
            gtx.drawImage( image, 0, 0, width, height, this );
        }
    }
}
