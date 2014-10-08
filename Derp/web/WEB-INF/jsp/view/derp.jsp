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
        ArrayList<String> currentFriends = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Map<String, List> friendslist = (Map<String, List>) session.getAttribute("friends");
        if(friendslist != null) {
            currentFriends = (ArrayList<String>) friendslist.get((String) session.getAttribute("username"));
        }

        if(currentFriends != null) {
            for (String afriend : currentFriends) {
                 String queryString = "/derp?send="+ afriend;   %>
                <form method="POST" action="<c:url value="<%=queryString%>" />">
                    <input type="submit" value="<%=afriend%>" /><br>
                </form>
     <%     }
        }%>
    <a href="<c:url value="/addUser" />">Add Friend</a><br>
    <a href="<c:url value="/deleteUser" />">Delete Friends</a><br>
    <a href="<c:url value="/invite" />">Invite Friends</a><br>
    <a href="<c:url value="/derp?logout" />">Logout</a>
</body>
</html>
