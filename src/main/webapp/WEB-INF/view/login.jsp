<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login Page</title>
</head>
<body>
<c:if test="${param.error != null}">
    <label>Invalid user name or password</label>
</c:if>
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Login"/></div>
</form>
<a href="/registration">Add new user</a>
</body>
</html>