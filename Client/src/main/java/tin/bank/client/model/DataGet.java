package tin.bank.client.model;

import java.sql.*;
public class DataGet {
    //mssql connection
    private String url = "jdbc:sqlserver://localhost:57000;databaseName=bank;integratedSecurity=true";
    private String usr= "sa";
    private String pss = "Abcd1234!";
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private void init(){
        try{
            //sql server driver
            Class.forName(driver);
            //connection
            Connection con = DriverManager.getConnection(url,usr,pss);
            //statement
            Statement st = con.createStatement();
            //result
            ResultSet rs = st.executeQuery("select * from account");
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        }catch (ClassNotFoundException e){

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
