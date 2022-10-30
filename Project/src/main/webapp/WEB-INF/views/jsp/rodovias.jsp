<%--
  Created by IntelliJ IDEA.
  User: Rodrigo
  Date: 10/29/2022
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../templates/head.jsp" %>
    <title>Listagem de rodovias</title>
</head>
<body>

<table class="table .table-dark">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">UF</th>
        <th scope="col">Nome da rodovia</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="rodovia" items="${rodovias}">
        <tr>
        <th scope="row">${rodovia.id}</th>
        <td>${rodovia.UF}</td>
        <td>${rodovia.nome}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="${pageContext.servletContext.contextPath}/" method="GET">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Voltar para o ínício</button>
</form>

</body>
</html>