package com.scg.beans;

import com.scg.domain.Consultant;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.Optional;

/**
 * An object of this class will be used to notify listeners of a change in enrollment
 * in a the company's medical or dental plans.
 * @author jcrowley
 */
public class BenefitEvent extends EventObject {
    private final Consultant consultant;
    private final LocalDate effectiveDate;
    private final Optional<Boolean> medicalStatus;
    private final Optional<Boolean> dentalStatus;


    /**
     *     /**
     *      * Constructs a prototypical Event.
     *      *
     *      * @param consultant
     *      * @param effectiveDate
     *      * @throws IllegalArgumentException if source is null.
     *
     * @param consultant
     * @param effectiveDate
     * @param medicalStatus
     * @param dentalStatus
     */
    private BenefitEvent(
            Consultant consultant,
            LocalDate effectiveDate,
            Optional<Boolean> medicalStatus,
            Optional<Boolean> dentalStatus) {
        super(consultant);
        this.consultant = consultant;
        this.effectiveDate = effectiveDate;
        this.medicalStatus = medicalStatus;
        this.dentalStatus = dentalStatus;
    }

    /**
     * Returns an object indicating that consultant has been enrolled in the company medical plan.
     * @param consultant The consultant being enrolled.
     * @param effectiveDate The effective date of enrollment.
     * @return an object indicating that consultant has been enrolled in the company medical plan.
     */
    public static BenefitEvent enrollMedical(
            Consultant consultant,
            LocalDate effectiveDate){
        BenefitEvent event = new BenefitEvent(
                consultant,
                effectiveDate,
                Optional.of(true),
                Optional.empty());
        return event;
    }

    /**
     * Returns an object indicating that a consultant's enrollment
     * in the company's dental plan has been cancelled.
     * @param consultant The consultant whose enrollment has been cancelled.
     * @param effectiveDate The effective date of cancellation.
     * @return an object indicating that a consultant's enrollment
     * in the company's dental plan has been cancelled.
     */
    public static BenefitEvent cancelMedical(
            Consultant consultant,
            LocalDate effectiveDate) {
        BenefitEvent event = new BenefitEvent(
                consultant,
                effectiveDate,
                Optional.of(false),
                Optional.empty());
        return event;
    }

    /**
     * If the consultant is being enrolled in the company's medical plan,
     * returns an Optional object with a value of true
     * If the consultant's enrollment in the company's medical plan is being cancelled,
     * returns an Optional object with a value of false.
     * If the event is unrelated to enrollment in the medical plan,
     * returns an empty Optional object
     * @return true or false based on the description
     */
    public Optional<Boolean> medicalStatus(){
        return medicalStatus;
    }

    /**
     * Returns an object indicating that a consultant has been enrolled
     * in the company's dental plan.
     * @param consultant The consultant being enrolled.
     * @param effectiveDate The effective date of enrollment.
     * @return an object indicating that a consultant has been enrolled
     * in the company's dental plan.
     */
    public static BenefitEvent enrollDental(
            Consultant consultant,
            LocalDate effectiveDate) {
        BenefitEvent event = new BenefitEvent(
                consultant,
                effectiveDate,
                Optional.empty(),
                Optional.of(true));
        return event;
    }

    /**
     * Returns an object indicating that a consultant's enrollment
     * in the company's dental plan has been cancelled.
     * @param consultant The consultant whose enrollment has been cancelled.
     * @param effectiveDate The effective date of cancellation.
     * @return an object indicating that a consultant's enrollment
     * in the company's dental plan has been cancelled.
     */
    public static BenefitEvent cancelDental( Consultant consultant, LocalDate effectiveDate ) {
        BenefitEvent event = new BenefitEvent(
                consultant,
                effectiveDate,
                Optional.empty(),
                Optional.of(false));
        return event;
    }


    /**
     * If the consultant is being enrolled in the company's dental plan,
     * returns an Optional object with a value of true
     * If the consultant's enrollment in the company's dental plan is being cancelled,
     * returns an Optional object with a value of false.
     * If the event is unrelated to enrollment in the dental plan,
     * returns an empty Optional object.
     * @return If the consultant is being enrolled in the company's dental plan,
     * returns an Optional object with a value of true
     * If the consultant's enrollment in the company's dental plan is being cancelled,
     * returns an Optional object with a value of false.
     * If the event is unrelated to enrollment in the dental plan,
     * returns an empty Optional object.
     */
    public Optional<Boolean> dentalStatus(){
        return dentalStatus;
    }

    /**
     * Returns the consultant whose status int the medical or dental plan is being changed.
     * @return the consultant whose status int the medical or dental plan is being changed.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Returns the effective date of the status change.
     * @return the effective date of the status change.
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }



}
