package app;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Same as ColorfulWidget, but with separate method for firing
 * VC events.
 *
 * @author jack
 *
 */
public class ColorfulWidget2
{
    private static final Logger log =
            LoggerFactory.getLogger( ColorfulWidget2.class );

    public static final String  COLOR_PROP_NAME = "Color";

    private final List<PropertyChangeListener>  pcListeners;
    private final List<VetoableChangeListener>  vcListeners;

    private Color   color;

    public ColorfulWidget2( Color color )
    {
        this.color = color;
        pcListeners = new ArrayList<>();
        vcListeners = new ArrayList<>();
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
        fireVetoableChange( evt );
        pcListeners.forEach( l -> l.propertyChange( evt ) );
    }

    public void addPropertyChangeListener( PropertyChangeListener listener )
    {
        pcListeners.add( listener );
    }

    public void removePropertyChangeListener( PropertyChangeListener listener )
    {
        pcListeners.remove( listener );
    }

    public void addVetoableChangeListener( VetoableChangeListener listener )
    {
        vcListeners.add( listener );
    }

    public void removeVetoableChangeListener( VetoableChangeListener listener )
    {
        vcListeners.remove( listener );
    }

    private void fireVetoableChange( PropertyChangeEvent event )
            throws PropertyVetoException
    {
        try
        {
            for ( VetoableChangeListener listener : vcListeners )
                listener.vetoableChange( event );
        }
        catch ( PropertyVetoException exc )
        {
            PropertyChangeEvent revEvent    =
                    new PropertyChangeEvent( event.getSource(),
                            event.getPropertyName(),event.getOldValue(),
                            event.getNewValue()
                    );
            for ( VetoableChangeListener listener : vcListeners )
            {
                try
                {
                    listener.vetoableChange( revEvent );
                }
                catch ( PropertyVetoException exc2 )
                {
                    // ignore
                }
            }
            throw exc;
        }
    }
}
