/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducquynh
 */
public class ProductCart {
    private final List cartItems;
    
    public ProductCart(){
        cartItems = new ArrayList();
    }
    
    private List products;
    public List getProducts() throws SQLException{
        List temp = new ArrayList();
        try{
            String dbUser ="sa";
            String dbPassword = "sa";
            String url ="jdbc:derby://localhost:1527/ShoppingMVC";
            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select ID, Name, Type, Price from Shopping");
            while(rs.next()){
                Product item = new Product();
                item.setProductId(rs.getInt(1));
                item.setProductName(rs.getString(2));
                item.setProductType(rs.getString(3));
                item.setPrice(rs.getFloat(4));
                
                temp.add(item);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }
    
    public void addItem(int productId, String productName, String productType, float price, int quantity){
        Product item = null;
        boolean match = false;
        for(int i = 0; i<cartItems.size(); i++ ){
            if(((Product) cartItems.get(i)).getProductId() == productId){
                item = (Product) cartItems.get(i);
                setAmount(getAmount() + quantity * item.getPrice());
                item.setQuantity(item.getQuantity() + quantity);
                match=true;
                break;
            }
            
        }
        if(!match){
            item = new Product();
            item.setProductId(productId);
            item.setProductName(productName);
            item.setProductType(productType);
            item.setPrice(price);
            setAmount(getAmount() + quantity *item.getPrice());
            item.setQuantity(quantity);
            cartItems.add(item);
        }
    }
    
    public void removeItem(int productId){
        for(int i=0; i<cartItems.size(); i++){
            Product item = (Product) cartItems.get(i);
            if(item.getProductId() == productId){
                setAmount(getAmount() - item.getPrice() * item.getQuantity());
                cartItems.remove(i);
                break;
            }
        }
    }
    public List getCartItems(){
        return cartItems;
    }
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
}
