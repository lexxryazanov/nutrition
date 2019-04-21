<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String userName = SecurityContextHolder.getContext().getAuthentication().getName();%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
    <script type="text/javascript" charset="utf8" src="/resources/js/jquery-1.12.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#button-create').click(function () {
                var payload = {
                    productId: ${product.getId()},
                    userName: "<%=userName%>"
                };
                $("#order-form").serializeArray().forEach(function (item) {
                    payload[item.name] = item.value;
                });
                $.ajax({
                    type: "POST",
                    url: "/api/order",
                    data: JSON.stringify(payload),
                    dataType: "json",
                    contentType: "application/json",
                    success: function () {
                        window.location.replace('/orders');
                    }
                });
            });
        });
    </script>

</head>
<body>
<div class="edit-product">
    <div class="title">Product Details</div>
    <form id="order-form" action="/api/order/">
        <table>
            <tr>
                <td>Name</td>
                <td><input id="property-name" type="text" name="name" value="${product.getName()}"/></td>
            </tr>
            <tr>
                <td>Calorie</td>
                <td><input id="property-calorie" type="text" name="calorie" value="${product.getCalorie()}"/></td>
            </tr>
            <tr>
                <td>Fats</td>
                <td><input id="property-fats" type="text" name="fats" value="${product.getFats()}"/></td>
            </tr>
            <tr>
                <td>Proteins</td>
                <td><input id="property-proteins" type="text" name="proteins" value="${product.getProteins()}"/></td>
            </tr>
            <tr>
                <td>Carbohydrates</td>
                <td><input id="property-carbohydrates" type="text" name="carbohydrates" value="${product.getCarbohydrates()}"/></td>
            </tr>
            <tr>
                <td>Vendor</td>
                <td><input id="property-company" type="text" name="companyName" value="${product.getCompany().getName()}"/></td>
            </tr>
        </table>
    </form>
        <div class="buttons">
            <button id="button-create">Create Order</button>
        </div>
</div>
</body>
</html>