<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Register New User</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/> "/>
</head>
<body>
<div class="credentials">
    <form action="/registration" method="post">
        <div class="error">
            ${message}
        </div>
        <table>
            <tr>
                <td>
                    <div>Login</div>
                </td>
                <td>
                    <div><input type="text" name="username"/></div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Password</div>
                </td>
                <td>
                    <div><input type="password" name="password"/></div>
                </td>
            </tr>
        </table>
        <div class="login-button"><input type="submit" value="Register"/></div>
    </form>
    <div class="page-link">
        Back to the <a href="/login">login page</a>
    </div>
    <div class="page-link">
        Back to the <a href="/">main page</a>
    </div>
</div>
</body>
</html>