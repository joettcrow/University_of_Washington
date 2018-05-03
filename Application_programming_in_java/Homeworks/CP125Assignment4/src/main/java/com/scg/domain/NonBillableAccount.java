package com.scg.domain;

/**
 * This enum encapsulates categories of activity that are not billable.
 * Each enum constant has an associated "friendly" name.
 * The friendly name is specified in the constructor of each constant.
 * @author jcrowley
 */
public enum NonBillableAccount implements Account{
    /**
     * Business Development
     */
    BUSINESS_DEVELOPMENT("Business Development"),
    /**
     * Sick Leave
     */
    SICK_LEAVE("Sick Leave"),
    /**
     * Vacation
     */
    VACATION("Vacation");

    private final String name;

    private NonBillableAccount(String name){
        this.name = name;
    }

    /**
     * Getter method for the NonBillableAccount name
     * @return the name from the account
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method that checks if the method is billable
     * @return false no matter what right now
     */
    public boolean isBillable(){
        return false;
    }
}
