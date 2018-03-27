<%@ page import="models.StoryModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="models.GameController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>Growth</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="styles/mainpage.css">
<!-- Latest compiled and minified CSS -->
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"--%>
      <%--integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">--%>

<!-- Optional theme -->
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"--%>
      <%--integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">--%>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>



</head>
<body>

<!-- Let's start by loading information we expect in the request.
     For any info missing, we'll just fake it.
  -->
<%
    UserModel user = (UserModel) request.getAttribute("user");

    StoryModel stories[] = (StoryModel[]) request.getAttribute("stories");
    if (stories == null) {
        stories = new StoryModel[0];
    }
%>
<p></p>
<p></p>
<div class="container">

    <form action="mainScreen" method="post">

        <nav id="mobile_menu"></nav>
        <nav id="nav_menu">
            <ul>
                <li><a><%=user.getPlayerName()%></a></li>
                <li><a>Time: <%%>&nbsp &nbsp &nbsp &nbsp Day: <%%></a></li>
                <li><a>Health: <%=user.getHealth()%></a></li>
                <li><a>Energy: <%=user.getEnergy()%></a></li>
                <li><a>Level: <%=user.getLevelNum()%> &nbsp &nbsp XP:
                    <%=user.getCurrentXP()%>/<%=user.getCurrentXPRequired()%></a></li>
                <li><a href="welcome"><span class="glyphicon glyphicon-log-out"></span>Exit</a></li>
            </ul>
        </nav>

        <main>
            <h1>Growth</h1>
            <section>
                <div class="dropdown">
                    <input type="submit" class="btn-left" id="dropdown" name="button" value="Actions">
                    <%if(user.getLevelNum() >= 9){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Apples">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Grass">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Go Mining">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Fight Enemies">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Go Fishing">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}else if(user.getLevelNum() >= 5){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Apples">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Grass">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Go Mining">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Fight Enemies">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}else if(user.getLevelNum() >= 4){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Apples">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Grass">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Go Mining">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}else if(user.getLevelNum() >=3){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Apples">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Grass">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}else if(user.getLevelNum() >= 2){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Apples">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}else if(user.getLevelNum() >= 1){%>
                        <div class="dropdown-content">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Gather Wood">
                            <input type="submit" class="btn-left" id="drpdwnButton" name="button" value="Sleep">
                        </div>
                    <%}%>
                </div>
                <input type="submit" class="btn-left" name="button" value="Inventory">
                <input type="submit" class="btn-left" name="button" value="Crafting">
                <input type="submit" class="btn-left" name="button" value="Building">
            </section>
            <aside>
                <input type="submit" class="btn-right" name="button" value="Achievements">
                <input type="submit" class="btn-right" name="button" value="Credits">
                <input type="submit" class="btn-right" name="button" value="Help">
                <input type="submit" class="btn-right" name="button" value="Pause">
            </aside>
        </main>
    </form>
</div>
</body>
</html>
