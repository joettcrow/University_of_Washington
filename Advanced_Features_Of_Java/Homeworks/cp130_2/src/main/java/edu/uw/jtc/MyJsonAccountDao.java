package edu.uw.jtc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.dao.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json version of accountDao
 * @author jcrowley
 */
public class MyJsonAccountDao implements AccountDao {
    private static final Logger log =
            LoggerFactory.getLogger( MyAccountDao.class );
    private static final String NULL_STR = "<null>";

    private static final String DIRECTORY = "target" + File.separator +"accounts" + File.separator;

    public Account getAccount(String accountName) {
        File file = new File(DIRECTORY + accountName + File.separator + "account.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            MyAccount account = mapper.readValue(file, MyAccount.class);
            return account;
        } catch (IOException e) {
            log.warn("", e);

        }
        return null;
    }

//    public class AccountDeserializer extends StdDeserializer<Address> {
//
//        public AccountDeserializer() {
//            this(null);
//        }
//
//        public AccountDeserializer(Class<?> vc) {
//            super(vc);
//        }
//
//        @Override
//        public Address deserialize(JsonParser jp, DeserializationContext ctxt)
//                throws IOException, JsonProcessingException {
//            JsonNode accountNode = jp.getCodec().readTree(jp);
//            JsonNode addressNode = (JsonNode) jp.getCodec().readTree(jp).get("address");
//            JsonNode creditNode = (JsonNode) jp.getCodec().readTree(jp).get("creditCard");
//
//            String name = accountNode.get("name").asText();
//            byte[] passwordHash = accountNode.get("name").as();
//            int balance;
//            String fullName;
//            String phone;
//            String email;
//
//            String streetAddress = addressNode.get("streetAddress").asText();
//            String city = addressNode.get("city").asText();
//            String state = addressNode.get("state").asText();
//            String zipCode = addressNode.get("zipCode").asText();
//
//
////            return new MyAddress(id, itemName, new User(userId, null));
//        }
//    }

    public void setAccount(Account account) throws AccountException {
        String accountDirectory = DIRECTORY + account.getName();
        new File(DIRECTORY + account.getName()).mkdirs();

        File file = new File(DIRECTORY +
                        account.getName() +
                        File.separator +
                        "account.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            mapper.writeValue(file,account);
        } catch (IOException e) {
            log.warn("", e);

        }
//        mapper.writeValue(file, account);
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

    public void close() throws AccountException {

    }
}
