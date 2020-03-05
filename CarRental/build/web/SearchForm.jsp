<%-- 
    Document   : SearchForm
    Created on : Mar 3, 2020, 5:04:17 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp" />

            <div class="sidenav">
                <div style=" margin: 2em;">
                    <form action="search" method="POST">
                        <input type="text" class="form-control mr-sm-2" 
                               name="searchVal" 
                               value="${param.searchVal}"
                               placeholder="Product's name"
                               />

                        <button type="submit" class="btn btn-success" 
                                style="margin:1em 4em;float: right">
                            Search
                        </button>

                        <br>
                        <br>
                        <!--.-->
                        <button type="button" class="btn btn-primary"
                                onclick="DeleteFilter()"
                                >
                            Delete Filter
                        </button>
                        <br><br>


                        <!--search by category-->
                        <div class="form-check">
                            <label class="form-check-label" for="selCate">
                                <input type="radio" class="form-check-input" 
                                       id="selCate" onclick="ShowBlock()"
                                       name="moreFilter" value="category" 
                                       <s:if test="%{MoreFilter == 'category'}">
                                           checked
                                       </s:if>
                                       >Category
                            </label>
                            <div class="form-group hiddenBlock" id="BlockCate">
                                <select class="form-control" id="categoryID"
                                        name="categoryApply">
                                    <s:iterator value="%{#application.CATEGORY}">
                                        <option
                                            <s:if test="%{category == categoryApply}">
                                                selected
                                            </s:if>
                                            >
                                            <s:property value="category"/>
                                        </option>
                                    </s:iterator>
                                </select>
                            </div>

                        </div>
                        <br>
                        <!--search by amount of car-->
                        <div class="form-check">
                            <label class="form-check-label" for="selAmount">
                                <input type="radio" class="form-check-input" 
                                       id="selAmount" onclick="ShowBlock()"
                                       name="moreFilter" value="amount"
                                       <s:if test="%{moreFilter == 'amount'}">
                                           checked
                                       </s:if>
                                       >Amount
                            </label>

                            <div class="hiddenBlock" id="BlockAmount">
                                <input type="number" class="form-control"
                                       style="width: 5em" min="1"
                                       name="amount" value="${amount}" />
                            </div>

                        </div>


                        <!--.-->
                    </form>

                    <hr style="border: 1px solid darkcyan"/>
                </div>
            </div>
        </div>

        <script>
            var selCate = document.getElementById("selCate");
            var selAmount = document.getElementById("selAmount");
            var BlockCate = document.getElementById("BlockCate");
            var BlockAmount = document.getElementById("BlockAmount");

            if (selCate.checked) {
                BlockCate.style.display = "block";
            }
            if (selAmount.checked) {
                BlockAmount.style.display = "block";
            }

            function ShowBlock() {
                if (selCate.checked) {
                    BlockCate.style.display = "block";

                    BlockAmount.style.display = "none";
                }
                if (selAmount.checked) {
                    BlockAmount.style.display = "block";

                    BlockCate.style.display = "none";
                }
            }

            function DeleteFilter() {
                selCate.checked = false;
                selAmount.checked = false;

                BlockCate.style.display = "none";
                BlockAmount.style.display = "none";
                
            }

        </script>
    </body>
</html>
