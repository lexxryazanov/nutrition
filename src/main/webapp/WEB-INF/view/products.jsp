<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nutrition Data Base</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/> "/>
</head>
<body>
<h2>Welcome to Nutrition Database</h2>
<di class="user-header">
    <table>
        <tr>
            <% if (!SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal().equals("anonymousUser")) {%>
            <td>
                <div>You're logged in as <%=SecurityContextHolder.getContext()
                        .getAuthentication().getName()%>
                </div>
            </td>
            <td>
                <div><a href="/orders">[My orders]</a></div>
            </td>
            <td>
                <div><a href="/logout">[Logout]</a></div>
            </td>
            <%} else {%>
            <td>
                <div><a href="/login">[Login]</a></div>
            </td>
            <%}%>
        </tr>
    </table>
</di>
</body>
</html>
