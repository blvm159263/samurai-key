/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import DAO.ConsolesDAO;
import DAO.GenreDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Consoles;
import models.Genre;
import models.Product;

/**
 *
 * @author buile
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

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
//        //Lấy action
//        String action = (String) request.getAttribute("action");
//        //Lấy op
//        //String op = (String) request.getAttribute("op");
//        //op = op.toLowerCase();
//        //Check cookies
//        check_cookies(request, response);
//        switch (action) {
//            case "homepage":
//                homepage(request, response);
//                break;
//
//        }
        //Lấy controller để sau truyền lại cho main hiện view cần hiển thị
        String controller = (String) request.getAttribute("controller");
        //Lấy action
        String action = (String) request.getAttribute("action");
        //Lấy op
        String op = (String) request.getAttribute("op");
        //Check cookies
        check_cookies(request, response);

        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.setAttribute("op", op);
        request.getRequestDispatcher("/" + action).forward(request, response);
    }

//    protected void homepage(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ProductDAO pd = new ProductDAO();
//        GenreDAO gd = new GenreDAO();
//        ConsolesDAO cd = new ConsolesDAO();
//        List<Product> listNew = pd.listNew();
//        List<Product> listAll = pd.listHome();
//        List<Genre> listGenre = gd.list();
//        List<Consoles> listConsoles = cd.list();
//        int size = listAll.size();
//        int maxPrice = pd.maxPrice();
//        int minPrice = pd.minPrice();
//        request.setAttribute("minPrice", minPrice);
//        request.setAttribute("maxPrice", maxPrice);
//        request.setAttribute("listConsoles", listConsoles);
//        request.setAttribute("listGenre", listGenre);
//        request.setAttribute("size", size);
//        request.setAttribute("listAll", listAll);
//        request.setAttribute("listNew", listNew);
//        request.getRequestDispatcher("/WEB-INF/layout/main.jsp").forward(request, response);
//    }

    protected void check_cookies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read cookies if available
            Cookie cookie = null;
            Cookie cUserName = null;
            Cookie cPassword = null;
            Cookie cRememberMe = null; // new
            Cookie[] cookies = null;
            
            // Get an array of Cookies associated with the this domain
            cookies = request.getCookies();
            
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if ((cookie.getName()).equals("userName")) {
                        cUserName = cookie;
                    } else if ((cookie.getName()).equals("password")) {
                        cPassword = cookie;
                    } else if ((cookie.getName()).equals("rememberMe")) {
                        cRememberMe = cookie;
                    }
                }
            }
            String userName = cUserName.getValue().toLowerCase();
            String password = cPassword.getValue();
            if (cUserName != null
                    && cPassword != null
                    && cRememberMe != null //new
                    //&& UserDAO.check_web(userName, password) != null
                    //&& cUserName.getValue().toLowerCase().equals("admin")
                    //&& cPassword.getValue().toLowerCase().equals("12345")
                    && cRememberMe.getValue().equals("on")) {
                //Lưu userName vào session để ghi nhận đã login thành công => thay tên cho user ở header
                HttpSession session = request.getSession();
                
                session.setAttribute("userName", userName);
                
            }
        } catch (Exception ex) {
            log("Error at Check_cookies: " + ex.toString());
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
