/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.UserDB;
import model.RequestStatus;
import model.User;
import java.util.List;
import model.MiscUtil;

/**
 *
 * @author Main
 */
public class AccountService
{
    public User get(String username) throws Exception
    {
        UserDB userDB = new UserDB();
        return userDB.get(username);
    }
    
    public List<User> getAll() throws Exception
    {
        UserDB userDB = new UserDB();
        return userDB.getAll();
    }
    
    public RequestStatus insert(String username, String password, String email,
                                String firstName, String lastName, boolean active,
                                boolean isAdmin)
    throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { username, password, email, firstName, lastName }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        
        
        UserDB userDB = new UserDB();
        userDB.insert(username, password, email, firstName, lastName, active, isAdmin);
        
        return RequestStatus.SUCCESS;
    }
    
    public void insert(User user) throws Exception
    {
        insert(user.getUsername(), user.getPassword(), user.getEmail(),
               user.getFirstName(), user.getLastName(),
               user.isActive(), user.isAdmin());
    }
    
     public RequestStatus delete(String username, String currentUsername) throws Exception
    {
        return delete(get(username), currentUsername);
    }
    
    //---------------
    
    /**
     * Deletes a User entry from the User data storage
     * @param user The User to delete
     * @throws Exception Thrown when entry cannot be deleted
     */
    public RequestStatus delete(User user, String username) throws Exception
    {
        if(user.getUsername().equals(username))
        {
            return RequestStatus.CANNOT_DELETE_SELF;
        }
        
        UserDB userDB = new UserDB();
        userDB.delete(user);
        
        return RequestStatus.SUCCESS;
    }
    
    public RequestStatus update(String username, String password, String email,
            String firstName, String lastName, boolean active, boolean isAdmin)
    throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { username, password, email, firstName, lastName }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        
        if(get(username) == null)
        {
            return RequestStatus.NO_USER_FOUND;
        }
        
        UserDB userDB = new UserDB();
        userDB.update(username, password, email, firstName, lastName, active, isAdmin);
        
        return RequestStatus.SUCCESS;
    }
    
    //---------------
    
    /**
     * Updates a User in the User data storage
     * @param user The User to update
     * @throws Exception Thrown when entry cannot be updated
     */
    public RequestStatus update(User user) throws Exception
    {
        return update(user.getUsername(), user.getPassword(), user.getEmail(),
                      user.getFirstName(), user.getLastName(),
                      user.isActive(), user.isAdmin());
    }
    
    public RequestStatus checkLoginInfo(String username, String password) throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { username, password }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        
        User user = get(username);
        
        if(user == null || !user.getPassword().equals(password))
        {
            return RequestStatus.INVALID_USERNAME_PASSWORD;
        }
        if(!user.isActive())
        {
            return RequestStatus.INACTIVE_USER;
        }
        
        return RequestStatus.SUCCESS;
    }
}