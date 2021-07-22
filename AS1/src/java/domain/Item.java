/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Main
 */
public class Item
{
    private
    int itemID,
        categoryID;
    
    private
    double price;
    
    private
    String itemName;
    
    private
    User owner;
    
    public Item(int itemID, int categoryID, double price, String itemName, User owner)
    {
        this.itemID = itemID;
        this.categoryID = categoryID;
        this.price = price;
        this.itemName = itemName;
        this.owner = owner;
    }
    
    //------------------------------
    
    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }
    
    //---------------
    
    public int getItemID()
    {
        return itemID;
    }
    
    //------------------------------
    
    public void setCategoryID(int categoryID)
    {
        this.categoryID = categoryID;
    }
    
    //---------------
    
    public int getCategoryID()
    {
        return categoryID;
    }
    
    //------------------------------
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    //---------------
    
    public double getPrice()
    {
        return price;
    }
    
    //------------------------------
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    //---------------
    
    public String getItemName()
    {
        return itemName;
    }
    
    //------------------------------
    
    public void setOwner(User owner)
    {
        this.owner = owner;
    }
    
    //---------------
    
    public User getOwner()
    {
        return owner;
    }
}
