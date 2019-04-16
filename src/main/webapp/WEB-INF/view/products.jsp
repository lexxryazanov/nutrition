<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Nutrition Data Base</title>
</head>
<body>
<h2>Welcome to Nutrition Database</h2>
<% if(!SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal().equals("anonymousUser")) {%>
<h3>Welcome, <%=SecurityContextHolder.getContext()
        .getAuthentication().getName()%>!</h3>
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
</form>
<%} else {%>
Please, <a href="/login">login!</a>
<%}%>
</body>
</html>
