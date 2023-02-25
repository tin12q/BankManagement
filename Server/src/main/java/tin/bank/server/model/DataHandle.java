package tin.bank.server.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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

    // export to excel
    public static void exportAccountsToExcel(List<Account> accounts, String filename) {
        try {
            // Create a new Excel workbook and sheet
            WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
            WritableSheet sheet = workbook.createSheet("Accounts", 0);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 18);
            WritableCellFormat wf = new WritableCellFormat(font);
            // Add column headings to the first row of the sheet
            sheet.addCell(new Label(0, 0, "ID", wf));
            sheet.addCell(new Label(1, 0, "First Name", wf));
            sheet.addCell(new Label(2, 0, "Last Name", wf));
            sheet.addCell(new Label(3, 0, "Balance", wf));
            sheet.addCell(new Label(4, 0, "Email", wf));
            sheet.addCell(new Label(5, 0, "Phone", wf));
            sheet.addCell(new Label(6, 0, "Address", wf));
            sheet.addCell(new Label(7, 0, "DOB", wf));
            sheet.addCell(new Label(8, 0, "City", wf));
            sheet.addCell(new Label(9, 0, "State", wf));
            sheet.addCell(new Label(10, 0, "Zipcode", wf));

            // Add account data to the remaining rows of the sheet
            int row = 1;
            for (Account account : accounts) {

                sheet.addCell(new Label(0, row, account.getId(), wf));
                sheet.addCell(new Label(1, row, account.getFname(), wf));
                sheet.addCell(new Label(2, row, account.getLname(), wf));
                sheet.addCell(new Label(3, row, String.valueOf(account.getBalance()), wf));
                sheet.addCell(new Label(4, row, account.getEmail(), wf));
                sheet.addCell(new Label(5, row, account.getPhone(), wf));
                sheet.addCell(new Label(6, row, account.getAddress(), wf));
                sheet.addCell(new Label(7, row, account.getDOB().toString(), wf));
                sheet.addCell(new Label(8, row, account.getCity(), wf));
                sheet.addCell(new Label(9, row, account.getState(), wf));
                sheet.addCell(new Label(10, row, account.getZipCode(), wf));

                row++;
            }
            // expand column
            for (int i = 0; i < 11; i++) {
                sheet.setColumnView(i, 30);
                // change font size

            }
            // Write the workbook to the file
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
