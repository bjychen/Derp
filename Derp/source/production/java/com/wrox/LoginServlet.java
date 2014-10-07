package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet
{
    private Map<String, String> userDatabase = new Hashtable<>();
    private Map<String, String> friends = new Hashtable<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        this.userDatabase = (Map<String, String>) session.getAttribute("database");
        session.setAttribute("friends", this.friends);

        if(request.getParameter("logout") != null)
        {
            session.invalidate();
            response.sendRedirect("login");
            return;
        }
        else if(session.getAttribute("username") != null)
        {
            response.sendRedirect("derp");
            return;
        }

        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
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

        this.userDatabase = (Map<String, String>) session.getAttribute("database");
        session.setAttribute("friends", this.friends);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || password == null ||
                !this.userDatabase.containsKey(username) ||
                !password.equals(this.userDatabase.get(username)))
        {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                   .forward(request, response);
        }
        else
        {
            session.setAttribute("username", username);
            request.changeSessionId();

            //TESTING
            System.out.println("LOGIN::username: " + session.getAttribute("username"));
            System.out.println("LOGIN::password: " + password);

            response.sendRedirect("derp");
        }
    }
}
