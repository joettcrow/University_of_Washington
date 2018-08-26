package edu.uw.jtc;
import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.dao.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * the manager for the given account, it is used to make all modifications
 * @author jcrowley
 */
public class MyAccountManager implements AccountManager{
    private static final Logger log =
            LoggerFactory.getLogger( MyAccountManager.class );

    private AccountDao myAccountDao;

    private static final String ENCODING = "ISO-8859-1";
    private static final String ALGORITHM = "SHA-256";

    /**
     * Used to persist an account.
     * @param account the account to persist
     * @throws AccountException if operation fails
     */
    public void persist(Account account) throws AccountException {
            myAccountDao.setAccount(account);
    }

    /**
     * Lookup an account based on account name.
     * @param accountName the name of the desired account
     * @return the account if located otherwise null
     * @throws AccountException if operation fails
     */
    public Account getAccount(String accountName) throws AccountException {
        MyAccount account = null;
        account = (MyAccount) myAccountDao.getAccount(accountName);
        return account;
    }

    /**
     * Remove the account.
     * @param accountName the name of the account to remove
     * @throws AccountException if operation fails
     */
    public void deleteAccount(String accountName) throws AccountException {
        myAccountDao.deleteAccount(accountName);
    }

    /**
     * Creates an account.
     * The creation process should include persisting the account and setting the account manager reference (through the Account registerAccountManager operation).
     * @param name the name for account to add
     * @param password the password used to gain access to the account
     * @param bal the initial balance of the account
     * @return the newly created account
     * @throws AccountException if operation fails
     */
    public Account createAccount(String name, String password, int bal) throws AccountException {
        MyAccount account;
        if (name.length() < 8){
            throw new AccountException("Name too short");
        }
        else if (bal < 100000){
            throw new AccountException("Account must start with $1000");
        }
        else if (myAccountDao.getAccount(name) != null){
            throw new AccountException("Account already exists");
        }
        else {
            MyAccountFactory accountFactory = new MyAccountFactory();

            account = (MyAccount) accountFactory.newAccount(
                    name, 
                    hashPassword(password),
                    bal);
            account.registerAccountManager(this);
//            myAccountDao.setAccount(account);
            persist(account);
        }
        return account;
    }

    private byte[] hashPassword(String password){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
            byte[] encodedhash = digest.digest(
                    password.getBytes("UTF-8"));
            return encodedhash;
        } catch (NoSuchAlgorithmException e) {
            log.warn("No Such Algorithm, looks like SHA-256 doesn't exist, so that's a thing.", e);

        } catch (UnsupportedEncodingException e) {
            log.warn("No Such encoding, I guess UTF 8 isn't a thing anymore...", e);
        }
        return new byte[0];
    }

    /**
     * Check whether a login is valid.
     * An account must exist with the account name and the password must match.
     * @param accountName name of account the password is to be validated for
     * @param password password is to be validated
     * @return true if password is valid for account identified by accountName
     * @throws AccountException if error occurs accessing accounts
     */
    public boolean validateLogin(String accountName, String password) throws AccountException {
        byte[] passwordHash = hashPassword(password);
        MyAccount account = (MyAccount) getAccount(accountName);
        if (account == null){
            return false;
        }
        return Arrays.equals(passwordHash, account.getPasswordHash());
    }

    /**
     * Release any resources used by the AccountManager implementation.
     * Once closed further operations on the AccountManager may fail.
     * @throws AccountException if error occurs accessing accounts
     */
    public void close() throws AccountException {
        myAccountDao.close();
        myAccountDao = null;
    }

    /**
     * Setter for the accounDao for the manager
     * @param myAccountDao the DAO to use.
     */
    public void setMyAccountDao(AccountDao myAccountDao) {
        this.myAccountDao = myAccountDao;
    }
}
