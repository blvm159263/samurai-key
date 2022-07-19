/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebpageControllers;

import DAO.CheckoutDAO;
import DAO.ConsolesDAO;
import DAO.GenreDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import models.User;

/**
 *
 * @author buile
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    private static final String Layout = "/WEB-INF/layout/main.jsp";

    ProductDAO pd = new ProductDAO();
    GenreDAO gd = new GenreDAO();
    ConsolesDAO cd = new ConsolesDAO();
    CheckoutDAO check = new CheckoutDAO();

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

        //Lấy controller để sau truyền lại cho main hiện view cần hiển thị
        String controller = (String) request.getAttribute("controller");
        //Lấy action
        String action = (String) request.getAttribute("action");
        //Check cookies
        create_cartCookie(request, response);
        check_cookies(request, response);
        switch (action) {
            case "homepage":
                homepage(request, response);
                break;
            case "shop-grid":
                shop_grid(request, response);
                break;
            case "shop-details":
                shop_details(request, response);
                break;
            case "shoping-cart":
                shoping_cart(request, response);
                break;
            case "checkout":
                checkout(request, response);
                break;

        }
        //Lấy controller để sau truyền lại cho main hiện view cần hiển thị
        if (action.compareTo("shoping-cart") != 0 && action.compareTo("checkout") != 0) {
            request.setAttribute("controller", controller);
            request.setAttribute("action", action);
            request.getRequestDispatcher(Layout).forward(request, response);
        }

    }

    protected void homepage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        GenreDAO gd = new GenreDAO();
        ConsolesDAO cd = new ConsolesDAO();
        List<Product> listNew = pd.listNew();
        List<Product> listAll = pd.listHome();
        List<Genre> listGenre = gd.list();
        List<Consoles> listConsoles = cd.list();
        int size = listAll.size();
        int maxPrice = pd.maxPrice();
        int minPrice = pd.minPrice();
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("listConsoles", listConsoles);
        request.setAttribute("listGenre", listGenre);
        request.setAttribute("size", size);
        request.setAttribute("listAll", listAll);
        request.setAttribute("listNew", listNew);
    }

    protected void shop_grid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Lấy controller để sau truyền lại cho main hiện view cần hiển thị
        String controller = (String) request.getAttribute("controller");
        //Lấy action
        String action = (String) request.getAttribute("action");
        //Lấy op
        String op = (String) request.getAttribute("op");
        op = op.toLowerCase();
        listBase(request, response);
        switch (op) {
            case "listall":
                listAll(request, response);
                paging(request, response);
                break;
            case "filter":
                filter(request, response);
                paging(request, response);
                break;
            case "search":
                search(request, response);
                paging(request, response);
                break;
            case "showpage":
                paging(request, response);
                break;
            case "listbygenre":
                listByGenre(request, response);
                paging(request, response);
                break;
        }
    }

    protected void shop_details(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        String id = request.getParameter("pid");
        ProductDAO dao = new ProductDAO();
        Product p = dao.getProductbyID(id);
        List<Product> listNew = dao.listNew();
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.setAttribute("listNew", listNew);
        request.setAttribute("detail", p);
        request.setAttribute("pid", id);
    }

    protected void check_cookies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read cookies if available
            Cookie cookie = null;
            Cookie cUserName = null;
            Cookie cPassword = null;
            //Cookie cRememberMe = null; // new
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
                    } 
//                    else if ((cookie.getName()).equals("rememberMe")) {
//                        cRememberMe = cookie;
//                    }
                }
            }
            String userName = cUserName.getValue().toLowerCase();
            String password = cPassword.getValue();
            User account = UserDAO.check_web(userName, password);
            if (cUserName != null
                    && cPassword != null
                    //&& cRememberMe != null //new
                    && account != null) {
                    //&& cUserName.getValue().toLowerCase().equals("admin")
                    //&& cPassword.getValue().toLowerCase().equals("12345")
                    //&& cRememberMe.getValue().equals("on")
                //Lưu userName vào session để ghi nhận đã login thành công => thay tên cho user ở header
                HttpSession session = request.getSession();

                // session.setAttribute("userName", userName);
                session.setAttribute("user", account);

            }
        } catch (Exception ex) {
            log("Error at Check_cookies: " + ex.toString());
        }
    }

    protected void listBase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Genre> listGenre = gd.list();
        List<Consoles> listConsoles = cd.list();
        int maxPrice = pd.maxPrice();
        int minPrice = pd.minPrice();
        List<Product> listNew = pd.listNew();
        request.setAttribute("listNew", listNew);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("listConsoles", listConsoles);
        request.setAttribute("listGenre", listGenre);

    }

    protected int countP(int size) {
        int endP = size / 15;
        if (endP % 15 != 0) {
            endP++;
        }
        return endP;
    }

    protected void listAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> listAll = new ArrayList<>();
        listAll = pd.listAll();
        int size = listAll.size();
        int endP = countP(size);
        session.setAttribute("curGen", null);
        session.setAttribute("curCons", null);
        session.setAttribute("curRating", null);
        session.setAttribute("endP", endP);
        session.setAttribute("list", listAll);
        session.setAttribute("size", size);
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

    protected void filter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String genreID = request.getParameter("genreID");
        int minPrice = Integer.parseInt(request.getParameter("minPrice").substring(1));
        int maxPrice = Integer.parseInt(request.getParameter("maxPrice").substring(1));
        String consolesID = request.getParameter("consolesID");
        String rating = request.getParameter("rating");
        List<Product> list = pd.filter(genreID, consolesID, minPrice, maxPrice, rating);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("curGen", genreID);
        session.setAttribute("curCons", consolesID);
        session.setAttribute("curRating", rating);
        session.setAttribute("curMin", minPrice);
        session.setAttribute("curMax", maxPrice);
        session.setAttribute("curRating", rating);
        session.setAttribute("list", list);
        session.setAttribute("size", size);
        session.setAttribute("endP", endP);
    }

    protected void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String fname = request.getParameter("fname");
        List<Product> list = pd.findProductByName(fname);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("list", list);
        session.setAttribute("fname", fname);
        session.setAttribute("size", size);
        session.setAttribute("endP", endP);

    }

    protected void listByGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int genreID = Integer.parseInt(request.getParameter("genreID"));
        List<Product> list = pd.findProductByGenreID(genreID);
        int size = list.size();
        int endP = countP(size);
        session.setAttribute("endP", endP);
        session.setAttribute("list", list);
        session.setAttribute("size", size);
    }

    protected void shoping_cart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String op = (String) request.getAttribute("op");
        op = op.toLowerCase();
        switch (op) {
            case "add":
                add(request, response);

                break;
            case "sub":
                sub(request, response);

                break;
            case "delete":
                delete(request, response);

                break;
            case "view":
                view(request, response);
                break;
        }

    }

    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = id;
        } else {
            txt = txt + "," + id;
        }
        Cookie c = new Cookie("id", txt);
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);
        response.sendRedirect("shoping-cart.do?op=view");

    }

    protected void sub(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String ids[] = txt.split(",");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) {
                check++;
            }
            if (check != 1) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "," + ids[i];
                }
            } else {
                check++;
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("id", txtOutPut);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }
        response.sendRedirect("shoping-cart.do?op=view");

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String ids[] = txt.split(",");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals(id)) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "," + ids[i];
                }
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("id", txtOutPut);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }
        response.sendRedirect("shoping-cart.do?op=view");

    }

    protected void create_cartCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        for (int i = 0; i <= list.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getProductID() == list.get(j).getProductID()) {
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
        session.setAttribute("listC", list);
        session.setAttribute("total", total);
        session.setAttribute("vat", Math.round((0.1 * total) * 100) / 100);
        session.setAttribute("sum", Math.round((1.1 * total) * 100) / 100);
    }

    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        for (int i = 0; i <= list.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getProductID() == list.get(j).getProductID()) {
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
        session.setAttribute("listC", list);
        session.setAttribute("total", total);
        session.setAttribute("vat", Math.round((0.1 * total) * 100) / 100);
        session.setAttribute("sum", Math.round((1.1 * total) * 100) / 100);
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.getRequestDispatcher("WEB-INF/layout/main.jsp").forward(request, response);
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String op = (String) request.getAttribute("op");
            op = op.toLowerCase();
            switch (op) {
                case "order":
                    placeOrder(request, response);
                    break;
                case "view":
                    view_checkout(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void view_checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        request.getRequestDispatcher(Layout).forward(request, response);
    }

    protected void placeOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        try {
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            double total = (Double) session.getAttribute("total");
            LocalDateTime current = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String date = current.format(formatter);
            User user = (User) session.getAttribute("user");
            int userID = user.getId();
            if (total == 0) {
                request.setAttribute("message", "There is no product!");
                request.getRequestDispatcher(Layout).forward(request, response);
            } else {
                if (fullName == "" || address == "" || email == "" || phone == "") {
                    request.setAttribute("message", "Some field are empty. Please check!");
                    request.getRequestDispatcher(Layout).forward(request, response);
                } else {
                    boolean status = check.addOrder(fullName, address, total, email, phone, date, userID);
                    Cookie arr[] = request.getCookies();
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
                    for (int i = 0; i < list.size(); i++) {
                        int count = 1;
                        for (int j = i + 1; j < list.size(); j++) {
                            if (list.get(i).getProductID() == list.get(j).getProductID()) {
                                count++;
                                list.remove(j);
                                j--;
                                list.get(i).setQuantity((byte) count);
                            }
                        }
                    }
                    for (Cookie o : arr) {
                        o.setMaxAge(0);
                        response.addCookie(o);

                    }
                    session.invalidate();
                    response.sendRedirect("homepage.do?op=list");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
