package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(
        name = "HomeServlet",
        urlPatterns = "/home"
)
public class HomeServlet extends HttpServlet
{
    private Map<String, String> userDatabase = new Hashtable<>();
    private Map<String, List> friendList = new Hashtable<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.userDatabase.put("bernice@derp.com", "password");
        this.userDatabase.put("gon@derp.com", "password");
        this.userDatabase.put("john@derp.com", "password");
        this.userDatabase.put("jane@derp.com", "password");
        this.userDatabase.put("billy@derp.com", "password");
        this.userDatabase.put("jill@derp.com", "password");

        this.friendList.put("bernice@derp.com", new ArrayList<String>(Arrays.asList("gon@derp.com", "john@derp.com")));
        this.friendList.put("gon@derp.com", new ArrayList<String>(Arrays.asList("bernice@derp.com", "jane@derp.com")));
        this.friendList.put("john@derp.com", new ArrayList<String>(Arrays.asList("gon@derp.com", "bernice@derp.com")));
        this.friendList.put("jane@derp.com", new ArrayList<String>(Arrays.asList("john@derp.com", "gon@derp.com")));
        this.friendList.put("bob@derp.com", new ArrayList<String>(Arrays.asList("jane@derp.com", "john@derp.com")));
        this.friendList.put("billy@derp.com", new ArrayList<String>(Arrays.asList("jill@derp.com", "jane@derp.com")));
        this.friendList.put("jill@derp.com", new ArrayList<String>(Arrays.asList("john@derp.com", "billy@derp.com")));

        HttpSession session = request.getSession(true);
        session.setAttribute ("database", this.userDatabase);
        session.setAttribute ("friends", this.friendList);

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
