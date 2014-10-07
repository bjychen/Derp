<%--
  Created by IntelliJ IDEA.
  User: Gon Choi
  Date: 10/5/2014
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <%
        if(((Boolean)request.getAttribute("addFailed")))
        {
    %>
    <b>The user does not exist. Please try a different user.</b><br/><br/>
    <%
        }
    %>
    <%
        if(((Boolean)request.getAttribute("addExist")))
        {
    %>
    <b>The user is already added. Please try a different user.</b><br/><br/>
    <%
        }
    %>
    <%
        if(((Boolean)request.getAttribute("sameUser")))
        {
    %>
    <b>Silly Rabbit, you can't add yourself, Trix are for kids! <br>
    Please try a different user.</b><br/><br/>
    <%
        }
    %>

    <form method="POST" action="<c:url value="/addUser?add" />">
        Username: <br />
        <input type="email" name="usernameToAdd" /><br /><br />
        <input type="submit" value="add" /><br /><br />
    </form>
    <form method="POST" action="<c:url value="/addUser?cancel" />">
        <input type="submit" value="cancel" /><br>
    </form>
</body>
</html>
