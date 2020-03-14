<%-- 
    Document   : login
    Created on : Jan 7, 2020, 2:20:36 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <title>Login</title>

        <meta name="google-signin-client_id" 
              content="41311026166-1cfslhs449d062qv7l862ficnju8h1ed.apps.googleusercontent.com">

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp" />

            <!--Login form-->
            <div class="small-form">
                <h1>Login page </h1>
                
                <!--register success-->
                <s:if test="%{#request.RegisterSuccess != null}">
                    <font color="green">Register Successful !!</font>
                </s:if>

                <!--wrong username or password-->
                <s:if test="%{#request.ERROR_LOGIN != null}">
                    <font color="red">Email or password wrong ! Please check again</font>
                </s:if>

                <form action="login" method="POST">
                    Email:
                    <input class="form-control" type="text" 
                           name="txtEmail" value="${txtEmail}" 
                           maxlength="50" />
                    <br><br>
                    Password:
                    <input class="form-control" type="password" 
                           name="txtPassword" value="" 
                           maxlength="100"/>
                    <br>

                    <!--captcha-->
                    <div class="g-recaptcha" data-callback="recaptcha_callback"
                         data-sitekey="6Ld6qd4UAAAAAIRrDuF0uyfNq53Q4Sa4GGW_BpWN">
                    </div>

                    <br>
                    <button type="submit" class="btn btn-primary"
                            id="btnLogin" disabled
                            >
                        Login
                    </button>

                </form>

                <!--Login with gg-->
                <br>
                <div id="my-signin2"></div>
                <br>

                <!--end  login with gg-->

                <p>If you don't have account: </p>
                <a href="registerNewAccount">Click here to register</a>

                <br>
                <br>
            </div>
        </div>
                           
        <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

        <script>
            function onSuccess(googleUser) {

                var profile = googleUser.getBasicProfile();
                var email = profile.getEmail();
                var name = profile.getName();
                var id = profile.getId();

                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'http://localhost:8085/CarRental/loginwithgg');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                var params = 'txtEmail=' + email + '&name=' + name + '&txtPassword=' + id;
                xhr.send(params);

                xhr.onload = function () {
                    window.location.replace('Home.jsp');
                };

            }
            function onFailure(error) {
                window.alert("Cancle login with google")
                console.log();
            }
            function renderButton() {
                gapi.signin2.render('my-signin2', {
                    'scope': 'profile email',
                    'width': 240,
                    'height': 50,
                    'longtitle': true,
                    'theme': 'dark',
                    'onsuccess': onSuccess,
                    'onfailure': onFailure
                });
            }
            
            function recaptcha_callback() {
                var btnLogin = document.querySelector("#btnLogin");

                btnLogin.removeAttribute("disabled");
                btnLogin.style.cursor = "pointer";
            }
        </script>
    </body>
</html>
