package tin.bank.client.model;

import java.sql.*;
import java.util.LinkedList;

public class DataGet {
    // mssql connection
    private static String url = "jdbc:sqlserver://localhost:57000;databaseName=Bank;encrypt=true;trustServerCertificate=true ";
    private static String usr = "sa";
    private static String pss = "Abcd1234!";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static LinkedList<Account> accounts = new LinkedList<>();
    public static Account mainAccount;
    /*  init method
    private void init() {
        try {
            // sql server driver
            Class.forName(driver);
            // connection
            Connection con = DriverManager.getConnection(url, usr, pss);
            // statement
            Statement st = con.createStatement();
            // result
            ResultSet rs = st.executeQuery("select * from login");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (ClassNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    // password check
    public static boolean passCheck(String id, String password) {
        try {
            // sql server driver
            Class.forName(driver);
            // connection
            Connection con = DriverManager.getConnection(url, usr, pss);
            // statement
            Statement st = con.createStatement();
            // result
            ResultSet rs = st.executeQuery(
                    "select * from login where username = '" + id + "' and password = '" + password + "'");

            if (rs.next()) {
                String thisId = rs.getString("id");
                // System.out.println(rs.getString("id"));
                ResultSet rs2 = st.executeQuery("select * from account where id = '" + thisId + "'");
                rs2.next();
                mainAccount = new Account(rs2.getString("id"), rs2.getString("name"), rs2.getDouble("balance"));
                return true;
            } else
                return false;

        } catch (ClassNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // TODO: Jan 01 2023
    // 1. Add a method to get all the accounts DONE
    // 2. Add a method to get all the transactions In progress 
    // 3. Add a method to get all the users DONE
    // 4. Output the result to UI DONE

    // Get all the user to a list
    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    public static void getUsers() {
        try {
            // sql server driver
            Class.forName(driver);
            // connection
            Connection con = DriverManager.getConnection(url, usr, pss);
            // statement
            Statement st = con.createStatement();
            // result without main account

            ResultSet rs = st.executeQuery("select * from account where id != '" + mainAccount.getId() + "'");

            while (rs.next()) {
                // add to list
                Account account = new Account(rs.getString("id"), rs.getString("name"), rs.getDouble("balance"));
                addAccount(account);
            }

        } catch (ClassNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to reset the list
    public static void resetList() {
        accounts.clear();
    }

}
