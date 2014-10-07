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
        name = "HomeServlet",
        urlPatterns = "/home"
)
public class HomeServlet extends HttpServlet
{
    private Map<String, String> userDatabase = new Hashtable<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.userDatabase.put("Bernice", "password");
        this.userDatabase.put("Bernice@derp.com", "password");
        this.userDatabase.put("Gonchoi", "password");
        this.userDatabase.put("Gonchoi@derp.com", "password");
        this.userDatabase.put("gon", "gon");

        HttpSession session = request.getSession();
        session.setAttribute ("database", this.userDatabase);

        //TEST
        //System.out.println("HOME::password: " + ((Map<String, String>) session.getAttribute("database")).get("gon"));

        request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
