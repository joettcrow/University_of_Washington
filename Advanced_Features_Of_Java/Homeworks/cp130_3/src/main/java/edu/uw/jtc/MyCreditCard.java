package edu.uw.jtc;
import edu.uw.ext.framework.account.*;

/**
 * My implementation for a credit card
 * @author jcrowley
 */
public class MyCreditCard implements CreditCard{

    private static final long serialVersionUID = -5534552389838543672L;
    private String issuer;
    private String type;
    private String holder;
    private String accountNumber;
    private String expirationDate;


    /**
     * Sets new expirationDate.
     *
     * @param expirationDate New value of expirationDate.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets expirationDate.
     *
     * @return Value of expirationDate.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets new type.
     *
     * @param type New value of type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets accountNumber.
     *
     * @return Value of accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets issuer.
     *
     * @return Value of issuer.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Sets new accountNumber.
     *
     * @param accountNumber New value of accountNumber.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets type.
     *
     * @return Value of type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets holder.
     *
     * @return Value of holder.
     */
    public String getHolder() {
        return holder;
    }

    /**
     * Sets new issuer.
     *
     * @param issuer New value of issuer.
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Sets new holder.
     *
     * @param holder New value of holder.
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }
}
