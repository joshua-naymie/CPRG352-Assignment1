/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import model.Category;

/**
 *
 * @author Main
 */
public class CategoryDB
{
     public List<Category> getAll() throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.createNamedQuery("Category.findAll").getResultList();
        }
        finally
        {
            entityManager.close();
        }
    }
}
