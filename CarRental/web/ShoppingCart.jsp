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

            <s:if test="%{listRentCarDetails.size()==0}">
                <h3 class="text-secondary" style="margin-left: 5em;">
                    is empty
                </h3>
                <a href="''" class="btn btn-primary">Continue shopping</a>
            </s:if>
            <s:elseif test="%{Paymented !=null}">
                <h3 style="color: green;">
                    <s:property value="Paymented" />
                </h3>
                <a href="''" class="btn btn-primary">Continue shopping</a>
            </s:elseif>
            <s:else>
                <div class="row">
                    <div class="col-sm-8" style="border: 1px solid lavender;">
                        <s:iterator value="listRentCarDetails" status="counter" var="item">
                            <div class="item-shopping-cart">
                                <s:if test="%{#request.ListOut != null}">
                                    <s:iterator value="%{#request.ListOut}" >
                                        <s:if test="%{key == idRent}">
                                            <p style="color: red; margin-left: 2em">
                                                This car has ${value} available 
                                            </p>
                                        </s:if>
                                    </s:iterator> 
                                </s:if>


                                <form action="updatequantityincart" method="POST" >
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
                                    <input type="hidden" name="idCartChange" value="${idCart}" />
                                    <input type="hidden" name="idCarChange" value="${idCar}" />
                                    <input type="hidden" name="oldQuantity" value="${quantity}" />

                                </form>
                            </div>
                           
                                    <s:set name="idCartNeedCheck" value="idCart" />
                        </s:iterator>

                    </div>

                    <!--total-->
                    <div class="col-sm-3 checkout-custom">
                        <div class="content-checkout">
                            <span class="text-primary text-lg-left">Total:</span>
                            <h4 class="text-center text-danger ">
                                <s:property value="%{totalCart}"/> $
                            </h4>
                            <p class="text-primary text-lg-left">
                                Voucher: <span id="PercentVoucher">0</span>%
                            </p>
                            <p id="msgForDiscountSucc" style="color: #28a745"><p>
                            <p id="msgForDiscountFail" style="color: #dc3545!important">
                                <s:property value="errorVoucher"/>
                            </p>

                            <input type="text" class="form-control"
                                   name="Voucher" value="" 
                                   placeholder="Voucher" id="codeDiscount"
                                   />

                            <button type="submit" class="btn btn-primary "
                                    style="margin-top: 1em;"
                                    onclick="loadDiscount()"
                                    >
                                Apply
                            </button>
                            <hr>
                            <span class="text-primary text-lg-left">Total:</span>
                            <h2 class="text-center text-danger " id="totalPriceInCart">
                                <s:property value="%{totalCart}"/> $
                            </h2>


                            <div style="margin-top: 2em;">
                                <form action="checkquantity" method="POST">

                                    <input type="hidden" name="idCartNeedCheck" 
                                           value="${idCartNeedCheck}"/>
                                    <input type="hidden" name="totalPriceAfterUseVoucher"
                                           id="totalPriceAfterUseVoucher"/>
                                    <input type="hidden" name="CodeDiscountValue"
                                           id="CodeDiscountValue"/>

                                    <button class="btn btn-success btn-lg btn-block">
                                        Payment
                                    </button>
                                </form>

                                <br>

                            </div>
                        </div>
                    </div>
                </div>
            </s:else>
        </div>

        <script>
            console.log(document.getElementById("totalPriceAfterUseVoucher").value);
            
            function loadDiscount() {
                var voucher = document.getElementById('codeDiscount').value;

                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var msg = this.responseText.toString().split(',');

                        if (msg[0] === 'Apply voucher successful !') {
                            document.getElementById("CodeDiscountValue").value = voucher;

                            document.getElementById("msgForDiscountSucc").innerHTML =
                                    msg[0];
                            document.getElementById("msgForDiscountFail").innerHTML =
                                    "";

                            var originPrice = <s:property value="%{totalCart}"/>;
                            var newPrice = Math.ceil(originPrice * (100 - msg[1]) / 100);

                            document.getElementById('totalPriceInCart').innerHTML = newPrice;
                            document.getElementById("PercentVoucher").innerHTML = msg[1];

                            document.getElementById('totalPriceAfterUseVoucher').value = newPrice;
                        } else {
                            document.getElementById("msgForDiscountFail").innerHTML =
                                    msg[0];
                            document.getElementById("msgForDiscountSucc").innerHTML =
                                    "";
                        }
                    }
                }
                ;

                xhttp.open("POST", "discountvoucher");
                xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                var param = 'voucher=' + voucher;
                xhttp.send(param);


            }
        </script>
    </body>
</html>
