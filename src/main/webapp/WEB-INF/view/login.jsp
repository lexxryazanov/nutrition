<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/> "/>
</head>
<body>
<div class="credentials">
    <c:if test="${param.error != null}">
        <div class="error">
            Invalid user name or password
        </div>
    </c:if>
    <div>
        <form action="/login" method="post">
            <table>
                <tr>
                    <td>
                        <div>Login:</div>
                    </td>
                    <td>
                        <div>
                            <div><input type="text" name="username"/></div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>Password:</div>
                    </td>
                    <td>
                        <div><input type="password" name="password"/></div>
                    </td>
                </tr>
            </table>
            <div class="login-button"><input type="submit" value="Login"/></div>
        </form>
    </div>
    <div class="page-link">
        Register a <a href="/registration">new user</a>
    </div>
    <div class="page-link">
        Back to the <a href="/">main page</a>
    </div>
</div>
</body>
</html>