package com.scg.beans;

import com.scg.domain.Consultant;
import com.scg.util.PersonalName;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * An object of this class describes a staff consultant,
 * a consultant who gets a salary and benefits.
 * @author jcrowley
 */
public class StaffConsultant extends Consultant {
    private double payRate;
    private int sickLeaveHours;
    private int vacationHours;

    private static final String PAY_RATE_PROPERTY_NAME = "payRate";
    private static final String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";
    private static final String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

    private List<PropertyChangeListener> listeners = new ArrayList<>();
    private List<VetoableChangeListener> vetoableChangeListeners = new ArrayList<>();

    /**
     * Constuctor for the staff consultant to do things
     * @param name the name of the staff consultant
     * @param payRate the pay rate of the staff consultant
     * @param sickLeaveHours the sick leave for the staff consultant
     * @param vacationHours the vacation hours for the staff consultant
     * @throws IllegalArgumentException if the name is null
     */
    public StaffConsultant(
            PersonalName name,
            double payRate,
            int sickLeaveHours,
            int vacationHours
    ) throws IllegalArgumentException {
        super(name);
        this.payRate = payRate;
        this.sickLeaveHours = sickLeaveHours;
        this.vacationHours = vacationHours;
    }

    /**
     * Returns the payRate of the StaffConsultant
     * @return the payRate of the StaffConsultant
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * Sets the payRate of the StaffConsultant
     * @param payRate the new payRate
     * @throws PropertyVetoException if the pay rate is more than a 5% increase
     */
    public void setPayRate(double payRate) throws PropertyVetoException {
        double oldPayRate = this.payRate;
        this.payRate = payRate;
        fireVetoableChangeEvent(
                PAY_RATE_PROPERTY_NAME,
                oldPayRate,
                payRate
        );
        fireChangeEvent(
                this.getName(),
                PAY_RATE_PROPERTY_NAME,
                oldPayRate,
                payRate);
    }

    /**
     * Returns the SickLeaveHours of the StaffConsultant
     * @return the SickLeaveHours of the StaffConsultant
     */
    public int getSickLeaveHours() {
        return sickLeaveHours;
    }

    /**
     * Sets the SickLeaveHours of the StaffConsultant
     * @param sickLeaveHours the new SickLeaveHours
     */
    public void setSickLeaveHours(int sickLeaveHours) {
        int oldSickLeaveHours = this.sickLeaveHours;
        this.sickLeaveHours = sickLeaveHours;
        fireChangeEvent(
                this.getName(),
                SICK_LEAVE_HOURS_PROPERTY_NAME,
                oldSickLeaveHours,
                sickLeaveHours
        );
    }

    /**
     * Returns the vacationHours of the StaffConsultant
     * @return the vacationHours of the StaffConsultant
     */
    public int getVacationHours() {
        return vacationHours;
    }

    /**
     * Sets the VacationHours of the StaffConsultant
     * @param vacationHours the new vacationHours
     */
    public void setVacationHours(int vacationHours) {
        int oldVacationHours = this.vacationHours;
        this.vacationHours = vacationHours;
        fireChangeEvent(
                this.getName(),
                VACATION_HOURS_PROPERTY_NAME,
                oldVacationHours,
                vacationHours
        );
    }

    /**
     * Registeres a new propertychange listener
     * @param listener the listener to register
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        listeners.add(listener);
    }

    /**
     * Deregisters a property change listener
     * @param listener the listener to deregister
     */
    public void removeProperyChangeListener(PropertyChangeListener listener){
        listeners.remove(listener);
    }

    /**
     * Adds a vetoable change listener
     * @param listener the vetoable change listener to add
     */
    public void addVetoableChangeListener(VetoableChangeListener listener){
        vetoableChangeListeners.add(listener);
    }

    /**
     * removes a vetoable change listener
     * @param listener the vetoable change listener to remove
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener){
        vetoableChangeListeners.remove(listener);
    }

    private void fireChangeEvent(
            PersonalName personalName,
            String name,
            Object oldVal,
            Object newVal) {
        PropertyChangeEvent event = new PropertyChangeEvent(
                personalName,
                name,
                oldVal,
                newVal);
        listeners.forEach( l -> l.propertyChange(event) );
    }

    private void fireVetoableChangeEvent( String name, Object oldVal, Object newVal ) throws
            PropertyVetoException{
        PropertyChangeEvent event = new PropertyChangeEvent (
                this,
                name,
                oldVal,
                newVal);
        try
        {
            for ( VetoableChangeListener listener : vetoableChangeListeners )
                listener.vetoableChange( event );
        }
        catch ( PropertyVetoException exc )
        {
            PropertyChangeEvent revEvent = new PropertyChangeEvent(
                    event.getSource(),
                    event.getPropertyName(),event.getOldValue(),
                    event.getNewValue()
                    );
            for ( VetoableChangeListener listener : vetoableChangeListeners )
            {
                try
                {
                    this.setPayRate((Double) oldVal);
                    listener.vetoableChange( revEvent );
                }
                catch ( PropertyVetoException exc2 ){// ignore
                }
            }
            throw exc;
        }
    }
}

