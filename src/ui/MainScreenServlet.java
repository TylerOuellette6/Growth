package ui;

import datalayer.StoryDao;
import datalayer.UniqueIdDao;
import datalayer.UserDao;
import models.GameController;
import models.StoryModel;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

public class MainScreenServlet extends javax.servlet.http.HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The post method is called by the browser when the user presses the button
     *
     * @param request The request has info on filled in fields and button presses.
     * @param response We use this to give the browser a response.
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logRequestParameters(request);  // Just to help with debugging.

        // Get data from the request
        UserModel user = loadUserFromRequest(request);
        String storyText=request.getParameter("storyText");
        String buttonValue = request.getParameter("button");
        String username = (String) request.getSession().getAttribute("username");

        if(buttonValue.equals("Gather Wood")){
            GameController.actionPerformed(user,"wood","Wood", GameController.randomCollectionGenerator(), 3, 30);
        }
        if(buttonValue.equals("Gather Apples")){
            GameController.actionPerformed(user, "apples","Apples", GameController.randomCollectionGenerator(), 2, 30);
        }
        if(buttonValue.equals("Gather Grass")){
            GameController.actionPerformed(user, "grass","Grass", GameController.randomCollectionGenerator(), 3, 30);
        }
        if(buttonValue.equals("Go Mining")){
            GameController.actionPerformed(user, "stones","Stone", GameController.randomCollectionGenerator(), 5, 50);
        }
        if(buttonValue.equals("Fight Enemies")){
            GameController.actionPerformed(user, "fight", "Wood", GameController.randomCollectionGenerator()+1, 0, 0);
            GameController.actionPerformed(user, "fight", "Apples", GameController.randomCollectionGenerator()+1, 0, 0);
            GameController.actionPerformed(user, "fight", "Grass", GameController.randomCollectionGenerator()+1, 0, 0);
            GameController.actionPerformed(user, "fight", "Stone", GameController.randomCollectionGenerator()+1, 0, 0);
            GameController.actionPerformed(user, "fightFinal", "Wood", 0, 12, 90);
        }
        if(buttonValue.equals("Go Fishing")){
            GameController.actionPerformed(user, "fish", "Fish", GameController.randomCollectionGenerator(), 5, 60);
        }

        if(buttonValue != null && buttonValue.equals("Inventory")){
            RequestDispatcher newDispatcher = request.getRequestDispatcher("/inventory");
            newDispatcher.forward(request, response);
            return;
        }
        else if(buttonValue != null && buttonValue.equals("Crafting")){
            RequestDispatcher newDispatcher = request.getRequestDispatcher("/crafting");
            newDispatcher.forward(request, response);
            return;
        }
        else if(buttonValue != null && buttonValue.equals("Building")){
            RequestDispatcher newDispatcher = request.getRequestDispatcher("/building");
            newDispatcher.forward(request, response);
            return;
        }
        else if(buttonValue != null && buttonValue.equals("Achievements")){
            RequestDispatcher newDispatcher = request.getRequestDispatcher("/achievements");
            newDispatcher.forward(request, response);
            return;
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/mainscreen.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Grab the username from the request and create a user model.
     */
    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        return user;
    }

    /**
     * The get method is called if the user directly invokes the URI.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mainscreen.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * This method is useful in debugging what you got back in the
     * response from the user.
     *
     * @param request
     */
    private void logRequestParameters(javax.servlet.http.HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    }

}
