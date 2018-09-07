package edu.uw.jtc;
import edu.uw.ext.framework.account.Address;

/**
 * My implementation of the abstract address class
 * It defines an address class
 * @author jcrowley
 */
public class MyAddress implements Address {
    private static final long serialVersionUID = 1758857926305938048L;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Gets city.
     *
     * @return Value of city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets state.
     *
     * @return Value of state.
     */
    public String getState() {
        return state;
    }

    /**
     * Gets streetAddress.
     *
     * @return Value of streetAddress.
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets new state.
     *
     * @param state New value of state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zipCode.
     *
     * @return Value of zipCode.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets new city.
     *
     * @param city New value of city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets new streetAddress.
     *
     * @param streetAddress New value of streetAddress.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets new zipCode.
     *
     * @param zipCode New value of zipCode.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * show's a formal toString
     * @return string
     */
    @Override
    public String toString() {
        return "MyAddress{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
