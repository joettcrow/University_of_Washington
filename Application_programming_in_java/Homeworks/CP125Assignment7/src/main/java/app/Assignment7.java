package app;

import com.scg.domain.ClientAccount;
import com.scg.domain.Invoice;
import com.scg.persistent.DbServer;

import java.sql.SQLException;
import java.time.Month;

/**
 * This application will invoke DbServer.getInvoice
 * to create an invoice for each client in the SCG database.
 * After invoice creation, it prints the invoice.
 * @author jcrowley
 */
public class Assignment7 {
    public static void main(String[] args) {
        String dbURL = "jdbc:derby://localhost:1527/memory:scgDb;create=true";
        String dbUserName = "student";
        String dbPassword = "student";

        DbServer dbServer = new DbServer(dbURL, dbUserName, dbPassword);
        SCGDriver driver = new SCGDriver();

        for (ClientAccount account : driver.getClientAccounts()) {
            try {
                Invoice invoice = dbServer.getInvoice(account, Month.MARCH, 2004);
                System.out.println(invoice.toReportString());
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("We have an exception in invoice");
            }
        }
    }
}
