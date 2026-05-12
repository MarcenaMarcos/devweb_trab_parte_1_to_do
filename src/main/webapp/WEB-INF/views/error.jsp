<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Erro — To-Do MVC</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/static/style.css">
</head>
<body class="login-page">
  <div class="card">
    <h1>Ops! Algo deu errado.</h1>
    <p class="subtitle">
      Erro interno do servidor. Tente novamente mais tarde.
    </p>
    <a href="${pageContext.request.contextPath}/tasks"
       class="btn btn-primary">Voltar</a>
  </div>
</body>
</html>

