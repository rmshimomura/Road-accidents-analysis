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
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>

<c:forEach var="rodovia" items="${requestScope.rodovias}">

    <c:out value="${rodovia.nome}"/>

</c:forEach>

<form action="${pageContext.servletContext.contextPath}/" method="GET">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Voltar para o ínício</button>
</form>

</body>
</html>