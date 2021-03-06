package ui;

import datalayer.UserDao;
import models.GameController;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class BuildingServlet extends javax.servlet.http.HttpServlet{
    private Logger logger = Logger.getLogger(getClass().getName());

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{

        UserModel user = loadUserFromRequest(request);
        String buttonValue = request.getParameter("button");

        if(buttonValue.equals("Construct Basic Shelter")){
            GameController.buildStructure(user, "Basic Shelter");
        }

        if(buttonValue != null && buttonValue.equals("Back to Main Page")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mainScreen");
            dispatcher.forward(request, response);
            return;
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the inventory page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/building.jsp"); //Same thing as main screen, loads into inventory, but I want it to load the Java then the JSP
        dispatcher.forward(request, response);
    }

    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        return user;
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/building.jsp");
        dispatcher.forward(request, response);
    }


}
