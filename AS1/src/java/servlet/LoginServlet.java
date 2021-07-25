/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;
import service.AccountService;

/**
 *
 * @author Main
 */
public class LoginServlet extends HttpServlet
{

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username"),
               password = request.getParameter("password");
        
        AccountService accountService = new AccountService();
        
        try
        {
            switch(accountService.checkLoginInfo(username, password))
            {
                case EMPTY_INPUT:
                    break;
                    
                case INVALID_USERNAME_PASSWORD:
                    break;
                    
                case INACTIVE_USER:
                    break;
                    
                case SUCCESS:
                    login(username, password, accountService, request, response);
                    break;
            }
        }
        catch(Exception exception)
        {
               System.out.println("ERROR 1"); 
               exception.printStackTrace();
        }
        
    }
    
    private void login(String username, String password, AccountService accountService,
                       HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            User user = accountService.get(username);
            
            if(user.isAdmin())
            {
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            }
            else
            {
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
