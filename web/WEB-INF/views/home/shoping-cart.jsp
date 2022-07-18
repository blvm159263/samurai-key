<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="<c:url value="/img/bread.jpg" />">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Shopping Cart</h2>
                            
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
        <section class="shoping-cart spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__table">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="shoping__product">Products</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listC}" var="o">
                                    <tr>
                                        <td class="shoping__cart__item">
                                            <img src="${o.linkImg1}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                            <h5>${o.productName}</h5>
                                        </td>
                                        <td class="shoping__cart__price">
                                            $${o.price}
                                        </td>
                                        <td class="align-middle">
                                                        <a href="<c:url value="/home/shoping-cart.do?op=sub&pid=${o.productID}" />"<button class="secondary-btn">-</button></a> <strong>${o.quantity}</strong>
                                                        <a href="<c:url value="/home/shoping-cart.do?op=add&pid=${o.productID}" />"<button class="secondary-btn">+</button></a>
                                                    </td>
                                        
                                        <td class="shoping__cart__item__close">
                                            <a href="<c:url value="/home/shoping-cart.do?op=delete&pid=${o.productID}" />" class="secondary-btn">Delete</a>
                                            
                                        </td>
                                    </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__btns">
                            <a href="<c:url value="/home/homepage.do?op=list" />" class="primary-btn cart-btn">CONTINUE SHOPPING</a>                          
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__continue">
                            <div class="shoping__discount">
                                <h5>Discount Codes</h5>
                                <form action="#">
                                    <input type="text" placeholder="Enter your coupon code">
                                    <button type="submit" class="site-btn">APPLY COUPON</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <h5>Cart Total</h5>
                            <ul>
                                <li>Subtotal <span>$${total}</span></li>
                                <li>VAT <span>$${vat}</span></li>                               
                                <li>Total <span>$${sum}</span></li>
                            </ul>
                            <a href="<c:url value="/home/checkout.do?op=view" />" class="primary-btn">PROCEED TO CHECKOUT</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shoping Cart Section End -->
