package com.scg.domain;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

/**
 * This class encapsulates the properties of an invoice line-item:
 * date: the date of the charge
 * consultant: the consultant responsible for the charge
 * skill: the skill being billed
 * hours: the hours being billed.
 * @author jcrowley
 */
public class InvoiceLineItem {
    private final LocalDate date;
    private final Consultant consultant;
    private final Skill skill;
    private final int hours;

    /**
     * Constructor, sets the date, consultant, skill, and hour for the line item
     * @param date the date associated with this line item as a LocalDate
     * @param consultant the consultant associated with this line item as a Consultant
     * @param skill the skill associated with this line item as a Skill
     * @param hours the hours associated with this line item as an int
     */
    public InvoiceLineItem(
            LocalDate date,
            Consultant consultant,
            Skill skill,
            int hours){
        this.date = date;
        this.consultant = consultant;
        this.skill = skill;
        this.hours = hours;
    }

    /**
     * Returns the value of the charge property.
     * @return the value of the charge property.
     */
    public int getCharge(){
        return (skill.getRate() * hours);
    }

    /**
     * Returns the value of the date property.
     * @return the value of the date property.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the value of the consultant property.
     * @return the value of the consultant property.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Returns the value of the skill property.
     * @return the value of the skill property.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Returns the value of the hours property.
     * @return the value of the hours property.
     */
    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
//         04/01/2004 Jefferson, Bob Joyce Project Manager 4 1,000.00
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);
        String dateFmt = "MM/dd/yyyy";
        String charge = NumberFormat.getNumberInstance(Locale.US).format(this.getCharge());

        return super.toString();
    }
}
