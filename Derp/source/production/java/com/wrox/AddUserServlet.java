package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            Map<String, String> currentUserFriends = (Map<String, String>) session.getAttribute(session.getAttribute("username") + "friends");

            if(userDB != null) {
                if(!userDB.containsKey(addUser)){
                    request.setAttribute("addFailed", true);
                    request.setAttribute("addExist", false);
                    request.setAttribute("sameUser", false);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                            .forward(request, response);
                    return;
                }
                else if (currentUserFriends.containsKey(addUser)){
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
                    //System.out.println("AddUser::addUser: " + addUser);
                    //System.out.println("AddUser::username: " + session.getAttribute("username"));
                    currentUserFriends.put(addUser, userDB.get(addUser));
                    request.setAttribute("addFailed", false);
                    request.setAttribute("addExist", false);
                    request.setAttribute("sameUser", false);
                    session.setAttribute(session.getAttribute("username") + "friends", currentUserFriends);
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
