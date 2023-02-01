package tin.bank.server.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataHandle {
    // mssql connection

    private final static String url = "jdbc:sqlserver://localhost;databaseName=Bank;encrypt=true;trustServerCertificate=true ";
    private static final String usr = "sa";
    private static final String pss = "Abcd1234!";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static List<Account> accounts = new ArrayList<>();
    public static List<Ledger> ledgers;
    public static Account mainAccount;
    private static Connection conn;

    // connect to database
    private static void connection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, usr, pss);
    }

    // disconnect from database
    private static void disconnection() throws SQLException {
        conn.close();
    }

    // get all accounts from database
    public static void getAllCustomers() {

        try {
            connection();
            CallableStatement cs = conn.prepareCall("{call GetALlCustomers}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String customerId = rs.getString("CustomerId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date dateOfBirth = rs.getDate("DateOfBirth");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipCode = rs.getString("ZipCode");
                Double balance = rs.getDouble("Balance");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                Account account = new Account(customerId, firstName, lastName, balance, email, phone, address,
                        dateOfBirth,
                        city, state, zipCode, username, password);
                accounts.add(account);
            }
            disconnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
