package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "AddUserServlet",
        urlPatterns = "/addUser"
)
public class AddUserServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("username") == null)
        {
            request.setAttribute("addFailed", false);
            request.setAttribute("addExist", false);
            request.setAttribute("sameUser", false);
            response.sendRedirect("derp");
            return;
        }
        if (session.getAttribute("username") == null) {
            session.invalidate();
            response.sendRedirect("home");
            return;
        }

        request.setAttribute("addFailed", false);
        request.setAttribute("addExist", false);
        request.setAttribute("sameUser", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(request.getParameter("cancel") != null)
        {
            request.setAttribute("addFailed", false);
            request.setAttribute("addExist", false);
            request.setAttribute("sameUser", false);
            response.sendRedirect("derp");
            return;
        }

        /** All Unchecked warning suppress help from:
         *  http://stackoverflow.com/questions/509076/how-do-i-address-unchecked-cast-warnings
         **/
        if(request.getParameter("add") != null)
        {
            String addUser = request.getParameter("usernameToAdd");
            @SuppressWarnings("unchecked")
            Map<String, String> userDB = (Map<String, String>) session.getAttribute("database");
            @SuppressWarnings("unchecked")
            Map<String, List> friendslist = (Map<String, List>) session.getAttribute("friends");
            @SuppressWarnings("unchecked")
            ArrayList<String> currentFriends = (ArrayList<String>) friendslist.get((String) session.getAttribute("username"));

            if(currentFriends == null){
                currentFriends = new ArrayList<>();
                friendslist.put((String) session.getAttribute("username"), currentFriends);
            }

            if(userDB != null) {
                if(!userDB.containsKey(addUser)){
                    request.setAttribute("addFailed", true);
                    request.setAttribute("addExist", false);
                    request.setAttribute("sameUser", false);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                            .forward(request, response);
                    return;
                }
                else if (currentFriends.contains(addUser)){
                    request.setAttribute("addFailed", false);
                    request.setAttribute("addExist", true);
                    request.setAttribute("sameUser", false);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                            .forward(request, response);
                    return;
                }
                else if (addUser.equals(session.getAttribute("username"))){
                    request.setAttribute("addFailed", false);
                    request.setAttribute("addExist", false);
                    request.setAttribute("sameUser", true);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                            .forward(request, response);
                    return;
                }
                else{
                    currentFriends.add(addUser);
                    friendslist.replace((String) session.getAttribute("username"), currentFriends);
                    request.setAttribute("addFailed", false);
                    request.setAttribute("addExist", false);
                    request.setAttribute("sameUser", false);
                    session.setAttribute("friends", friendslist);
                    response.sendRedirect("derp");
                    return;
                }
            }
        }

        request.setAttribute("addFailed", false);
        request.setAttribute("addExist", false);
        request.setAttribute("sameUser", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                .forward(request, response);
    }
}
