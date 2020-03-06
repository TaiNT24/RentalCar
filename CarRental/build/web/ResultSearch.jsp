<%-- 
    Document   : ResultSearch
    Created on : Mar 4, 2020, 9:08:03 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="SearchForm.jsp" />

            <div class="main">
                <s:if test="%{listCars.size() == 0}">
                    <h2>Not match any car</h2>
                </s:if>
                
                <s:iterator value="listCars">
                    <div class="row">
                        <div class="col-sm-4">
                            <h2><s:property value="carName"/></h2>
                            <br>
                            <s:property value="yearProduce" />
                        </div>
                        <div class="col-sm-4">
                            Color: <s:property value="color"/>
                            <br>
                            Category: <s:property value="category" />
                        </div>
                        <div class="col-sm-4">
                            Price: <s:property value="price"/>$
                            <br>
                            Quantity: <s:property value="quantity" />
                            <br><br>

                            <form action="addtocart" >
                                <s:hidden name="idCar" value="%{idCar}" />

                                <input class="btn btn-primary" type="submit"
                                       value="Add to cart" />
                            </form>

                        </div>

                    </div>
                    <br>
                </s:iterator>

            </div>

        </div>
    </body>
</html>
