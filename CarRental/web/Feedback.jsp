<%-- 
    Document   : Feedback
    Created on : Mar 14, 2020, 9:08:52 AM
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
        <div class="container">
            <jsp:include page="NavBar.jsp"/>
            <div style="width: 50%; margin-left: 25%; margin-bottom: 3em">
                <div class="row">
                    <label for="ratingNumber">Rate:</label>
                    <div class="rating" id="ratingNumber">
                        <input type="radio" id="star10" name="rating" value="10" checked onclick="getNumberRating('star10')"/><label for="star10" title="Rocks!">10</label>
                        <input type="radio" id="star9" name="rating" value="9" onclick="getNumberRating('star9')" /><label for="star9" title="Rocks!">9</label>
                        <input type="radio" id="star8" name="rating" value="8" onclick="getNumberRating('star8')" /><label for="star8" title="Pretty good">8</label>
                        <input type="radio" id="star7" name="rating" value="7" onclick="getNumberRating('star7')" /><label for="star7" title="Pretty good">7</label>
                        <input type="radio" id="star6" name="rating" value="6" onclick="getNumberRating('star6')" /><label for="star6" title="Meh">6</label>
                        <input type="radio" id="star5" name="rating" value="5" onclick="getNumberRating('star5')" /><label for="star5" title="Meh">5</label>
                        <input type="radio" id="star4" name="rating" value="4" onclick="getNumberRating('star4')" /><label for="star4" title="Kinda bad">4</label>
                        <input type="radio" id="star3" name="rating" value="3" onclick="getNumberRating('star3')" /><label for="star3" title="Kinda bad">3</label>
                        <input type="radio" id="star2" name="rating" value="2" onclick="getNumberRating('star2')" /><label for="star2" title="Sucks big tim">2</label>
                        <input type="radio" id="star1" name="rating" value="1" onclick="getNumberRating('star1')" /><label for="star1" title="Sucks big time">1</label>
                    </div>
                </div>

                <br>
                <form action="submitfeedback">
                    <input type="hidden" name="Rating" id="Rating" value="10" />
                    <input type="hidden" name="rentID" id="rentID" value="${param.rentID}" />

                    <div class="form-group">
                        <label for="comment">Feedback:</label>
                        <textarea class="form-control" rows="5" name="feedback" 
                                  placeholder="No required ! Max 500 character"
                                  ></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary"
                            onclick="submitSuccess()"
                            >
                        Submit
                    </button>
                </form>
            </div>

        </div>

        <script>
            function getNumberRating(id) {
                var idd = document.getElementById(id);

                document.getElementById("Rating").value = idd.value;

                console.log(idd.value);

            }
            
            function submitSuccess() {
                window.alert("Submit successful !");
            }
        </script>
    </body>
</html>
