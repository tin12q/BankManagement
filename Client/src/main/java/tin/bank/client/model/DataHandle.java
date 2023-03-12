package tin.bank.client.model;

import java.sql.*;
import java.util.LinkedList;

public class DataHandle {
    // mssql connection

    private final static String url = "jdbc:sqlserver://localhost;databaseName=Bank;encrypt=true;trustServerCertificate=true ";
    private static final String usr = "sa";
    private static final String pss = "Abcd1234";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static LinkedList<Account> accounts = new LinkedList<>();
    public static LinkedList<Ledger> ledgers = new LinkedList<>();
    public static Account mainAccount;
    private static Connection conn;

    // connect to database
    private static void connection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usr, pss);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    // get Main Account

    public static void getMainAccount(String username) {
        try {
            connection();
            String sql = "{CALL GetCustomerInfo(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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
                mainAccount = new Account(customerId, firstName, lastName, balance, email, phone, address, dateOfBirth,
                        city, state, zipCode, username);
                // process the customer information
            } else {
                System.out.println("No customer found");
            }
        } catch (Exception e) {

            System.out.println("Error");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // get all accounts
    //update password to hashed
    public static void updatePass() {
        try {
            connection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Logins");
            while (rs.next()) {
                String username = rs.getString("Username");
                //String password = rs.getString("Password");
                String sql = "{CALL updatePass(?,?)}";
                CallableStatement stmt2 = conn.prepareCall(sql);
                stmt2.setString(1, username);
                stmt2.setBytes(2, Encrypt.encrypt("abcd").getBytes());
                stmt2.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // login check
    public static boolean loginCheck(String username, String password) {
        boolean isValid = false;
        try {
            connection();
            String sql = "{CALL CheckLogin(?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, Encrypt.encrypt(password));
            stmt.registerOutParameter(3, Types.BIT);
            stmt.execute();

            isValid = stmt.getBoolean(3);
            return isValid;
        } catch (Exception e) {
            System.out.println("DataHandle.loginCheck()");
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValid;

    }

    public static boolean checkLoginHashed(String username, String password) {
        boolean isValid = false;
        try {
            connection();
            String sql = "{CALL getPass(?)} ";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] hashed = rs.getBytes("password");
                //System.out.println("Hashed: " + new String(hashed));
                isValid = Encrypt.checkPassword(password, new String(hashed));
            }
            return isValid;
        } catch (Exception e) {
            System.out.println("DataHandle.loginCheck()");
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    public static void createUser(String fname, String lname, Date dob, String email, String phone, String address,
                                  String city,
                                  String state, String zip, String username, String password, Double initialDeposit) {
        try {
            connection();
            String sql = "{CALL CreateCustomerAccount(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setDate(3, dob);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, address);
            stmt.setString(7, city);
            stmt.setString(8, state);
            stmt.setString(9, zip);
            stmt.setString(10, username);
            stmt.setString(11, Encrypt.encrypt(password));
            stmt.setDouble(12, initialDeposit);
            stmt.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // get customer counts
    /*
     * public static int getCustomerCount() {
     * int count = 0;
     * try {
     * connection();
     * String sql = "{CALL GetCustomerCount()}";
     * CallableStatement stmt = conn.prepareCall(sql);
     * ResultSet rs = stmt.executeQuery();
     *
     * if (rs.next()) {
     * int customerCount = rs.getInt("CustomerCount");
     * // process the customer count
     * count = customerCount;
     * }
     * } catch (Exception e) {
     * System.out.println("DataHandle.getCustomerCount()");
     * } finally {
     * try {
     * conn.close();
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * }
     * return count;
     * }
     */
    // money transfer
    public static void transferMoney(String sourceAccountId, String destinationAccountId, Double amount, String des) {
        try {
            connection();
            String sql = "{CALL TransferDes(?, ?, ?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, sourceAccountId);
            stmt.setString(2, destinationAccountId);
            stmt.setDouble(3, amount);
            stmt.setString(4, des);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DataHandle.transferMoney()");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // transfer money with description
    public static void tranDes(String sourceAccountId, String destinationAccountId, Double amount, String des) {
        try {
            connection();
            String sql = "{CALL TransferDes(?, ?, ?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, sourceAccountId);
            stmt.setString(2, destinationAccountId);
            stmt.setDouble(3, amount);
            stmt.setString(4, des);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DataHandle.transferMoney()");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // get other account list
    public static void getCustomers() {
        try {
            connection();
            String sql = "{CALL GetCustomers(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mainAccount.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String customerId = rs.getString("CustomerId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                // process the customer information
                Account account = new Account(customerId, firstName, lastName, username);
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // check username exists
    public static boolean checkUserExists(String username) {
        boolean isValid = false;
        try {
            connection();
            String sql = "{CALL GetLoginInfo(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            stmt.registerOutParameter(2, Types.BIT);
            stmt.execute();

            isValid = stmt.getBoolean(2);
            return isValid;
        } catch (Exception e) {
            System.out.println("DataHandle.checkUsernameExists()");
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("DataHandle.checkUsernameExists()");
            }
        }
        return isValid;
    }

    // withdraw money
    public static void withdrawMoney(String accountId, Double amount) {
        try {
            connection();
            String sql = "{CALL WithdrawFunds(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, accountId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DataHandle.withdrawMoney()");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // deposit fund
    public static void depositMoney(String accountId, Double amount) {
        try {
            connection();
            String sql = "{CALL DepositFunds(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, accountId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DataHandle.depositMoney()");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetList() {
        accounts.clear();
        ledgers.clear();

    }

    public static void getLedger() {
        try {
            connection();
            String sql = "{CALL GetLedger(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mainAccount.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("TransactionId");
                int sourceCustomerId = rs.getInt("AccountId");
                String transactionType = rs.getString("TransactionType");
                Double amount = rs.getDouble("Amount");
                String Date = rs.getDate("DateTime").toString();
                String description = rs.getString("Description");
                int destinationCustomerId = rs.getInt("DestinationAccountId");
                String destinationName = rs.getString("DestinationName");
                // process the customer information
                Ledger ledger = new Ledger(transactionId, sourceCustomerId, transactionType, amount, Date, description,
                        destinationCustomerId, destinationName);
                ledgers.add(ledger);
                //System.out.println(ledger.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetLedger() {
        ledgers.clear();
    }

    public static void sortBy(String type) {
        try {
            connection();

            String sql = "{CALL sortBy" + type + "(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mainAccount.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("TransactionId");
                int sourceCustomerId = rs.getInt("AccountId");
                String transactionType = rs.getString("TransactionType");
                Double amount = rs.getDouble("Amount");
                String Date = rs.getDate("DateTime").toString();
                String description = rs.getString("Description");
                int destinationCustomerId = rs.getInt("DestinationAccountId");
                String destinationName = rs.getString("DestinationName");
                // process the customer information
                Ledger ledger = new Ledger(transactionId, sourceCustomerId, transactionType, amount, Date, description,
                        destinationCustomerId, destinationName);
                ledgers.add(ledger);
                System.out.println(ledger.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void findName(String DestinationName) {
        try {
            connection();
            String sql = "{CALL FindDestinationName(?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mainAccount.getId());
            stmt.setString(2, DestinationName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("TransactionId");
                int sourceCustomerId = rs.getInt("AccountId");
                String transactionType = rs.getString("TransactionType");
                Double amount = rs.getDouble("Amount");
                String Date = rs.getDate("DateTime").toString();
                String description = rs.getString("Description");
                int destinationCustomerId = rs.getInt("DestinationAccountId");
                String destinationName = rs.getString("DestinationName");
                // process the customer information
                Ledger ledger = new Ledger(transactionId, sourceCustomerId, transactionType, amount, Date, description,
                        destinationCustomerId, destinationName);
                ledgers.add(ledger);
                System.out.println(ledger.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void find(String DestinationName) {
        try {
            connection();
            String sql = "{CALL Find(?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mainAccount.getId());
            stmt.setString(2, DestinationName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("TransactionId");
                int sourceCustomerId = rs.getInt("AccountId");
                String transactionType = rs.getString("TransactionType");
                Double amount = rs.getDouble("Amount");
                String Date = rs.getDate("DateTime").toString();
                String description = rs.getString("Description");
                int destinationCustomerId = rs.getInt("DestinationAccountId");
                String destinationName = rs.getString("DestinationName");
                // process the customer information
                Ledger ledger = new Ledger(transactionId, sourceCustomerId, transactionType, amount, Date, description,
                        destinationCustomerId, destinationName);
                ledgers.add(ledger);
                System.out.println(ledger.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateCustomer(String id, String fname, String lname, String email, String phone, String address,
                                      String city, String state, String zip, Date dob) {
        try {
            connection();
            String sql = "{CALL UpdateCustomer(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            stmt.setString(2, fname);
            stmt.setString(3, lname);
            stmt.setDate(4, dob);
            stmt.setString(5, email);
            stmt.setString(6, phone);
            stmt.setString(7, address);
            stmt.setString(8, city);
            stmt.setString(9, state);
            stmt.setString(10, zip);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update Failed");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getName(String Id) {

        try {
            connection();
            String sql = "{CALL getName(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("FirstName") + " " + rs.getString("LastName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void init() {
        // for (int i = 100; i <= 10000; i++) {
        // String fname = "Hao";
        // String lname = "Phan";
        // Date dob = Date.valueOf(LocalDate.of(2004, 07, 27));
        // String email = "quy";
        // String phone = "1234567890";
        // String address = "1234 Main St ";
        // String city = "New York ";
        // String state = "NY ";
        // String zip = "12345 ";
        // String username = "hao";
        // String password = "abcd";
        // Double init = 1000000.0;
        // DataHandle.createUser(fname, lname, dob, email, phone, address, city, state,
        // zip, username, password, init);
        // // DataHandle.createUser("acc", Integer.toString(i),new LocalDate().now(),
        // // STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN,
        // // STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN,
        // // STYLESHEET_MODENA, STYLESHEET_CASPIAN, null);
        // }
        // for (int i = 1; i <= 3000; i++) {
        // DataHandle.depositMoney(Integer.toString(i), 100000.0);
        // }
        // randomly generate 5000 transactions from 1521 accounts
        // for (int i = 1; i <= 5000; i++) {
        // int from = (int) (Math.random() * 1521) + 1;
        // int to = (int) (Math.random() * 1521) + 1;
        // double amount = (Math.random() * 1000) + 1;
        // DataHandle.transferMoney(Integer.toString(from), Integer.toString(to),
        // amount);
        // }
        // randomly generate 5000 deposits and withdrawals for 1521 accounts
        // for (int i = 1; i <= 5000; i++) {
        // int from = (int) (Math.random() * 1521) + 1;
        // int to = (int) (Math.random() * 1521) + 1;
        // double amount = (Math.random() * 1000) + 1;
        // double amount2 = (Math.random() * 1000) + 1;
        // DataHandle.depositMoney(Integer.toString(from), amount);
        // DataHandle.withdrawMoney(Integer.toString(to), amount2);
        // }
        // // randomly generate 5000 deposits or withdrawals for account 1522
        // for (int i = 1; i <= 70; i++) {
        // int from = (int) (Math.random() * 2) + 1;
        // double amount = (Math.random() * 1000) + 1;
        // if (from == 1) {
        // DataHandle.depositMoney("1522", amount);
        // } else {
        // DataHandle.withdrawMoney("1522", amount);
        // }
        // }
        // // randomly generate 5000 transactions from account 1522
        // for (int i = 1; i <= 50; i++) {
        // int to = (int) (Math.random() * 1521) + 1;
        // double amount = (Math.random() * 1000) + 1;
        // DataHandle.transferMoney("1522", Integer.toString(to), amount);
        // }
        // // randomly generate 5000 transactions to account 1522
        // for (int i = 1; i <= 50; i++) {
        // int from = (int) (Math.random() * 1521) + 1;
        // double amount = (Math.random() * 1000) + 1;
        // DataHandle.transferMoney(Integer.toString(from), "1522", amount);
        // }
    }

}