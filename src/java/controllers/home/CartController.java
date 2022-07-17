<<<<<<< Upstream, based on origin/main
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.home;

import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Product;

/**
 *
 * @author Le Nguyen Nhat Minh
 */
@WebServlet(name = "CartController", urlPatterns = {"/shoping-cart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        Cookie arr[] = request.getCookies();
        PrintWriter out = response.getWriter();
        List<Product> list = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                String txt[] = o.getValue().split(",");
                for (String s : txt) {
                    list.add(dao.getProductbyID(s));
                }
            }
        }
        for (int i =0; i <= list.size(); i++) {
            int count = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).getProductID()== list.get(j).getProductID()){
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setQuantity((byte) count);
                }
            }
        }
        double total = 0;
        for (Product o : list) {
            total = total + o.getQuantity() * o.getPrice();
        }
        session.setAttribute("list", list);
        session.setAttribute("total", total);
        session.setAttribute("vat", Math.round(( 0.1 * total)*100)/100);
        session.setAttribute("sum", Math.round(( 1.1 * total)*100)/100);
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.getRequestDispatcher("WEB-INF/layout/main.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.home;

import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Product;

/**
 *
 * @author Le Nguyen Nhat Minh
 */
@WebServlet(name = "CartController", urlPatterns = {"/shoping-cart"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        Cookie arr[] = request.getCookies();
        PrintWriter out = response.getWriter();
        List<Product> list = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                String txt[] = o.getValue().split(",");
                for (String s : txt) {
                    list.add(dao.getProductbyID(s));
                }
            }
        }
        for (int i =0; i <= list.size(); i++) {
            int count = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).getProductID()== list.get(j).getProductID()){
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setQuantity((byte) count);
                }
            }
        }
        double total = 0;
        for (Product o : list) {
            total = total + o.getQuantity() * o.getPrice();
        }
        session.setAttribute("list", list);
        session.setAttribute("total", total);
        session.setAttribute("vat", Math.round(( 0.1 * total)*100)/100);
        session.setAttribute("sum", Math.round(( 1.1 * total)*100)/100);
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.getRequestDispatcher("WEB-INF/layout/main.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
>>>>>>> 973d34e cart-function
