<%-- 
    Document   : manage-product
    Created on : Jul 17, 2022, 12:11:57 AM
    Author     : buile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-us"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Administrator Area</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

        <div class="container">
            <div class="row flex-lg-nowrap">
                <div class="col-12 col-lg-auto mb-3" style="width: 200px;">
                    <div class="card p-3">
                        <div class="e-navlist e-navlist--active-bg">
                            <ul class="nav">
                                <li class="nav-item"><a class="nav-link px-2  " href="#"><i class="fa fa-fw fa-bar-chart mr-1"></i><span>Manage Product</span></a></li>
                                <li class="nav-item"><a class="nav-link px-2 " href="<c:url value="/home/homepage.do?op=list" />"><i class="fa fa-shopping-cart mr-1"></i><span>Back to Shoping Site</span></a></li>
                                <li class="nav-item"><a class="nav-link px-2 " href="<c:url value="/user/logout.do" />"><i class="fa fa-sign-out mr-1"></i><span>Log Out</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="e-tabs mb-3 px-3">
                        <ul class="nav nav-tabs">
                            <li class="nav-item"><a class="nav-link active">Product</a></li>
                        </ul>
                    </div>

                    <div class="row flex-lg-nowrap">
                        <div class="col mb-3">
                            <div class="e-panel card">
                                <div class="card-body">
                                    <div class="card-title">
                                        <h6 class="mr-2"><span>Product</span><small class="px-1 message">${message}</small></h6>
                                    </div>
                                    <div class="e-table">
                                        <div class="table-responsive table-lg mt-3">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th class="max-width">Name</th>
                                                        <th class="sortable">Price</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="pro" items="${listP}" varStatus="loop">
                                                        <tr>

                                                            <td class="align-middle text-center">
                                                                ${pro.productID}
                                                            </td>
                                                            <td class="text-nowrap align-middle">${pro.productName}</td>
                                                            <td class="text-nowrap align-middle"><fmt:formatNumber value="${pro.price}" type="currency"/></td>
                                                            <td class="text-center align-middle">
                                                                <div class="btn-group align-top">
                                                                    <button class="btn btn-sm btn-outline-secondary badge" type="button" data-toggle="modal" data-target="#user-form-modal${loop.count}">Edit</button>
                                                                    <a class="btn btn-sm btn-outline-secondary badge" href="<c:url value="/admin/manage.do?op=delete&productID=${pro.productID}" />"><i class="fa fa-trash" ></i></a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <ul class="pagination mt-3 mb-0">
                                                <c:forEach begin="1" end="${endP}" varStatus="loop">
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/admin/manage.do?op=showPage&page=${loop.count}" />">${loop.count}</a></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-3 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="text-center px-xl-3">
                                        <button class="btn btn-success btn-block" type="button" data-toggle="modal" data-target="#user-form-modal">New Product</button>
                                    </div>

                                    <hr class="my-3">
                                    <div>
                                        <form action="<c:url value="/admin/manage.do" />">
                                            <div class="form-group">
                                                <label>Search by ID:</label>
                                                <div><input class="form-control w-100" type="text" name="productID" placeholder="ID" value="${fID}"></div>
                                                <button class="btn btn-success btn-block" type="submit" name="op" value="findByID" >Find</button>

                                            </div>
                                        </form>
                                        <form action="<c:url value="/admin/manage.do" />">
                                            <div class="form-group">
                                                <label>Search by Name:</label>
                                                <div><input class="form-control w-100" type="text" name="productName" placeholder="Name" value="${fName}"></div>
                                                <button class="btn btn-success btn-block" type="submit" name="op" value="findByName">Find</button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- User Form Modal -->
                    <c:forEach var="pro" items="${listP}" varStatus="loop">
                        <div class="modal fade" role="dialog" tabindex="-1" id="user-form-modal${loop.count}">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit Product ID ${pro.productID}</h5>
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="py-1">
                                            <form class="form" action="<c:url value="/admin/manage.do" />" >
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <input class="form-control" type="text" name="productID" value="${pro.productID}" hidden>
                                                                    <label>Product Name</label>
                                                                    <input class="form-control" type="text" name="productName" value="${pro.productName}">
                                                                </div>
                                                            </div>
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Price ($)</label>
                                                                    <input class="form-control" type="text" name="price" value="${pro.price}">
                                                                </div>
                                                            </div>
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Quantity</label>
                                                                    <input class="form-control" type="text" name="quantity" value="${pro.quantity}">
                                                                </div>
                                                            </div>
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Rating</label>
                                                                    <select name="rating" class="form-control">
                                                                        <c:forEach begin="1" end="5" varStatus="loop">
                                                                            <option ${loop.count==pro.rating?'selected':''} >${loop.count}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col mb-3">
                                                                <div class="form-group">
                                                                    <label>Description</label>
                                                                    <textarea class="form-control" rows="3" name="desc" placeholder="My Bio" >${pro.desc}</textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Link Image 1</label>
                                                                    <input class="form-control" type="text" name="linkImg1" value="${pro.linkImg1}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Link Image 2</label>
                                                                    <input class="form-control" type="text" name="linkImg2" value="${pro.linkImg2}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Link Image 3</label>
                                                                    <input class="form-control" type="text" name="linkImg3" value="${pro.linkImg3}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Link Image 4</label>
                                                                    <input class="form-control" type="text" name="linkImg4" value="${pro.linkImg4}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Link Image 5</label>
                                                                    <input class="form-control" type="text" name="linkImg5" value="${pro.linkImg5}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Genre</label>
                                                                    <select name="genre" class="form-control">
                                                                        <c:forEach var="genre" items="${listGenre}" >
                                                                            <option value="${genre.genreID}"  ${genre.genreName==pro.genre.genreName?'selected':''} >${genre.genreName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>Consoles</label>
                                                                    <select name="console" class="form-control">
                                                                        <c:forEach var="consoles" items="${listConsoles}" >
                                                                            <option value="${consoles.consolesID}" >${consoles.consolesName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col d-flex justify-content-end">
                                                        <input class="btn btn-primary" type="submit" name="op" value="Update">
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="modal fade" role="dialog" tabindex="-1" id="user-form-modal">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Create New Product</h5>
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="py-1">
                                        <form class="form" action="<c:url value="/admin/manage.do" />" >
                                            <div class="row">
                                                <div class="col">
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Product Name</label>
                                                                <input class="form-control" type="text" name="productName" value="${pro.productName}">
                                                            </div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Price ($)</label>
                                                                <input class="form-control" type="text" name="price" value="${pro.price}">
                                                            </div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Quantity</label>
                                                                <input class="form-control" type="text" name="quantity" value="${pro.quantity}">
                                                            </div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Rating</label>
                                                                <select name="rating" class="form-control">
                                                                    <c:forEach begin="1" end="5" varStatus="loop">
                                                                        <option >${loop.count}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col mb-3">
                                                            <div class="form-group">
                                                                <label>Description</label>
                                                                <textarea class="form-control" rows="3" name="desc"  ></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Link Image 1</label>
                                                                <input class="form-control" type="text" name="linkImg1" value="${pro.linkImg1}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Link Image 2</label>
                                                                <input class="form-control" type="text" name="linkImg2" value="${pro.linkImg2} ">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Link Image 3</label>
                                                                <input class="form-control" type="text" name="linkImg3" value="${pro.linkImg3}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Link Image 4</label>
                                                                <input class="form-control" type="text" name="linkImg4" value="${pro.linkImg4}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Link Image 5</label>
                                                                <input class="form-control" type="text" name="linkImg5" value="${pro.linkImg5}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Genre</label>
                                                                <select name="genre" class="form-control">
                                                                    <c:forEach var="genre" items="${listGenre}" >
                                                                        <option value="${genre.genreID}"  ${genre.genreName==pro.genre.genreName?'selected':''} >${genre.genreName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <label>Consoles</label>
                                                                <select name="console" class="form-control">
                                                                    <c:forEach var="consoles" items="${listConsoles}" >
                                                                        <option value="${consoles.consolesID}" >${consoles.consolesName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col d-flex justify-content-end">
                                                    <input class="btn btn-primary" type="submit" name="op" value="Create">
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <style type="text/css">
            body{
                margin-top:20px;
                background:#f8f8f8
            }
        </style>


    </body>
</html>
