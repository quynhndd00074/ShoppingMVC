/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductCart;

/**
 *
 * @author ducquynh
 */
public class ShoppingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }

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
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("shoppingError.jsp");
        }
        
        ProductCart buyList = (ProductCart) session.getAttribute("prod");
        String action = request.getParameter("action");
        
        if(!action.equals("CHECKOUT")){
           switch (action){
               case "DELETE":
                int productId = Integer.parseInt(request.getParameter("delItem"));
                buyList.removeItem(productId);
                   break;      
        
                   case "ADD":
                if(action.equals("ADD")){
                    if(buyList == null){
                        buyList = new ProductCart();
                    }
                    addProduct(request,response, buyList);
                }
                    session.setAttribute("prod", buyList);
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
            
           }
        }
        else if(action.equals("CHECKOUT"))
            {
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/checkout.jsp");
                rd.forward(request, response);
            }
        
    }
    
    private void addProduct(HttpServletRequest request, HttpServletResponse response, ProductCart obj){
        String myProd = request.getParameter("products");
        String quantity = request.getParameter("qty");
        try{
            if(!validateQuantity(quantity)){
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/shoppingError.jsp");
                request.setAttribute("message", "Quantity should be a positive nonzero value.");
                rd.forward(request, response);
                return;
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        StringTokenizer t = new StringTokenizer(myProd,"|" );
        Integer productId = new Integer(t.nextToken());
        String productName = t.nextToken();
        String productType = t.nextToken();
        Float price = new Float(t.nextToken());
        obj.addItem(productId.intValue(), productName, productType, price.floatValue(), Integer.parseInt(quantity));
        
    }
    
    private boolean validateQuantity(String quantity){
        boolean valid = false;
        for(int i=0; i<quantity.length(); i++)
        {
            if("0123456789".indexOf(quantity.charAt(i))>0)
            {
                valid = true;
            }
            else
            {
                valid = false;
                break;
            }
            
        }
        if(!valid)
        {
            return false;
        }
        else
            if(Integer.parseInt(quantity) == 0)
                return false;
            else
                return true;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
