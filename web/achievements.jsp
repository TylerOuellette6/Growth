<%@ page import="models.UserModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/achievements.css">
<body>
    <%
        UserModel user = (UserModel) request.getAttribute("user");
    %>
    <form action="achievements" method="post">

    <nav id="mobile_menu"></nav>
    <nav id="nav_menu">
        <ul>
            <li><a><%=user.getPlayerName()%></a></li>
            <li><a>Time: <%=user.getHourNum()%>:<%=user.getMinuteNum()%><%=user.getAMorPM()%> &nbsp &nbsp &nbsp Day: <%=user.getDayNum()%></a></li>
            <li><a>Health: <%=user.getHealth()%></a></li>
            <li><a>Energy: <%=user.getEnergy()%></a></li>
            <li><a>Level: <%=user.getLevelNum()%> &nbsp &nbsp XP:
                <%=user.getCurrentXP()%>/<%=user.getCurrentXPRequired()%></a></li>
            <li><b><input type="submit" class="btn-back" name="button" value="Back to Main Page"></b></li>
        </ul>
    </nav>

    <main>
        <h1>Achievements</h1>

        <section>
            <%for(String keyName : user.getAchievementsList().keySet()){%>
                <%if(user.getAchievementByName(keyName) == true){%>
                    <li><%=keyName%></li>
                <%}%>
            <%}%>
        </section>
    </main>

    </form>
</body>
</html>
