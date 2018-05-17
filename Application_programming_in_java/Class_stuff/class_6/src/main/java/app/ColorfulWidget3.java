package app;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Same as ColorfulWidget, but with separate method for firing
 * VC events.
 *
 * @author jack
 *
 */
public class ColorfulWidget3
{
    private static final Logger log =
            LoggerFactory.getLogger( ColorfulWidget3.class );

    public static final String  COLOR_PROP_NAME = "Color";

    private final PropertyChangeSupport pcSupport   =
            new PropertyChangeSupport( this );
    private final VetoableChangeSupport vcSupport   =
            new VetoableChangeSupport( this );

    private Color   color;

    public ColorfulWidget3( Color color )
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor( Color color )
            throws PropertyVetoException
    {
        log.debug( color.toString() );
        Color   oldVal  = this.color;
        PropertyChangeEvent evt =
                new PropertyChangeEvent( this, COLOR_PROP_NAME, oldVal, color );
        vcSupport.fireVetoableChange( evt );
        pcSupport.firePropertyChange( evt );
    }

    public void addPropertyChangeListener( PropertyChangeListener listener )
    {
        pcSupport.addPropertyChangeListener( listener );
    }

    public void removePropertyChangeListener( PropertyChangeListener listener )
    {
        pcSupport.removePropertyChangeListener( listener );
    }

    public void addVetoableChangeListener( VetoableChangeListener listener )
    {
        vcSupport.addVetoableChangeListener( listener );
    }

    public void removeVetoableChangeListener( VetoableChangeListener listener )
    {
        vcSupport.removeVetoableChangeListener( listener );
    }
}
