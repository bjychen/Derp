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
        if(request.getSession().getAttribute("username") == null)
        {
            request.setAttribute("addFailed", false);
            request.setAttribute("addExist", false);
            response.sendRedirect("derp");
            return;
        }

        request.setAttribute("addFailed", false);
        request.setAttribute("addExist", false);
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
            response.sendRedirect("derp");
            return;
        }

        /** All Unchecked warning suppress help from:
         *  http://stackoverflow.com/questions/509076/how-do-i-address-unchecked-cast-warnings
         **/
        if(request.getParameter("add") != null)
        {
            String addUser = (String) request.getAttribute("usernameToAdd");
            @SuppressWarnings("unchecked")
            Map<String, String> userDB = (Map<String, String>) session.getAttribute("database");
            @SuppressWarnings("unchecked")
            Map<String, String> currentUserFriends = (Map<String, String>) session.getAttribute("friends");

            if(userDB.containsKey(addUser)){
                request.setAttribute("addFailed", true);
                request.setAttribute("addExist", false);
                response.sendRedirect("addUser");
                return;
            }
            else if (currentUserFriends.containsKey(addUser)){
                request.setAttribute("addFailed", false);
                request.setAttribute("addExist", true);
                response.sendRedirect("addUser");
                return;
            }
            else{
                currentUserFriends.put(addUser, userDB.get(addUser));
                request.setAttribute("addFailed", false);
                request.setAttribute("addExist", false);
                response.sendRedirect("derp");
                return;
            }
        }

        request.setAttribute("addFailed", false);
        request.setAttribute("addExist", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/addUser.jsp")
                .forward(request, response);
    }
}
