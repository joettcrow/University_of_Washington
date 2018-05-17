package app;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

import app.ColorfulWidget3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VetoableChangeEventExample
        implements VetoableChangeListener, PropertyChangeListener
{
    private static final Logger log =
            LoggerFactory.getLogger( VetoableChangeEventExample.class );

    //    private final ColorfulWidget    widget;
//    private final ColorfulWidget2  widget;
    private final ColorfulWidget3 widget;
    private final List<Color>       allowedColors;
    private Color[]                 colorsArray =
            { Color.RED, Color.BLUE, Color.GREEN };

    public static void main( String[] args )
    {
        VetoableChangeEventExample  example = new VetoableChangeEventExample();
        example.changeColor( Color.GREEN );
        example.changeColor( Color.BLACK );
    }

    public VetoableChangeEventExample()
    {
        allowedColors = new ArrayList<>();
        for ( Color color : colorsArray )
            allowedColors.add( color );
        widget = new ColorfulWidget3( Color.RED );
        widget.addPropertyChangeListener( this );
        widget.addVetoableChangeListener( this );
    }

    public void changeColor( Color color )
    {
        log.info( "attempting to change color to " + color );
        try
        {
            widget.setColor( color );
            log.info( "color change succeeded" );
        }
        catch ( PropertyVetoException exc )
        {
            log.info( "color change failed" );
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String  name    = evt.getPropertyName();
        if ( name.equals( ColorfulWidget2.COLOR_PROP_NAME ) )
        {
            Color   newVal  = (Color)evt.getNewValue();
            Color   oldVal  = (Color)evt.getOldValue();
            String  msg     =
                    "Changing color from " + oldVal + " to " + newVal;
            log.info( msg );
        }
    }

    @Override
    public void vetoableChange( PropertyChangeEvent evt )
            throws PropertyVetoException
    {
        String  name    = evt.getPropertyName();
        if ( name.equals( ColorfulWidget2.COLOR_PROP_NAME ) )
        {
            Color   color   = (Color)evt.getNewValue();
            if ( allowedColors.contains( color ) )
                log.info( "allowing color change" );
            else
            {
                log.info( "vetoing color change" );
                throw new PropertyVetoException( "vetoed", evt );
            }
        }
    }
}
