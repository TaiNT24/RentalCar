<%-- 
    Document   : RegisterNewAccount
    Created on : Jan 7, 2020, 2:40:57 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New Account</title>

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <div class="small-form">
                <h1>Register New Account</h1>

                <form action="registeraccount" method="POST"
                      onsubmit="return submitUserForm();"
                      >
                    Email:
                    <input type="text" class="form-control"
                           name="txtEmail" value="${txtEmail}"  
                           maxlength="50" required/>
                    <s:if test="%{#request.ERROR_EMAIL != null}">
                        <br>
                        <p style="color: red"><s:property value="%{#request.ERROR_EMAIL}"/></p>
                    </s:if>
                    <br>
                    
                    Name:
                    <input type="text" class="form-control"
                           name="txtName" value="${txtName}" 
                           maxlength="50" required/>
                    <br>

                    Password: 
                    <input type="password" class="form-control"
                           name="txtPassword" value="" 
                           maxlength="50" required/>
                    <br>

                    Confirm Password:
                    <input type="password" class="form-control"
                           name="txtConfPassword" value="" 
                           maxlength="50" required/>
                    <s:if test="%{#request.ERROR_PASSWORD != null}">
                        <br>
                        <p style="color: red"><s:property value="%{#request.ERROR_PASSWORD}"/></p>
                    </s:if>
                    <br>

                    Address:
                    <input type="text" class="form-control"
                           name="txtAddress" value="${txtAddress}" 
                           maxlength="100" required/>
                    <br>

                    Phone:
                    <input type="text" class="form-control"
                           name="txtPhone" value="${txtPhone}" 
                           maxlength="20" required/>
                    <s:if test="%{#request.ERROR_PHONE != null}">
                        <br>
                        <p style="color: red"><s:property value="%{#request.ERROR_PHONE}"/></p>
                    </s:if>
                    <br>

                    <!--captcha-->
                    <div class="g-recaptcha" 
                         data-sitekey="6Ld6qd4UAAAAAIRrDuF0uyfNq53Q4Sa4GGW_BpWN">
                    </div>
                    
                    <p id="msgRecaptcha" style="color: red"></p>
                    <!---->

                    <button type="submit" class="btn btn-primary">
                        Register
                    </button>
                </form>
                <br><br>

                <p>If you have account: </p>

                <a href="loginPage">Click here to login</a>
                <br>                <br>

                <p>Or</p>
                <a href="''">Back home</a>
                <br>
                <br>

            </div>
        </div>
                           
        <script>
            function submitUserForm(){
                var response = grecaptcha.getResponse();
                var msg = document.getElementById("msgRecaptcha");
                
                if(response.length == 0){
                    msg.innerHTML = "You are robot and I don't like robot";
                    return false;
                }
                return true;
                
            }
        </script>
    </body>
</html>
