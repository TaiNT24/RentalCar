<%-- 
    Document   : HistoryShopping
    Created on : Feb 24, 2020, 11:03:55 PM
    Author     : nguye
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <s:if test="%{#session.ROLE == null}">
            <s:action name="''" ></s:action>
        </s:if>

        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <s:if test="%{listIDCart.size() == 0 && isSearch!=null}">
                <jsp:include page="SearchHistoryForm.jsp"/>
                <h2 class="text-secondary">Search not match</h2>
            </s:if>
            <s:elseif test="%{listIDCart.size() == 0}">
                <h2 class="text-secondary">Your History is empty</h2>
            </s:elseif>
            <s:else>
                <jsp:include page="SearchHistoryForm.jsp"/>

                <div class="main">

                    <!--list search match or all history-->
                    <!--<c:if test="${not empty listCart and empty msg}">-->
                        <!--<c:forEach var="cart" items="${listCart}">-->
                    <s:iterator value="listIDCart" var="id">
                        <s:iterator value="listHistory">
                            <s:if test="%{#id == key}">
                                <div class="item-shopping-cart">
                                    <div class="row">
                                        <div class="col-sm-7" style="margin:1em;" >
                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Rent time</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                        <th>Total</th>
                                                    </tr>
                                                </thead>

                                                <s:iterator value="value">
                                                    <tbody>
                                                        <tr>
                                                            <td><s:property value="carName"/></td>
                                                            <td>
                                                                <s:property value="dateRent"/>
                                                                to
                                                                <s:property value="dateReturn"/>
                                                            </td>
                                                            <td><s:property value="price"/></td>
                                                            <td><s:property value="quantity"/></td>
                                                            <td><s:property value="totalPrice"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="5" class="text-right">
                                                                <s:url var="feedback" value="feedback">
                                                                    <s:param name="rentID">
                                                                        <s:property value="idRent"/>
                                                                    </s:param>
                                                                </s:url>
                                                                <s:if test="%{status == 'Returned' && idFeedback == 0}">
                                                                    <a href="${feedback}">
                                                                        Feedback
                                                                    </a>
                                                                </s:if>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    <s:set var="totalPriceInCart">
                                                        <s:property value="totalPriceInCart"/>
                                                    </s:set>
                                                    <s:set var="dateRentCart">
                                                        <s:property value="dateRentCart"/>
                                                    </s:set>
                                                    <s:set var="statusCar">
                                                        <s:property value="status"/>
                                                    </s:set>

                                                </s:iterator>
                                            </table>
                                        </div>

                                        <!---->
                                        <div class="col-sm-3 " style="margin-top: 1em; margin-left: 3em;" >
                                            <span class="text-primary text-lg-left">Total:</span>
                                            <h2 class="text-center text-danger ">
                                                <s:property value="%{#totalPriceInCart}"/>
                                            </h2>

                                            <!--Date rent cart-->
                                            <p class="text-right text-secondary">
                                                <s:property value="%{#dateRentCart}"/>
                                            </p>

                                            <!--status-->
                                            <s:if test="%{#statusCar == 'Paymented'}">
                                                <p class="text-right badge badge-info">
                                                    Paymented
                                                </p>
                                            </s:if>
                                            <s:elseif test="%{#statusCar == 'Canceled'}">
                                                <p class="text-right badge badge-danger">
                                                    Canceled
                                                </p>
                                            </s:elseif>
                                            <s:else>
                                                <p class="text-right badge badge-success">
                                                    Returned
                                                </p>
                                            </s:else>

                                            <!--Cancel order-->
                                            <s:if test="%{#statusCar == 'Paymented'}">
                                                <s:url var="cancel" value="cancelorder">
                                                    <s:param name="CartID">
                                                        <s:property value="%{#id}"/>
                                                    </s:param>
                                                </s:url>
                                                <a href="${cancel}" 
                                                   class="btn btn-danger">
                                                    Cancel order
                                                </a>
                                            </s:if>

                                            <!--Re order-->
                                            <s:if test="%{#statusCar == 'Canceled'}">
                                                <s:url var="reorder" value="reorder">
                                                    <s:param name="CartID">
                                                        <s:property value="%{#id}"/>
                                                    </s:param>
                                                </s:url>
                                                <a href="${reorder}"
                                                   class="btn btn-primary">
                                                    Reorder
                                                </a>
                                            </s:if>

                                        </div>
                                    </div>
                                </div>

                            </s:if>
                        </s:iterator>
                    </s:iterator>

                </div>
            </s:else>
        </div>

    </body>
</html>
