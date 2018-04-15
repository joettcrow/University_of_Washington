package cp120.class7;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * @author jcrowley
 */

public class FrameDemo implements Runnable{
    private JFrame frame = new JFrame();

    public static void main(String[] args) {
        FrameDemo demo = new FrameDemo();
        SwingUtilities.invokeLater(demo);
//        pause( 2000 );
//        demo.execute();
    }

    public void run(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new DrawingSurface() );
        frame.pack();
        frame.setVisible(true);
    }

//    private void execute(){
//        Graphics2D gtx = (Graphics2D) frame.getContentPane().getGraphics();
//        gtx.setColor(Color.red);
//        gtx.fillOval(50,50, 256, 256);
//
//    }
//
//    private static void pause(int millis){
//        try {
//            Thread.sleep(millis);
//        }
//        catch (InterruptedException exc){
//
//        }
//    }

    private class DrawingSurface extends JPanel{
        public DrawingSurface(){
            Dimension dim = new Dimension(500, 500);
            setPreferredSize(dim);
        }

        public void paintComponent(Graphics param){
            Graphics2D gtx = (Graphics2D)param;
            gtx.setColor(Color.red);
            Arc2D arc = new Arc2D.Double(
                100,
                100,
                300,
                500,
                90,
                120,
                Arc2D.OPEN);
            gtx.fill(arc);
            gtx.drawString("Commiseration", 300, 600);
        }
    }
}
