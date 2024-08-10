
package Model;

import Controler.DbControler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public  abstract  class User {
    
    public int userId;
    public String userName;
    private String password;
    public String userType;
    private String email;
    public static Connection conn;

    public User(int userId, String userName, String password, String userType, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.email = email;
        conn = (Connection) DbControler.connect(); 
    }

 

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
      public  abstract  boolean authenticate(String userName,String password); 
     public abstract String getUserType();
    public abstract void setUserType(String userType) ;
    
}
