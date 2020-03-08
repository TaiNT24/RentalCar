<%-- 
    Document   : ShoppingCart
    Created on : Mar 5, 2020, 12:47:44 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <h2>Your cart:</h2>

            <s:if test="%{listRentCar.size()==0}">
                <h2 class="text-secondary">Your cart is empty</h2>
            </s:if>
            <s:else>
                <div class="row">
                    <div class="col-sm-8" style="border: 1px solid lavender;">
                        <s:iterator value="listRentCarDetails" status="counter">
                            <div class="item-shopping-cart">

                                <form action="updatequantityincart" method="POST">
                                    <div class="row">
                                        <div class="col-sm-4" style="margin-left: 1em" >
                                            <!--Name-->
                                            <h2 style="padding: 0.5em">
                                                <s:property value="carName" />
                                            </h2>
                                            <p>Time renting:</p>
                                            <p style="margin: 0">
                                                <s:property value="dateRent"/> 
                                                - <s:property value="dateReturn"/>
                                            </p>
                                        </div>

                                        <div class="col-sm-7" >
                                            <br>
                                            <div style="float: left;" class="text-primary">
                                                <h4>Price: <s:property value="price"/>$</h4>

                                                <h4>Category: <s:property value="category"/></h4>
                                            </div>


                                            <div style="float: right;margin-right: 1em">
                                                <h5>Quantity:</h5>
                                                <h5>
                                                    <button class="btn btn-success" id="decreItem" 
                                                            name="btnAction" value="decreItem"
                                                            type="submit"
                                                            <s:if test="%{quantity==1}">disabled</s:if>>
                                                        -
                                                    </button>

                                                            <input type="tel" 
                                                                   name="quantityItem"
                                                                   value="${quantity}" 
                                                            style="width: 2em;"/>

                                                    <button class="btn btn-success" id="increItem" 
                                                            name="btnAction" value="increItem"
                                                            type="submit">
                                                        +
                                                    </button>

                                                </h5>

                                                <h5 class="text-danger">
                                                    Total: <s:property value="totalPrice"/>$
                                                </h5>

                                                <!--delete-->
                                                <s:set var="showConfirm" >
                                                    Confirm<s:property value="%{#counter.count}"/>
                                                </s:set>

                                                <a href="#${showConfirm}" 
                                                   data-toggle="collapse"
                                                   >
                                                    Delete item
                                                </a>
                                                <div id="${showConfirm}" class="collapse"
                                                     >
                                                    <p>Delete this item ?</p>
                                                    <div style="margin-bottom: 1em">
                                                        <s:url var="delete" value="deleteitemfromcart">
                                                            <s:param name="idRentInRentCar">
                                                                <s:property value="%{idRent}"/>
                                                            </s:param>
                                                            <!--                                                            <c:param name="userID" 
                                                                                                                                 value="${sessionScope.USER_ID}"/>-->
                                                        </s:url>
                                                        <a href="${delete}" class="btn btn-danger" >
                                                            Yes
                                                        </a>
                                                        <a href="#${showConfirm}" 
                                                           data-toggle="collapse"
                                                           class="btn btn-primary" 
                                                           >
                                                            No
                                                        </a>
                                                    </div>
                                                </div>
                                                <!--end delete-->
                                            </div>

                                        </div>
                                    </div>
                                    <input type="hidden" name="idRentChange" value="%{idRent}" />
                                    <input type="hidden" name="oldQuantity" value="%{quantity}" />


                                </form>
                            </div>

                        </s:iterator>

                    </div>

                    <!--total-->
                    <div class="col-sm-3 checkout-custom">
                        <div class="content-checkout">
                            <span class="text-primary text-lg-left">Total:</span>
                            <h2 class="text-center text-danger ">
                                <s:property value="%{totalCart}"/> $
                            </h2>

                            <div style="margin-top: 3em;">
                                <!--                                <c:url var="paymentByCast" value="CheckQuantityInStock">
                                                                    <c:param name="cartID" 
                                                                             value="${sessionScope.CART_ID}"/>
                                                                    <c:param name="paymentMethod" 
                                                                             value="PaymentByCast"/>
                                                                </c:url>-->

                                <a href="${paymentByCast}" 
                                   class="btn btn-success btn-lg btn-block">
                                    Payment by CASH
                                </a>
                                <br>
                                <!--                                <c:url var="paymentByPaypal" value="CheckQuantityInStock">
                                                                    <c:param name="userID" 
                                                                             value="${sessionScope.USER_ID}"/>
                                                                    <c:param name="cartID" 
                                                                             value="${sessionScope.CART_ID}"/>
                                                                    <c:param name="totalPayment" 
                                                                             value="${totalPayment}"/>
                                                                    <c:param name="paymentMethod" 
                                                                             value="PaymentByPaypal"/>
                                                                </c:url>-->

                                <a href="${paymentByPaypal}"
                                   class="btn btn-success btn-lg btn-block">
                                    Payment by PayPal
                                </a>

                            </div>
                        </div>
                    </div>
                </div>
            </s:else>
        </div>
    </body>
</html>
