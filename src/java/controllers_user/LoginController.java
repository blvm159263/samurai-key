/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers_user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "LogController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        //Lấy op
        String op = (String) request.getAttribute("op");
        op = op.toLowerCase();
        switch (op) {
            case "login_form":
                loginForm(request, response);
                break;
            case "login_handler":
                loginHandler(request, response);
                //request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);
                break;
            case "logout":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    protected void loginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read cookies if available
        Cookie cookie = null;
        Cookie cUserName = null;
        Cookie cPassword = null;
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
            }
        }
        if (cUserName != null
                && cPassword != null
                && cUserName.getValue().equals("admin")
                && cPassword.getValue().equals("12345")) {
            //Lưu userName vào session để ghi nhận đã login thành công
            HttpSession session = request.getSession();
            session.setAttribute("userName", "admin");
            //Lưu userName truyền đến header để thay tên
            request.setAttribute("userName", "admin");
            //Chuyển đến trang index.jsp => header.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
             
        } else {
            //Chuyển đến trang login.jsp
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        }
    }
    
        protected void loginHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberMe") != null;

        // If login successfully
        if (userName.equals("admin") && password.equals("12345")) {
            if (rememberMe) {
                // Create cookies for username and password     
                Cookie cUserName = new Cookie("userName", userName);
                Cookie cPassword = new Cookie("password", password);

                // Set expiry date after 24 Hrs for both the cookies
                cUserName.setMaxAge(60 * 60 * 24);
                cPassword.setMaxAge(60 * 60 * 24);

                // Add both the cookies in the response header
                response.addCookie(cUserName);
                response.addCookie(cPassword);

                System.out.println("Cookies have been stored");
            }
            //Lưu userName vào session để ghi nhận đã login thành công
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            //Chuyển đến trang index.jsp => header.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            //Lưu thông tin đã nhập vào request để bảo tồn trạng thái của form
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            //Lưu thông báo lỗi vào request
            request.setAttribute("message", "Please check your username and password.");
            //Cho hiện lại trang login.jsp
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
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
