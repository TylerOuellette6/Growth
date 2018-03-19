<%--
  Created by IntelliJ IDEA.
  User: aceof
  Date: 2/26/2018
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.StoryModel" %>
<%@ page import="models.UserModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<body>
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
    <form action="viewStories" method="post">
        <h1>Inventory</h1>
        <a><%=user.getPlayerName()%></a>
        <input type="submit" class="btn-back" name="button" value="Back to Main Page">
    </form>
</body>
</html>
