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
                
            </s:if>
            <s:else>
                
            </s:else>
        </div>
    </body>
</html>
