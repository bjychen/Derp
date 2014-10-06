<%--
  Created by IntelliJ IDEA.
  User: Gon Choi
  Date: 10/5/2014
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  %>
<html>
<head>
    <title>DERP Main Page</title>
</head>
<body>
    <a href="<c:url value="/addUser" />">Add Friend</a><br>
    <a href="<c:url value="#" />">Delete Friends</a><br>
    <a href="<c:url value="/invite" />">Invite Friends</a><br>
    <a href="<c:url value="/derp?logout" />">Logout</a>
</body>
</html>
