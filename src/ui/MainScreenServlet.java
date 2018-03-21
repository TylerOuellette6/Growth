package ui;

import datalayer.StoryDao;
import datalayer.UniqueIdDao;
import datalayer.UserDao;
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

        // If submit was hit, add a story.
//        if (buttonValue != null && buttonValue.equals("Submit")){
//            addStory(user, storyText);
//        }

        if(buttonValue.equals("Add Wood")){
            user.addWood(500);
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
        loadStoriesIntoRequest(request);

        // Show the page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewstories.jsp"); //I don't want this to load directly to the JSP right now, but this works temporarily
        dispatcher.forward(request, response);

    }

    /**
     * Grab the username from the request and create a user model.
     */
    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        // If there is no user for some weird reason, just use anonymous.
        if (user == null) {
            user = new UserModel();
            user.createPlayer("anonymous",0,0,0,0);
        }

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
        // Before we go the page to display the stories, we need to get the stories.
        // And then shove the stories in to the request.
        loadStoriesIntoRequest(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewstories.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Retrieve all the stories and put them in the request.
     * We can then use then in the JSP file.
     *
     * @param request
     */
    private void loadStoriesIntoRequest(HttpServletRequest request) {
        ArrayList<StoryModel> storiesList = StoryDao.getStories();

        // We're going to convert the array list to an array because it works better in the JSP.
        StoryModel[] stories = storiesList.toArray(new StoryModel[storiesList.size()]);
        request.setAttribute("stories", stories);
    }

    /**
     * Save a story.
     */
    private void addStory(UserModel user, String storyText) {
        if (storyText != null && storyText.length() > 0 && user != null) {
            StoryDao.saveStory(UniqueIdDao.getID(), storyText, user.getPlayerName(), 0);
        }
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
