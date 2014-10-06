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
<form method="POST" action="<c:url value="/deleteUser" />">
    Username: <br />
    <input type="text" name="username" /><br /><br />
    <input type="submit" value="Delete" /><br /><br />
</form>
<form method="POST" action="<c:url value="/derp" />">
    <input type="submit" value="Cancel" /><br>
</form>
</body>
</html>
