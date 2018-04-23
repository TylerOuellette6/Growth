<%@ page import="models.StoryModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/inventory.css">
<link href='https://fonts.googleapis.com/css?family=Aclonica' rel='stylesheet'>
<body>
    <%
        UserModel user = (UserModel) request.getAttribute("user");
        HashMap inventory = user.getPlayerInventory();
        HashMap tools = user.getPlayerTools();
    %>
    <form action="inventory" method="post">
        <nav id="mobile_menu"></nav>
        <nav id="nav_menu">
            <ul>
                <li><a><%=user.getPlayerName()%></a></li>
                <li><a>Time: <%=user.getHourNum()%>:<%=user.getMinuteNum()%><%=user.getAMorPM()%> &nbsp &nbsp &nbsp Day: <%=user.getDayNum()%></a></li>
                <li><a>Health: <%=user.getHealth()%></a></li>
                <li><a>Energy: <%=user.getEnergy()%></a></li>
                <li><a>Level: <%=user.getLevelNum()%> &nbsp &nbsp XP:
                    <%=user.getCurrentXP()%>/<%=user.getCurrentXPRequired()%></a></li>
                <li><b><input type="submit" class="btn-back" name="button" value="Back to Main Page" style="font-size: 120%; font-family: 'Aclonica';"></b></li>
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
                    <li>Apples: <%=inventory.get("Apples")%>&emsp;<input type="submit" name="button" value ="Eat Apple" style="font-family: 'Aclonica';"></li>
                <%}%>
                <%if(user.getLevelNum() >= 3){%>
                    <li>Grass: <%=inventory.get("Grass")%></li>
                <%}%>
                <%if(user.getLevelNum() >= 4){%>
                    <li>Stone: <%=inventory.get("Stone")%></li>
                    <li>Copper: <%=inventory.get("Copper")%></li>
                <%}%>
                <%if(user.getLevelNum() >= 6){%>
                    <li>Copper Bars: <%=inventory.get("Copper Bars")%></li>
                    <li>Baked Apples: <%=inventory.get("Baked Apples")%>&emsp;<input type="submit" name="button" value ="Eat Baked Apple" style="font-family: 'Aclonica';"></li>
                <%}%>
                <%if(user.getLevelNum() >= 7){%>
                    <li>Gold: <%=inventory.get("Gold")%></li>
                <%}%>
                <%if(user.getLevelNum() >= 8){%>
                    <li>Gold Bars: <%=inventory.get("Gold Bars")%></li>
                <%}%>
                <%if(user.getLevelNum() >= 9){%>
                    <li>Fish: <%=inventory.get("Fish")%>&emsp;<input type="submit" name="button" value ="Eat Fish" style="font-family: 'Aclonica';"></li>
                <%}%>
                <%if(user.getLevelNum() >= 10){%>
                    <li>Fish and Apples: <%=inventory.get("Fish and Apples")%>&emsp;<input type="submit" name="button" value ="Eat Fish and Apple" style="font-family: 'Aclonica';"></li>
                <%}%>
            </section>

            <%--Shows things associated with the player (tools, not items)--%>
            <aside>
                <%if(user.getLevelNum() >= 2){%>
                    <li>Axe: <%=tools.get("Axe")%>
                <%}%>
                <%if(user.getLevelNum() >= 3){%>
                    <li>Hat: <%=tools.get("Hat")%>
                <%}%>
                <%if(user.getLevelNum() >= 3){%>
                    <li>Shirt: <%=tools.get("Shirt")%>
                <%}%>
                <%if(user.getLevelNum() >= 3){%>
                    <li>Pants: <%=tools.get("Pants")%>
                <%}%>
                <%if(user.getLevelNum() >= 4){%>
                    <li>Pickaxe: <%=tools.get("Pickaxe")%>
                <%}%>
                <%if(user.getLevelNum() >= 5){%>
                    <li>Sword: <%=tools.get("Sword")%>
                <%}%>
                <%if(user.getLevelNum() >= 6){%>
                    <li>Oven: <%=tools.get("Oven")%>
                <%}%>
                <%if(user.getLevelNum() >= 9){%>
                    <li>Fishing Rod: <%=tools.get("Fishing Rod")%>
                <%}%>
            </aside>

            <article>
                <%for(int i = 0; i < user.getOutputText().size(); i++){%>
                    <a><%=user.getOutputText().get(i)%><br></a>
                <%}%>
            </article>
        </main>

    </form>
</body>
</html>
