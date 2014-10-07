package com.wrox;

import org.apache.commons.mail.SimpleEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "DeleteUserServlet",
        urlPatterns = "/deleteUser"
)
public class DeleteUserServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("derp");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/view/deleteUser.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if(request.getParameter("cancel") != null)
        {
            response.sendRedirect("derp");
            return;
        }

       /** if(action == null)
        {
            action = "list";
        }

        switch(action)
        {
            case "delete":

                break;
            case "list":
            default:
                //this.listTickets(request, response);
                break;
        }**/
    }
}
