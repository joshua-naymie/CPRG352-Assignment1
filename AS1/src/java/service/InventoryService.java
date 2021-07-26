/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.*;
import model.*;
import java.util.List;

/**
 *
 * @author Main
 */
public class InventoryService
{
    public List<Item> getAllItems() throws Exception
    {
        ItemDB itemDB = new ItemDB();
        return itemDB.getAll();
    }
    
    public Item getItem(int itemID)
    {
        return null;
    }
    
    public List<Category> getAllCategories() throws Exception
    {
        CategoryDB categoryDB = new CategoryDB();
        return categoryDB.getAll();
    }
    
    public Category getCategory(int categoryID)
    {
        return null;
    }
}