package com.scg.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Encapsulates one week's worth of consultant activity. It has the following properties:
 *
 * The consultant.
 * The starting date for this time card.
 * A list of ConsultantTime objects that describe the consultant's activity.
 * @author jcrowley
 */
public class TimeCard {
    private final Consultant consultant;
    private final LocalDate weekStartingDate;
    private List<ConsultantTime> consultantTimes = new ArrayList<>();

    /**
     * Constructor; sets the consultant and starting date properties for this TimeCard.
     * @param consultant The consultant associated with this time card.
     * @param weekStartingDate The starting date for this time card.
     */
    public TimeCard( Consultant consultant, LocalDate weekStartingDate ){
        this.consultant = consultant;
        this.weekStartingDate = weekStartingDate;
    }

    /**
     * Gets the consultant associated with this time card.
     * @return The consultant associated with this time card.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Gets the week starting date.
     * @return The week starting date.
     */
    public LocalDate getWeekStartingDate() {
        return weekStartingDate;
    }

    /**
     * Gets a list of billable activities for a given client.
     * @param clientName The name of the given client.
     * @return A list of billable activities for a given client.
     */
    public List<ConsultantTime> getBillableHoursForClient( String clientName ){
        List<ConsultantTime> givenClient = new ArrayList<>();
        for (ConsultantTime var:consultantTimes ) {
            if (var.getAccount().getName() == clientName){
                givenClient.add(var);
            }
        }
        return givenClient;
    }

    /**
     * Adds the given time to the list of times kept by this time card.
     * @param time The given time.
     */
    public void addConsultantTime(ConsultantTime time) {
        consultantTimes.add(time);
    }

    /**
     * Gets the total of all billable hours for this time card.
     * @return The total of all billable hours for this time card.
     */
    public int getTotalBillableHours(){
        int totalBillableHours = 0;
        for (ConsultantTime var: consultantTimes) {
            if (!var.getSkill().equals(Skill.UNKNOWN_SKILL)){
                totalBillableHours += var.getHours();
            }
        }
        return totalBillableHours;
    }

    /**
     * Gets the total of all non-billable hours for this time card.
     * @return The total of all non-billable hours for this time card.
     */
    public int getTotalNonBillableHours(){
        int totalNonBillableHours = 0;
        for (ConsultantTime var: consultantTimes) {
            if (var.getSkill().equals(Skill.UNKNOWN_SKILL)){
                totalNonBillableHours += var.getHours();
            }
        }
        return totalNonBillableHours;
    }

    /**
     * Gets the total number of hours reported on this time card.
     * @return The total number of hours reported on this time card.
     */
    public int getTotalHours(){
        int totalHours = getTotalBillableHours() + getTotalNonBillableHours();
        return totalHours;
    }

    private String consultantSectionString(){
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter( bldr );


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        String consultName = "Consultant: " + this.getConsultant().getName().toString();
        String dateFormatted = dateTimeFormatter.format(weekStartingDate);
        String fmt = "%-30s %11s Week Starting: %s" + System.lineSeparator();
        formatter.format(fmt,consultName," ", dateFormatted );
        return bldr.toString() + System.lineSeparator();
    }

    private String billableSectionString(){
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);

        bldr.append("Billable Time:" + System.lineSeparator());
        String fmt = "%-30s  %-10s  %5s  %-20s" + System.lineSeparator();
        formatter.format(fmt,"Account", "Date","Hours","Skill");

        String billableThirdLine = "------------------------------  " +
                "----------  " +
                "-----  " +
                "--------------------" +
                System.lineSeparator();
        bldr.append(billableThirdLine);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        for (ConsultantTime var: consultantTimes) {
            if (!var.getSkill().equals(Skill.UNKNOWN_SKILL)) {
                String df = dateTimeFormatter.format(var.getDate());
                formatter.format(
                        fmt,
                        var.getAccount().getName(),
                        df,
                        var.getHours(),
                        var.getSkill());
            }
        }
        bldr.append(System.lineSeparator());

        return bldr.toString();
    }

    private String nonBillableSectionString(){
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);

        bldr.append("Non-Billable Time:" + System.lineSeparator());
        String fmt = "%-30s  %-10s  %5s" + System.lineSeparator();
        formatter.format(fmt,"Account", "Date","Hours");

        String nonBillableThirdLine = "------------------------------  " +
                "----------  " +
                "-----  " +
                System.lineSeparator();
        bldr.append(nonBillableThirdLine);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        for (ConsultantTime var: consultantTimes) {
            if (var.getSkill().equals(Skill.UNKNOWN_SKILL)) {
                String df = dateTimeFormatter.format(var.getDate());
                formatter.format(
                        fmt,
                        var.getAccount().getName(),
                        df,
                        var.getHours(),
                        var.getSkill());
            }
        }
        bldr.append(System.lineSeparator());

        return bldr.toString();
    }

    private String summarySectionString(){
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);
        bldr.append("Summary:" + System.lineSeparator());
        String fmt = "%-40s%9s" + System.lineSeparator();
        formatter.format(
                fmt,
                "Total Billable:",
                getTotalBillableHours());
        formatter.format(
                fmt,
                "Total Non-Billable:",
                getTotalNonBillableHours());
        formatter.format(
                fmt,
                "Total Hours:",
                getTotalHours());

        return bldr.toString();
    }

    /**
     * Creates a report, suitable for printing, of the time reported on this time card.
     * @return A formatted report of the time reported on this time card
     */
    public String toReportString(){
        StringBuilder cardBld = new StringBuilder();
        String topAndBottom = "==================================" +
                "===================================== " +
                System.lineSeparator();

        cardBld.append(topAndBottom);

        cardBld.append(consultantSectionString());
        cardBld.append(billableSectionString());
        cardBld.append(nonBillableSectionString());
        cardBld.append(summarySectionString());

        cardBld.append(topAndBottom);

        return cardBld.toString();
    }


    /**
     * Overrides Object.toString(); returns a string consisting of the consultant's name,
     * followed by a single space followed by the week starting day
     * @return A string consisting of the name of the consultant,
     * and the week starting day of this time card.
     */
    @Override
    public String toString() {
        String consultantName = this.getConsultant().getName().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = formatter.format(weekStartingDate);
        String resp = consultantName + " " + date;
        return resp;

    }
}
