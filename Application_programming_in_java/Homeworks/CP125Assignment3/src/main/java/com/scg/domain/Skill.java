package com.scg.domain;

/**
 * This class encapsulates categories of skill for billing customers.
 * Each enum constant has an associated description and hourly rate.
 * The description and billing rate are specified in the constructor of each constant.
 * The names of the constants, and the associated friendly names and billing rates
 * @author jcrowley
 */
public enum Skill {
    /**
     * Project Manager, 250 as hourly cost
     */
    PROJECT_MANAGER("Project Manager",250),

    /**
     * System Architect, 200 as hourly cost
     */
    SYSTEM_ARCHITECT("System Architect", 200),

    /**
     * Software Engineer, 150 as hourly cost
     */
    SOFTWARE_ENGINEER("Software Engineer",150),

    /**
     * Software Tester, 100 as hourly cost
     */
    SOFTWARE_TESTER("Software Tester",100),

    /**
     * Unknown Skill, 0 as hourly cost
     */
    UNKNOWN_SKILL("Unknown Skill",0);

    private final String description;
    private final int rate;

    private Skill(String description, int rate){
        this.description = description;
        this.rate = rate;
    }

    /**
     * Getter method for the skill description
     * @return the description as a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for the skill rate
     * @return the rate as an integer
     */
    public int getRate() {
        return rate;
    }

    /**
     * Overrides the to string to return the description of the skill
     * @return the description as the string.
     */
    @Override
    public String toString() {
        return getDescription();
    }
}
