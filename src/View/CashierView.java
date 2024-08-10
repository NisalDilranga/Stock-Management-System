
package View;
import Controler.CashierControler;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import Controler.DbControler;
import Model.Product;
import Model.Sells;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.jdi.connect.Connector;
import java.awt.Desktop;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
public class CashierView extends javax.swing.JFrame {

    private CashierControler cashier;
  private Product selectedProduct;

    private DefaultListModel<String> invoiceListModel;
    private double    totalBill;
    private int totalQuantity;
     Connection conn ;
      PreparedStatement pps = null;
      ResultSet rs = null;

    public CashierView() {
        initComponents();
        conn = DbControler.connect();
         invoiceListModel = new DefaultListModel<>();
       cashier = new CashierControler(0, null, null, null, null);
      
  
       cashier.loadTableData(jTable1);
       cashier.loadTableData(jTable2);
       cashier.tableLoardSell(jTable3);
        
      cashier.loadTableData(jTable2);
       
       


     btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                if (selectedProduct != null) {
                
                txt_PName.setText(selectedProduct.getProduct_name());
                txt_PPrice.setText(Double.toString(selectedProduct.getProduct_price()));
                txt_pId.setText(Integer.toString(selectedProduct.getProductid()));
                
                
                }
             else{
                    System.out.println("null");}
               
            }
        });
         btn_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        // Create a DefaultListModel and set it for your JList

                 String productName = txt_PName.getText();
                String priceText = txt_PPrice.getText();
                String qtyText = txt_pquntity.getText();
                String pIdText = txt_pId.getText();
                
                 if (productName.isEmpty() || priceText.isEmpty() || qtyText.isEmpty() || pIdText.isEmpty()) {
                    // Display a message to the user that some fields are empty
                    JOptionPane.showMessageDialog(CashierView.this, "First Select Products.", "Missing Products", JOptionPane.WARNING_MESSAGE);
                    return; // Exit the actionPerformed method
                }
                  double price;
                int quantity;
                 try {
                    price = Double.parseDouble(priceText);
                    quantity = Integer.parseInt(qtyText);
                } catch (NumberFormatException ex) {
                    // Display a message to the user that price and quantity must be valid numbers
                    JOptionPane.showMessageDialog(CashierView.this, "Please enter valid numbers for Price and Quantity.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return; // Exit the actionPerformed method
                }
                
                 double productPrice = quantity * price;
                totalBill +=  calculatePrice(quantity);
                totalQuantity += quantity;

                 // Add the product information to the invoice list
                String productInfo = "Product Name: " + productName + "     | "
                        + "Price: $" + price + "     | "
                        + "Quantity: " + quantity + "      | "
                        + "Full Price: " + productPrice;
               
                invoiceListModel.addElement(productInfo); // Add to the model
                 invoiceList.setModel(invoiceListModel);
              
           

                // Update the total bill and quantity displayed on the form
                txt_total.setText(Double.toString(totalBill)  );
                txt_TotalQ.setText(Integer.toString(totalQuantity) );

               
                
                
                
        }
        });
          btn_Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Create a new Document
        Document document = new Document();

        try {
            // Define a file path where the PDF will be saved
            String outputPath = "D:\\test\\invoic.pdf";

            // Create a PdfWriter to write content to the PDF
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            } catch (DocumentException ex) {
                Logger.getLogger(CashierView.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open(); // Open the document for writing
             // Add a logo as a background image
             // Generate an alphanumeric invoice number
                    String invoiceNumber = generateAlphanumericInvoiceNumber();

           
             // Store Information
            Paragraph storeInfo = new Paragraph();
            storeInfo.add(new Chunk("Apple Store", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            storeInfo.add(Chunk.NEWLINE);
            storeInfo.add("123 Main Street, Kandy");
            storeInfo.add(Chunk.NEWLINE);
            storeInfo.add("Phone: 03000033392");
            storeInfo.setAlignment(Element.ALIGN_CENTER);
            document.add(storeInfo);
 // Invoice Information
                    Paragraph invoiceInfo = new Paragraph();
                    invoiceInfo.add("Invoice Number: " + invoiceNumber);
                    invoiceInfo.add(Chunk.NEWLINE);
                    // Format the date as "yyyy-MM-dd"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  ");
                    String formattedDate = dateFormat.format(new Date());
                    invoiceInfo.add("Date: " + formattedDate+"        ");
                     invoiceInfo.add(Chunk.NEWLINE);
                    SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss");
                    String formattedTime = timeFormat.format(new Date());
                    invoiceInfo.add("Time: " + formattedTime+"                 ");
                    invoiceInfo.setAlignment(Element.ALIGN_RIGHT);
                    
                    document.add(invoiceInfo);
                    
                    // Add a table for product details
                    PdfPTable table = new PdfPTable(4);
                    
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);
//                 
//              float borderWidthe = if;
//              BaseColor borderColr =BaseColor.BLACK;

                    // Table headers
                    PdfPCell cell = new PdfPCell(new Phrase("Product Name"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    
                    
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Quantity"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("price"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Full Price"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                    table.addCell(cell);
            
                    
                    double total = 0;
                    int totaQuntity = 0;
                    
            // Iterate through your invoiceListModel and add each element to the PDF
            for (int i = 0; i < invoiceListModel.size(); i++) {
//                String productInfo = invoiceListModel.getElementAt(i).toString();
                String productInfo = invoiceListModel.get(i);
                            String[] productData = productInfo.split("\\|");
                            String productName = productData[0].trim().split(":")[1].trim();
                            double price = Double.parseDouble(productData[1].trim().split(":")[1].trim().replace("$", ""));
                            int quantity = Integer.parseInt(productData[2].trim().split(":")[1].trim());
                            
                           double productPrice = price * quantity;
                            // Add product details to the table
                            table.addCell(productName);
                            table.addCell(String.valueOf(quantity));
                            table.addCell("$" + price);
                            table.addCell("$" + productPrice);
                            
                            total += price*quantity;
                           
                 
            }
             document.add(table);
             Paragraph totalPrice = new Paragraph();
             
             totalPrice.add("Total Price : $"+total);
             totalPrice.setAlignment(Element.ALIGN_RIGHT);
             document.add(totalPrice);
            
             
             Paragraph cloceLine = new Paragraph();
             cloceLine.add("********************************************************************************** ");
             cloceLine.setAlignment(Element.ALIGN_CENTER);
             document.add(cloceLine);
            
Desktop.getDesktop().open(new File("D:\\test\\invoic.pdf"));
            // Close the document and writer to save the PDF
            document.close();

        } catch ( IOException ex) {
            ex.printStackTrace();
        }       catch (DocumentException ex) {
                    Logger.getLogger(CashierView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
         }
        });
         
       jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && jTable2.getSelectedRow() != -1) {
                    int selectedRow = jTable2.getSelectedRow();

                    // Get the product data from the selected row
                    int productId = (int) jTable2.getValueAt(selectedRow, 0); // Assuming the first column contains product ID
                    String productName = (String) jTable2.getValueAt(selectedRow, 1); // Assuming the second column contains product name
                    double price = (double) jTable2.getValueAt(selectedRow, 3); // Assuming the third column contains price
                    int quantity = (int) jTable2.getValueAt(selectedRow, 4); // Assuming the fourth column contains quantity
                    String category = (String) jTable2.getValueAt(selectedRow, 2); // Assuming the fifth column contains category
            
            
                    selectedProduct = new Product(productId,productName,price,quantity,category)
                    {
                    };
            }
            }
       
       
       
       });
     

       
    }
     public void search(){
    
     String enter  = txt_search.getText();
        try {
            String sql = "SELECT * FROM product_detailss WHERE product_name LIKE '%"+enter+"%' OR product_id LIKE '%"+enter +"%'";
            PreparedStatement pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
           jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private double calculatePrice(int quantity) {
        return quantity * selectedProduct.getProduct_price();
    }
    // Method to generate an alphanumeric invoice number
    private static String generateAlphanumericInvoiceNumber() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        Random random = new Random();

        StringBuilder invoiceNumber = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            invoiceNumber.append(letters.charAt(random.nextInt(letters.length())));
        }
        for (int i = 0; i < 4; i++) {
            invoiceNumber.append(digits.charAt(random.nextInt(digits.length())));
        }
        return invoiceNumber.toString();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_view = new View.MyButton();
        btn_ViewProduct = new View.MyButton();
        myButton4 = new View.MyButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txt_PName = new javax.swing.JTextField();
        txt_PPrice = new javax.swing.JTextField();
        txt_pquntity = new javax.swing.JTextField();
        txt_pId = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        txt_TotalQ = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        invoiceList = new javax.swing.JList<>();
        btn_Clear = new View.MyButton();
        btnadd = new View.MyButton();
        btn_done = new View.MyButton();
        btn_Print = new View.MyButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        myButton1 = new View.MyButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashier DashBoard");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 15, 32));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_view.setForeground(new java.awt.Color(255, 255, 255));
        btn_view.setText("Add Sells Details");
        btn_view.setColor(new java.awt.Color(0, 0, 0));
        btn_view.setColorover(new java.awt.Color(255, 0, 0));
        btn_view.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_view.setRadius(20);
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });
        jPanel1.add(btn_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 210, 40));

        btn_ViewProduct.setBackground(new java.awt.Color(0, 0, 0));
        btn_ViewProduct.setForeground(new java.awt.Color(255, 255, 255));
        btn_ViewProduct.setText("View Product Details");
        btn_ViewProduct.setColor(new java.awt.Color(0, 0, 0));
        btn_ViewProduct.setColorover(new java.awt.Color(204, 0, 0));
        btn_ViewProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ViewProduct.setRadius(20);
        btn_ViewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ViewProductActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ViewProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 210, 40));

        myButton4.setBackground(new java.awt.Color(204, 0, 0));
        myButton4.setForeground(new java.awt.Color(255, 255, 255));
        myButton4.setText("Logout");
        myButton4.setColor(new java.awt.Color(204, 0, 0));
        myButton4.setColorover(new java.awt.Color(0, 255, 0));
        myButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        myButton4.setRadius(20);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(myButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 130, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/new.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 220, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 230, 730));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 978, 160));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Product Details");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 280, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Sells Details ");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 160, -1));

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

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 960, 170));

        jTabbedPane1.addTab("tab1", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 958, 169));
        jPanel5.add(txt_PName, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 180, 30));
        jPanel5.add(txt_PPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 210, 30));
        jPanel5.add(txt_pquntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 130, 30));
        jPanel5.add(txt_pId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 50, 30));
        jPanel5.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 190, 30));
        jPanel5.add(txt_TotalQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 290, 30));

        invoiceList.setBackground(new java.awt.Color(255, 255, 255));
        invoiceList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        invoiceList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        invoiceList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(invoiceList);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 648, 198));

        btn_Clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_Clear.setText("Clear");
        btn_Clear.setColor(new java.awt.Color(255, 0, 0));
        btn_Clear.setColorover(new java.awt.Color(0, 255, 0));
        btn_Clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Clear.setRadius(20);
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });
        jPanel5.add(btn_Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 670, 110, 30));

        btnadd.setBackground(new java.awt.Color(0, 0, 0));
        btnadd.setForeground(new java.awt.Color(255, 255, 255));
        btnadd.setText("Add TO List");
        btnadd.setColor(new java.awt.Color(0, 0, 0));
        btnadd.setColorover(new java.awt.Color(204, 0, 0));
        btnadd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd.setRadius(20);
        jPanel5.add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 190, 160, 40));

        btn_done.setBackground(new java.awt.Color(0, 0, 0));
        btn_done.setForeground(new java.awt.Color(255, 255, 255));
        btn_done.setText("Done");
        btn_done.setColor(new java.awt.Color(0, 0, 0));
        btn_done.setColorover(new java.awt.Color(255, 0, 0));
        btn_done.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_done.setRadius(20);
        btn_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doneActionPerformed(evt);
            }
        });
        jPanel5.add(btn_done, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 310, 80, 30));

        btn_Print.setBackground(new java.awt.Color(0, 0, 0));
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.setColor(new java.awt.Color(0, 0, 0));
        btn_Print.setColorover(new java.awt.Color(0, 204, 0));
        btn_Print.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Print.setRadius(20);
        jPanel5.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 670, 110, 30));
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Total Qunty  :");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Product Name  :");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Price  :");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Qunty  :");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Product Id  :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Total Price  :");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Search  :");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel5.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 200, 30));

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setText("ReFresh");
        myButton1.setColor(new java.awt.Color(0, 204, 0));
        myButton1.setColorover(new java.awt.Color(255, 0, 0));
        myButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        myButton1.setRadius(20);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(myButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 670, 100, 30));

        jTabbedPane1.addTab("tab2", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", jPanel7);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, -25, 1090, 810));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, -10, 1110, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
      jTabbedPane1.setSelectedIndex(1);
       cashier.loadTableData(jTable2);
    }//GEN-LAST:event_btn_viewActionPerformed

    private void btn_ViewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ViewProductActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btn_ViewProductActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
             txt_PName.setText("");
             txt_PPrice.setText("");
             txt_TotalQ.setText("");
             txt_pId.setText("");
             txt_total.setText("");
             txt_pquntity.setText("");
           
            invoiceListModel.clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doneActionPerformed
       
        int pId = Integer.parseInt(txt_pId.getText());
        String pName = txt_PName.getText();
        double pPrice = Double.parseDouble(txt_PPrice.getText());
        int pQuantity = Integer.parseInt(txt_pquntity.getText());
        
        double toPrice = Double.parseDouble(txt_total.getText());
         if(pQuantity != 0){  
       
       Sells sells = new Sells(pId, pName, pPrice, pQuantity, null, 0, pQuantity,toPrice);
      
      cashier.sellProduct(sells);
      cashier.loadTableData(jTable2);
     JOptionPane.showMessageDialog(null,"successfully Updated Database! ","DONE",JOptionPane.INFORMATION_MESSAGE);
        
        
        txt_pId.setText("");
       txt_PName.setText("");
       txt_PPrice.setText("");
       txt_pquntity.setText("");
       
         }
       
    }//GEN-LAST:event_btn_doneActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        new LoginGui().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        search();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        new CashierView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myButton1ActionPerformed
  
  
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
            java.util.logging.Logger.getLogger(CashierView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.MyButton btn_Clear;
    private View.MyButton btn_Print;
    private View.MyButton btn_ViewProduct;
    private View.MyButton btn_done;
    private View.MyButton btn_view;
    private View.MyButton btnadd;
    private javax.swing.JList<String> invoiceList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private View.MyButton myButton1;
    private View.MyButton myButton4;
    private javax.swing.JTextField txt_PName;
    private javax.swing.JTextField txt_PPrice;
    private javax.swing.JTextField txt_TotalQ;
    private javax.swing.JTextField txt_pId;
    private javax.swing.JTextField txt_pquntity;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
