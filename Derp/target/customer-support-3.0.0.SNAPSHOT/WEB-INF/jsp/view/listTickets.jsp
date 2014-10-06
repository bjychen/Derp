<%@ page import="java.util.Map" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Ticket> ticketDatabase =
            (Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Derp</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>Click on a name to send a Derp!</h2>
        <a href="<c:url value="/tickets">
            <c:param name="action" value="create" />
        </c:url>">Bernice</a><br /><br />
        <%
            if(ticketDatabase.size() == 0)
            {
                %><i>This is where the usernames will be.</i><%
            }
            else
            {
                for(int id : ticketDatabase.keySet())
                {
                    String idString = Integer.toString(id);
                    Ticket ticket = ticketDatabase.get(id);
                    %>Ticket #<%= idString %>: <a href="<c:url value="/tickets">
                        <c:param name="action" value="view" />
                        <c:param name="ticketId" value="<%= idString %>" />
                    </c:url>"><%= ticket.getSubject() %></a> (customer:
        <%= ticket.getCustomerName() %>)<br /><%
                }
            }
        %>

        <br>
        <a href="<c:url value="/invite">
            <c:param name="action" value="invite" />
        </c:url>">Invite</a><br /><br />
    </body>
</html>
