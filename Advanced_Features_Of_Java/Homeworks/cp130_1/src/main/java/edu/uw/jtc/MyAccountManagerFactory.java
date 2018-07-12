package edu.uw.jtc;
import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.dao.AccountDao;

/**
 * Factory for the creation of account managers.
 * @author jcrowley
 */
public class MyAccountManagerFactory implements AccountManagerFactory{
    /**
     * Instantiates a new account manager instance.
     * @param accountDao the data access object to be used by the account manager
     * @return a newly instantiated account manager
     */
    public AccountManager newAccountManager(AccountDao accountDao) {
        MyAccountManager accountManager =  new MyAccountManager();
        accountManager.setMyAccountDao((MyAccountDao) accountDao);
        return accountManager;
    }
}
