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
    <body>
        <div class="create-table" >
            <span onclick="location.href = '<c:url value="/user/login_form.do" />'" class="close" title="Cancel">&times;</span>
            <form action="<c:url value="/user/reset_password.do" />">
                <div class="table-content">  
                    <h1>Reset Password</h1>
                    <hr>
                    <label for="id"><b>ID:</b></label>
                    <input class="id" type="text" name="id" value="${student.id}" readonly /><br/>
                    <label for="name"><b>Name:</b></label>
                    <input type="text" name="name" placeholder="student name" value="${student.name}" required /><br/>
                    <label class="gender-label" for="gender"><b>Gender: </b></label>
                    <div class="gender">
                        <input type="radio" name="gender" value="true" ${student.gender == "true" ? "checked" : ""} required/>
                        <label for="true">Male</label>
                        <input type="radio" name="gender" value="false" ${student.gender == "false" ? "checked" : ""} required />
                        <label for="false">Female</label>
                    </div>        
                    <br/>
                    <label for="dob"><b>Date of birth:</b></label>
                    <input type="date" name="dob" value="<fmt:formatDate value="${student.dob}" pattern="yyyy-MM-dd" />" required /><br/>
                    <input type="submit" name="op" value="Update" /><br/>
                </div>
            </form>
            <i style="color: red">${result}</i> 
        </div>
    </body>
</html>
