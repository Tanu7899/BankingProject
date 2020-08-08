package bank;

import java.sql.*;
import java.util.Scanner;

public class AccountHolderDetails extends DBConDetails{
        public static void main(String args[]) {
            Scanner scanner=new Scanner(System.in);
            AccountHolderDetails userDetails=new AccountHolderDetails();
            boolean choice;
            do {
                System.out.print("1.Show user details\n2.Insert user details\n3.Update user details\nSelect an option:");
                int choice_ud = scanner.nextInt();
                if (choice_ud == 1) {
                    userDetails.showAllAccountHolderDetails();
                } else if (choice_ud == 2) {
                    System.out.println("Enter following details");
                    System.out.print("Name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Phone no:");
                    Long ph_no = Long.parseLong(scanner.nextLine());
                    System.out.print("Address:");
                    String address = scanner.nextLine();
                    userDetails.insertAccountHolderDetails(name, ph_no, address);
                } else if (choice_ud == 3) {
                    System.out.println("Enter following details");
                    System.out.print("account_id:");
                    int  account_id=scanner.nextInt();
                    System.out.print("Which field do you want to update(name/ph_no/address):");
                    scanner.nextLine();
                    String updateColumn = scanner.nextLine();
                    System.out.print("Enter new value for "+updateColumn+":");
                    String updateColumnValue = scanner.nextLine();
                    userDetails.updateAccountHolderDetails(account_id,updateColumn,updateColumnValue);
                }
                System.out.print("Do you want to continue(Y/N):");
                choice=(scanner.next().equalsIgnoreCase("Y"))?true:false;
            }while (choice);
            scanner.close();
        }

       public void showAllAccountHolderDetails(){
           try {
               initialiseMySQLStatement();
               ResultSet rs = statement.executeQuery("select * from user_details");
               while (rs.next())
               System.out.printf("%-5d %-20s %-15d %-20s %-10d %-30s %-30s \n",rs.getInt("account_id"),
                       rs.getString("name"),
                       rs.getLong("ph_no"),
                       rs.getString("address"),
                       rs.getLong("balance"),
                       rs.getTimestamp("created_at"),
                       rs.getTimestamp("updated_at"));

           } catch (Exception e) {
               System.out.println(e);
           }
           finally {
               try {con.close(); }catch (Exception e){e.printStackTrace();}
           }
        }

    public int insertAccountHolderDetails(String name, Long ph_no, String address){
        int insertedInt=0;
        try {
            initialiseMySQLStatement();
            insertedInt = statement.executeUpdate("insert into user_details (name,ph_no,address) " +
                    "values('"+name+"',"+ph_no+",'"+address+"')");
            if (insertedInt>0)
                System.out.println("Your data inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {con.close(); }catch (Exception e){e.printStackTrace();}
        }
        return insertedInt;
    }
    public int updateAccountHolderDetails(int account_id, String userDetailsKey,String userDetailsValue){
            int updatedInt=0;
        try {
            initialiseMySQLStatement();
            if (userDetailsKey.equalsIgnoreCase("name")) {
                updatedInt = statement.executeUpdate("UPDATE user_details SET name='" + userDetailsValue +
                        "' WHERE account_id=" + account_id);
            } else if (userDetailsKey.equalsIgnoreCase("ph_no")) {
                int userDetailsValueInt=Integer.parseInt(userDetailsValue);
                updatedInt = statement.executeUpdate("UPDATE user_details SET ph_no='" + userDetailsValueInt +
                        "' WHERE account_id=" + account_id);
            } else if (userDetailsKey.equalsIgnoreCase("address")) {
                updatedInt = statement.executeUpdate("UPDATE user_details SET address='" + userDetailsValue +
                        "' WHERE account_id=" + account_id);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {con.close(); }catch (Exception e){e.printStackTrace();}
        }
        return updatedInt;
    }

}
