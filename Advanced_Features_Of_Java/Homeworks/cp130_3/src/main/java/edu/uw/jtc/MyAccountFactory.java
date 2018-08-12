package edu.uw.jtc;
import edu.uw.ext.framework.account.*;

/**
 * Factory for the creation of accounts
 * @author jcrowley
 */
public class MyAccountFactory implements AccountFactory{

    /**
     * Creates an account and sets values for it
     * @param name the name of the account
     * @param hashedPassword the password hash of the account
     * @param balance the initial balance of the account
     * @return the newly instantiated account, or null if unable to instantiate the account
     */
    public Account newAccount(String name, byte[] hashedPassword, int balance) {
        MyAccount account = new MyAccount();
        try {
            account.setName(name);
        }
        catch (AccountException e){
            return null;
        }
        account.setPasswordHash(hashedPassword);
        if (balance<100000){
            return null;
        }
        account.setBalance(balance);
        return account;
    }
}
