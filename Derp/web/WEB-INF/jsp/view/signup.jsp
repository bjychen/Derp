<%--
  Created by IntelliJ IDEA.
  User: Bernice Chen
  Date: 10/5/2014
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <h2>Create an Account</h2>
    <form method="POST" action="<c:url value="/signup" />">
        Username<br />
        <input type="text" name="username" /><br /><br />
        Password<br />
        <input type="password" name="password" /><br /><br />
        <input type="submit" value="Sign Up" /><br /><br />
    </form>
    <form method="POST" action="<c:url value="/home" />">
        <input type="submit" value="Cancel" /><br>
    </form>
</body>
</html>
