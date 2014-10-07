<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Gon Choi
  Date: 10/5/2014
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DERP Main Page</title>
</head>
<body>
    <b> Hello <%=(String) session.getAttribute("username")%></b><br>
    <%
        @SuppressWarnings("unchecked")
        Map<String, String> friendsList = (Map<String, String>) session.getAttribute("friends");
        @SuppressWarnings("unchecked")
        List<String> friends = new ArrayList<>(friendsList.keySet());

        for (String afriend : friends) { %>
    <form method="POST" action="<c:url value="/derp?send=<%=afriend%>" />">
        <input type="submit" value=<%=afriend%> /><br>
    </form>
     <%  } %>
    <a href="<c:url value="/addUser" />">Add Friend</a><br>
    <a href="<c:url value="/deleteUser" />">Delete Friends</a><br>
    <a href="<c:url value="/invite" />">Invite Friends</a><br>
    <a href="<c:url value="/derp?logout" />">Logout</a>
</body>
</html>
