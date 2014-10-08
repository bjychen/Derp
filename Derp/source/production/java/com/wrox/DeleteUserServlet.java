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
        name = "DeleteUserServlet",
        urlPatterns = "/deleteUser"
)
public class DeleteUserServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("username") == null)
        {
            request.setAttribute("deleteFailed", false);
            response.sendRedirect("derp");
            return;
        }
        if (session.getAttribute("username") == null) {
            session.invalidate();
            response.sendRedirect("home");
            return;
        }

        request.setAttribute("deleteFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/deleteUser.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(request.getParameter("cancel") != null)
        {
            request.setAttribute("deleteFailed", false);
            response.sendRedirect("derp");
            return;
        }

        if(request.getParameter("delete") != null)
        {
            String deleteUser = request.getParameter("usernameToDelete");

            @SuppressWarnings("unchecked")
            Map<String, String> userDB = (Map<String, String>) session.getAttribute("database");
            @SuppressWarnings("unchecked")
            Map<String, List> friendslist = (Map<String, List>) session.getAttribute("friends");
            @SuppressWarnings("unchecked")
            ArrayList<String> currentFriends = (ArrayList<String>) friendslist.get((String) session.getAttribute("username"));

            if (currentFriends.contains(deleteUser)) {
                currentFriends.remove(deleteUser);
                friendslist.replace((String) session.getAttribute("username"), currentFriends);
                session.setAttribute("friends", friendslist);
                request.setAttribute("deleteFailed", false);
                response.sendRedirect("derp");
                return;
            }
            else{
                request.setAttribute("deleteFailed", true);
                request.getRequestDispatcher("/WEB-INF/jsp/view/deleteUser.jsp")
                        .forward(request, response);
                return;
            }
        }

        request.setAttribute("deleteFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/deleteUser.jsp")
                .forward(request, response);
    }
}
