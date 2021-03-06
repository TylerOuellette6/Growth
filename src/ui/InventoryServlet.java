package ui;

import datalayer.UserDao;
import models.GameController;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class InventoryServlet extends javax.servlet.http.HttpServlet{
    private Logger logger = Logger.getLogger(getClass().getName());

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{

        UserModel user = loadUserFromRequest(request);
        String buttonValue = request.getParameter("button");

        if(buttonValue != null && buttonValue.equals("Eat Apple")){
            GameController.eat(user, "Apples");
        }
        if(buttonValue != null && buttonValue.equals("Eat Baked Apple")){
            GameController.eat(user, "Baked Apples");
        }
        if(buttonValue != null && buttonValue.equals("Eat Fish")){
            GameController.eat(user, "Fish");
        }
        if(buttonValue != null && buttonValue.equals("Eat Fish and Apple")){
            GameController.eat(user, "Fish and Apples");
        }

        if(buttonValue != null && buttonValue.equals("Back to Main Page")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mainScreen");
            dispatcher.forward(request, response);
            return;
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the inventory page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/inventory.jsp");
        dispatcher.forward(request, response);
    }

    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        return user;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventory.jsp");
        dispatcher.forward(request, response);
    }


}
