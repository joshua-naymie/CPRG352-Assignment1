package dataaccess;

import javax.persistence.*;

public class DBUtil
{
    private static final
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("InventoryPU");
    
    public static EntityManagerFactory getEMFactory()
    {
        return emFactory;
    }
}