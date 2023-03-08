package tin.bank.client.model;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypt {

    public static String encrypt(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }
    public static boolean checkPassword(String password, String hashed){
        //System.out.println("Password: " + password);
        //System.out.println("Hashed: " + hashed);
        if(BCrypt.checkpw(password, hashed)){
            return true;
        }
        return false;
    }




}
