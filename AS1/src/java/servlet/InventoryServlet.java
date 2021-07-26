/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Item;
import model.User;
import service.AccountService;
import service.InventoryService;

/**
 *
 * @author Main
 */
public class InventoryServlet extends HttpServlet
{
    private static final
    String INVENTORY_JSP_DIR = "/WEB-INF/inventory.jsp";
    
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
        HttpSession session = request.getSession(false);
        
        AccountService accountService = new AccountService();
        InventoryService inventoryService = new InventoryService();
        try
        {
            User user = accountService.get((String) session.getAttribute("username"));
            
            for(Item i : user.getItemList())
            {
                System.out.println(i.getItemName());
            }
            request.setAttribute("user", accountService.get((String) session.getAttribute("username")));
            request.setAttribute("itemlist", user.getItemList());
            request.setAttribute("categories", inventoryService.getAllCategories());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        
        getServletContext().getRequestDispatcher(INVENTORY_JSP_DIR).forward(request, response);
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
        switch(request.getParameter("action"))
        {
            case "Add":
                addItem(request, response);
                break;
                
            case "Delete":
                removeItem(request, response);
                break;
        }
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
    {
        InventoryService inventoryService = new InventoryService();
        
        try
        {
            switch(inventoryService.insertItem(request.getParameter("itemname"),
                                               Double.parseDouble(request.getParameter("itemprice")), 
                                               Integer.parseInt(request.getParameter("category")), 
                                               (String)request.getSession(false).getAttribute("username")))
            {
                case SUCCESS:
                    response.sendRedirect("inventory");
                    break;
                
                case INVALID_PERMISSION:
                    break;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response)
    {
        AccountService accountService = new AccountService();
        InventoryService inventoryService = new InventoryService();
        
        try
        {
            switch(inventoryService.deleteItem(Integer.parseInt(request.getParameter("itemkey")),
                                               (String)request.getSession().getAttribute("username")))
            {
                case SUCCESS:
                    response.sendRedirect("inventory");
                    break;
                    
                case INVALID_PERMISSION:
                    request.setAttribute("user", accountService.get((String)request.getSession().getAttribute("username")));
                    request.setAttribute("categories", inventoryService.getAllCategories());
                    request.setAttribute("message", "You do not have permession do delete this item");
                    
                    getServletContext().getRequestDispatcher(INVENTORY_JSP_DIR).forward(request, response);
                    break;
            }
            
            
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}