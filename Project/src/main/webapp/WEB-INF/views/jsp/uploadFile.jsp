<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@include file="../templates/head.jsp" %>
  <title>Processo de carga</title>
</head>
<body>
<div class="container">
  <H1>Processo de carga</H1>
    <form action="${pageContext.servletContext.contextPath}/upload/analisar" method="post" enctype="multipart/form-data">
      <H2>Selecione o arquivo</H2>
      <input type="file" name="arquivo" />
      <input type="submit" value="Enviar" />
      <br>
      <H3>Selecione o tipo de arquivo</H3>
      <select id="tipoArquivo" name="tipoArquivo">
        <option value="rodovias">Arquivo de Rodovias</option>
        <option value="acidentes">Arquivo de Acidentes</option>
      </select>

    </form>
  <button class="btn btn-primary" type="submit">diferente!</button>

  <form action="${pageContext.servletContext.contextPath}/" method="GET">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Voltar para o ínício</button>
  </form>

</div>

<%@include file="../templates/scripts.jsp" %>
</body>
</html>