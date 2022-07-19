<%-- 
    Document   : logout
    Created on : Jul 10, 2022, 7:51:56 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link href="<c:url value="/css/forgot_password.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-image: url('<c:url value="/img/pwd.png" />');">
        <c:if test="${empty user.userName}">
            <div class="create-table" >
                <span onclick="location.href = '<c:url value="/user/login_form.do" />'" class="close" title="Cancel">&times;</span>
                <form action="<c:url value="/user/find_user.do" />">
                    <div class="table-content">  
                        <h1>Reset Password</h1>
                        <hr>
                        <label for="userName"><b>Enter Name:</b></label>
                        <input id="userName" type="text" name="userName" placeholder="User name" value="${userName}" ${message=="Reset Password Completed. Please login to see change." ? "disabled" : "required"} /><br/>                      
                        <br/>
                        <p style="color: red; position: relative; text-align: center; margin: 0">${message}</p>
                        <input type="submit" value="Next" ${message=="Reset Password Completed. Please login to see change." ? "disabled" : ""}/><br/>
                    </div>
                </form>

            </div>
        </c:if>
        <c:if test="${not empty user.userName}">
            <div class="create-table" >
                <span onclick="location.href = '<c:url value="/user/login_form.do" />'" class="close" title="Cancel">&times;</span>
                <form action="<c:url value="/user/reset_password.do" />">
                    <div class="table-content">  
                        <h1>Reset Password</h1>
                        <hr>
                        <input type="hidden" name="id" value="${user.id}" />
                        <input type="hidden" name="userName" value="${user.userName}" />
                        <input type="hidden" name="role" value="${user.role}" />
                        <label for="np1"><b>Enter new password:</b></label>
                        <input id="np1" type="password" name="newPassword1" placeholder="Password" value="${empty newPassword2 ? "" : newPassword}" /><br/>                      
                        <br/>
                        <label for="np2"><b>Confirm new password:</b></label>
                        <input id="np2" type="password" name="newPassword2" placeholder="Confirm password"  /><br/>                      
                        <br/>
                        <p style="color: red; position: relative; text-align: center; margin: 0">${message}</p>
                        <input type="submit" value="Reset" /><br/>
                    </div>
                </form>

            </div>
        </c:if>

    </body>
</html>
