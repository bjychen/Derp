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
    private static final Map<String, String> userDatabase = new Hashtable<>();

    static {
        userDatabase.put("Bernice", "password");
        userDatabase.put("Gon", "password");
        userDatabase.put("gon", "gon");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view/signup.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("cancel") != null) {
            response.sendRedirect("home");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null && !SignupServlet.userDatabase.containsKey(username)) {
            userDatabase.put(username, password);
            request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
                    .forward(request, response);
        }
    }
}
