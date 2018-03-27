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
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/inventory.css">
<body>
<%
    UserModel user = (UserModel) request.getAttribute("user");
    HashMap inventory = user.getPlayerInventory();
//    Inventory inventory = (Inventory) request.getAttribute("inventory");

    StoryModel stories[] = (StoryModel[]) request.getAttribute("stories");
    if (stories == null) {
        stories = new StoryModel[0];
    }
%>
    <form action="mainScreen" method="post">

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
        <%--Shows the things in the player's inventory (items, not tools)--%>
        <section>
            <%if(user.getLevelNum() >= 1){%>
                <li>Wood: <%=inventory.get("Wood")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 2){%>
                <li>Apples: <%=inventory.get("Apples")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 3){%>
                <li>Grass: <%=inventory.get("Grass")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 4){%>
                <li>Stone: <%=inventory.get("Stone")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 4){%>
                <li>Copper: <%=inventory.get("Copper")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 6){%>
                <li>Copper Bars: <%=inventory.get("Copper Bars")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 6){%>
                <li>Baked Apples: <%=inventory.get("Baked Apples")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 7){%>
                <li>Gold: <%=inventory.get("Gold")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 8){%>
                <li>Gold Bars: <%=inventory.get("Gold Bars")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 9){%>
                <li>Fish: <%=inventory.get("Fish")%></li>
            <%}%>
            <%if(user.getLevelNum() >= 10){%>
                <li>Fish and Apples: <%=inventory.get("Fish and Apples")%></li>
            <%}%>
        </section>

        <%--Shows things associated with the player (tools, not items)--%>
        <aside>
            <%if(user.getLevelNum() >= 2){%>
                <li>Axe: <%=user.getAxe()%>
            <%}%>
            <%if(user.getLevelNum() >= 3){%>
                <li>Hat: <%=user.getHat()%>
            <%}%>
            <%if(user.getLevelNum() >= 3){%>
                <li>Shirt: <%=user.getShirt()%>
            <%}%>
            <%if(user.getLevelNum() >= 3){%>
                <li>Pants: <%=user.getPants()%>
            <%}%>
            <%if(user.getLevelNum() >= 4){%>
                <li>Pickaxe: <%=user.getPickaxe()%>
            <%}%>
            <%if(user.getLevelNum() >= 5){%>
                <li>Sword: <%=user.getSword()%>
            <%}%>
            <%if(user.getLevelNum() >= 6){%>
                <li>Oven: <%=user.isOven()%>
            <%}%>
            <%if(user.getLevelNum() >= 9){%>
                <li>Fishing Rod: <%=user.getFishingRod()%>
            <%}%>
        </aside>
    </main>

    </form>
</body>
</html>
