package com.scg.persistent;

import com.scg.domain.*;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import java.security.PrivateKey;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class DbServerTest {

    private String dbURL = "jdbc:derby://localhost:1527/memory:scgDb";
    private String dbUserName = "student";
    private String dbPassword = "student";
    private List<ClientAccount> clients = new ArrayList<ClientAccount>();


    private final String name = "AccountName";
    private PersonalName contact = new PersonalName(
            "Last",
            "First",
            "Middle"
    );

    private PersonalName consultantName = new PersonalName(
            "Consultant_Last",
            "Consultant_First",
            "Consultant_Middle"
    );

    private Consultant consultant = new Consultant(consultantName);

    private Address address = new Address(
            "1234 StreetNum",
            "City",
            StateCode.WA,
            "1234"
    );
    ClientAccount clientAccount = new ClientAccount(name,contact,address);

    LocalDate date = LocalDate.of(2018,05,20);
    TimeCard timeCard = new TimeCard(consultant,date);

    private int hours = 10;
    private Skill skill = Skill.SOFTWARE_TESTER;
    private ConsultantTime billableTimes = new ConsultantTime(
            date,
            clientAccount,
            skill,
            hours);

    private ConsultantTime nonBillableTimes = new ConsultantTime(
            date,
            NonBillableAccount.BUSINESS_DEVELOPMENT,
            Skill.UNKNOWN_SKILL,
            hours);

    DbServer dbServer = new DbServer(dbURL,dbUserName,dbPassword);


    @Test
    public void addClientTest() throws SQLException {
        dbServer.initTables();
        List<ClientAccount> emptyClients = new ArrayList<ClientAccount>();
        dbServer.addClient(clientAccount);
        clients.add(clientAccount);
        assertEquals(clients,dbServer.getClients());
    }

    @Test
    public void getConsultantTest() throws SQLException{
        dbServer.initTables();
        List<Consultant> consultants = new ArrayList<Consultant>();
        dbServer.addConsultant(consultant);
        consultants.add(consultant);
        System.out.println(consultants);
        assertEquals(consultants.get(0),dbServer.getConsultants().get(0));
    }

    @Test
    public void getTimeCardTest() throws SQLException {
        dbServer.initTables();
        timeCard.addConsultantTime(billableTimes);
        timeCard.addConsultantTime(nonBillableTimes);

        dbServer.addConsultant(consultant);
        dbServer.addClient(clientAccount);

        dbServer.addTimeCard(timeCard);

        TimeCard timeCardActual = dbServer.getTimeCards().get(0);
        assertEquals(timeCard.compareTo(timeCardActual),0);
    }
}