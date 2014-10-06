package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.mail.*;

@WebServlet(
        name = "derpServlet",
        urlPatterns = {"/derp"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
)
public class DerpServlet extends HttpServlet
{

    private volatile int USER_ID_SEQUENCE = 0;
    private Map<Integer, User> currentUserFriends = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if (request.getParameter("logout") != null) {
            session.invalidate();
            response.sendRedirect("home");
            return;
        }

        /**if (request.getParameter("add") != null ||
                request.getAttribute("delete") != null) {
            response.sendRedirect("derp");
            return;
        }**/

        session.setAttribute("friends", this.currentUserFriends);

        request.getRequestDispatcher("/WEB-INF/jsp/view/derp.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        User user = new User();

        int id=0;

        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        if(action == null)
        {
            action = "list";
        }

        switch(action)
        {
            case "send":
                try {
                    SimpleEmail email = new SimpleEmail();
                    email.setDebug(true);
                    email.setHostName("localhost");
                    email.setSmtpPort(2525);
                    email.setSSLOnConnect(false);
                    email.setFrom("yo@der.p");
                    email.setSubject("DERP!");
                    email.setMsg("YOU'VE BEEN DERPED! YOU'RE WELCOME! :-)");
                    email.addTo("Gon+Bernice@gmail.com");
                    email.send();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "add":
                if (!currentUserFriends.containsValue(user)) {
                    synchronized (this) {
                        id = this.USER_ID_SEQUENCE++;
                        this.currentUserFriends.put(id, user);
                    }
                    session.setAttribute("friends", this.currentUserFriends);
                }
                break;
            case "delete":
                if (currentUserFriends.containsValue(user)) {
                    for (Map.Entry entry : currentUserFriends.entrySet()){
                        if (entry.equals(user)){
                            id = (int) entry.getKey();
                        }
                    }
                    synchronized (this) {
                        this.USER_ID_SEQUENCE--;
                        this.currentUserFriends.remove(id);
                    }
                }
                break;
            case "list":
            default:
                //this.listTickets(request, response);
                break;
        }
    }
}
