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
    
    public boolean canAccessItem(int itemID, User owner) throws Exception
    {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        
        if(item.getOwner().equals(owner))
        {
            return true;
        }
        
        return false;
    }
    
    public Item getItem(int itemID) throws Exception
    {
        ItemDB itemDB = new ItemDB();
        return itemDB.get(itemID);
    }
    
    public RequestStatus insertItem(String itemName, String itemPrice,
                                    String categoryID, String username)
    throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { itemName, itemPrice, categoryID }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        if(!MiscUtil.stringIsNumber(itemPrice))
        {
            return RequestStatus.INVALID_PRICE;
        }
        if(!MiscUtil.stringIsNumber(categoryID))
        {
            return RequestStatus.INVALID_CATEGORYID;
        }
        
        return insertItem(itemName, Double.parseDouble(itemPrice), Integer.parseInt(categoryID), username);
    }
    
    public RequestStatus insertItem(String itemName, double itemPrice,
                                    int categoryID, String username)
    throws Exception
    {
        AccountService accountService = new AccountService();
        
        if(MiscUtil.hasEmptyValues(new String[] { itemName }))
        {
            System.out.println("EMPTY");
            return RequestStatus.EMPTY_INPUT;
        }
        if(getCategory(categoryID) == null)
        {
            System.out.println(categoryID);
            return RequestStatus.NO_CATEGORY_FOUND;
        }
        if(accountService.get(username) == null)
        {
            System.out.println("NO USER");
            return RequestStatus.NO_USER_FOUND;
        }
        
        Item item = new Item();
        
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        item.setCategory(getCategory(categoryID));
        item.setOwner(accountService.get(username));
        
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
        
        System.out.println("success?");
        return RequestStatus.SUCCESS;
    }
    
    public RequestStatus deleteItem(int itemID, String username) throws Exception
    {
        AccountService accountService = new AccountService();
        
        if(getItem(itemID) == null)
        {
            return RequestStatus.NO_ITEM_FOUND;
        }
        if(accountService.get(username) == null)
        {
            return RequestStatus.NO_USER_FOUND;
        }
        if(!getItem(itemID).getOwner().equals(accountService.get(username)))
        {
            return RequestStatus.INVALID_PERMISSION;
        }
        
        ItemDB itemDB = new ItemDB();
        itemDB.delete(getItem(itemID));
        
        return RequestStatus.SUCCESS;
    }
    
    public List<Category> getAllCategories() throws Exception
    {
        CategoryDB categoryDB = new CategoryDB();
        return categoryDB.getAll();
    }
    
    public Category getCategory(int categoryID) throws Exception
    {
        CategoryDB categoryDB = new CategoryDB();
        
        return categoryDB.get(categoryID);
    }
}