package edu.uw.jtc;
import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * javabean representation of an account
 * @author jcrowley
 */
public class MyAccount implements Account{
    private static final long serialVersionUID = 2577117701854001266L;
    private String name;
    private byte[] passwordHash;
    private int balance;
    private String fullName;
    private Address address;
    private String phone;
    private String email;
    private CreditCard creditCard;
    private transient AccountManager accountManager;
    private Order order;

    private static final Logger log =
            LoggerFactory.getLogger( MyAccountManager.class );

    /**
     * Sets the account manager responsible for persisting/managing this account.
     * This may be invoked exactly once on any given account,
     * any subsequent invocations should be ignored.
     * The account manager member should not be serialized with implementing class object.
     * @param accountManager the account manager
     */
    public void registerAccountManager(AccountManager accountManager) {
        if (this.accountManager == null) {
            this.accountManager = accountManager;
        }
    }

    /**
     * Incorporates the effect of an order in the balance.
     * @param order the order to be reflected in the account
     * @param i the price the order was executed at
     */
    public void reflectOrder(Order order, int i) {
        try {
            this.order = order;
            this.setBalance(balance += i);
            if (accountManager != null){
                accountManager.persist(this);
            }
            else {
                log.error("Account manager not yet initialized", new Exception());
            }
        } catch (AccountException e) {
            log.warn("Failed to persist the account after the adjustment", e);

        }

    }

    /**
     * Gets passwordHash.
     *
     * @return Value of passwordHash.
     */
    public byte[] getPasswordHash() {
        byte[] copy = null;
        if (passwordHash != null){
            copy = new byte[passwordHash.length];
            System.arraycopy(
                    passwordHash,
                    0,
                    copy,
                    0,
                    passwordHash.length
            );
        }
        return copy;
    }

    /**
     * Sets new passwordHash.
     *
     * @param passwordHash New value of passwordHash.
     */
    public void setPasswordHash(byte[] passwordHash) {
        byte[] copy = null;
        if (passwordHash != null){
            copy = new byte[passwordHash.length];
            System.arraycopy(
                    passwordHash,
                    0,
                    copy,
                    0,
                    passwordHash.length
            );
        }
        this.passwordHash = copy;
    }

    /**
     * Gets creditCard.
     *
     * @return Value of creditCard.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets new email.
     *
     * @param email New value of email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) throws AccountException {
        if(name == null || name.length() < 8){
            throw new AccountException("Cannot set name to less than 8 characters");
        }
        else {
            this.name = name;
        }
    }

    /**
     * Sets new balance.
     *
     * @param balance New value of balance.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets address.
     *
     * @return Value of address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets new creditCard.
     *
     * @param creditCard New value of creditCard.
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * Gets fullName.
     *
     * @return Value of fullName.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets email.
     *
     * @return Value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new fullName.
     *
     * @param fullName New value of fullName.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new address.
     *
     * @param address New value of address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets balance.
     *
     * @return Value of balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets new phone.
     *
     * @param phone New value of phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets phone.
     *
     * @return Value of phone.
     */
    public String getPhone() {
        return phone;
    }
}
