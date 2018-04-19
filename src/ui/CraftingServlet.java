package ui;

import datalayer.UserDao;
import models.GameController;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class CraftingServlet extends javax.servlet.http.HttpServlet{
    private Logger logger = Logger.getLogger(getClass().getName());

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{

        UserModel user = loadUserFromRequest(request);
        String buttonValue = request.getParameter("button");

        if(buttonValue.equals("Craft Basic Axe")){
            GameController.craftTool(user, "Wood", 6, "Wood", 0,
                    "Basic Axe",  "Axe");
        }
        if(buttonValue.equals("Craft Grass Hat")){
            GameController.craftTool(user, "Grass", 6, "Grass", 0,
                    "Grass Hat","Hat");
        }
        if(buttonValue.equals("Craft Grass Shirt")){
            GameController.craftTool(user, "Grass", 10, "Grass", 0,
                    "Grass Shirt", "Shirt");
        }
        if(buttonValue.equals("Craft Grass Pants")){
            GameController.craftTool(user, "Grass", 8, "Grass", 0,
                    "Grass Pants", "Pants");
        }
        if(buttonValue.equals("Craft Basic Pickaxe")){
            GameController.craftTool(user, "Wood", 4, "Stone", 3,
                    "Basic Pickaxe","Pickaxe");
        }
        if(buttonValue.equals("Craft Basic Sword")){
            GameController.craftTool(user, "Wood", 6, "Stone", 4,
                    "Basic Sword","Sword");
        }
        if(buttonValue.equals("Craft Oven")){
            GameController.craftTool(user, "Stone", 15, "Copper", 3,
                    "Oven","Oven");
        }
        if(buttonValue.equals("Smelt Copper Bar")){
            GameController.craftItem(user, "Copper", 3,"Copper Bars");
        }
        if(buttonValue.equals("Bake Apple")){
            GameController.craftItem(user, "Apple", 1, "Baked Apples");
        }
        if(buttonValue.equals("Craft Copper Axe")){
            GameController.craftTool(user, "Wood", 4, "Copper Bars", 3,
                    "Copper Axe", "Axe");
        }
        if(buttonValue.equals("Craft Copper Pickaxe")){
            GameController.craftTool(user, "Wood", 4, "Copper Bars", 3,
                    "Copper Pickaxe", "Pickaxe");
        }
        if(buttonValue.equals("Craft Copper Sword")){
            GameController.craftTool(user, "Wood", 6, "Copper Bars", 3,
                    "Copper Sword", "Sword");
        }
        if(buttonValue.equals("Smelt Gold Bar")){
            GameController.craftItem(user, "Gold", 3, "Gold Bar");
        }
        if(buttonValue.equals("Craft Fishing Rod")){
            GameController.craftTool(user, "Wood", 10, "Grass", 4,
                    "Basic Fishing Rod", "Fishing Rod");
        }
        if(buttonValue.equals("Bake Fish and Apples")){
            GameController.craftItem(user, "Fish", 1, "Fish and Apples");
        }

        if(buttonValue != null && buttonValue.equals("Back to Main Page")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mainScreen");
            dispatcher.forward(request, response);
            return;
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the inventory page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/crafting.jsp"); //Same thing as main screen, loads into inventory, but I want it to load the Java then the JSP
        dispatcher.forward(request, response);
    }

    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        return user;
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crafting.jsp");
        dispatcher.forward(request, response);
    }


}
