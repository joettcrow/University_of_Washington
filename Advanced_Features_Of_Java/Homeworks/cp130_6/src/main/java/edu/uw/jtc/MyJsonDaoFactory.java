package edu.uw.jtc;

import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;

/**
 * My implementation of the DAO factory for JSON
 * @author jcrowley
 */
public class MyJsonDaoFactory implements DaoFactory {
    /**
     * Instantiates a new AccountDao object.
     * @return a newly instantiated account DAO object
     * @throws DaoFactoryException if unable to instantiate the DAO object
     */
    public AccountDao getAccountDao() throws DaoFactoryException {
        return new MyJsonAccountDao();
    }
}
