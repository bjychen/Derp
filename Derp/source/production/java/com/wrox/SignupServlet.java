package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(
        name = "signupServlet",
        urlPatterns = "/signup"
)
public class SignupServlet extends HttpServlet {
    private Map<String, String> userDatabase = new Hashtable<>();
    private Map<String, String> friends = new Hashtable<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        this.userDatabase = (Map<String, String>) session.getAttribute ("database");

        session.setAttribute("database", this.userDatabase);
        session.setAttribute("friends", this.friends);
        if(request.getParameter("cancel") != null)
        {
            response.sendRedirect("home");
            return;
        }
        request.setAttribute("signupFailed", false);

        request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("cancel") != null) {
            response.sendRedirect("home");
            return;
        }

        this.userDatabase = (Map<String, String>) session.getAttribute ("database");
        session.setAttribute("friends", this.friends);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != "" && password != "" && !this.userDatabase.containsKey(username)) {
            this.userDatabase.put(username, password);
            session.setAttribute("database", this.userDatabase);
            session.setAttribute("username", username);
            request.changeSessionId();

            //TESTING
            System.out.println("SIGNUP::username: " + session.getAttribute("username"));
            System.out.println("SIGNUP::password: " + password);

            response.sendRedirect("derp");
        }

        else {
            request.setAttribute("signupFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp")
                    .forward(request, response);
        }

    }
}
