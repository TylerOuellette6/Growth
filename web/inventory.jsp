<%--
  Created by IntelliJ IDEA.
  User: aceof
  Date: 2/26/2018
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.StoryModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="models.Inventory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/inventory.css">
<body>
<%
    UserModel user = (UserModel) request.getAttribute("user");
    ArrayList inventory = user.getPlayerInventory();
//    Inventory inventory = (Inventory) request.getAttribute("inventory");
    if (user == null) {
        user = new UserModel();
        user.createPlayer("anonymous", 0,0,0,0);
    }

    StoryModel stories[] = (StoryModel[]) request.getAttribute("stories");
    if (stories == null) {
        stories = new StoryModel[0];
    }
%>
    <form action="viewStories" method="post">

        <nav id="mobile_menu"></nav>
        <nav id="nav_menu">
            <ul>
                <li><a><%=user.getPlayerName()%></a></li>
                <li><a>Time: <%%>&nbsp &nbsp &nbsp &nbsp Day: <%%></a></li>
                <li><a>Health: <%=user.getHealth()%></a></li>
                <li><a>Energy: <%=user.getEnergy()%></a></li>
                <li><a>Level: <%=user.getLevelNum()%></a></li>
                <li><b><input type="submit" class="btn-back" name="button" value="Back to Main Page"></b></li>
            </ul>
        </nav>
    <main>
        <h1>Inventory</h1>
        <section>
            <%--<a><%=user.getPlayerName()%></a>--%>
            <li>Wood: <%=inventory.get(0)%></li>
            <li>Grass: <%=inventory.get(1)%></li>
            <li>Apples: <%=inventory.get(2)%></li>
            <li>Stone: <%=inventory.get(3)%></li>
            <li>Copper: <%=inventory.get(4)%></li>
            <li>Copper Bars: <%=inventory.get(5)%></li>
            <li>Baked Apples: <%=inventory.get(6)%></li>
            <li>Gold: <%=inventory.get(7)%></li>
            <li>Gold Bars: <%=inventory.get(8)%></li>
            <li>Fish: <%=inventory.get(9)%></li>
            <li>Fish and Apples: <%=inventory.get(10)%></li>
        </section>
        <aside>
            <li>Axe: <%=user.getAxe()%></li>
            <li>Pickaxe: <%=user.getPickaxe()%></li>
            <li>Sword: <%=user.getSword()%></li>
            <li>Hat: <%=user.getHat()%></li>
            <li>Shirt: <%=user.getShirt()%></li>
            <li>Pants: <%=user.getPants()%></li>
            <li>Oven: <%=user.isOven()%></li>
            <li>Fishing Rod: <%=user.getFishingRod()%></li>
        </aside>
    </main>

    </form>
</body>
</html>
