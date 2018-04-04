package ui;

import datalayer.UserDao;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        String username = request.getParameter("username");

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
                    ArrayList<String> names = new ArrayList<String>(Arrays.asList("Wood","Grass","Apples",
                            "Stone","Copper","Copper Bars","Baked Apples","Gold","Gold Bars","Fish",
                            "Fish and Apples"));
                    HashMap tempInventory = new HashMap();
                    for(int i = 0; i < names.size(); i++){
                        tempInventory.put(names.get(i), 0);
                    }
                    user.createPlayer(username, 10,50,50,
                            9, "00", "AM",1, 50, 50);
                    user.setPlayerInventory(tempInventory);
                    UserDao.saveUser(user);
                }

                // Or log in
                else if (buttonValue != null && buttonValue.equals("Continue Your Journey")) {
                    user = UserDao.getUser(username);
                }
            }
            request.getSession().setAttribute("username", user.getPlayerName());
            request.getSession().setAttribute("user", user);
        }

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the main page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/mainScreen");
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
