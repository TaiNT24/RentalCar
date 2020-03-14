<%-- 
    Document   : NavBar
    Created on : Feb 18, 2020, 8:25:51 PM
    Author     : nguye
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nav Bar</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta name="google-signin-client_id" 
              content="41311026166-1cfslhs449d062qv7l862ficnju8h1ed.apps.googleusercontent.com">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="library/login.css">
    </head>

    <body >
        <script>
            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
            }

            function onLoad() {
                gapi.load('auth2', function () {
                    gapi.auth2.init();
                });
            }
        </script>

        <div class="jumbotron">
            <h1 class="display-2">Car Center</h1>
        </div>

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <a class="navbar-brand" href="''">Home</a>
                </li>
            </ul>
            <ul class="navbar-nav" style="margin-left: 20em">
                <s:if test="%{#session.USER_EMAIL == null}">

                    <li class="nav-item" style="width: 14em">
                        <a class="text-white text-center">
                            You have to login to rent a car
                        </a>
                    </li>

                </s:if>
            </ul>

            <!--.-->
            <ul class="navbar-nav" >

                <!--Not Login yet-->
                <s:if test="%{#session.USER_EMAIL == null}">
                    <li class="nav-item  " 
                        style="text-align: center;margin-left: 20em">
                        <a href="loginPage" class="text-white">Login</a> 
                    </li>
                </s:if>
            </ul>

            <ul class="navbar-nav" style="margin-left: 25%">
                <!--User-->
                <s:if test="%{#session.ROLE == 0}">

                    <li class="nav-item text-white " 
                        style="text-align: center; width: 15em;"
                        >
                        <div class="dropdown">
                            <button type="button" class="btn btn-primary dropdown-toggle"  
                                    data-toggle="dropdown">
                                Welcome: <s:property value="%{#session.USER_NAME}"/>
                            </button>

                            <div class="dropdown-menu" >
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="historyshopping">History shopping</a>
                                </p>
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="logout" onclick="signOut();">Logout</a>
                                </p>
                            </div>
                        </div>                   
                    </li>


                    <li class="nav-item" style="margin-left: 1em">
                        <a href="gotocart" class="btn btn-info">
                            Shopping Cart
                        </a>
                    </li>
                </ul>
            </s:if>

        </nav>
        <br>

        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </body>
</html>
