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
    if (user == null) {
        user = new UserModel();
        user.setPlayerName("anonymous");
    }

    StoryModel stories[] = (StoryModel[]) request.getAttribute("stories");
    if (stories == null) {
        stories = new StoryModel[0];
    }
%>
<p></p>
<p></p>
<div class="container">

    <form action="viewStories" method="post">

        <nav id="mobile_menu"></nav>
        <nav id="nav_menu">
            <ul>
                <li><a><%=user.getPlayerName()%></a></li>
                <li><a>Time: <%%>&nbsp &nbsp &nbsp &nbsp Day: <%%></a></li>
                <li><a>Health: <%=user.getHealth()%></a></li>
                <li><a>Energy: <%=user.getEnergy()%></a></li>
                <li><a>Level: <%=user.getLevelNum()%></a></li>
                <li><a href="welcome"><span class="glyphicon glyphicon-log-out"></span>Exit</a></li>
            </ul>
        </nav>

        <main>
            <h1>Growth</h1>
            <section>
                <input type="submit" class="btn-left" name="button" value="Activities">
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



        <!-- Navigation Bar -->
        <%--<nav class="navbar navbar-inverse">--%>
            <%--<div class="container-fluid">--%>
                <%--<div class="navbar-header">--%>
                    <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">--%>
                        <%--<span class="icon-bar"></span>--%>
                        <%--<span class="icon-bar"></span>--%>
                        <%--<span class="icon-bar"></span>--%>
                    <%--</button>--%>
                <%--</div>--%>
                <%--<div class="collapse navbar-collapse" id="myNavbar">--%>
                    <%--<ul class="nav navbar-nav">--%>
                        <%--&lt;%&ndash;<li class="active"><a href="viewStories">Stories</a></li>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<li class="inactive"><a href="viewStories">Ratings</a></li>&ndash;%&gt;--%>
                        <%--<li class="inactive"><a href=""><%=user.getPlayerName()%></a></li>--%>
                        <%--<li class="inactive"><a href="">Level: <%=user.getLevelNum()%></a></li>--%>
                        <%--<li class="inactive"><a href="">Health: <%=user.getHealth()%></a></li>--%>
                        <%--<li class="inactive"><a href="">Energy: <%=user.getEnergy()%></a></li>--%>

                    <%--</ul>--%>
                    <%--<ul class="nav navbar-nav navbar-right">--%>

                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</nav>--%>

        <!-- Display the jumbotron -->
        <%--<div class="jumbotron">--%>
            <%--<h1>Oh No!</h1>--%>
        <%--</div>--%>

        <%--<!-- Display a list of stories -->--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="well well-sm">--%>
                    <%--<h3><p class="text-primary"><%=stories.length%> Stories</h3>--%>
                    <%--<div class="pre-scrollable">--%>
                        <%--<ul class="list-group">--%>
                            <%--<%--%>
                                <%--for (int i = stories.length - 1; i >= 0; i--) {--%>
                            <%--%>--%>
                            <%--<li class="list-group-item">[<%=stories[i].getUsername()%>] - <%=stories[i].getStory()%>--%>
                            <%--</li>--%>
                            <%--<%--%>
                                <%--}--%>
                            <%--%>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- Input for a new story -->
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="well well-sm">--%>
                <%--<div class="form-group">--%>
                    <%--<label for="storyText">Tell your story</label>--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" id="storyText" name="storyText"--%>
                               <%--placeholder="What's your story?">--%>
                    <%--</div>--%>
                    <%--<!-- Button -->--%>
                    <%--<input type="submit" class="btn btn-info" name="submitButton" value="Submit">--%>
                <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>


        <!-- This is a screet input to the post!  Acts as if the user
             had an input field with the username.
         -->
        <%--<input type="hidden" name="username" value="<%=user.getPlayerName()%>">--%>

    </form>
</div>
</body>
</html>
