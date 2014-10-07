<%--
  Created by IntelliJ IDEA.
  User: Gon Choi
  Date: 10/5/2014
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
    <%
        if(((Boolean)request.getAttribute("deleteFailed")))
        {
    %>
    <b>The user cannot be deleted. Please try again.</b><br/><br/>
    <%
        }
    %>
    <form method="POST" action="<c:url value="/deleteUser?delete" />">
        Username: <br />
        <input type="text" name="usernameToDelete" /><br /><br />
        <input type="submit" value="delete" /><br /><br />
    </form>
    <form method="POST" action="<c:url value="/deleteUser?cancel" />">
        <input type="submit" value="cancel" /><br>
    </form>
</body>
</html>
