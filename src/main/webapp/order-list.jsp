<%@ page import="com.huipengpay.demo.Order" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单明细</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css"/>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th>订单ID</th>
        <th>订单标题</th>
        <th>创建时间</th>
        <th>支付渠道</th>
        <th>订单金额</th>
        <th>订单状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%
        Collection<Order> data = (Collection<Order>) request.getAttribute("data");
        for (Order item : data) {
    %>
    <tr>
        <td><%=item.getOrderNumber()%>
        </td>
        <td><%=item.getOrderSubject()%>
        </td>
        <td><%=item.getCreateOn()%>
        </td>
        <td><%=item.getPayInterface()%>
        </td>
        <td><%=item.getAmount()%>
        </td>
        <td><%=item.getStatus()%>
        </td>
        <td>
            <a href="/order-query/<%=item.getOrderNumber()%>">订单状态更新</a>&nbsp;
            <a href="/jump/<%=item.getOrderNumber()%>" target="_blank">跳转</a>
        </td>
    </tr>
    <% }%>
    </tbody>
</table>
</body>
</html>