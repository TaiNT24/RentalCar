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
        <jsp:include page="NavBar.jsp" />

        <div class="sidenav">
            <div style=" margin: 2em;">
                <form action="search" method="POST">
                    <input type="text" class="form-control mr-sm-2" 
                           name="searchVal" 
                           value="${param.searchVal}"
                           placeholder="Product's name"
                           />
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

                    <div class="form-inline">
                        Amount:
                        <input type="number" class="form-control" required
                               style="width: 12em; margin-left: 2em" min="1"
                               name="amount" value="${amount}" />
                    </div>
                    <br>
                    <!--.-->
                    <jsp:include page="DateInput.html" />
                    <!--dateRent-->
                    <!--dateReturn-->
                    <br>
                    <p id="msg_DateToLessThanDateFrom" style="color: red;"></p>
                    <!--/-->
                    <br>
                    <button type="submit" class="btn btn-success form-control" 
                            id="btn-submit-search"
                            >
                        Search
                    </button>

                    <br>
                    <br>
                </form>

                <hr style="border: 1px solid darkcyan"/>
            </div>
        </div>

        <script>
            var datepicker1 = document.getElementById("datepicker1");
            var datepicker2 = document.getElementById("datepicker2");
            datepicker1.value = '${dateRent}';
            datepicker2.value = '${dateReturn}';

            function checkDateTo() {
                var btn_search = document.getElementById("btn-submit-search");

                var from = new Date(datepicker1.value);
                var to = new Date(datepicker2.value);

                if (to.getTime() < from.getTime()) {
                    console.log('false');
                    btn_search.disabled = true;
                    
                    document.getElementById("msg_DateToLessThanDateFrom").innerHTML 
                            = "Choose Date To more than Date From";
                } else {
                    btn_search.disabled = false;
                    document.getElementById("msg_DateToLessThanDateFrom").innerHTML 
                            = "";
                }
            }


            var selCate = document.getElementById("selCate");
            var BlockCate = document.getElementById("BlockCate");

            if (selCate.checked) {
                BlockCate.style.display = "block";
            }

            function ShowBlock() {
                if (selCate.checked) {
                    BlockCate.style.display = "block";
                }
            }

            function DeleteFilter() {
                selCate.checked = false;
                BlockCate.style.display = "none";
            }

        </script>
    </body>
</html>
