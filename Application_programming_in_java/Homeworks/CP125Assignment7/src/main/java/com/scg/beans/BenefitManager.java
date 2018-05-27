package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * An object of this type is used as a callback to process changes
 * in a consultant's medical and dental benefits
 * @author jcrowley
 */
public class BenefitManager implements PropertyChangeListener, BenefitListener {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(BenefitManager.class);

    /**
     * This method is invoked when a consultant's enrollment in the company's dental plan
     * has been cancelled.
     * @param event A description of the event.
     */
    public void cancelDental(BenefitEvent event) {
        LOGGER.info("Dental, cancelled: " +
                event.getConsultant().toString() +
                "; effective date: " +
                event.getEffectiveDate().toString());
    }

    /**
     * This method is invoked when a consultant has been enrolled in the company's dental plan.
     * @param event A description of the event.
     */
    public void enrollDental(BenefitEvent event) {
        LOGGER.info("Dental, enrolled: " +
                event.getConsultant().toString() +
                "; effective date: " +
                event.getEffectiveDate().toString());
    }

    /**
     * This method is invoked when a consultant's enrollment in the company's medical plan
     * has been cancelled.
     * @param event A description of the event.
     */
    public void cancelMedical(BenefitEvent event) {
        LOGGER.info("Medical, cancelled: " +
                event.getConsultant().toString() +
                "; effective date: " +
                event.getEffectiveDate().toString());
    }

    /**
     * This method is invoked when a consultant has been enrolled in the company's medical plan
     * @param event A description of the event.
     */
    public void enrollMedical(BenefitEvent event) {
        LOGGER.info("Medical, enrolled : " +
                event.getConsultant().toString() +
                "; effective date: " +
                event.getEffectiveDate().toString());
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        LOGGER.info(evt.getPropertyName() + ": " +
                evt.getSource().toString() +
                "; old value: " +
                evt.getOldValue() +
                ", new value: " +
                evt.getNewValue());
    }
}
