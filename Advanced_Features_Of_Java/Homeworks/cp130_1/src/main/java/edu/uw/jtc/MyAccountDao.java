package edu.uw.jtc;

import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.dao.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;

/**
 * Defines the methods needed to store and load accounts from a persistent storage mechanism.
 * The implementing class must provide a no argument constructor.
 * @author jcrowley
 */
public class MyAccountDao implements AccountDao {
    private static final Logger log =
            LoggerFactory.getLogger( MyAccountDao.class );
    
    private static final String DIRECTORY = "target" + File.separator +"accounts" + File.separator;

    /**
     * Lookup an account in based on account name.
     * @param accountName the name of the desired account
     * @return the account if located otherwise null
     */
    public Account getAccount(String accountName) {
        MyAccount account = readAccount(accountName);
        if (account == null){
            return account;
        }
        else {
            account.setAddress(readAddress(accountName));
            account.setCreditCard(readCreditCard(accountName));
            return account;
        }
    }

    private MyAccount readAccount(String accountName){
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(DIRECTORY + accountName + "/account"));
            String name = dataInputStream.readUTF();
            int len = dataInputStream.readInt();

            byte[] passHash = new byte[len];
            dataInputStream.read(passHash);
            int bal = dataInputStream.readInt();
            String fullName = dataInputStream.readUTF();
            String phone = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();

            MyAccount account = new MyAccount();
            account.setName(name);
            account.setBalance(bal);
            account.setPasswordHash(passHash);
            if (!fullName.equals("<null>")){
                account.setFullName(fullName);
            }
            if (!phone.equals("<null>")) {
                account.setPhone(phone);
            }
            if (!email.equals("<null>")) {
                account.setEmail(email);
            }

            return account;

        } catch (FileNotFoundException e) {
            log.info("Account does not exist", e);
            return null;
        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
        } catch (AccountException e) {
            log.warn("Account Name is Invalid", e);

        }
        return null;
    }

    private MyAddress readAddress(String accountName){
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(DIRECTORY + accountName + "/address"));
            String streetAddress = dataInputStream.readUTF();
            if (streetAddress.equals("<null>")){
                return null;
            }
            String city = dataInputStream.readUTF();
            String state = dataInputStream.readUTF();
            String zipCode = dataInputStream.readUTF();

            MyAddress address = new MyAddress();
            if (!streetAddress.equals("<null>")) {
                address.setStreetAddress(streetAddress);
            }
            if (!city.equals("<null>")) {
                address.setCity(city);
            }
            if (!state.equals("<null>")) {
                address.setState(state);
            }
            if (!zipCode.equals("<null>")) {
                address.setZipCode(zipCode);
            }

            return address;

        } catch (FileNotFoundException e) {
            log.warn("Account does not exist", e);
            return null;
        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
        }
        return null;
    }

    private MyCreditCard readCreditCard(String accountName){
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(DIRECTORY + accountName + "/creditCard"));
            String accountNumber = dataInputStream.readUTF();
            if (accountNumber.equals("<null>")) {
                return null;
            }
            String expirationDate = dataInputStream.readUTF();
            String holder = dataInputStream.readUTF();
            String issuer = dataInputStream.readUTF();
            String type = dataInputStream.readUTF();

            MyCreditCard creditCard = new MyCreditCard();
            if (!accountNumber.equals("<null>")) {
                creditCard.setAccountNumber(accountNumber);
            }
            if (!expirationDate.equals("<null>")) {
                creditCard.setExpirationDate(expirationDate);
            }
            if (!holder.equals("<null>")) {
                creditCard.setHolder(holder);
            }
            if (!issuer.equals("<null>")) {
                creditCard.setIssuer(issuer);
            }
            if (!type.equals("<null>")) {
                creditCard.setType(type);
            }
            return creditCard;

        } catch (FileNotFoundException e) {
            log.warn("Account does not exist", e);
            return null;
        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
        }
        return null;
    }

    /**
     * Adds or updates an account.
     * @param account the account to add/update
     * @throws AccountException if operation fails
     */
    public void setAccount(Account account) throws AccountException {
        String accountDirectory = DIRECTORY + account.getName();
        new File(DIRECTORY + account.getName()).mkdirs();
        writeAccount(account, accountDirectory);
        writeAddress(account, accountDirectory);
        writeCreditCard(account, accountDirectory);
    }

    private void writeAccount(Account account, String accountDirectory) throws AccountException{
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new FileOutputStream(accountDirectory + "/account"));
            dataOutputStream.writeUTF(account.getName());
            dataOutputStream.writeInt(account.getPasswordHash().length);
            dataOutputStream.write(account.getPasswordHash());
            dataOutputStream.writeInt(account.getBalance());
            if (account.getFullName() == null){
                dataOutputStream.writeUTF("<null>");
            }
            else {
                dataOutputStream.writeUTF(account.getFullName());
            }
            if (account.getPhone() == null){
                dataOutputStream.writeUTF("<null>");
            }
            else {
                dataOutputStream.writeUTF(account.getPhone());
            }
            if (account.getEmail() == null){
                dataOutputStream.writeUTF("<null>");
            }
            else {
                dataOutputStream.writeUTF(account.getEmail());
            }
        } catch (FileNotFoundException e) {
            log.warn("File does not exist", e);
            throw new AccountException("Account Does not Exist in database");

        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
        }
    }

    private void writeAddress(Account account, String accountDirectory) throws AccountException {
        try {

            DataOutputStream dataOutputStream = new DataOutputStream(
                    new FileOutputStream(accountDirectory + "/address"));
            if (account.getAddress() == null){
                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
            }
            else {
                dataOutputStream.writeUTF(account.getAddress().getStreetAddress());
                dataOutputStream.writeUTF(account.getAddress().getCity());
                dataOutputStream.writeUTF(account.getAddress().getState());
                dataOutputStream.writeUTF(account.getAddress().getZipCode());
            }
        } catch (FileNotFoundException e) {
            log.warn("File does not exist", e);
            throw new AccountException("Account Does not Exist in database");

        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
        }
    }

    private void writeCreditCard(Account account, String accountDirectory) throws AccountException {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new FileOutputStream(accountDirectory + "/creditCard"));
            if (account.getCreditCard() == null){
                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
//                dataOutputStream.writeUTF("<null>");
            }
            else {
                dataOutputStream.writeUTF(account.getCreditCard().getAccountNumber());
                dataOutputStream.writeUTF(account.getCreditCard().getExpirationDate());
                dataOutputStream.writeUTF(account.getCreditCard().getHolder());
                dataOutputStream.writeUTF(account.getCreditCard().getIssuer());
                if (account.getCreditCard().getType() == null){
                    dataOutputStream.writeUTF("<null>");
                }
                else {
                    dataOutputStream.writeUTF(account.getCreditCard().getType());
                }
            }
        } catch (FileNotFoundException e) {
            log.warn("File does not exist", e);
            throw new AccountException("Account Does not Exist in database");

        } catch (IOException e) {
            log.warn("File closed before operation could finish", e);
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
     * Close the DAO.
     * Release any resources used by the DAO implementation.
     * If the DAO is already closed then invoking this method has no effect.
     * @throws AccountException if operation fails
     */
    public void close() throws AccountException {
//        this.close();
//        Currently this isn't used because the resource closes itself
    }
}
