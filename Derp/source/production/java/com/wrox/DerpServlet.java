package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.commons.mail.*;

@WebServlet(
        name = "derpServlet",
        urlPatterns = {"/derp"}
)
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
)
public class DerpServlet extends HttpServlet
{
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
        if (session.getAttribute("username") == null) {
            session.invalidate();
            response.sendRedirect("home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/view/derp.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(request.getParameter("send") != null) {
            try {
                SimpleEmail email = new SimpleEmail();
                email.setDebug(true);
                email.setHostName("localhost");
                email.setSmtpPort(2525);
                email.setSSLOnConnect(false);
                email.setFrom((String) session.getAttribute("username"));
                email.setSubject("DERP!");
                email.setMsg("YOU'VE BEEN DERPED! YOU'RE WELCOME! :-)");
                email.addTo(request.getParameterValues("send"));
                email.send();
            } catch (Exception e) {
                response.sendRedirect("derp");
                System.out.println(e);
            }
        }
    }
}
