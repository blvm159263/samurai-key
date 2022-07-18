/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebpageControllers;

import DAO.ConsolesDAO;
import DAO.GenreDAO;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Consoles;
import models.Genre;
import models.Product;
import models.User;

/**
 *
 * @author buile
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    ProductDAO pd = new ProductDAO();
    GenreDAO gd = new GenreDAO();
    ConsolesDAO cd = new ConsolesDAO();

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
        HttpSession session = request.getSession();
        //Lấy action
        String action = (String) request.getAttribute("action");
        //Xác Minh Admin
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        if (role.compareTo("ADMIN") == 0) {
            switch (action) {
                case "manage":
                    mangage_product(request, response);
                    break;
            }
            request.getRequestDispatcher("/WEB-INF/views/admin/manage-product.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    protected void mangage_product(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op").toLowerCase();
        try {
            listBase(request, response);
            switch (op) {
                case "listfull":
                    listFull(request, response);
                    paging(request, response);
                    break;
                case "showpage":
                    paging(request, response);
                    break;
                case "update":
                    update(request, response);
                    listFull(request, response);
                    paging(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    listFull(request, response);
                    paging(request, response);
                    break;
                case "create":
                    create(request, response);
                    listFull(request, response);
                    paging(request, response);
                    break;
                case "findbyid":
                    findByID(request, response);
                    paging(request, response);
                    break;
                case "findbyname":
                    findByName(request, response);
                    paging(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    protected void paging(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> list = (ArrayList<Product>) session.getAttribute("list");
        String curPage = request.getParameter("page");
        int size = (Integer) session.getAttribute("size");
        if (curPage == null) {
            curPage = "1";
        }
        int index = (Integer.parseInt(curPage) - 1) * 15;
        List<Product> listP = new ArrayList<>();
        if (size != 0) {
            for (int i = index; i < index + 15; i++) {
                Product p = list.get(i);
                listP.add(p);
                if (i == size - 1) {
                    break;
                }
            }
        }
        request.setAttribute("listP", listP);
    }

    protected int countP(int size) {
        int endP = size / 15;
        if (endP % 15 != 0) {
            endP++;
        }
        return endP;
    }

    protected void listBase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Genre> listGenre = gd.list();
        List<Consoles> listConsoles = cd.list();
        session.setAttribute("listConsoles", listConsoles);
        session.setAttribute("listGenre", listGenre);
    }

    protected void listFull(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> list = pd.listFull();
        session.setAttribute("list", list);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("size", size);
        session.setAttribute("endP", endP);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int price = Integer.parseInt(request.getParameter("price"));
            String productName = request.getParameter("productName");
            byte quantity = Byte.parseByte(request.getParameter("quantity"));
            String desc = request.getParameter("desc");
            byte rating = Byte.parseByte(request.getParameter("rating"));
            String linkImg1 = request.getParameter("linkImg1");
            String linkImg2 = request.getParameter("linkImg2");
            String linkImg3 = request.getParameter("linkImg3");
            String linkImg4 = request.getParameter("linkImg4");
            String linkImg5 = request.getParameter("linkImg5");
            int genreID = Integer.parseInt(request.getParameter("genre"));
            int consolesID = Integer.parseInt(request.getParameter("console"));
            boolean status = pd.updateProduct(productID, price, productName, quantity, desc, rating, linkImg1, linkImg2, linkImg3, linkImg4, linkImg5, genreID, consolesID);
            if (status) {
                request.setAttribute("message", "Update Successful!");
            } else {
                request.setAttribute("message", "Update Fail!");
            }
        } catch (Exception ex) {
            log("Error at Admin update: " + ex.toString());
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            boolean status = pd.delete(productID);
            if (status) {
                request.setAttribute("message", "Delete Successful!");
            } else {
                request.setAttribute("message", "Delete Fail!");
            }
        } catch (Exception ex) {
            log("Error at Admin delete: " + ex.toString());
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int price = Integer.parseInt(request.getParameter("price"));
            String productName = request.getParameter("productName");
            byte quantity = Byte.parseByte(request.getParameter("quantity"));
            String desc = request.getParameter("desc");
            byte rating = Byte.parseByte(request.getParameter("rating"));
            String linkImg1 = request.getParameter("linkImg1");
            String linkImg2 = request.getParameter("linkImg2");
            String linkImg3 = request.getParameter("linkImg3");
            String linkImg4 = request.getParameter("linkImg4");
            String linkImg5 = request.getParameter("linkImg5");
            int genreID = Integer.parseInt(request.getParameter("genre"));
            int consolesID = Integer.parseInt(request.getParameter("console"));
            boolean status = pd.createProduct(price, productName, quantity, desc, rating, linkImg1, linkImg2, linkImg3, linkImg4, linkImg5, genreID, consolesID);
            if (status) {
                request.setAttribute("message", "Create Successful!");
            } else {
                request.setAttribute("message", "Create Fail!");
            }
        } catch (Exception ex) {
            log("Error at Admin create: " + ex.toString());
        }
    }

    protected void findByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
        List<Product> list = pd.listByID(id);
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("size", size);
        session.setAttribute("endP", endP);
        request.setAttribute("fID", id);
    }

    protected void findByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("productName");
        List<Product> list = pd.listByName(name);
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("size", size);
        session.setAttribute("endP", endP);
        request.setAttribute("fName", name);
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
