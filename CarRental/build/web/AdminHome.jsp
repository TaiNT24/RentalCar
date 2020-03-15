<%-- 
    Document   : AdminHome
    Created on : Mar 15, 2020, 8:02:19 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="NavBar.jsp" />

            <!--<div class="row">-->
            <s:iterator value="%{listCart}">
                <s:iterator value="listDetailDTO">
                    <s:if test="%{idCart == key}">
                        <div class="item-shopping-cart row">
                            <div class="col-sm-12 " style="margin: 1em;">
                                <table class="table table-hover bg-info text-white">
                                    <thead>
                                        <tr>
                                            <th>
                                                CartID: <s:property value="idCart" />
                                            </th>
                                            <th>Email: <s:property value="email" /></th>
                                            <th>Phone: </th>
                                            <th>
                                                Date rent: <s:property value="dateRent"/>
                                            </th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>


                            <div class="col-sm-8" style="margin-left: 1em;" >
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
                                        </tbody>

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
                                    <s:property value="totalPrice"/>
                                </h2>

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

                                <br>
                                <!--Cancel order-->
                                <s:if test="%{#statusCar == 'Paymented'}">
                                    <s:url var="returnCar" value="returncar">
                                        <s:param name="CartID">
                                            <s:property value="idCart"/>
                                        </s:param>
                                    </s:url>
                                    <a href="${returnCar}" style="margin-bottom: 2em;"
                                       class="btn btn-info btn-block">
                                        Return
                                    </a>
                                </s:if>

                            </div>
                        </div>

                    </s:if>
                </s:iterator>
            </s:iterator>
            <!--</div>-->

            <h2 class="text-secondary text-center">
                -----Rental Car-----
            </h2>

        </div>
    </body>
</html>
