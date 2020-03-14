<%-- 
    Document   : SearchHistoryForm
    Created on : Feb 25, 2020, 8:13:07 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2 >
            <span class="badge badge-info">History Shopping</span>
        </h2>

        <div class="sidenav">
            <div style=" margin: 2em;">

                <form action="searchhistory" method="POST">
                    <input class="form-control mr-sm-2" type="text" 
                           name="searchHistoryVal" value="${searchHistoryVal}"
                           placeholder="Product's name">

                    <button class="btn btn-success" type="submit"
                            style="float: right;margin-right: 4em" 
                            id="btn-history-search"
                            >
                        Search
                    </button>
                    <br><br>
                    <span class="btn btn-info">Search by date</span>
                    <br>
                    <jsp:include page="DateInput.html" />

                    <p id="msg_DateToLessThanDateFrom_History" style="color: red;"></p>
                    <!--                    From:
                                        <input type="date" name="dateFrom" value="${dateFrom}"/>
                                        <br><br>To:
                                        <input type="date" name="dateTo" value="${dateTo}"/>-->

                </form>
            </div>
        </div>
        <script>
            var datepicker1 = document.getElementById("datepicker1");
            var datepicker2 = document.getElementById("datepicker2");
            datepicker1.value = '${dateRent}';
            datepicker2.value = '${dateReturn}';
            
            datepicker1.required = false;
            datepicker2.required = false;
            
            function checkDateTo() {
                var btn_search = document.getElementById("btn-history-search");

                var from = new Date(datepicker1.value);
                var to = new Date(datepicker2.value);

                if (to.getTime() < from.getTime()) {
                    console.log('false');
                    btn_search.disabled = true;

                    document.getElementById("msg_DateToLessThanDateFrom_History").innerHTML
                            = "Choose Date To more than Date From";
                } else {
                    btn_search.disabled = false;
                    document.getElementById("msg_DateToLessThanDateFrom_History").innerHTML
                            = "";
                }
            }

        </script>
    </body>
</html>
