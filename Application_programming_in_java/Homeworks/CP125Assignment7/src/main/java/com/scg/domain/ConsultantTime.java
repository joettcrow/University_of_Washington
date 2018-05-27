package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Encapsulates the details of the time a consultant spends working.
 * This includes both billable hours,
 * such as is spent on work for a client account,
 * and non-billable hours, such as vacation time.
 * It has the following properties:
 * Date
 * Account to Charge
 * Skill level, for calculating charges
 * Hours
 * @author jcrowley
 */
public class ConsultantTime implements Serializable{
    private LocalDate date;
    private Account account;
    private Integer hours;
    private Skill skill;

    /**
     * Constructor; sets the properties of this object.
     * @param date The date the activity was performed.
     * @param account The account to charge.
     * @param skill The skill level for calculating charges.
     *              If the account is non-billable,
     *              the skill should be Skill.unknown skill.
     * @param hours The hours to charge.
     * @throws IllegalArgumentException if hours is less than, or equal to, 0.
     */
    public ConsultantTime(
            LocalDate date,
            Account account,
            Skill skill,
            int hours  )
            throws IllegalArgumentException{
        if (hours <= 0){
            throw new IllegalArgumentException("Hours Cannot be Zero or Negative");
        }
        else {
            this.date = date;
            this.account = account;
            this.skill = skill;
            this.hours = hours;
        }
    }

    /**
     * Gets the date of this object.
     * @return The date of this object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of this object.
     * @param date The new date for this object.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the account for this object.
     * @return The account for this object.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account for this object.
     * @param account The new account for this object.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the hours for this object.
     * @return The hours for this object.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the hours for this object.
     * @param hours The new hours for this object.
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * Gets the skill level for this object.
     * @return The skill for this object.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the skill level for this object.
     * @param skill The Skill for this object.
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Indicates whether the time recorded in this object is billable.
     * @return True if the time recorded in this object is billable,
     * false otherwise.
     */
    public boolean isBillable(){
        Boolean billable = false;
        if (this.skill != Skill.UNKNOWN_SKILL){
            billable = true;
        }
        return billable;
    }

    /**
     * Determines whether this object is equal to a given object.
     * The two objects are equal if they are both concrete ConsultantTime objects,
     * and all corresponding properties are equal.
     * @param o The given object
     * @return True if the this object is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        else if (getClass() != o.getClass()) { return false; }
        if (this == o) {
            return true;
        }
        ConsultantTime that = (ConsultantTime) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(account, that.account) &&
                Objects.equals(hours, that.hours) &&
                skill == that.skill;
    }

    /**
     * Overrides Object.hashCode(); required because equals is overridden.
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date, account, hours, skill);
    }
}
