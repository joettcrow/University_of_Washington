package com.scg.beans;

import java.util.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An object of this type is used as a callback
 * to process changes in a consultant's medical and dental benefits.
 * @author jcrowley
 */
public interface BenefitListener extends EventListener {

    /**
     * This method is invoked when a consultant's enrollment in the company's dental plan
     * has been cancelled.
     * @param event A description of the event.
     */
    public void cancelDental( BenefitEvent event );

    /**
     * This method is invoked when a consultant has been enrolled in the company's dental plan.
     * @param event A description of the event.
     */
    public void enrollDental( BenefitEvent event );

    /**
     * This method is invoked when a consultant's enrollment in the company's medical plan
     * has been cancelled.
     * @param event A description of the event.
     */
    public void cancelMedical( BenefitEvent event );

    /**
     * This method is invoked when a consultant has been enrolled in the company's medical plan
     * @param event A description of the event.
     */
    public void enrollMedical( BenefitEvent event );


}
