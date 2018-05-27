package com.scg.persistent;

import com.scg.domain.*;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for all updates and queries to the SCG database.
 * It has the following properties:
 * @author jcrowley
 */
public class DbServer {
    private static final String NONBILLABLES_TABLE_NAME = "NON_BILLABLE_HOURS";
    private static final String CLIENTS_TABLE_NAME = "CLIENTS";
    private static final String CONSULTANTS_TABLE_NAME = "CONSULTANTS";
    private static final String TIMECARDS_TABLE_NAME = "TIMECARDS";
    private static final String BILLABLES_TABLE_NAME = "BILLABLE_HOURS";
    private String dbURL;
    private String dbPassword;
    private String dbUserName;

    private List<ClientAccount> clients = new ArrayList<ClientAccount>();
    private List<Consultant> consultants = new ArrayList<Consultant>();
    private List<TimeCard> timeCards = new ArrayList<TimeCard>();

    /**
     * Constructor. Establishes the URL, user name and password for accessing the database.
     * @param dbURL url for the database
     * @param dbUserName user name for the database
     * @param dbPassword password for the database
     */
    public DbServer( String dbURL, String dbUserName, String dbPassword ){
        this.dbURL = dbURL;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    /**
     * Getter for the connection
     * @return the connection for the db
     * @throws SQLException if something is wrong
     */
    private Connection getConnection() throws SQLException {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch(ClassNotFoundException e){
            System.out.println("Can't find class");
        }
        Connection conn = DriverManager.getConnection(
                dbURL,
                dbUserName,
                dbPassword);
        return conn;
    }

    /**
     * Empties the database tables.
     * This method is useful for emptying the database prior to running your tests,
     * or establishing the database content in InitDb.
     * @throws SQLException if the SQL is malformed
     */
    public void initTables() throws SQLException{
        try ( Connection conn = getConnection() ) {
            final String truncate = "DELETE FROM %s WHERE 1=1";
            final String[] tables =
                    {
                            NONBILLABLES_TABLE_NAME,
                            BILLABLES_TABLE_NAME,
                            TIMECARDS_TABLE_NAME,
                            CLIENTS_TABLE_NAME,
                            CONSULTANTS_TABLE_NAME,
                    };
            Statement statement = conn.createStatement();
            for (String table : tables)
                statement.executeUpdate(String.format(truncate, table));
        }
    }

    /**
     * Adds a client to the clients table.
     * @param client The client to add
     * @throws SQLException if the expressions are malformed
     */
    public void addClient( ClientAccount client ) throws SQLException{
        try(Connection conn = getConnection()) {
            String sql =
                    "INSERT INTO CLIENTS (" +
                            "name, " +
                            "street, " +
                            "city, " +
                            "state," +
                            "postal_code, " +
                            "contact_last_name, " +
                            "contact_first_name, " +
                            "contact_middle_name) VALUES (?,?,?,?,?,?,?,?)";
            String name = client.getName();
            String street = client.getAddress().getStreetNumber();
            String city = client.getAddress().getCity();
            String state = client.getAddress().getState().toString();
            String postal_code = client.getAddress().getPostalCode();
            String contact_last_name = client.getContact().getLastName();
            String contact_first_name = client.getContact().getFirstName();
            String contact_middle_name = client.getContact().getMiddleName();
            int flags = Statement.RETURN_GENERATED_KEYS;
            PreparedStatement statement = conn.prepareStatement(sql, flags);

            int inx = 1;
            statement.setString(inx++, name);
            statement.setString(inx++, street);
            statement.setString(inx++, city);
            statement.setString(inx++, state);
            statement.setString(inx++, postal_code);
            statement.setString(inx++, contact_last_name);
            statement.setString(inx++, contact_first_name);
            statement.setString(inx++, contact_middle_name);
            statement.executeUpdate();
            ResultSet results = statement.getGeneratedKeys();
            if (!results.next()) throw new SQLException("get generated keys failure");
        }

    }

    /**
     * Returns a list of all client accounts in the clients table.
     * @return list of all client accounts in the clients table.
     * @throws SQLException if the expression is malformed
     */
    public List<ClientAccount> getClients() throws SQLException{
        try(Connection conn = getConnection()) {
            String sql = "SELECT * FROM CLIENTS";
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                String name = results.getString(2);
                String street = results.getString(3);
                String city = results.getString(4);
                StateCode stateCode = StateCode.valueOf(results.getString(5));
                String postal_code = results.getString(6);
                String contact_last_name = results.getString(7);
                String contact_first_name = results.getString(8);
                String contact_middle_name = results.getString(9);

                PersonalName contactName = new PersonalName(
                        contact_last_name,
                        contact_first_name,
                        contact_middle_name);
                Address address = new Address(
                        street,
                        city,
                        stateCode,
                        postal_code);
                ClientAccount clientAccount = new ClientAccount(
                        name,
                        contactName,
                        address);

                clients.add(clientAccount);
            }
            return clients;
        }
    }

    /**
     * Adds a consultant to the consultants table.
     * @param consultant The consultant to add
     * @throws SQLException if the expression is malformed
     */
    public void addConsultant( Consultant consultant ) throws SQLException{
        try(Connection conn = getConnection()) {
            String sql =
                    "INSERT INTO CONSULTANTS (" +
                            "last_name, " +
                            "first_name, " +
                            "middle_name) VALUES (?,?,?)";
            PersonalName name = consultant.getName();
            String last_name = name.getLastName();
            String first_name = name.getFirstName();
            String middle_name = name.getMiddleName();

            int flags = Statement.RETURN_GENERATED_KEYS;
            PreparedStatement statement = conn.prepareStatement(sql, flags);

            int inx = 1;
            statement.setString(inx++, last_name);
            statement.setString(inx++, first_name);
            statement.setString(inx++, middle_name);
            statement.executeUpdate();
            ResultSet results = statement.getGeneratedKeys();
            if (!results.next())
                throw new SQLException("get generated keys failure");
        }
    }

    /**
     * Returns a list of all consultants in the consultants table.
     * @return List of all consultants in the consultants table.
     * @throws SQLException if the expression is malformed
     */
    public List<Consultant> getConsultants() throws SQLException{
        try(Connection conn = getConnection()) {
            String sql = "SELECT * FROM CONSULTANTS";
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                String last_name = results.getString(2);
                String first_name = results.getString(3);
                String middle_name = results.getString(4);

                PersonalName contactName = new PersonalName(
                        last_name,
                        first_name,
                        middle_name);
                Consultant consultant = new Consultant(contactName);

                consultants.add(consultant);
            }
        }
        return consultants;
    }

    /**
     * private method for getting clientIds
     * @param conn connection to use
     * @param name the name to search for
     * @return the id of the client
     * @throws SQLException if things don't work
     */
    private int getClientId(
            Connection conn,
            String name) throws SQLException {
        String getClientId = "SELECT * FROM CLIENTS WHERE name = ?";
        PreparedStatement getStatement = conn.prepareStatement(getClientId);
        getStatement.setString(1, name);

        ResultSet results = getStatement.executeQuery();

        int clientId = 0;
        while(results.next()) {
            clientId = results.getInt("id");
        }
        return clientId;
    }

    /**
     * Helper method for getting the consultant ID from the table
     * @param conn the connection to use
     * @param name the name to look up for the consultant
     * @return the ID of the consultant
     * @throws SQLException if stuff don't work
     */
    private int getConsultantId(
            Connection conn,
            PersonalName name
    ) throws SQLException {
        String getConsultantId = "SELECT ID FROM CONSULTANTS where " +
                    "LAST_NAME = ? AND " +
                    "FIRST_NAME = ? AND " +
                    "MIDDLE_NAME = ?";
        PreparedStatement getStatement = conn.prepareStatement(getConsultantId);
        getStatement.setString(1, name.getLastName());
        getStatement.setString(2, name.getFirstName());
        getStatement.setString(3, name.getMiddleName());

        ResultSet results2 = getStatement.executeQuery();


        results2.next();
        return results2.getInt("id");
    }

    /**
     * Helper method to actually add the time card in the row
     * @param conn the connection to use
     * @param consultantId the id of the consultant to add
     * @param date the date to add
     * @return the timeCard int for the created card
     * @throws SQLException if shit be borked
     */
    private int addTimeCardRow(
            Connection conn,
            int consultantId,
            LocalDate date) throws SQLException {
        String addSQL = "INSERT INTO TIMECARDS (" +
                "CONSULTANT_ID, " +
                "start_date) VALUES (?,?)";

        int flags = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement addStatement = conn.prepareStatement(addSQL, flags);

        int inx = 1;
        addStatement.setInt(inx++, consultantId);
        addStatement.setDate(inx++, Date.valueOf(date));
        addStatement.executeUpdate();
        ResultSet timeCardInsertResults = addStatement.getGeneratedKeys();

        if (!timeCardInsertResults.next())
            throw new SQLException("get generated keys failure");
        return timeCardInsertResults.getInt(1);
    }

    /**
     * Private method for adding billable hours
     * @param conn
     * @param clientId
     * @param timeCardId
     * @param time
     * @param date
     * @throws SQLException
     */
    private void addBillableHours(
            Connection conn,
            int clientId,
            int timeCardId,
            ConsultantTime time,
            LocalDate date) throws SQLException {
        String addBillSQL = "INSERT INTO BILLABLE_HOURS (" +
                "CLIENT_ID, " +
                "TIMECARD_ID," +
                "DATE," +
                "SKILL," +
                "HOURS ) VALUES (?,?,?,?,?)";
        int flags = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement addBillable = conn.prepareStatement(addBillSQL, flags);
        int inx = 1;
        addBillable.setInt(inx++, clientId);
        addBillable.setInt(inx++, timeCardId);
        addBillable.setDate(inx++, Date.valueOf(date));
        addBillable.setString(inx++, time.getSkill().name());
        addBillable.setInt(inx++, time.getHours());
        addBillable.executeUpdate();
        ResultSet billableResults = addBillable.getGeneratedKeys();
        if (!billableResults.next()) throw new SQLException("get generated keys failure");
    }

    /**
     * Private method for adding Non Billable Hours
     * @param conn the connection
     * @param timeCardId the id of the time card associated with these hours
     * @param time the Consultant time to write
     * @param date the date of the hours
     * @throws SQLException if the SQL is borked
     */
    private void addNonBillableHours(
            Connection conn,
            int timeCardId,
            ConsultantTime time,
            LocalDate date) throws SQLException {
        String addNonBillSQL = "INSERT INTO NON_BILLABLE_HOURS (" +
                "ACCOUNT_NAME, " +
                "TIMECARD_ID," +
                "DATE," +
                "HOURS ) VALUES (?,?,?,?)";
        int flags = Statement.RETURN_GENERATED_KEYS;
        PreparedStatement addNonBill = conn.prepareStatement(addNonBillSQL, flags);
        int inx = 1;
        addNonBill.setString(inx++, time.getAccount().toString());
        addNonBill.setInt(inx++, timeCardId);
        addNonBill.setDate(inx++, Date.valueOf(date));
        addNonBill.setInt(inx++, time.getHours());
        addNonBill.executeUpdate();
        ResultSet billableResults = addNonBill.getGeneratedKeys();
        if (!billableResults.next()) throw new SQLException("get generated keys failure");
    }

    /**
     * Adds a time card to the time_cards table
     * as well as adding billable and non billable hours from the timecards .
     * @param timeCard The time card to add.
     * @throws SQLException if the expression is malformed
     */
    public void addTimeCard( TimeCard timeCard ) throws SQLException{
        PersonalName name = timeCard.getConsultant().getName();

        try (Connection conn = getConnection()) {
            int consultantId = getConsultantId(conn,name);
            LocalDate date = timeCard.getWeekStartingDate();
            int timeCardId = addTimeCardRow(conn,consultantId,date);

            for (ConsultantTime time: timeCard.getConsultantTimes()) {
                int clientId = getClientId(conn, time.getAccount().getName());
                if (time.isBillable()) {
                    this.addBillableHours(
                            conn,
                            clientId,
                            timeCardId,
                            time,
                            date);
                }
                else {
                    this.addNonBillableHours(conn,timeCardId,time,date);
                }
            }


        }
    }

    /**
     * Private helper method to get the consultant for a timeCard
     * @param conn the connection
     * @param consultantId the consultant id to look up
     * @return the consultant object to build the time card
     * @throws SQLException if things are broked
     */
    private Consultant getConsultant(
            Connection conn,
            int consultantId) throws SQLException {
        String getConsultantId = "SELECT * FROM CONSULTANTS WHERE ID = ?";
        PreparedStatement getStatement = conn.prepareStatement(getConsultantId);
        getStatement.setInt(1, consultantId);
        ResultSet results = getStatement.executeQuery();

        results.next();
        String last = results.getString("LAST_NAME");
        String first = results.getString("FIRST_NAME");
        String middle = results.getString("MIDDLE_NAME");
        PersonalName name = new PersonalName(last,first,middle);
        Consultant consultant = new Consultant(name);
        return consultant;
    }

    /**
     * Helper method to get specific client account
     * @param conn connection to use
     * @param clientId the client id to look up
     * @return a client account matching the id
     * @throws SQLException if stuff don't work
     */
    private ClientAccount getClientAccount(
            Connection conn,
            int clientId) throws SQLException {
        String getClientAccount = "SELECT * FROM CLIENTS WHERE ID = ?";
        PreparedStatement getStatement = conn.prepareStatement(getClientAccount);
        getStatement.setInt(1, clientId);
        ResultSet results = getStatement.executeQuery();

        results.next();
        String name = results.getString("NAME");
        String street = results.getString("STREET");
        String city = results.getString("CITY");
        StateCode state = StateCode.valueOf(results.getString("STATE"));
        String postal_code = results.getString("POSTAL_CODE");
        String last = results.getString("CONTACT_LAST_NAME");
        String first = results.getString("CONTACT_FIRST_NAME");
        String middle = results.getString("CONTACT_MIDDLE_NAME");

        PersonalName contatctName = new PersonalName(last,first,middle);
        Address address = new Address(
                street,
                city,
                state,
                postal_code);
        ClientAccount clientAccount = new ClientAccount(
                name,
                contatctName,
                address);
        return clientAccount;
    }

    /**
     * Get billable hours helper method
     * @param conn connection to use
     * @param timeCard timecard to add times to
     * @param timeCardId id of the timecard
     * @throws SQLException if stuff is borked
     */
    private void getBillableHours(
            Connection conn,
            TimeCard timeCard,
            int timeCardId) throws SQLException {

        String getBillableHour = "SELECT * FROM BILLABLE_HOURS WHERE TIMECARD_ID = ?";
        PreparedStatement getStatement = conn.prepareStatement(getBillableHour);
        getStatement.setInt(1, timeCardId);
        ResultSet results = getStatement.executeQuery();
        while (results.next()) {
            int clientId = results.getInt("CLIENT_ID");
            LocalDate date = results.getDate("DATE").toLocalDate();
            Skill skill = Skill.valueOf(results.getString("SKILL"));
            int hours = results.getInt("HOURS");

            ClientAccount clientAccount = getClientAccount(conn,clientId);
            ConsultantTime time = new ConsultantTime(
                    date,
                    clientAccount,
                    skill,
                    hours);
            timeCard.addConsultantTime(time);
        }

    }

    private void getNonBillableHours(
            Connection conn,
            TimeCard timeCard,
            int timeCardId) throws SQLException {
        String getNonBillableHour = "SELECT * FROM NON_BILLABLE_HOURS WHERE TIMECARD_ID = ?";
        PreparedStatement getStatement = conn.prepareStatement(getNonBillableHour);
        getStatement.setInt(1, timeCardId);
        ResultSet results = getStatement.executeQuery();
        while (results.next()) {
            NonBillableAccount account = NonBillableAccount.valueOf(
                    results.getString("ACCOUNT_NAME")
            );
            LocalDate date = results.getDate("DATE").toLocalDate();
            int hours = results.getInt("HOURS");

            ConsultantTime time = new ConsultantTime(
                    date,
                    account,
                    Skill.UNKNOWN_SKILL,
                    hours);
            timeCard.addConsultantTime(time);
        }
    }

    /**
     * Returns a list of all time cards in the time_cards table.
     * Also pulls in billable and non billable hours for the consultant
     * Basically reconstructs the timecard we wrote
     * @return list of timecards
     * @throws SQLException if things don't work
     */
    public List<TimeCard> getTimeCards() throws SQLException{
        try(Connection conn = getConnection()) {
            String sql = "SELECT * FROM TIMECARDS";
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                int timeCardId = results.getInt("ID");
                int consultantId = results.getInt("CONSULTANT_ID");
                LocalDate weekStartDate = results.getDate(3).toLocalDate();
                Consultant consultant = getConsultant(conn,consultantId);
                TimeCard timeCard = new TimeCard(consultant,weekStartDate);
                getBillableHours(conn,timeCard,timeCardId);
                getNonBillableHours(conn,timeCard,timeCardId);

                timeCards.add(timeCard);
            }
        }
        return timeCards;
    }

    /**
     * Reads the database and creates and returns an invoice for the given client, month and year.
     * @param client The given client.
     * @param month The given month.
     * @param year The given year.
     * @return An invoice for the given client, month and year
     * @throws SQLException if things don't go as planned
     */
    public Invoice getInvoice(ClientAccount client, Month month, int year )
            throws SQLException{
        Invoice invoice = new Invoice(client,month,year);
        List<TimeCard> timeCardsForInvoice = getTimeCards();
        for (TimeCard time: timeCardsForInvoice) {
            invoice.extractLineItems(time);
        }
        return invoice;
    }
}
