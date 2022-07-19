<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-us" />
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<link rel="stylesheet" href="<c:url value="/css/reset_pwd.css" />" type="text/css">

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> 4AnhEmSieuNhanGao@fpt.edu.vn</li>
                            <li>Support 24/24</li>
                            <li>Gaming Market Site</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <!--Language--> 
                        <div class="header__top__right__language">
                            <!--for user-->
                            <c:if test="${user.userName == null}">
                                <a href="<c:url value="/user/login_form.do" />"><i style="margin-right: 8px" class="fa fa-user"></i>Login</a>
                            </c:if>
                            <c:if test="${user.userName != null}">
                                <div><i style="margin-right: 8px" class="fa fa-user"></i>${user.userName}</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <!--<li><a href="#">My Profile</a></li>-->
                                    <li><a onclick="document.getElementById('reset').style.display = 'block'">Reset Password</a></li>
                                        <%--<li><a href="<c:url value="/user/reset_form.do" />">Reset Password</a></li>--%>
                                        <c:if test="${user.role == 'ADMIN'}">
                                        <li><a href="<c:url value="/admin/manage.do?op=listFull" />">Management</a></li>
                                        </c:if>
                                    <li><a href="<c:url value="/user/logout.do" />">Log Out</a></li>
                                </ul>
                            </c:if>  

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="<c:url value="/home/homepage.do?op=list" />"><img src="<c:url value="/img/logo.png" />" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="<c:url value="/home/shop-grid.do" /> ">
                                <input type="text" placeholder="What do you need?" name="fname" value="${fname}">
                                <button type="submit" class="site-btn" name="op" value="search">SEARCH</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="<c:url value="/home/shoping-cart.do?op=view" />"><i class="fa fa-shopping-bag"></i></a></li>                       
                    </ul>

                    <div class="header__cart__price">item: <span><fmt:formatNumber value="${total}" type="currency" /></span></div>



                </div>
            </div>

            <c:if test="${cap_userName != null}">
                <div class="col-lg-12">
                    <p style="font-size: 35px; text-align: center; color: #000">Welcome back, ${cap_userName}</p>
                </div>
            </c:if>

        </div>

        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<section class="hero hero-normal">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="hero__categories">
                    <div class="hero__categories__all">
                        <i class="fa fa-bars"></i>
                        <span>All Genre</span>
                    </div>
                    <ul>
                        <c:forEach var="genre" items="${listGenre}">
                            <li><a href="<c:url value="/home/shop-grid.do?op=listByGenre&genreID=${genre.genreID}" />">${genre.genreName}</a></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <nav class="header__menu">
                    <ul>
                        <li class="${action=='homepage'?'active':''}"><a href="<c:url value="/home/homepage.do?op=list"/>">Home</a></li>
                        <li class="${action=='shop-grid'?'active':''}"><a href="<c:url value="/home/shop-grid.do?op=listAll"/>">Shop</a></li>
                        <li class="${action=='shoping-cart'?'active':''}"><a href="<c:url value="/home/shoping-cart.do?op=view"/>">Shoping Cart</a></li>
                        <li class="${action=='checkout'?'active':''}"><a href="<c:url value="/home/checkout.do?op=view"/>">Check Out</a></li>
                    </ul>
                </nav>
            </div>

        </div>
    </div>
</section>
<div style="${not empty message1 ? "display: block;" : ""}" id="reset" class="modal">
    <form class="modal-content animate" action="<c:url value="/user/reset_form.do" />" method="post">
        <span onclick="document.getElementById('reset').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        <div class="container-form">
            <h1>Reset Password</h1>
            <hr>
            <input type="hidden" name="id" value="${user.id}" />
            <input type="hidden" name="userName" value="${user.userName}" />
            <input type="hidden" name="role" value="${user.role}" />
            <label for="newPassword1"><b>Enter new password</b></label>
            <input type="password" placeholder="Enter new Password" name="newPassword1" value="${empty newPassword2 ? "" : newPassword}"  ${message1=="Reset Password Completed. Login again to see change." ? "disabled" : "required"} >
            <label for="newPassword2"><b>Confirm Password</b></label>
            <input type="password" placeholder="Confirm Password" name="newPassword2" ${message1=="Reset Password Completed. Login again to see change." ? "disabled" : "required"}>
            <p style="color: red; position: relative; text-align: center; margin: 0">${message1}</p>
            <button class="reset" type="submit" ${message1=="Reset Password Completed. Login again to see change." ? "disabled" : ""}>Reset</button>
        </div>
    </form>
</div>


<script>
    // Get the modal
    var modal = document.getElementById('reset');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>      
<!-- Header Section End -->

