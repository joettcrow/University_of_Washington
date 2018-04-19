package com.scg.domain;

/**
 * This interface provides access to the name and status of an account.
 * The status may be billable or non-billable.
 * @author jcrowley
 */
public interface Account {
    public String getName();
    public boolean isBillable();
}
