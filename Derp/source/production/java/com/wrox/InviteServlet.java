package com.wrox;

/**
 * Created by Bernice Chen on 10/5/2014.
 */

import org.apache.commons.mail.SimpleEmail;

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
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("home");
            return;
        }
        if (session.getAttribute("username") == null) {
            session.invalidate();
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
        if(request.getParameter("cancel") != null)
        {
            response.sendRedirect("derp");
            return;
        }

        String emailAddress = request.getParameter("email");
        if(emailAddress != null )
        {
            String username = (String) session.getAttribute("username");

            try {
                SimpleEmail email = new SimpleEmail();
                email.setDebug(true);
                email.setHostName("localhost");
                email.setSmtpPort(2525);
                email.setSSLOnConnect(false);
                email.setFrom("donotreply@derp.com");
                email.setSubject("Join Derp!");
                email.setMsg(username + " would like to send you a Derp! Join Derp today!");
                email.addTo(emailAddress);
                email.send();
                response.sendRedirect("derp");
            }
            catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("derp");
            }
        }
        else
        {
            response.sendRedirect("derp");
        }
    }
}
