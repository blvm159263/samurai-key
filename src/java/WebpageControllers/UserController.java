/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebpageControllers;

import Context.Hasher;
import DAO.ConsolesDAO;
import DAO.GenreDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Lenovo
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

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
        //Lấy controller để sau truyền lại cho main hiện view cần hiển thị
        //String controller = (String) request.getAttribute("controller");
        //Lấy action
        String action = (String) request.getAttribute("action");
        //Lấy op
        //String op = (String) request.getAttribute("op");
        //op = op.toLowerCase();
        switch (action) {
            case "login_form":
                request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
                break;
            case "login_handler":
                loginHandler(request, response);
                //request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "forgot_password":
                request.getRequestDispatcher("/WEB-INF/views/user/forgot_password.jsp").forward(request, response);
                break;
            case "find_user":
                findUser(request, response);
                break;
            case "reset_password":
                resetPassword(request, response);
                break;
            case "reset_form":
                resetForm(request, response);
                break;
        }
    }

    protected void loginHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName").toLowerCase();
            String password = request.getParameter("password");
            String rememberMe = request.getParameter("rememberMe");
            if (rememberMe == null) {
                rememberMe = "off";
            }
            // check account
            User account = UserDAO.check(userName, password);
            // If login successfully
            //if (userName.equals("admin") && password.equals("1")) {
            if (account != null) {
                if (rememberMe.equals("on")) {
                    // Create cookies for username and password
                    Cookie cUserName = new Cookie("userName", userName);
                    Cookie cPassword = new Cookie("password", Hasher.hash(password));
                    //Cookie cRememberMe = new Cookie("rememberMe", rememberMe); //new

                    // Set path to homepage
                    cUserName.setPath("/Group1_Assignment");
                    cPassword.setPath("/Group1_Assignment");
                    //cRememberMe.setPath("/Group1_Assignment"); //new

                    // Set expiry date after 24 Hrs for both the cookies
                    cUserName.setMaxAge(60 * 60 * 24);
                    cPassword.setMaxAge(60 * 60 * 24);
                    //cRememberMe.setMaxAge(60 * 60 * 24); //new

                    // Add both the cookies in the response header
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                    //response.addCookie(cRememberMe); //new

                }
                //Lưu userName vào session để ghi nhận đã login thành công
                HttpSession session = request.getSession();
                session.setAttribute("user", account);

                //Lưu userName + viết hoa chữ cái đầu rồi truyền đến header để thay tên vào phần welcome back
                String firstLetter = userName.substring(0, 1);
                String remainingLetters = userName.substring(1, userName.length());
                firstLetter = firstLetter.toUpperCase();
                String output = firstLetter + remainingLetters;
                request.setAttribute("cap_userName", output);

                //Chuyển đến trang index.jsp => header.jsp
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                //Lưu thông tin đã nhập vào request để bảo tồn trạng thái của form
                request.setAttribute("userName", userName);
                request.setAttribute("password", password);
                request.setAttribute("rememberMe", rememberMe);
                //Lưu thông báo lỗi vào request
                request.setAttribute("message", "Wrong username or password.");
                //Cho hiện lại trang login.jsp
                request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            log("Error at Login_Handler: " + ex.toString());
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Destroy session
        HttpSession session = request.getSession();
        session.invalidate();

        //Clear cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
                cookies[i].setPath("/Group1_Assignment");
                response.addCookie(cookies[i]);
            }
        }

        //Forward to homepage list to get back the list and to clear userName from header.jsp
        list(request, response);
        request.setAttribute("controller", "home");
        request.setAttribute("action", "homepage");
        request.getRequestDispatcher("/WEB-INF/layout/main.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName").toLowerCase();
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");

            User account = new User(userName, password);
            if (UserDAO.register(account)) { // check duplicate
                if (password.equals(password2)) { // check confirm_password
                    //Lưu thông báo lỗi confirm vào request
                    request.setAttribute("rMessage", "Please login to complete your registration.");
                    //Cho hiện lại trang login.jsp
                    request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

                } else { // check confirm_password
                    //Lưu thông tin đã nhập vào request để bảo tồn trạng thái của form
                    request.setAttribute("rUserName", userName);
                    request.setAttribute("rPassword", password);
                    request.setAttribute("rPassword2", password2);
                    //Lưu thông báo lỗi vào request
                    request.setAttribute("rMessage", "Confirm Password not matched.");
                    //Cho hiện lại trang login.jsp
                    request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
                }
            } else { // check duplicate
                //Lưu thông tin đã nhập vào request để bảo tồn trạng thái của form
                request.setAttribute("rUserName", userName);
                request.setAttribute("rPassword", password);
                //Lưu thông báo lỗi vào request
                request.setAttribute("rMessage", "Username has been used.");
                //Cho hiện lại trang login.jsp
                request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

            }

        } catch (Exception ex) {
            log("Error at Register: " + ex.toString());
        }
    }

    protected void findUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName").toLowerCase();
//            String path = request.getParameter("path");
//            if(path==null){
//                path = "off";
//            }
            User account = UserDAO.find(userName);
            if (account != null) {
                request.setAttribute("user", account);
            } else {
                // save wrong name
                request.setAttribute("userName", userName);
                // send message
                request.setAttribute("message", "User name not found.");
            }
//            request.setAttribute("path", path);
            //Cho hiện lại trang forgot_password.jsp
            request.getRequestDispatcher("/WEB-INF/views/user/forgot_password.jsp").forward(request, response);
        } catch (Exception ex) {
            log("Error at Find User: " + ex.toString());
        }
    }

    protected void resetPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName").toLowerCase();
            String newPassword = request.getParameter("newPassword1");
            String newPassword2 = request.getParameter("newPassword2");
            String role = request.getParameter("role");

            User account = UserDAO.find(userName);
            if (newPassword.equals(newPassword2)) {
                // create new user
                User user = new User();
                user.setId(id);;
                user.setUserName(userName);
                user.setPassword(newPassword);
                user.setRole(role);
                //Update user
                UserDAO ud = new UserDAO();
                ud.update(user);               
                // lưu thông báo 
                request.setAttribute("message", "Reset Password Completed. Please login to see change.");
//                //Cho hiện lại trang login.jsp
//                request.getRequestDispatcher("/WEB-INF/views/user/forgot_password.jsp").forward(request, response);
            } else {
                // save unmatched password
                request.setAttribute("user", account);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("newPassword2", newPassword2);
                // send message
                request.setAttribute("message", "Confirm Password not matched.");
            }
            //Cho hiện lại trang forgot_password.jsp
            request.getRequestDispatcher("/WEB-INF/views/user/forgot_password.jsp").forward(request, response);
        } catch (Exception ex) {
            log("Error at Reser Password: " + ex.toString());
        }
    }
    
    protected void resetForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName").toLowerCase();
            String newPassword = request.getParameter("newPassword1");
            String newPassword2 = request.getParameter("newPassword2");
            String role = request.getParameter("role");

            User account = UserDAO.find(userName);
            if (newPassword.equals(newPassword2)) {
                // create new user
                User user = new User();
                user.setId(id);;
                user.setUserName(userName);
                user.setPassword(newPassword);
                user.setRole(role);
                //Update user
                UserDAO ud = new UserDAO();
                ud.update(user);
                // lưu thông báo 
                request.setAttribute("message1", "Reset Password Completed. Login again to see change.");
                //logout
                logout(request, response);
//                //Cho hiện lại trang login.jsp
//                request.getRequestDispatcher("/WEB-INF/views/user/ma.jsp").forward(request, response);
            } else {
                // save unmatched password
                request.setAttribute("user", account);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("newPassword2", newPassword2);
                // send message
                request.setAttribute("message1", "Confirm Password not matched.");
                
            }
            //Cho hiện lại trang forgot_password.jsp
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception ex) {
            log("Error at Reser Form: " + ex.toString());
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
