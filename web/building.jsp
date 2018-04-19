<%@ page import="models.UserModel" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: aceof
  Date: 2/26/2018
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/building.css">
<body>
    <%
        UserModel user = (UserModel) request.getAttribute("user");
        HashMap inventory = user.getPlayerInventory();
        HashMap tools = user.getPlayerTools();
    %>
    <form action="building" method="post">
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
            <h1>Building</h1>
        </main>
        <section>
            <%if(user.getLevelNum() >= 10){%>
                <li>Basic Shelter (150 Wood): </li><input type="submit" class="btn-back" name="button" value="Construct Basic Shelter">
            <%}%>
        </section>

        <article>
            <%for(int i = 0; i < user.getOutputText().size(); i++){%>
            <a><%=user.getOutputText().get(i)%><br></a>
            <%}%>
        </article>
    </form>
</body>
</html>
