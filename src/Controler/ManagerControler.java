
package Controler;

import Model.Product;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;



public class ManagerControler extends User{
    
   
    
    public ManagerControler(int userId, String userName, String password, String userType, String email) {
        super(userId, userName, password, userType, email);
        conn = (Connection) DbControler.connect();
    }
     // add new product
    public void addProduct(Product product){
    
    try { 
        
        String checkQuery = "SELECT product_id FROM product_detailss WHERE product_name = ?";
        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
        checkStatement.setString(1, product.getProduct_name());
        ResultSet resultSet = checkStatement.executeQuery();     
        
         if (resultSet.next()) {
            // A product with the same name already exists
            System.out.println("Product with the same name already exists. Cannot add.");
            JOptionPane.showMessageDialog(null, "Product with the same name already exists. Cannot add.", "Duplicate Product", JOptionPane.WARNING_MESSAGE);
            
        } else {
        
            String query = "INSERT INTO product_detailss (product_id, product_name, product_category,product_price,product_quantity ) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductid());
            preparedStatement.setString(2, product.getProduct_name());
            preparedStatement.setString(3, product.getProduct_category());
            preparedStatement.setDouble(4, product.getProduct_price());
            preparedStatement.setInt(5, product.getProduct_quantity());
            
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product added successfully.", "ADED", JOptionPane.INFORMATION_MESSAGE);
         }
        } catch (Exception  e) {
            e.printStackTrace();
            System.out.println("error");
        }
    
    
    }
    public void updateProduct(Product product) {
        try {
            String query = "UPDATE  product_detailss SET  product_name = ?, product_category = ?,product_price = ?,product_quantity = ? WHERE product_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
                       preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setString(2, product.getProduct_category());
            preparedStatement.setDouble(3, product.getProduct_price());
            preparedStatement.setInt(4, product.getProduct_quantity());
             preparedStatement.setInt(5, product.getProductid());

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"successfully Updated! ","DONE",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Check Again! ","ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }
    
     public void removeProduct(int productid){
        try {
            String query = "DELETE FROM product_detailss  WHERE product_id = ?";
            PreparedStatement  preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productid);
            preparedStatement.executeUpdate();
             JOptionPane.showMessageDialog(null,"successfully Removed! ","DONE",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
             e.printStackTrace();
              JOptionPane.showMessageDialog(null,"Check Again! ","ERROR",JOptionPane.WARNING_MESSAGE);
        }
    
    
    }
     public boolean createNewUserAccount(String newUserName, String newPassword, String newUserType, String newUserEmail){
     
         
     
        try {
            // Check if the username is available
            String checkQuery = "SELECT COUNT(*) FROM user_details WHERE user_name = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
            checkStatement.setString(1, newUserName);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int userCount = resultSet.getInt(1);
            
            if (userCount > 0) {
                // Username is not available
                System.out.println("Username is not available. Please choose a different username.");
                
                 JOptionPane.showMessageDialog(null,"Username is not available. Please choose a different username! ","ERROR",JOptionPane.WARNING_MESSAGE);
                return false;
            } else {
                // Username is available, so create the new account
                String insertQuery = "INSERT INTO user_details (user_name, user_password, user_type, user_email) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                insertStatement.setString(1, newUserName);
                insertStatement.setString(2, newPassword);
                insertStatement.setString(3, newUserType);
                insertStatement.setString(4, newUserEmail);
                insertStatement.executeUpdate();
                 JOptionPane.showMessageDialog(null,"successfully Create New User Account! ","DONE",JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Check Again! ","ERROR",JOptionPane.WARNING_MESSAGE);
            return false;
        }
     
     
     }
     
     public boolean  updateUserAccount(String newUserName, String newPassword, String newUserType, String newUserEmail,int userId){
   
     try {
            String query = "UPDATE  user_details SET  user_name = ?, user_password = ?,user_type = ?,user_email = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, newUserName);
                preparedStatement.setString(2, newPassword);
                preparedStatement.setString(3, newUserType);
                preparedStatement.setString(4, newUserEmail);  
                preparedStatement.setInt(5, userId); 
             

            preparedStatement.executeUpdate();
             JOptionPane.showMessageDialog(null,"successfully Updated, User Account! ","DONE",JOptionPane.INFORMATION_MESSAGE);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
              JOptionPane.showMessageDialog(null,"Check Again! ","ERROR",JOptionPane.WARNING_MESSAGE);
            return false;
            
        }
   
     
     }
   
      public boolean removeUserAccount(int userId){
      
      
       try {
            String query = "DELETE FROM user_details  WHERE user_id = ?";
            PreparedStatement  preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"successfully Removed, User Account! ","DONE",JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (SQLException e) {
             e.printStackTrace();
              JOptionPane.showMessageDialog(null,"Check Again! ","ERROR",JOptionPane.WARNING_MESSAGE);
             return false;
        }
      
     
      }
      
      public void loadTableData(JTable jTable) {
        try {
            String sql = "SELECT user_id, user_name, user_password, user_type, user_email FROM user_details";
            PreparedStatement pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @Override
    public boolean authenticate(String userName, String password) {
        try {
            String query = "SELECT user_id FROM user_details WHERE user_name = ? AND user_password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getUserType() {
         try {
             String query = "SELECT user_type FROM user_details WHERE user_name = ?";
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             preparedStatement.setString(1, userName);
             ResultSet result = preparedStatement.executeQuery();
              if (result.next()) {
                return result.getString("user_type");
            }
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
     return null;
     }

    @Override
    public void setUserType(String userType) {
        
        this.userType = userType;
    
    }
    
        
}
