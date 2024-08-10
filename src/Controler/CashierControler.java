
package Controler;


import Model.User;
import Model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Controler.DbControler;
import java.awt.Desktop;
import Model.Sells;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class CashierControler extends User{
    private final Connection conn;

      PreparedStatement pps = null;
      ResultSet rs = null;
    public CashierControler(int userId, String userName, String password, String userType, String email) {
        super(userId, userName, password, userType, email);
        conn = (Connection) DbControler.connect();
       
    }

  
    public boolean sellProduct(Sells sell)   {
    
        try {
            
//            Connection conn = (Connection) DbControler.connect();
             conn.setAutoCommit(false);  // Start a transaction
  
        String checkQuantityQuery = "SELECT product_quantity FROM product_detailss WHERE product_id = ?";
        PreparedStatement checkQuantityStatement = conn.prepareStatement(checkQuantityQuery);
        checkQuantityStatement.setInt(1, sell.getProductid());
        ResultSet resultSet = checkQuantityStatement.executeQuery();
        
          if (resultSet.next()) {
            int availableQuantity = resultSet.getInt("product_quantity");
            if (availableQuantity < sell.getProduct_quantity()) {
                conn.rollback(); // Rollback the transaction
                return false; // Insufficient quantity
                
            }
        }else {
            conn.rollback(); // Rollback the transaction
            return false; // Product not found
        }
            // Record the sale in the sales table
        String insertSaleQuery = "INSERT INTO sells_details (sells_id,product_id, product_name, product_price,sells_quantity,Sells_price , sells_date) VALUES (?, ?, ?, ?, ?,?, NOW())";
        PreparedStatement insertSaleStatement = conn.prepareStatement(insertSaleQuery);
        insertSaleStatement.setInt(1, sell.getSellId());
        insertSaleStatement.setInt(2, sell.getProductid());
        insertSaleStatement.setString(3, sell.getProduct_name()); // Assuming productName is a parameter
        
        insertSaleStatement.setDouble(4, sell.getProduct_price());
        insertSaleStatement.setInt(5, sell.getProduct_quantity());
            insertSaleStatement.setDouble(6, sell.getSellsPrice());
       
        insertSaleStatement.executeUpdate();
        
        // Update the product quantity in product_details
        String updateQuantityQuery = "UPDATE product_detailss SET product_quantity = product_quantity - ? WHERE product_id = ?";
        PreparedStatement updateQuantityStatement = conn.prepareStatement(updateQuantityQuery);
        updateQuantityStatement.setInt(1, sell.getProduct_quantity());
        updateQuantityStatement.setInt(2, sell.getProductid());
        updateQuantityStatement.executeUpdate();

        conn.commit(); // Commit the transaction
        conn.setAutoCommit(true); // Reset the auto-commit mode
        
        return true; // Sale recorded and quantity updated
     
     
       
 
      
           
       
    } catch (SQLException e) {
 
        return false; // Sale recording failed
    }
   
    }
    
//     public List<Product> fetchTableData( ) {
//         
//         List<Product> products = new ArrayList<>();
//         
//         
//        
//        try {
//             String query = "SELECT product_id, product_name, product_category, product_price, product_quantity FROM product_detailss";
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//          ResultSet resultSet = preparedStatement.executeQuery();
//          
//          while(resultSet.next()){
//          
//          int productId = resultSet.getInt("product_id");
//          String productName = resultSet.getString("product_Name");
//          String Category = resultSet.getString("product_Category");
//          double price = resultSet.getDouble("product_price");
//          int Quantity = resultSet.getInt("product_quantity");
//          
//          Product product = new Product(productId, productName, price, Quantity, Category) {
//          };
//           products.add(product);
//          
//          }
//           resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return products;
//    } 
    
    public void loadTableData(JTable jTable) {
        try {
            String sql = "SELECT product_id, product_name, product_category, product_price, product_quantity FROM product_detailss";
            PreparedStatement pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
      public void tableLoardSell(JTable jTable){
        
         try {
             String sql = "SELECT  sells_id, product_id,product_name ,product_price,sells_quantity,sells_date FROM sells_details";
             pps = conn.prepareStatement(sql);
             rs = pps.executeQuery();
             jTable.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             
         }
         
     }

    @Override
    public boolean authenticate(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUserType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setUserType(String userType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    


            
           

             
      
  
    
        
    

    


