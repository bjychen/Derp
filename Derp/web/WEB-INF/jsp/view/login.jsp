<!DOCTYPE html>
<html>
    <head>
        <title>Derp Login</title>
    </head>
    <body>
        <h2>Login</h2>
        Log in to send your friends a Derp!<br /><br />
        <%
            if(((Boolean)request.getAttribute("loginFailed")))
            {
                %>
        <b>The username or password you entered are not correct. Please try
            again.</b><br /><br />
                <%
            }
        %>
        <form method="POST" action="<c:url value="/login" />">
            Username<br />
            <input type="email" name="username" /><br /><br />
            Password<br />
            <input type="password" name="password" /><br /><br />
            <input type="submit" value="Log In" /><br /><br />
        </form>
        <form method="POST" action="<c:url value="/login?cancel" />">
            <input type="submit" value="cancel" /><br>
        </form>
    </body>
</html>
