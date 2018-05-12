package com.scg.domain;

import com.scg.util.PersonalName;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

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

    /**
     * Returns a readable string containing the properties of this object.
     * Must be properly formatted for printing on an invoice.
     * @return Readable string containing the properties of this object.
     */
    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String df = dateTimeFormatter.format(date);

        String charge = new DecimalFormat("###,###.00").format(this.getCharge());
        String fmt = "%-10s  %-28s  %-18s  %5d  %10s";
        formatter.format(
                fmt,
                df,
                consultant.getName().toString(),
                skill.toString(),
                hours,
                charge
                );

        return bldr.toString();
    }
}
