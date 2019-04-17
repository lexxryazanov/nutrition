<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nutrition Data Base</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/> "/>
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="/resources/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" charset="utf8" src="/resources/js/jquery.dataTables.js"></script>

    <script>
        var keyword = "";

        $(document).ready(function () {
            $('#search_id').keypress(function (event) {
                if (event.which == 13) {
                    keyword = $(this).val();
                    table.ajax.reload();
                }
            })
            var table = $('#products').DataTable({
                bFilter: false,
                "ajax": {
                    url: "/api/product",
                    dataSrc: "",
                    data: {
                        keyword: function () {
                            return keyword;
                        },
                    }
                },
                columns: [
                    {title: "Name", data: 'name'},
                    {title: "Calorie", data: 'calorie'},
                    {title: "Fats", data: 'fats'},
                    {title: "Proteins", data: 'proteins'},
                    {title: "Carbohydrates", data: 'carbohydrates'},
                    {title: "Vendor", data: 'company.name'}
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

<div class="search">
    Search for product: <input id="search_id" type="search">
</div>

<table id="products" class="display">
    <tr>
        <th>Name</th>
        <th>Savings</th>
    </tr>
</table>

</body>
</html>
