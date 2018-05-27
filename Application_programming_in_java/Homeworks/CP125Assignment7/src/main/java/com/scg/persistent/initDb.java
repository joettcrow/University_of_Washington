package com.scg.persistent;

import app.SCGDriver;
import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

import java.sql.SQLException;


/**
 * This application configures the database with data suitable for creating and printing invoices.
 * @author jcrowley
 */
public class initDb {
    public static void main(String[] args) {
        String dbURL = "jdbc:derby://localhost:1527/memory:scgDb;create=true";
        String dbUserName = "student";
        String dbPassword = "student";

        DbServer dbServer = new DbServer(dbURL,dbUserName,dbPassword);
        SCGDriver driver = new SCGDriver();
        
        try {
			dbServer.initTables();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        for (ClientAccount account: driver.getClientAccounts()) {
            try {
                dbServer.addClient(account);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        for (Consultant cons: driver.getConsultants()) {
            try {
                dbServer.addConsultant(cons);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        for (TimeCard card: driver.getTimeCards()) {
            try {
                dbServer.addTimeCard(card);
            } catch (SQLException e){
                System.out.println(e);
            }

        }
    }
}
