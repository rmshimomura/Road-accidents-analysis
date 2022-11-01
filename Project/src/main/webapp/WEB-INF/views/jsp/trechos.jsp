<%--
  Created by IntelliJ IDEA.
  User: Felipe
  Date: 10/30/2022
  Time: 12:07 AM
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
    <th scope="col">ID do trecho</th>
    <th scope="col">KM inicial</th>
    <th scope="col">KM final</th>
    <th scope="col">Data da avaliação</th>
    <th scope="col">ICC</th>
    <th scope="col">ICP</th>
    <th scope="col">ICM</th>
  </tr>
  </thead>
  <tbody>
    <c:forEach var="trecho" items="${trechos}">
        <tr>
        <th scope="row">${trecho.id}</th>
        <td>${trecho.kmInicial}</td>
        <td>${trecho.kmFinal}</td>
        <td>${trecho.dataAvaliacao}</td>
        <td>${trecho.ICC}</td>
        <td>${trecho.ICP}</td>
        <td>${trecho.ICM}</td>
        </tr>
    </c:forEach>

  </tbody>
</table>

<form action="${pageContext.servletContext.contextPath}/" method="GET">
  <button class="btn btn-lg btn-primary btn-block" type="submit">Voltar para o ínício</button>
</form>

</body>
</html>