package bank;

import java.sql.*;

public class DBConDetails extends PropertyFileUtility {
    private Connection con=null;
    private Statement statement=null;
    //Reads all the required config and set connection and statement

    public DBConDetails() {
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
    public Connection getCon() {
        return con;
    }

    public Statement getStatement() {
        return statement;
    }

}
