package cp120.assignments.geo_shape;

import cp120.d_list.DList;
import cp120.d_list.DNode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A simple drawing surface for displaying GeoShapes.
 * @author jcrowley
 */
public class GeoPlane implements Runnable{
    private final DList list = new DList();
    private final Color color;
    private final Panel panel;

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        JFrame frame   = new JFrame( "GeoPlane" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setContentPane( panel );
        frame.pack();
        frame.setVisible( true );
    }

    public GeoPlane(){
        this(new Color( 0.5f, 0.5f, 0.5f));
    }

    public GeoPlane(Color color){
        this.color = color;
        panel = new Panel();
    }

    /**
     * Displays the plane. Any shapes stored in the list of shapes to draw
     * will immediately be drawn. If part or all of the plane requires
     * redrawing due to operator activity, the shapes will automatically
     * be redrawn.
     *
     *  @see #addShape(GeoShape)
     */
    public void show()
    {
        new Thread( this, "GeoPlane Thread" ).start();
    }

    /**
     * Adds a shape to the list of shapes to be drawn on the plane.
     * Note that the shape is not immediately drawn;
     * to draw the shape immediately, call the redraw() method.
     * @param shape the GeoShape shape to add to the list.
     */
    public void addShape( GeoShape shape){
        list.addTail(new DNode(shape));
    }

    /**
     * Removes a shape from the list of shapes to be drawn on the plane.
     * Note that the plane is not immediately redrawn;
     * to redraw the plane immediately, call the redraw() method.
     * @param shape the GeoShape shape to remove from the list.
     * @return boolean value, true, if shape is found and removed; false, otherwise.
     */
    public boolean removeShape(GeoShape shape){
        boolean found = false;
        DNode node = list.getHead();
        while (!found && (node != list)) {
            if (node.getData().equals(shape)) {
                node.remove();
                found = true;
            }
        }
        return found;
    }

    /**
     * Redraw the plane.
     */
    /**
     * Explicitly draws the shapes in the list of shapes. Calling this method
     * has no effect if the plane is not visible.
     */
    public void redraw()
    {
        panel.repaint();
    }

    /**
     * A simple panel for drawing.
     */
    public class Panel extends JPanel
    {
        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = -7413434451145995808L;

        /**
         * Instantiates a new panel.
         */
        public Panel()
        {
            setPreferredSize( new Dimension( 500, 500 ) );
        }

        /* (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         */
        @Override
        public void paintComponent( Graphics graphics )
        {
            Graphics2D  gtx = (Graphics2D)graphics.create();
            gtx.setColor( color );
            gtx.fillRect( 0, 0, getWidth(), getHeight() );
            DNode node = list.getHead();
            for ( ; node != list ; node = node.getNext() )
                ((GeoShape)node.getData()).draw( gtx );
        }
    }
}
