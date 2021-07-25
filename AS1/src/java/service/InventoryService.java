/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Category;
import model.Item;
import model.User;
import java.util.ArrayList;

/**
 *
 * @author Main
 */
public class InventoryService
{
    public Item[] getAllItems()
    {
        return null;
    }
    
    public Item[] getAllItemsByOwner(User owner)
    {
        Item[] allItems = getAllItems();
        ArrayList<Item> items = new ArrayList<Item>();
        
        for(Item item : allItems)
        {
            if(item.getOwner().equals(owner))
            {
                items.add(item);
            }
        }
        
        return (Item[]) items.toArray();
    }
    
    public Item getItem(int itemID)
    {
        return null;
    }
    
    public Category[] getAllCategories()
    {
        return null;
    }
    
    public Category getCategory(int categoryID)
    {
        return null;
    }
}