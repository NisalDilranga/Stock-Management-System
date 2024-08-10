
package Model;

import java.util.List;


public abstract class Product {
    private int productid;
    private String product_name;
    private double product_price;
    private int product_quantity;
    private String product_category;
    private List<Product> products;

    public Product(int productid, String product_name, double product_price, int product_quantity, String product_category) {
        this.productid = productid;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_category = product_category;
    }

    public int getProductid() {
        return productid;
    }

    public void  setProductid(int productid) {
        this.productid = productid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }
    
  
    
    }
