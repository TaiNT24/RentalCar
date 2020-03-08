<%-- 
    Document   : Home
    Created on : Mar 3, 2020, 4:25:41 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div class="container">


            <jsp:include page="SearchForm.jsp" />

            <div class="main">
                <s:if test="%{#session.USER_EMAIL != null}">
                    <p class="bg-info text-white text-center" style="padding: 0.5em">
                        This is the list car we provide.
                        You MUST search to have a suitable car !!
                    </p>
                </s:if>
                    
                <s:iterator value="%{#application.LISTCAR}">
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
                            Quantity: <s:property value="quantity" />
                            <br><br>

                        </div>

                    </div>
                    <br>
                </s:iterator>
            </div>

            <h2 class="text-secondary text-center">
                -----Rental Car-----
            </h2>

        </div>
    </body>
</html>
