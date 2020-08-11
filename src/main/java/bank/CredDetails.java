package bank;

import java.sql.ResultSet;

public class CredDetails extends SessionUtilities{
    DBConDetails dbConDetails;
    public CredDetails() {
        dbConDetails = new DBConDetails();
    }
    //Validate username, password and returns account_id
    public int validateCredentials(String username, String password){
           int accountId=-1;
            try {
               ResultSet rs = dbConDetails.getStatement().executeQuery("select account_id from cred_details WHERE username ='"+username+"' AND password = '"+password+"'");
               while (rs.next())
             accountId = rs.getInt("account_id");
           } catch (Exception e) {
               e.printStackTrace();
           }
            return  accountId;
        }
    //Create credentials for existing user
    public int insertCredentials(int account_id, String username , String password){
        int insertedInt=0;
        try {
            insertedInt = dbConDetails.getStatement().executeUpdate("insert into cred_details (account_id,username,password) " +
                    "values("+account_id+",'"+username+"','"+password+"')");
            if (insertedInt>0)
                System.out.println("Your data inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertedInt;
    }
    //Update only password in credentials for existing user
    public int updateCredentials( String password){
            int updatedInt=0;
        try {
                updatedInt =  dbConDetails.getStatement().executeUpdate("UPDATE cred_details SET password='" + password +
                        "' WHERE account_id=" + session_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedInt;
    }
}