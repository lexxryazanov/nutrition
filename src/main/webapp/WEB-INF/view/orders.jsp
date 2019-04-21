<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String userName = SecurityContextHolder.getContext().getAuthentication().getName(); %>
<html>
<head>
    <title>Nutrition Data Base</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="/resources/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" charset="utf8" src="/resources/js/jquery.dataTables.js"></script>

    <script>
        $(document).ready(function () {
            $('#orders').DataTable({
                bFilter: false,
                "ajax": {
                    url: "/api/order/<%=userName%>",
                    dataSrc: "",
                },
                columns: [
                    {title: "", data: function (data) {
                            return data.productId === null ? '+' : '?';
                        }},
                    {title: "Name", data: 'name'},
                    {title: "Calorie", data: 'calorie'},
                    {title: "Fats", data: 'fats'},
                    {title: "Proteins", data: 'proteins'},
                    {title: "Carbohydrates", data: 'carbohydrates'},
                    {title: "VendorID", data: 'companyId'},
                    {title: "Vendor Name", data: 'companyName'},
                    {title: "Status", data: 'state'}
                ]
            });
        });
    </script>
</head>
<body>
<h2>Welcome to Nutrition Database</h2>
<di class="user-header">
    <table>
        <tr>
            <% if (!userName.equals("anonymousUser")) {%>
            <td>
                <div>You're logged in as <%=userName%>
                </div>
            </td>
            <td>
                <div><a href="/">[Products]</a></div>
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

<div width="100%">
    <div class="orders-table">
        <table id="orders" class="display">
        </table>
    </div>
</div>
</body>
</html>
