package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs each termination event
 * @author jcrowley
 */
public class Eeoc implements TerminationListener {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Eeoc.class);

    private int forcedCount = 0;
    private int voluntaryCount = 0;

    /**
     * Invoked when a consultant is being voluntarily terminated.
     * @param event Object that describes the event.
     */
    public void voluntaryTermination(TerminationEvent event) {
        LOGGER.info("Voluntary termination: " + event.getConsultant().toString());
        voluntaryCount += 1;
    }

    /**
     * Invoked when a consultant is being involuntarily terminated.
     * @param event Object that describes the event.
     */
    public void forcedTermination (TerminationEvent event) {
        LOGGER.info("Involuntary termination: " + event.getConsultant().toString());
        forcedCount +=1;
    }

    /**
     * Returns the number of involuntary terminations.
     * @return the number of involuntary terminations.
     */
    public int forcedTerminationCount(){
        return forcedCount;
    }

    /**
     * Returns the number of voluntary terminations.
     * @return the number of voluntary terminations.
     */
    public int voluntaryTerminationCount(){
        return voluntaryCount;
    }

}
