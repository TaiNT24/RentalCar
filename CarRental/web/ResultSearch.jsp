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
                    <div class="row row-car">
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
                            Quantity: <s:property value="availableQuantity" />
                            <br><br>

                            <form action="addtocart" >
                                <s:hidden name="idCar" value="%{idCar}" />
                                <s:hidden name="searchVal" value="%{#request.searchVal}" />
                                <s:hidden name="moreFilter" value="%{#request.moreFilter}" />
                                <s:hidden name="categoryApply" value="%{#request.categoryApply}" />
                                <s:hidden name="amount" value="%{#request.amount}" />
                                <s:hidden name="dateRent" value="%{#request.dateRent}" />
                                <s:hidden name="dateReturn" value="%{#request.dateReturn}" />

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
