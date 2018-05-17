package com.scg.beans;

import java.util.EventListener;

/**
 * An object of this type is used as a callback to process termination events.
 * @author jcrowley
 */
public interface TerminationListener extends EventListener{

    /**
     * Invoked when a consultant is being involuntarily terminated.
     * @param event Object that describes the event.
     */
    public abstract void forcedTermination( TerminationEvent event );

    /**
     * Invoked when a consultant is being voluntarily terminated.
     * @param event Object that describes the event.
     */
    public abstract void voluntaryTermination( TerminationEvent event );
}
