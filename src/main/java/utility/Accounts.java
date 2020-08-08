package utility;

import bank.AccountHolder;
import java.util.Scanner;

public class Accounts {
    public static void main(String[] args) {
        AccountHolder acc[];
        acc=new AccountHolder[4];
        acc[0]= new AccountHolder("pabitrakumardash@gmail.com","9861378830","Pabitra Kumar Dash",2000);
        acc[1]= new AccountHolder("snehalatadash@gmail.com","6370654921","Snehalata Dash",1000);
        acc[2]= new AccountHolder("chinmayadash@gmail.com","7353416041","Chinmaya Dash",1000);
        acc[3]= new AccountHolder("tanmayadash@gmail.com","7978723143","Tanmaya Dash",100);

        String userid,password;
        Scanner sc=new Scanner(System.in);
        System.out.println("\t-----------Welcome to your Bank----------\n\n");
        System.out.print("Please Enter Your User-Id:");
        userid=sc.nextLine();


        for(int i=0;i<acc.length;i++)
        {
            if(userid.equals(acc[i].getUserid()))
            {
                System.out.print("Enter password:");
                password=sc.nextLine();
                if(password.equals(acc[i].getPassword()))
                {
                    System.out.println("Hii..."+acc[i].getName());
                    acc[i].deposit(10000);
                    System.out.println("Your account balance is:"+acc[i].getBalance());
                }
                else
                    System.out.println("Password Authentication Error");
            }
        }
    }
}
