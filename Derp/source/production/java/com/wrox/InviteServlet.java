package com.wrox;

/**
 * Created by Bernice Chen on 10/5/2014.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "InviteServlet",
        urlPatterns = "/invite"
)
public class InviteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/view/invite.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("cancel") != null)
        {
            response.sendRedirect("home");
            return;
        }

        if(session.getAttribute("username") != null)
        {
            response.sendRedirect("tickets");
            return;
        }

        String emailAddress = request.getParameter("email");
        if(emailAddress == null )
        {
            /*
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                    .forward(request, response);
                    */
        }
        else
        {
            //session.setAttribute("username", username);
            //request.changeSessionId();
            response.sendRedirect("tickets");
        }
    }
}
