package app;

import java.beans.PropertyChangeListener;

/**
 * @author jcrowley
 */

public interface OilChangeListener extends PropertyChangeListener{
    public void oilChange( OilChangeEvent event);
}
