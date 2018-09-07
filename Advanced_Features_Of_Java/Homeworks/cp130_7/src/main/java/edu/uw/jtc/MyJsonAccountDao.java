package edu.uw.jtc;

import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.dao.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json version of accountDao
 * @author jcrowley
 */
public class MyJsonAccountDao implements AccountDao {
    private static final Logger log =
            LoggerFactory.getLogger( MyAccountDao.class );

    private static final String DIRECTORY = "target" + File.separator +"accounts" + File.separator;

    /**
     * Getter for the account, reads in a json file and returns an account object
     * @param accountName the account to read in
     * @return the account
     */
    public Account getAccount(String accountName) {
        File file = new File(DIRECTORY + accountName + File.separator + "account.json");
        final SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(Account.class, MyAccount.class);
        module.addAbstractTypeMapping(Address.class, MyAddress.class);
        module.addAbstractTypeMapping(CreditCard.class, MyCreditCard.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        try {
            return mapper.readValue(file, MyAccount.class);
        } catch (IOException e) {
            log.warn("Error reading from file", e);
        }
        return null;
    }

    /**
     * Writer for the account, writes out a json file representing the account's variables
     * @param account the account to write
     * @throws AccountException if things go wrong
     */
    public void setAccount(Account account) throws AccountException {
        new File(DIRECTORY + account.getName()).mkdirs();

        File file = new File(DIRECTORY +
                        account.getName() +
                        File.separator +
                        "account.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file,account);
        } catch (IOException e) {
            log.warn("", e);

        }
    }

    /**
     * Remove the account.
     * @param accountName the name of the account to be deleted
     * @throws AccountException if operation fails
     */
    public void deleteAccount(String accountName) throws AccountException {
        File file = new File(DIRECTORY + accountName);
        if (!file.exists()){
            throw new AccountException("Account does not exist");
        }
        boolean result = deleteDirectory(file);
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    /**
     * Remove all accounts.
     * This is primarily available to facilitate testing.
     * @throws AccountException
     * if operation fails
     */
    public void reset() throws AccountException {
        File file = new File(DIRECTORY);
        boolean result = deleteDirectory(file);
    }

    /**
     * Close function, it does things, probably
     * @throws AccountException if the things don't get did
     */
    public void close() throws AccountException {

    }
}
