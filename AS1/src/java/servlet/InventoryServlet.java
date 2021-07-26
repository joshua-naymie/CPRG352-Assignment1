/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
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
            
        InventoryService inventoryService = new InventoryService();
        try
        {
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
        }
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
    {
        InventoryService inventoryService = new InventoryService();
    }
}