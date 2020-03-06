<%-- 
    Document   : VerifyNewAccount
    Created on : Mar 5, 2020, 10:49:15 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp" />

            <div class="small-form">

                <form action="verifycode" method="POST">

                    Code: <input type="text" class="form-control"
                                 name="txtVerifyCode" value="" />

                    <s:if test="%{#request.CodeNotMatch != null}">
                        <p style="color: red">Code not matches ! Please input again</p>
                    </s:if>

                    <input type="hidden" name="txtEmail" value="${txtEmail}" />
                    
                    <!---->
                    <button type="submit" class="btn btn-success">Verify</button>
                </form>

                <a href="''">Cancel</a>
            </div>
        </div>
    </body>
</html>
