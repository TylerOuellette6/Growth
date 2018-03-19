package ui;

import datalayer.UserDao;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class WelcomeServlet extends javax.servlet.http.HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The post method is called by the browser when the user presses the button
     *
     * @param request The request has info on filled in fields and button presses.
     * @param response We use this to give the browser a response.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = null;

        // Load data from the request
        String buttonValue = request.getParameter("button");
        String username=request.getParameter("username");

//        if(username == "" && buttonValue != ""){
//            RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }


        if (buttonValue.equals("Begin Your Journey") || buttonValue.equals("Continue Your Journey")) {
            username = request.getParameter("username");
            if (username.equals("")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }
            else if (!(username.equals(""))){
                // Create an account
                if (buttonValue != null && buttonValue.equals("Begin Your Journey")) {
                    user = new UserModel();
                    ArrayList tempInventory = new ArrayList();
                    for(int i = 0; i < 11; i++) {
                        tempInventory.add(0);
                    }
                    user.setPlayerName(username);
                    user.setLevelNum(1);
                    user.setEnergy(50);
                    user.setHealth(50);
                    user.setCurrentXP(0);
                    user.setInventory(tempInventory);
                    UserDao.saveUser(user);
                }

                // Or log in
                else if (buttonValue != null && buttonValue.equals("Continue Your Journey")) {
                    user = UserDao.getUser(username);
                    //THIS CODE NEEDS TO READ FROM A FILE, NOT JUST GRAB THE DATA
                    user.getLevelNum();
                    user.getEnergy();
                    user.getHealth();
                    user.getCurrentXP();
                }
            }
        }


//        // Or by anonymous
//        else if (buttonValue != null && buttonValue.equals("Be Anonymous")){
//            user = new UserModel();
//            user.setPlayerName("anonymous");
//            UserDao.saveUser(user);
//        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the stories page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewStories");
        dispatcher.forward(request, response);
    }

    /**
     * The get method is invoked when the user goes to the page by browser URI.
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
        dispatcher.forward(request, response);
    }

}
