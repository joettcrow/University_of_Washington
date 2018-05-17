package com.scg.beans;

import com.scg.domain.Consultant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.EventListenerList;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Optional;

/**
 * An object of this class is responsible for managing the pay rate,
 * sick leave hours and vacation hours of a staff consultant.
 * It requires the following methods:
 * @author jcrowley
 */
public class HumanResourceManager {

    private final List<BenefitListener> benefitListeners;
    private final List<TerminationListener> terminationListeners;
    private final List<EventListener> eventListeners = new ArrayList<>();
    private final EventListenerList listeners = new EventListenerList();
    private static final Logger LOGGER =
            LoggerFactory.getLogger(HumanResourceManager.class);

    private void fireTerminationEvent( TerminationEvent event ){
        if (event.isVoluntary()){
            for (TerminationListener listener: terminationListeners) {
                listener.voluntaryTermination(event);
            }
        }
        else {
            for (TerminationListener listener: terminationListeners) {
                listener.forcedTermination(event);
            }
        }
    }

    private void fireBenefitEvent(BenefitEvent event){
        if (event.dentalStatus().equals(Optional.of(true))){
            for (BenefitListener listener: benefitListeners) {
                listener.enrollDental(event);
            }
        }
        else if (event.dentalStatus().equals(Optional.of(false))){
            for (BenefitListener listener: benefitListeners) {
                listener.cancelDental(event);
            }
        }

        if (event.medicalStatus().equals(Optional.of(true))){
            for (BenefitListener listener: benefitListeners) {
                listener.enrollMedical(event);
            }
        }
        else if (event.medicalStatus().equals(Optional.of(false))){
            for (BenefitListener listener: benefitListeners) {
                listener.cancelMedical(event);
            }
        }
    }

    /**
     * Constructor for the HumanResourceManager, it does things probably...
     */
    public HumanResourceManager() {
        terminationListeners = new ArrayList<>();
        benefitListeners = new ArrayList<>();
    }

    /**
     * Processes the voluntary termination of a staff consultant.
     *  Fires a voluntary termination event to termination listeners.
     * @param consultant The consultant who is resigning.
     */
    public void acceptResignation(Consultant consultant ){
        TerminationEvent event = new TerminationEvent(this, consultant,true);
        fireTerminationEvent(event);
    }

    /**
     * Processes the involuntary termination of a staff consultant.
     * Fires an involuntary termination event to termination listeners.
     * @param consultant The consultant who has been terminated.
     */
    public void terminate( Consultant consultant ){
        TerminationEvent event = new TerminationEvent(this, consultant,false);
        fireTerminationEvent(event);
    }

    /**
     * Attempts to change the pay rate of a staff consultant.
     * Must log a message recording whether or not the change was vetoed.
     * @param consultant The consultant whose pay rate is to be changed.
     * @param newPayRate The proposed new pay rate.
     */
    public void adjustPayRate( StaffConsultant consultant, int newPayRate ){
        String message = "Pay rate change for: " + consultant.getName().toString() + "; ";
        try {
            consultant.setPayRate(newPayRate);
            message = message + "approved";
        }
        catch (PropertyVetoException e) {
            message = message + "vetoed";
        }
        LOGGER.info(message);
    }

    /**
     * Changes the sick leave hours for a given staff consultant.
     * @param consultant The given staff consultant.
     * @param newSickLeaveHours The new sick leave hours.
     */
    public void adjustSickLeaveHours( StaffConsultant consultant, int newSickLeaveHours ){
        consultant.setSickLeaveHours(newSickLeaveHours);
    }

    /**
     * Changes the vacation hours for a given staff consultant.
     * @param consultant The given staff consultant.
     * @param newVacationHours The new vacation hours.
     */
    public void adjustVacationHours( StaffConsultant consultant, int newVacationHours ){
        consultant.setVacationHours(newVacationHours);
    }

    /**
     * Enrolls a given consultant in the corporate medical plan.
     * Fires a BenefitEvent to BenefitListeners.
     * @param consultant The given consultant.
     */
    public void enrollMedical( Consultant consultant ) {
        LocalDate date = LocalDate.now();
        BenefitEvent event = BenefitEvent.enrollMedical( consultant, date);
        fireBenefitEvent( event );
    }

    /**
     * Cancels a given consultant's enrollment in the corporate medical plan.
     * Fires a BenefitEvent to BenefitListeners.
     * @param consultant The given consultant.
     */
    public void cancelMedical( Consultant consultant ) {
        LocalDate date = LocalDate.now();
        BenefitEvent event = BenefitEvent.cancelMedical( consultant, date);
        fireBenefitEvent( event );
    }

    /**
     * Enrolls a given consultant in the corporate medical plan.
     * Fires a BenefitEvent to BenefitListeners.
     * @param consultant The given consultant.
     */
    public void enrollDental( Consultant consultant ) {
        LocalDate date = LocalDate.now();
        BenefitEvent event = BenefitEvent.enrollDental( consultant, date);
        fireBenefitEvent( event );
    }

    /**
     * Cancels a given consultant's enrollment in the corporate medical plan.
     * Fires a BenefitEvent to BenefitListeners.
     * @param consultant The given consultant.
     */
    public void cancelDental( Consultant consultant ){
        LocalDate date = LocalDate.now();
        BenefitEvent event = BenefitEvent.cancelDental( consultant, date);
        fireBenefitEvent( event );
    }

    /**
     * Registers a listener for termination events.
     * @param listener the listener to register
     */
    public void addTerminationListener( TerminationListener listener ){
        terminationListeners.add(listener);
    }

    /**
     * Deregisters a listener for termination events.
     * @param listener the listener to deregister
     */
    public void removeTerminationListener( TerminationListener listener ){
        terminationListeners.remove(listener);
    }

    /**
     * Registers a listener for changes to benefits events.
     * @param listener the listener to register
     */
    public void addBenefitListener( BenefitListener listener ){
        benefitListeners.add(listener);
    }

    /**
     * Deregisters a listener for termination events.
     * @param listener the listener to deregister
     */
    public void removeBenefitListener( BenefitListener listener ){
        benefitListeners.remove(listener);
    }




}
