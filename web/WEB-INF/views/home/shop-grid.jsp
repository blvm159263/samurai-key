
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-us"/>



<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="<c:url value="/img/bread.jpg" />">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Every Game You're Finding</h2>
                    <div class="breadcrumb__option">
                        <span>Authentication Key</span>
                        <span> - </span>
                        <span>Cheap</span>
                        <span> - </span>
                        <span>Trending</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->
<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <!-- Left Side Begin -->
            <div class="col-lg-3 col-md-5">
                <div class="sidebar">
                    <form action="<c:url value="/home/shop-grid.do" />">
                        <div class="sidebar__item">
                            <h4>Genre</h4>
                            <ul>
                                <c:forEach var="genre" items="${listGenre}">
                                    <!--<li><a href="#">${genre.genreName}</a></li>-->
                                    <li>
                                        <label>
                                            <input type="radio" name="genreID" value="${genre.genreID}" ${curGen == genre.genreID?"checked":""} />
                                            ${genre.genreName}
                                        </label>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Price</h4>
                            <div class="price-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                     data-min="${minPrice}" data-max="${maxPrice}">
                                    <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                </div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <input type="text" name="minPrice" id="minamount" value="${curMin}">
                                        <input type="text" name="maxPrice" id="maxamount" value="${curMax}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <h4>Consoles</h4>
                            <ul class="console__item row" >
                                <c:forEach var="consoles" items="${listConsoles}" >
                                    <li class="col-md-5">
                                        <label class="console__option">
                                            <input type="radio" name="consolesID" value="${consoles.consolesID}" ${curCons == consoles.consolesID?"checked":""}/>
                                            ${consoles.consolesName}
                                        </label>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Rating</h4>
                            <div class="rating">
                                <input type="radio" name="rating" value="5" id="5" ${curRating == 5?"checked":""}><label for="5">&star;</label>
                                <input type="radio" name="rating" value="4" id="4" ${curRating == 4?"checked":""}><label for="4">&star;</label>
                                <input type="radio" name="rating" value="3" id="3" ${curRating == 3?"checked":""}><label for="3">&star;</label>
                                <input type="radio" name="rating" value="2" id="2" ${curRating == 2?"checked":""}><label for="2">&star;</label>
                                <input type="radio" name="rating" value="1" id="1" ${curRating == 1?"checked":""}><label for="1">&star;</label>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="confirm_find">
                                <input style="width: 48%; display: inline-block; " class="btn-block btn btn-outline-info left_input" type="submit" name="op" value="Filter" />
                                <input style="width: 48%; float: right; margin-top: 0px " class="btn-block btn btn-outline-info right_input" type="reset" />
                                <input class="btn-block btn btn-outline-info right_input" type="submit" name="op" value="ListAll" />
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Featured Product</h4>
                                <div class="latest-product__slider owl-carousel">
                                    <div class="latest-prdouct__slider__item">
                                        <c:forEach begin="0" end="2" var="pro" items="${listNew}" >
                                            <a href="<c:url value="/home/shop-details.do?pid=${pro.productID}" />" class="latest-product__item">
                                                <div class="latest-product__item__pic">
                                                    <img src="${pro.linkImg1}" alt="">
                                                </div>

                                            </a>
                                        </c:forEach>
                                    </div>
                                    <div class="latest-prdouct__slider__item">
                                        <c:forEach begin="3" end="5" var="pro" items="${listNew}" >
                                            <a href="<c:url value="/home/shop-details.do?pid=${pro.productID}" />" class="latest-product__item">
                                                <div class="latest-product__item__pic">
                                                    <img src="${pro.linkImg1}" alt="">
                                                </div>

                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--Left Side End  -->

            <div class="col-lg-9 col-md-7">
                <!-- List Product Begin -->
                <div class="filter__item">
                    <div class="row">
                        <div class="col-lg-4 col-md-5">
                            <div class="filter__sort">
                                <span>Sort By</span>
                                <select>
                                    <option value="0">Default</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4">
                            <div class="filter__found">
                                <h6><span>${size}</span> Games found</h6>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-3">
                            <div class="filter__option">
                                <span class="icon_grid-2x2"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="pro" items="${listP}" >
                        <div  class="col-lg-4 col-md-6 col-sm-6 ">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="${pro.linkImg1}">
                                    <ul class="product__item__pic__hover">
                                        <c:choose>
                                            <c:when test="${not empty user.userName}">
                                                <li><a href="<c:url value="/home/shoping-cart.do?op=add&pid=${pro.productID}"/>"><i class="fa fa-shopping-cart"></i></a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="<c:url value="/user/login_form.do"/>"><i class="fa fa-shopping-cart"></i></a></li>
                                            </c:otherwise>
                                        </c:choose> 
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="<c:url value="/home/shop-details.do?pid=${pro.productID}" />">${pro.productName}</a></h6>
                                    <h5><fmt:formatNumber value="${pro.price}" type="currency" /></h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- List Product End -->

                <div class="product__pagination">
                    <c:forEach begin="1" end="${endP}" varStatus="loop">
                        <a href="<c:url value="/home/shop-grid.do?op=showPage&page=${loop.count}" />">${loop.count}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Section End -->

