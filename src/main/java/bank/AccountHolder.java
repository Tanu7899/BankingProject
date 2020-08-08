package bank;

public class AccountHolder {
    private String userid;
    private String password;
    private String name;
    private double balance;

    public AccountHolder (String userid,String password,String name,double balance) {
        this.userid=userid;
        this.password=password;
        this.name=name;
        this.balance=balance;
    }
    public String getUserid() {
        return userid;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance=balance+amount;
    }
    public void withdraw(double amount) {
        balance=balance-amount;
    }
}
