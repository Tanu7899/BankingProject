package bank;

import java.sql.*;

public class DBConDetails extends PropertyFileUtility {
    protected Connection con=null;
    protected Statement statement=null;
    protected void initialiseMySQLStatement(){
        String host=getConfigProperties("mysql_host");
        String username=getConfigProperties("mysql_username");
        String password=getConfigProperties("mysql_password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, username, password);
            statement=con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
