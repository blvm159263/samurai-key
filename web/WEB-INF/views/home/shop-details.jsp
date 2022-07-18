
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-us"/>
<!-- Product Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <!--Picture of product -->
            <div class="col-lg-6 col-md-6">
                <div class="product__details__pic">
                    <div class="product__details__pic__item">
                        <img class="product__details__pic__item--large"
                             src="${detail.linkImg1}" alt="Hinh anh Game">
                    </div>
                    <!--Picture of gameplay-->
                    <div class="product__details__pic__slider owl-carousel">
                        <img src="${detail.linkImg2}" alt="Hinh anh Game">
                        <img src="${detail.linkImg3}" alt="Hinh anh Game">
                        <img src="${detail.linkImg4}" alt="Hinh anh Game">
                        <img src="${detail.linkImg5}" alt="Hinh anh Game">
                    </div>
                </div>
            </div>

            <div class="col-lg-6 col-md-6">

                <div class="product__details__text">
                    <h3>${detail.productName}</h3>
                    <!--Rating ???? -->
                    <div class="product__details__rating">
                        <c:forEach begin="1" end="${detail.rating}">
                            <i class="fa fa-star"></i>
                        </c:forEach>
                        <c:if test="${detail.rating < 5}">
                            <c:forEach begin="${detail.rating+1}" end="5">
                                <i class="fa fa-star-o"></i>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="product__details__price"><fmt:formatNumber value="${detail.price}" type="currency" /> </div>
                    <!--Description -->
                    <p>${detail.desc}</p>

                    <c:choose>
                        <c:when test="${not empty user.userName}">
                            <a href="<c:url value="/home/shoping-cart.do?op=add&pid=${pid}" />" class="primary-btn" > Add to Cart</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/user/login_form.do" />" class="primary-btn" > Add to Cart</a>
                        </c:otherwise>
                    </c:choose> 
                </div>
            </div>

        </div>
    </div>
</div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title related__product__title">
                    <h2>Related Product</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach begin="2" end="7" var="pro" items="${listNew}" >
                <div class="col-lg-3 col-md-4 col-sm-6">
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
    </div>
</section>
<!-- Related Product Section End -->
