package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Responsible for monitoring changes to the pay rates of staff consultants.
 * @author jcrowley
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(CompensationManager.class);

    /**
     * Logs the old and new pay rates for the consultant whose benefits have been changed.
     *
     * @param evt An object describing the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LOGGER.info("Changed Salary from: " + evt.getOldValue() + "to: " + evt.getNewValue());
    }

    /**
     * Monitors changes to pay rates of staff consultants.
     * Throws a PropertyVetoException if the new pay rate exceeds the old pay rate by more than 5%.
     *
     * @param evt a <code>PropertyChangeEvent</code> object describing the
     *            event source and the property that has changed.
     * @throws PropertyVetoException If the proposed salary change is greater than 5%
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        double percent = (double)evt.getNewValue() / (double)evt.getOldValue();
        if (percent > 1.05){
            throw new PropertyVetoException("Vetoed",evt);
        }
    }
}
