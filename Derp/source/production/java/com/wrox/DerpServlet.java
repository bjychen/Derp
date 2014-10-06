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

    private volatile int USER_ID_SEQUENCE = 1;
    private Map<Integer, User> userDatabase = new LinkedHashMap<>();

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

        if (request.getParameter("add") != null ||
                request.getAttribute("delete") != null) {
            response.sendRedirect("derp");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/view/derp.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
        {
            action = "list";
        }

        switch(action)
        {
            case "add":
                //this.showTicketForm(request, response);
                try {
                    SimpleEmail email = new SimpleEmail();
                    email.setDebug(true);
                    email.setHostName("localhost");
                    email.setSmtpPort(2525);
                    //email.setAuthenticator(new DefaultAuthenticator("Bernice", "password"));
                    email.setSSLOnConnect(false);
                    email.setFrom("marroquincraig@gmail.com");
                    email.setSubject("TestMail");
                    email.setMsg("This is a test mail ... :-)");
                    email.addTo("bjychen10@gmail.com");
                    email.send();
                }
                catch (Exception e) {
                    System.out.println(e);
                }


                break;
            case "delete":
                //this.viewTicket(request, response);
                break;
            case "invite":
                //this.downloadAttachment(request, response);
                break;
            case "list":
            default:
                //this.listTickets(request, response);
                break;
        }
    }
}
