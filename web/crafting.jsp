<%@ page import="models.StoryModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Growth</title>
</head>
<link rel="stylesheet" href="styles/crafting.css">
<link href='https://fonts.googleapis.com/css?family=Aclonica' rel='stylesheet'>
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
    <form action="crafting" method="post">

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
        <h1>Crafting</h1>

        <section>
            <%if(user.getLevelNum() >= 2){%>
                <li>Craft Basic Axe (6 Wood): </li><input type="submit" class="btn-back" name="button" value="Craft Basic Axe" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 3){%>
                <li>Craft Grass Hat (6 Grass): </li><input type="submit" class="btn-back" name="button" value="Craft Grass Hat" style="font-family: 'Aclonica';">
                <li>Craft Grass Shirt (10 Grass): </li><input type="submit" class="btn-back" name="button" value="Craft Grass Shirt" style="font-family: 'Aclonica';">
                <li>Craft Grass Pants (6 Grass): </li><input type="submit" class="btn-back" name="button" value="Craft Grass Pants" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 4){%>
                <li>Craft Basic Pickaxe (4 Wood, 3 Stone): </li><input type="submit" class="btn-back" name="button" value="Craft Basic Pickaxe" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 5){%>
                <li>Craft Basic Sword (6 Wood, 4 Stone): </li><input type="submit" class="btn-back" name="button" value="Craft Basic Sword" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 6){%>
                <li>Craft Oven (15 Stone, 3 Copper): </li><input type="submit" class="btn-back" name="button" value="Craft Oven" style="font-family: 'Aclonica';">
                <li>Smelt Copper Bar (3 Copper, Requires Oven): </li><input type="submit" class="btn-back" name="button" value="Smelt Copper Bar" style="font-family: 'Aclonica';">
            <%}%>
        </section>

        <aside>
            <%if(user.getLevelNum() >= 6){%>
                <li>Bake Apple (1 Apple): </li><input type="submit" class="btn-back" name="button" value="Bake Apple" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 7){%>
                <li>Craft Copper Axe (4 Wood, 3 Copper Bars): </li><input type="submit" class="btn-back" name="button" value="Craft Copper Axe" style="font-family: 'Aclonica';">
                <li>Craft Copper Pickaxe (5 Wood, 3 Copper Bars): </li><input type="submit" class="btn-back" name="button" value="Craft Copper Pickaxe" style="font-family: 'Aclonica';">
                <li>Craft Copper Sword (6 Wood, 3 Copper Bars): </li><input type="submit" class="btn-back" name="button" value="Craft Copper Sword" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 8){%>
                <li>Smelt Gold Bar (3 Gold, Requires Oven): </li><input type="submit" class="btn-back" name="button" value="Smelt Gold Bar" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 9){%>
                <li>Craft Fishing Rod (10 Wood, 5 Grass): </li><input type="submit" class="btn-back" name="button" value="Craft Fishing Rod" style="font-family: 'Aclonica';">
            <%}%>
            <%if(user.getLevelNum() >= 10){%>
                <li>Bake Fish and Apples (1 Fish, 1 Apple): </li><input type="submit" class="btn-back" name="button" value="Bake Fish and Apples" style="font-family: 'Aclonica';">
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
