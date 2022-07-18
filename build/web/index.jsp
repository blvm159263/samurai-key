<%-- 
    Document   : index
    Created on : Jun 22, 2022, 7:25:32 PM
    Author     : buile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="" var="context"/>
<!DOCTYPE html>
<%-- cú pháp là : .../[controller]/[action].do
  ->  đưa tới FrontController xử lý tiếp
--%>
<%--<jsp:forward page="/home/homepage.do?op=list"/>--%>
<jsp:forward page="/home/homepage.do"/>
<%--<jsp:forward page="${context}/admin/manage.do?op=listFull"/>--%>

