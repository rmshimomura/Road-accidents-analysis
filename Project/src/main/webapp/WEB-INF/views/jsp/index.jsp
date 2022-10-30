<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../templates/head.jsp" %>
    <title>Road accidents analysis</title>
</head>
<body>
<div class="container">
    <form action="${pageContext.servletContext.contextPath}/upload" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Enviar arquivo... (.csv)</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/rodovias" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Ver rodovias</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/acidentes" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Ver acidentes</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/veiculos" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Estatísticas sobre veículos</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/casualidades" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Estatísticas sobre casualidades</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/trechos" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Informações sobre trechos</button>
    </form>

</div>

<%@include file="../templates/scripts.jsp" %>
</body>
</html>