<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login — To-Do MVC</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/static/style.css">
</head>
<body class="login-page">
  <div class="card">
    <h1>To-Do MVC</h1>
    <p class="subtitle">Entre com suas credenciais</p>
 
    <!-- Mensagem de erro do Controller -->
    <c:if test="${not empty error}">
      <div class="alert alert-error">${error}</div>
    </c:if>
    <c:if test="${not empty param.msg}">
      <div class="alert alert-info">${param.msg}</div>
    </c:if>
 
    <form id="loginForm"
          action="${pageContext.request.contextPath}/login"
          method="post">
 
      <label for="username">Usuário</label>
      <input type="text" id="username" name="username"
             value="${savedUsername}" required autocomplete="username">
 
      <label for="password">Senha</label>
      <input type="password" id="password" name="password"
             required autocomplete="current-password">
 
      <label class="checkbox-label">
        <input type="checkbox" name="remember"
               ${"on".equals(savedUsername) ? "" : ""}> Lembrar usuário
      </label>
 
      <button type="submit" class="btn btn-primary">Entrar</button>
    </form>
  </div>
 
  <script src="${pageContext.request.contextPath}/static/app.js"></script>
</body>
</html>

