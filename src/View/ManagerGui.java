    
package View;

import Controler.DbControler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Controler.ManagerControler;
import Model.Product;
import Model.User;
import static Model.User.conn;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class ManagerGui extends javax.swing.JFrame {
        
        private  ManagerControler manager;
        private  User selectUser;
        private  Product selectedProduct ;

      Connection conn ;
      PreparedStatement pps = null;
      ResultSet rs = null;
    public ManagerGui() {
        initComponents();
        conn = DbControler.connect();
        manager = new ManagerControler(0, null, null, null, null);
        
        
        manager.loadTableData(jTable4);
        tableLoard();
        tableLoardSell();
        tableLoardSellProductName();
        
             jTable4.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && jTable4.getSelectedRow() != -1) {
                    int selectedRow = jTable4.getSelectedRow();

                    
                    int userId = (int) jTable4.getValueAt(selectedRow, 0); 
                    String userName = (String) jTable4.getValueAt(selectedRow, 1); 
                    String userpass = (String) jTable4.getValueAt(selectedRow, 2); 
                    String usertype = (String) jTable4.getValueAt(selectedRow, 3);
                    String useremail = (String) jTable4.getValueAt(selectedRow, 4); 
            
                  
              selectUser = new User(userId, userName, userpass, usertype, useremail) {
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
                    };
                   
                    {
                    };
            }
            }
       
       
       
       });
                    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    int selectedRow = jTable1.getSelectedRow();

                    // Get the product data from the selected row
                    int productId = (int) jTable1.getValueAt(selectedRow, 0); // Assuming the first column contains product ID
                    String productName = (String) jTable1.getValueAt(selectedRow, 1); // Assuming the second column contains product name
                    double price = (double) jTable1.getValueAt(selectedRow, 3); // Assuming the third column contains price
                    int quantity = (int) jTable1.getValueAt(selectedRow, 4); // Assuming the fourth column contains quantity
                    String category = (String) jTable1.getValueAt(selectedRow, 2); // Assuming the fifth column contains category
            
            
                    selectedProduct = new Product(productId,productName,price,quantity,category)
                    {
                    };
            }
            }
       
       
       
       });
                 btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                txt_PN.setText("hello");
                if (selectUser != null) {
                txt_userId.setText(Integer.toString(selectUser.getUserId()));
                txt_UserName.setText(selectUser.getUserName());
                txt_UserEmail.setText (selectUser.getEmail());
                txt_UserType.setText(selectUser.getUserType());
                txt_UserPassword.setText(selectUser.getPassword());
                
                
                }
             else{
                    System.out.println("null");}
               
            }
        });
                      btn_Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (selectedProduct != null) {
                
                txt_pname.setText(selectedProduct.getProduct_name());
                txt_pprice.setText(Double.toString(selectedProduct.getProduct_price()));
                txt_pid.setText(Integer.toString(selectedProduct.getProductid()));
                txt_pqunty.setText(Integer.toString(selectedProduct.getProduct_quantity()));
                txt_cat.setText(selectedProduct.getProduct_category());
                
                
                }
             else{
                    System.out.println("null");}
               
            }
        });
                      
    }
 public void searchdetails(){
    
     String enter  = txt_search.getText();
        try {
            String sql = "SELECT * FROM user_details WHERE user_name LIKE '%"+enter+"%' OR user_id LIKE '%"+enter +"%'";
            PreparedStatement pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }
  public void searchdetailsTwo(){
    
     String enter  = txt_searchTwo.getText();
        try {
            String sql = "SELECT * FROM product_detailss WHERE product_name LIKE '%"+enter+"%' OR product_id LIKE '%"+enter +"%'";
            PreparedStatement pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salesPieChart1 = new View.SalesPieChart();
        salesPieChart2 = new View.SalesPieChart();
        salesPieChart3 = new View.SalesPieChart();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_manage = new View.MyButton();
        btn_create = new View.MyButton();
        btn_view = new View.MyButton();
        myButton1 = new View.MyButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_cat = new javax.swing.JTextField();
        txt_pqunty = new javax.swing.JTextField();
        txt_pname = new javax.swing.JTextField();
        txt_pid = new javax.swing.JTextField();
        txt_pprice = new javax.swing.JTextField();
        btn_update = new View.MyButton();
        btn_remove = new View.MyButton();
        btn_Select = new View.MyButton();
        btn_addProduct = new View.MyButton();
        btn_clear = new View.MyButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txt_searchTwo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_catagory = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_quntity = new javax.swing.JTextField();
        btn_addProduct2 = new View.MyButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_UserName = new javax.swing.JTextField();
        txt_userId = new javax.swing.JTextField();
        txt_UserPassword = new javax.swing.JTextField();
        txt_UserEmail = new javax.swing.JTextField();
        txt_UserType = new javax.swing.JTextField();
        btn_Create = new View.MyButton();
        btn_Remove = new View.MyButton();
        btn_Update = new View.MyButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        btnadd = new View.MyButton();
        btnclear = new View.MyButton();
        jLabel18 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DashBoard");
        setBackground(new java.awt.Color(255, 102, 255));
        setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 15, 32));

        btn_manage.setBackground(new java.awt.Color(0, 0, 0));
        btn_manage.setForeground(new java.awt.Color(255, 255, 255));
        btn_manage.setText("Manage Product");
        btn_manage.setColor(new java.awt.Color(0, 0, 0));
        btn_manage.setColorover(new java.awt.Color(255, 0, 0));
        btn_manage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_manage.setRadius(25);
        btn_manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manageActionPerformed(evt);
            }
        });

        btn_create.setBackground(new java.awt.Color(0, 0, 0));
        btn_create.setForeground(new java.awt.Color(255, 255, 255));
        btn_create.setText("Create Account");
        btn_create.setColor(new java.awt.Color(0, 0, 0));
        btn_create.setColorover(new java.awt.Color(255, 51, 0));
        btn_create.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_create.setRadius(25);
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });

        btn_view.setBackground(new java.awt.Color(0, 0, 0));
        btn_view.setForeground(new java.awt.Color(255, 255, 255));
        btn_view.setText("View Sells Details");
        btn_view.setColor(new java.awt.Color(0, 0, 0));
        btn_view.setColorclick(new java.awt.Color(255, 255, 255));
        btn_view.setColorover(new java.awt.Color(255, 51, 0));
        btn_view.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_view.setRadius(25);
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });

        myButton1.setBackground(new java.awt.Color(255, 0, 0));
        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("Logout");
        myButton1.setBordercolor(new java.awt.Color(255, 0, 0));
        myButton1.setColor(new java.awt.Color(255, 0, 0));
        myButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        myButton1.setRadius(20);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_manage, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_view, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btn_manage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btn_view, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, 590));

        jPanel2.setBackground(new java.awt.Color(0, 15, 32));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/new.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 220, 170));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 200));

        jTabbedPane1.setBackground(new java.awt.Color(0, 15, 32));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 896, 230));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 510, 270, 118));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Total Sells");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 450, 100, 40));

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Product name            :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, 210, 28));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Product catogory     :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 180, 28));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Product Id              :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 170, 28));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Product qunty          :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 180, 28));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Product price            :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 190, 28));

        txt_cat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_cat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txt_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 298, 41));

        txt_pqunty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_pqunty.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txt_pqunty, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 298, 41));

        txt_pname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_pname.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txt_pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 298, 41));

        txt_pid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_pid.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txt_pid, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 298, 41));

        txt_pprice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel3.add(txt_pprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 298, 41));

        btn_update.setBackground(new java.awt.Color(0, 0, 0));
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("update");
        btn_update.setColor(new java.awt.Color(0, 0, 0));
        btn_update.setColorover(new java.awt.Color(255, 51, 0));
        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_update.setRadius(20);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        jPanel3.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, 110, 40));

        btn_remove.setForeground(new java.awt.Color(255, 255, 255));
        btn_remove.setText("remove");
        btn_remove.setColor(new java.awt.Color(255, 51, 0));
        btn_remove.setColorover(new java.awt.Color(0, 255, 0));
        btn_remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_remove.setRadius(20);
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });
        jPanel3.add(btn_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 680, 110, 40));

        btn_Select.setForeground(new java.awt.Color(255, 255, 255));
        btn_Select.setText("Select");
        btn_Select.setColor(new java.awt.Color(0, 0, 0));
        btn_Select.setColorover(new java.awt.Color(255, 0, 0));
        btn_Select.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Select.setRadius(20);
        jPanel3.add(btn_Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 280, 130, 40));

        btn_addProduct.setBackground(new java.awt.Color(0, 0, 0));
        btn_addProduct.setForeground(new java.awt.Color(255, 255, 255));
        btn_addProduct.setText("Add");
        btn_addProduct.setColor(new java.awt.Color(0, 0, 0));
        btn_addProduct.setColorover(new java.awt.Color(255, 51, 0));
        btn_addProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_addProduct.setRadius(20);
        btn_addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addProductActionPerformed(evt);
            }
        });
        jPanel3.add(btn_addProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 680, 120, 40));

        btn_clear.setBackground(new java.awt.Color(255, 51, 0));
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("Clear");
        btn_clear.setColor(new java.awt.Color(255, 51, 0));
        btn_clear.setColorover(new java.awt.Color(0, 255, 0));
        btn_clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_clear.setRadius(20);
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel3.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 680, 110, 40));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(0, 51, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(jTable1);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 30, 1140, 180));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Search  :");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txt_searchTwo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchTwoKeyReleased(evt);
            }
        });
        jPanel3.add(txt_searchTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 210, 40));

        jTabbedPane1.addTab("tab1", jPanel3);

        jLabel2.setText("Product ca  :");

        jLabel3.setText("Product id  :");

        jLabel4.setText("Product Name  :");

        jLabel5.setText("Product q  :");

        jLabel6.setText("Product pri :");

        btn_addProduct2.setText("Add");
        btn_addProduct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addProduct2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_quntity, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_catagory, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(513, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_addProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(489, 489, 489))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(950, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(943, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_catagory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txt_quntity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addComponent(btn_addProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(153, 153, 153)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(611, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(284, 284, 284)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(480, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("user password      :");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 160, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("user  name    :");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("user email             :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 170, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("user type       :");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 130, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("User Id           :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 30));

        txt_UserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_UserName.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(txt_UserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 300, 40));

        txt_userId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_userId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userIdActionPerformed(evt);
            }
        });
        jPanel6.add(txt_userId, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 300, 40));

        txt_UserPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_UserPassword.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(txt_UserPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 300, 40));

        txt_UserEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_UserEmail.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(txt_UserEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 300, 40));

        txt_UserType.setBackground(new java.awt.Color(255, 255, 255));
        txt_UserType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel6.add(txt_UserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 300, 40));

        btn_Create.setBackground(new java.awt.Color(0, 0, 0));
        btn_Create.setForeground(new java.awt.Color(255, 255, 255));
        btn_Create.setText("Create Account");
        btn_Create.setColor(new java.awt.Color(0, 0, 0));
        btn_Create.setColorover(new java.awt.Color(255, 0, 0));
        btn_Create.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Create.setRadius(20);
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });
        jPanel6.add(btn_Create, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 150, 40));

        btn_Remove.setForeground(new java.awt.Color(255, 255, 255));
        btn_Remove.setText("Remove Account");
        btn_Remove.setColor(new java.awt.Color(255, 0, 0));
        btn_Remove.setColorover(new java.awt.Color(0, 255, 0));
        btn_Remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Remove.setRadius(20);
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });
        jPanel6.add(btn_Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 320, 160, 40));

        btn_Update.setBackground(new java.awt.Color(0, 0, 0));
        btn_Update.setForeground(new java.awt.Color(255, 255, 255));
        btn_Update.setText("Update Details");
        btn_Update.setColor(new java.awt.Color(0, 0, 0));
        btn_Update.setColorover(new java.awt.Color(255, 51, 0));
        btn_Update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Update.setRadius(20);
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        jPanel6.add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 160, 40));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 1100, 280));

        btnadd.setBackground(new java.awt.Color(0, 0, 0));
        btnadd.setForeground(new java.awt.Color(255, 255, 255));
        btnadd.setText("Select ");
        btnadd.setColor(new java.awt.Color(0, 0, 0));
        btnadd.setColorover(new java.awt.Color(51, 204, 0));
        btnadd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd.setRadius(20);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        jPanel6.add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 710, 140, 40));

        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setText("Clear");
        btnclear.setColor(new java.awt.Color(255, 0, 0));
        btnclear.setColorover(new java.awt.Color(51, 204, 0));
        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnclear.setRadius(20);
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        jPanel6.add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 710, 140, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Search  : ");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 80, -1));

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel6.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 230, 40));

        jTabbedPane1.addTab("tab4", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -30, 1200, 820));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
SalesPieChart s = new SalesPieChart();
    private void btn_manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manageActionPerformed
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btn_manageActionPerformed

    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
             jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btn_createActionPerformed

    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
            jTabbedPane1.setSelectedIndex(0);
       loadChart();
       
        
    }//GEN-LAST:event_btn_viewActionPerformed

    
     void loadChart() {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/applestore-02";
        String username = "root";
        String password = "";

       DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product_name, SUM(sells_quantity) as total_quantity FROM sells_details GROUP BY product_name");

            while (resultSet.next()) {
                String product = resultSet.getString("product_name");
                int totalQuantity = resultSet.getInt("total_quantity");
              dataset.addValue(totalQuantity, "sells_details", product);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the pie chart
//      JFreeChart chart = ChartFactory.createBarChart(
JFreeChart chart = ChartFactory.createBarChart(
                "Combined Product Sales Bar Chart",
                "Product",
                "Total Quantity",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
         // Create a ChartPanel to display the chart
    ChartPanel chartPanel = new ChartPanel(chart);

   

    // Add the new chart to jPanel7
    jPanel7.setLayout(new BorderLayout());
    jPanel7.add(chartPanel);
    jPanel7.revalidate();

  
     }
    private void btn_addProduct2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addProduct2ActionPerformed
     
      
      
    }//GEN-LAST:event_btn_addProduct2ActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
       int id = Integer.parseInt(txt_pid.getText());
      String name = txt_pname.getText();
      String catagory = txt_cat.getText();
        double price =  Double.parseDouble(txt_pprice.getText());
      int qunt = Integer.parseInt(txt_pqunty.getText());
      
        ManagerControler manager = new ManagerControler(0,null,null,null,null);
        
        Product p = new Product(id, name, price,qunt , catagory) {
        };
      manager.updateProduct(p);
      tableLoard();
    txt_pname.setText("");
    txt_pid.setText("");
    txt_pqunty.setText("");
    txt_pprice.setText("");
    txt_cat.setText("");
  
      
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        int id = Integer.parseInt(txt_pid.getText());
        String name = null;
      String catagory = null;
      double price =0;
      int qunt = 0;
           ManagerControler manager = new ManagerControler(id,null,null,null,null);
           Product p = new Product(id, name, price,qunt , catagory) {
        };
        manager.removeProduct(id);
         tableLoard();
    txt_pname.setText("");
    txt_pid.setText("");
    txt_pqunty.setText("");
    txt_pprice.setText("");
    txt_cat.setText("");
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
         String newUserName = txt_UserName.getText();
         String newPassword = txt_UserPassword.getText();
         String newUserType = txt_UserType.getText();
         String newUserEmail = txt_UserEmail.getText();
          ManagerControler manager = new ManagerControler(0,null,null,null,null);
          
          
          if( newUserName.isEmpty()|| newPassword.isEmpty()|| newUserEmail.isEmpty() || newUserType.isEmpty()){
           JOptionPane.showMessageDialog(this, "fill all ");
          
          
          }
          else if(newPassword.length() < 8 || !newPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")){
          JOptionPane.showMessageDialog(this, "Password must include at least one symbol and one digit ");
          
          }
          
          else{
             if ("admin".equals(newUserType) || "cashier".equals(newUserType) || "user".equals(newUserType)) {
               boolean userCreated = manager.createNewUserAccount(newUserName, newPassword, newUserType, newUserEmail);
               if(userCreated){
               JOptionPane.showMessageDialog(this, "user aded");
               }
               else{
               JOptionPane.showMessageDialog(this, "user aded fails");
               }
             }
             else{
               JOptionPane.showMessageDialog(this, "Invalid user type. Please enter 'admin', 'cashier', or 'user'.", "Error", JOptionPane.ERROR_MESSAGE);
             }
          }

    }//GEN-LAST:event_btn_CreateActionPerformed

    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed
   
         int userId  = Integer.parseInt(txt_userId.getText());
         ManagerControler manager = new ManagerControler(0,null,null,null,null);
         
         manager.removeUserAccount(userId);
         
          
    }//GEN-LAST:event_btn_RemoveActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
         String newUserName = txt_UserName.getText();
         String newPassword = txt_UserPassword.getText();
         String newUserType = txt_UserType.getText();
         String newUserEmail = txt_UserEmail.getText();
         int userId  = Integer.parseInt(txt_userId.getText());
          ManagerControler manager = new ManagerControler(0,null,null,null,null);
             if ("admin".equals(newUserType) || "cashier".equals(newUserType) || "user".equals(newUserType)) {
        boolean userCreated = manager.updateUserAccount(newUserName, newPassword, newUserType, newUserEmail,userId);

        if (userCreated) {
           
        } else {
            // User account creation failed
            // You can add code here to display an error message or perform other actions
        }
    } else {
        // Invalid user type entered
        JOptionPane.showMessageDialog(this, "Invalid user type. Please enter 'admin', 'cashier', or 'user'.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void txt_userIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userIdActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnaddActionPerformed

    private void btn_addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addProductActionPerformed
         int id = Integer.parseInt(txt_pid.getText());
      String name = txt_pname.getText();
      String catagory = txt_cat.getText();
      double price =  Double.parseDouble(txt_pprice.getText());
      int qunt = Integer.parseInt(txt_pqunty.getText());
      
  
      
      ManagerControler manager = new ManagerControler(0,null,null,null,null);
      Product product = new Product(0, name, price, qunt, catagory) {
      };
      manager.addProduct(product);
      
      tableLoard();
      txt_pname.setText("");
    txt_pid.setText("");
    txt_pqunty.setText("");
    txt_pprice.setText("");
    txt_cat.setText("");
    }//GEN-LAST:event_btn_addProductActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
    txt_pname.setText("");
    txt_pid.setText("");
    txt_pqunty.setText("");
    txt_pprice.setText("");
    txt_cat.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
      txt_UserEmail.setText("");
      txt_UserName.setText("");
      txt_UserPassword.setText("");
      txt_UserType.setText("");
      txt_userId.setText("");
    }//GEN-LAST:event_btnclearActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
     searchdetails();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchTwoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchTwoKeyReleased
      searchdetailsTwo();
    }//GEN-LAST:event_txt_searchTwoKeyReleased

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        new LoginGui().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myButton1ActionPerformed
 public void tableLoard(){
        
         try {
             String sql = "SELECT  product_id, product_name, product_category,product_price,product_quantity FROM product_detailss";
             pps = conn.prepareStatement(sql);
             rs = pps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             
         }
         
     }
  public void tableLoardSell(){
        
         try {
             String sql = "SELECT  sells_id, product_id,product_name ,product_price,sells_quantity,sells_date FROM sells_details";
             pps = conn.prepareStatement(sql);
             rs = pps.executeQuery();
             jTable2.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             
         }
         
     }
   public void tableLoardSellProductName(){
        
         try {
             String sql = "SELECT product_name, SUM(sells_quantity) as total_quantity FROM sells_details GROUP BY product_name";
             pps = conn.prepareStatement(sql);
             rs = pps.executeQuery();
             jTable3.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             
         }
         
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new ManagerGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.MyButton btn_Create;
    private View.MyButton btn_Remove;
    private View.MyButton btn_Select;
    private View.MyButton btn_Update;
    private View.MyButton btn_addProduct;
    private View.MyButton btn_addProduct2;
    private View.MyButton btn_clear;
    private View.MyButton btn_create;
    private View.MyButton btn_manage;
    private View.MyButton btn_remove;
    private View.MyButton btn_update;
    private View.MyButton btn_view;
    private View.MyButton btnadd;
    private View.MyButton btnclear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private View.MyButton myButton1;
    private View.SalesPieChart salesPieChart1;
    private View.SalesPieChart salesPieChart2;
    private View.SalesPieChart salesPieChart3;
    private javax.swing.JTextField txt_UserEmail;
    private javax.swing.JTextField txt_UserName;
    private javax.swing.JTextField txt_UserPassword;
    private javax.swing.JTextField txt_UserType;
    private javax.swing.JTextField txt_cat;
    private javax.swing.JTextField txt_catagory;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_pid;
    private javax.swing.JTextField txt_pname;
    private javax.swing.JTextField txt_pprice;
    private javax.swing.JTextField txt_pqunty;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_quntity;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_searchTwo;
    private javax.swing.JTextField txt_userId;
    // End of variables declaration//GEN-END:variables
}
