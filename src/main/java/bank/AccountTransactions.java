package bank;

import java.sql.ResultSet;

public class AccountTransactions extends SessionUtilities{
    DBConDetails dbConDetails;
    public AccountTransactions() {
        dbConDetails=new DBConDetails();
    }
    //Deposit an amount to user's account
    public int credit(String desc,long amount){
        int updateInt=0;
        try {
            ResultSet rs = dbConDetails.getStatement().executeQuery("select balance from user_details where account_id ="+session_id);
            long balance =rs.getLong("balance");
            balance = balance+amount;
            updateInt= dbConDetails.getStatement().executeUpdate("UPDATE user_details SET balance ="+balance+ "WHERE account_id ="+session_id);
            dbConDetails.getStatement().executeUpdate("INSERT INTO account_statement (account_id,description,credit,balance) " +
                    "VALUES("+session_id+",'"+desc+"',"+amount+","+balance+")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateInt;
    }
    //Withdraw an amount from user's account
    public int debit(String desc,long amount){
        int updateInt=0;
        try {
            ResultSet rs = dbConDetails.getStatement().executeQuery("select balance from user_details where account_id ="+session_id);
            long balance =rs.getLong("balance");
            if(balance>amount){
                balance = balance-amount;
                updateInt= dbConDetails.getStatement().executeUpdate("UPDATE user_details SET balance ="+balance+ "WHERE account_id ="+session_id);
                dbConDetails.getStatement().executeUpdate("INSERT INTO account_statement (account_id,description,credit,balance) " +
                        "VALUES("+session_id+",'"+desc+"',"+amount+","+balance+")");
            }
            else {
                throw new Exception("Insufficient balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateInt;
    }
    //Show user's account statement
    public void showAccountStatement(){
        try {
            ResultSet rs = dbConDetails.getStatement().executeQuery("SELECT * FROM account_statement WHERE account_id = "+session_id);
            while (rs.next())
                System.out.printf("%-20s %-10d %-10d %-10d %-20s \n",
                        rs.getString("description"),
                        rs.getLong("credit"),
                        rs.getLong("debit"),
                        rs.getLong("balance"),
                        rs.getTimestamp("date"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
