
package Model;


public class Sells extends Product{
    
    private int sellsId;
    private int sellsQuntity;
    private double  sellsPrice;
    
    public Sells(int productid, String product_name, double product_price, int product_quantity, String product_category,int sellsId,int sellsQuntity,double sellsPrice) {
        super(productid, product_name, product_price, product_quantity, product_category);
        this.sellsId = sellsId;
        this.sellsQuntity = sellsQuntity;
        this.sellsPrice = sellsPrice;
    }
    public int getSellId(){

        return sellsId;
    }
    public void setSellsId(int sellsId){
    
    this.sellsId = sellsId;
    }
    public int getsellQuantity(){
    
    return sellsQuntity;
    }

    public double getSellsPrice() {
        return sellsPrice;
    }

    public void setSellsPrice(double sellsPrice) {
        this.sellsPrice = sellsPrice;
    }
    public void setsellsQuantity(int sellsQuantity){
    
    this.sellsQuntity =sellsQuantity;
    }
}
