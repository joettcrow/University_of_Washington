package com.scg.domain;

/**
 * This interface provides access to the name and status of an account.
 * The status may be billable or non-billable.
 * @author jcrowley
 */
public interface Account {
    /**
     * Returns the name of the account.
     * @return the name of the account.
     */
    public String getName();

    /**
     * Returns true if the account is billable, false otherwise.
     * @return true if the account is billable, false otherwise.
     */
    public boolean isBillable();
}
