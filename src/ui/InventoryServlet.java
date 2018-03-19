package ui;

import datalayer.UserDao;
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

        if(buttonValue != null && buttonValue.equals("Back to Main Page")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewStories");
            dispatcher.forward(request, response);
            return;
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the inventory page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/inventory.jsp"); //Same thing as main screen, loads into inventory, but I want it to load the Java then the JSP
        dispatcher.forward(request, response);
    }

    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username=request.getParameter("username");
        UserModel user = UserDao.getUser(username);

        // If there is no user for some weird reason, just use anonymous.
        if (user == null) {
            user = new UserModel();
            user.setPlayerName("anonymous");
        }

        return user;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventory.jsp");
        dispatcher.forward(request, response);
    }


}
