package com.scg.domain;

import com.scg.util.TimeCardConsultantComparator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
public class TimeCard implements Comparable<TimeCard>, Serializable {
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
            if (clientName.equals(var.getAccount().getName())){
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

    /**
     * Gets the consultant Time list
     * @return the consultant time list
     */
    public List<ConsultantTime> getConsultantTimes() {
        return consultantTimes;
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


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param card the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(TimeCard card) {
//        if (card == null)
//            throw new NullPointerException("Can't compare to nothing");
//
//        int rcode = Comparator.comparing( TimeCard::getConsultant)
//                .thenComparing(TimeCard::getWeekStartingDate)
//                .thenComparing(TimeCard::getTotalBillableHours)
//                .thenComparing(TimeCard::getTotalNonBillableHours)
//                .compare(this,card);

        TimeCardConsultantComparator comparator  =
                new TimeCardConsultantComparator();

        return comparator.compare(this,card);
    }
}
