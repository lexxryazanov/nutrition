<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String userName = SecurityContextHolder.getContext().getAuthentication().getName();%>
<html>
<head>
    <title>Nutrition Data Base</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="/resources/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" charset="utf8" src="/resources/js/jquery.dataTables.js"></script>

    <script>
        var keyword = "";
        $(document).ready(function () {
            $('#edit-links').hide();
            var tProducts = $('#products').DataTable({
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
                    {title: "Vendor", data: 'company.name'}
                ]
            });
            $('#products tbody').on('click', 'tr', function () {
                var data = tProducts.row(this).data();
                $.ajax({
                    url: "/api/product/" + data.id,
                }).done(function (json) {
                    $('#edit-links').show();
                    $('#edit-link').attr("href", "/products/edit?id=" + json.id);
                    $('#add-link').attr("href", "/products/add?id=" + json.id);
                    $('#property-name').text(json.name);
                    $('#property-calorie').text(json.calorie);
                    $('#property-fats').text(json.fats);
                    $('#property-proteins').text(json.proteins);
                    $('#property-carbohydrates').text(json.carbohydrates);
                    $('#property-company').text(json.company.name);
                });
            });

            $('#search_id').keypress(function (event) {
                if (event.which == 13) {
                    keyword = $(this).val();
                    tProducts.ajax.reload();
                }
            })
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

<div width="100%">
    <div class="products-table">
        <table id="products" class="display">
        </table>
    </div>
    <div class="product-details">
        <div>Product Details</div>
        <table>
            <tr>
                <td>Name</td>
                <td id="property-name">...</td>
            </tr>
            <tr>
                <td>Calorie</td>
                <td id="property-calorie">...</td>
            </tr>
            <tr>
                <td>Fats</td>
                <td id="property-fats">...</td>
            </tr>
            <tr>
                <td>Proteins</td>
                <td id="property-proteins">...</td>
            </tr>
            <tr>
                <td>Carbohydrates</td>
                <td id="property-carbohydrates">...</td>
            </tr>
            <tr>
                <td>Vendor</td>
                <td id="property-company">...</td>
            </tr>
        </table>
        <% if (!userName.equals("anonymousUser")) { %>
        <div id="edit-links">
            <a id="edit-link">[Edit product]</a>
            <a id="add-link">[Add new product]</a>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
