<%--
  Created by IntelliJ IDEA.
  User: Bernice Chen
  Date: 10/5/2014
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invite</title>
    <h2>Invite your Friends to Derp!</h2>
</head>
<body>
    <form method="POST" action="<c:url value="/invite" />">
        Email: <br />
        <input type="email" name="email" /><br /><br />
        <input type="submit" value="invite" /><br /><br />
    </form>
    <form method="POST" action="<c:url value="/invite?cancel" />">
        <input type="submit" value="cancel" /><br>
    </form>
</body>
</html>
