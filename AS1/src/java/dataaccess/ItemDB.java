/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Item;

/**
 *
 * @author Main
 */
public class ItemDB
{
    public List<Item> getAll() throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.createNamedQuery("Item.findAll").getResultList();
        }
        finally
        {
            entityManager.close();
        }
    }
    
    public Item get(int itemID) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.find(Item.class, itemID);
        }
        finally
        {
            entityManager.close();
        }
    }
    
    public void insert(Item item) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try
        {
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
        }
        catch(Exception exception)
        {
            transaction.rollback();
            throw exception;
        }
        finally
        {
            entityManager.close();
        }
    }
    
    public void insert(Integer itemID, String itemName, double price) throws Exception
    {
        insert(new Item(itemID, itemName, price));
    }
    
    public void delete(Item item)
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try
        {
            transaction.begin();
            entityManager.remove(entityManager.merge(item));
            transaction.commit();
        }
        catch(Exception exception)
        {
            transaction.rollback();
            throw exception;
        }
        finally
        {
            entityManager.close();
        }
    }
}
