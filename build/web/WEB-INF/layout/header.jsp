<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>


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
                        <!-- Language 
                        <div class="header__top__right__language">
                            <img src="img/language.png" alt="">
                            <div>English</div>
                            <span class="arrow_carrot-down"></span>
                            <ul>
                                <li><a href="#">Spanis</a></li>
                                <li><a href="#">English</a></li>
                            </ul>
                        </div>-->
                        <div class="header__top__right__auth">
                            <c:if test="${userName == null}">
                                <a href="<c:url value="/user/login.do?op=login_form" />"><i class="fa fa-user"></i> Login</a>
                            </c:if>
                            <c:if test="${userName != null}">
                                <a href="<c:url value="/user/logout.do" />"><i class="fa fa-user"></i> ${userName}</a>
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
                        <li><a href="<c:url value="/home/shoping-cart.do" />"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>                       
                    </ul>
                    <div class="header__cart__price">item: <span>$150.00</span></div>
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
<!-- Header Section End -->

