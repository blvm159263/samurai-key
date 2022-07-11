
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
                        <i>${detail.rating}</i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star-half-o"></i>
                    </div>
                    <div class="product__details__price"><fmt:formatNumber value="${detail.price}" type="currency" /> </div>
                    <!--Description -->
                    <p>${detail.desc}</p>
                    <div class="product__details__quantity">
                        <div class="quantity">
                            <div class="pro-qty">
                                <input type="text" value="1">
                            </div>
                        </div>
                    </div>
                    <a href="<c:url value="/WEB-INF/home/checkout.do"/>" class="primary-btn">Add to cart</a>
                    <ul>
                        <li><b>Availability</b> <span>In Stock</span></li>
                        <li><b>Shipping</b> <span>01 day shipping.</span></li>
                        <li><b>Weight</b> <span>0.5 kg</span></li>

                    </ul>
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
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
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
