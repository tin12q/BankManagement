package tin.bank.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataUp {

    public static void createUser(String username, String password, String name) {
        // sql driver
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        // sql connection
        String url = "jdbc:sqlserver://localhost:57000;databaseName=Bank;encrypt=true;trustServerCertificate=true ";
        // conecting to database
        // TODO: Can Drop
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, "sa", "Abcd1234!");
            Statement st = con.createStatement();

            // insert into login(id,account_id,username,password)
            st.executeUpdate("insert into login (username,password) values ( '" + username + "', '" + password + "')");
            // insert into account table
            st.executeUpdate("insert into account(name,balance) values ( '" + name + "', 0)");
            con.close();
        } catch (ClassNotFoundException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
