package tin.bank.client.model;

import java.sql.*;
public class DataGet {
    //mssql connection
    private static String url = "jdbc:sqlserver://localhost:57000;databaseName=Bank;encrypt=true;trustServerCertificate=true ";
    private static String usr= "sa";
    private static String pss = "Abcd1234!";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private void init(){
        try{
            //sql server driver
            Class.forName(driver);
            //connection
            Connection con = DriverManager.getConnection(url,usr,pss);
            //statement
            Statement st = con.createStatement();
            //result
            ResultSet rs = st.executeQuery("select * from login");
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        }catch (ClassNotFoundException e){

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean passCheck(String id, String password){
        try{
            //sql server driver
            Class.forName(driver);
            //connection
            Connection con = DriverManager.getConnection(url,usr,pss);
            //statement
            Statement st = con.createStatement();
            //result
            ResultSet rs = st.executeQuery("select * from login where username = '"+id+"' and password = '"+password+"'");
            if( rs.next()){
                return true;
            }
            else return false;



        }catch (ClassNotFoundException e){

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
