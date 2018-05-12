package app;

import java.beans.PropertyChangeEvent;

/**
 * @author jcrowley
 */
public class OilChangeEvent extends PropertyChangeEvent {

//    private final boolean needed;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public OilChangeEvent(Object source, Object oldVal, Object newVal) {
        super(source, "needed", oldVal, newVal);
//        this.needed = newVal;
    }

//    public boolean isNeeded(){
//        return needed;
//    }

}
