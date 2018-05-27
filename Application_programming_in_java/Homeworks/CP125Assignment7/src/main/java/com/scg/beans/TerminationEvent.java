package com.scg.beans;

import com.scg.domain.Consultant;

import java.util.EventObject;

/**
 * An object of this class will be used to notify listeners of the termination of a consultant.
 * @author jcrowley
 */
public class TerminationEvent extends EventObject {
    private final Consultant consultant;
    private final Boolean voluntary;

    /**
     * Returns the consultant being terminated.
     * @return the consultant being terminated.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Returns true if the consultant is being terminated voluntarily.
     * @return true if the consultant is being terminated voluntarily.
     */
    public Boolean isVoluntary() {
        return voluntary;
    }

    /**
     * Constructor for termination event
     * @param source the source to sent to
     * @param consultant the consultant to be terminated
     * @param voluntary true if the consultant is leaving voluntarily
     */
    public TerminationEvent(Object source, Consultant consultant, boolean voluntary ){
        super(source);
        this.consultant = consultant;
        this.voluntary = voluntary;
    }
}
