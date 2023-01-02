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
        // TODO: update excuteUpdate
        // Account(id,name,balance)
        // login(id,username,password)
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, "sa", "Abcd1234!");
            Statement st = con.createStatement();
            // insert into login table
            st.executeUpdate("insert into login values ('" + username + "', '" + password + "')");
            // insert into account table
            st.executeUpdate("insert into account values ('" + username + "', '" + name + "', 0)");
            con.close();
        } catch (ClassNotFoundException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
