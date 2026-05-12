<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Minhas Tarefas — To-Do MVC</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/static/style.css">
</head>
<body class="tasks-page">
  <header class="topbar">
    <h1>📋 Minhas Tarefas</h1>
    <span>Olá, <strong>${sessionScope.loggedUser.username}</strong></span>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline">
      Sair
    </a>
  </header>
 
  <main class="container">
    <!-- Formulário: adicionar tarefa -->
    <form id="addForm"
          action="${pageContext.request.contextPath}/tasks/add"
          method="post"
          class="add-form">
      <input type="text" id="newTask" name="title"
             placeholder="Nova tarefa..." required maxlength="200">
      <button type="submit" class="btn btn-primary">Adicionar</button>
    </form>
 
    <!-- Lista de tarefas -->
    <ul class="task-list">
      <c:forEach var="task" items="${tasks}">
        <li class="task-item ${task.done ? 'done' : ''}">
 
          <!-- Marcar como concluída -->
          <form action="${pageContext.request.contextPath}/tasks/done"
                method="post" class="inline-form">
            <input type="hidden" name="id" value="${task.id}">
            <button type="submit" class="btn-check"
                    title="${task.done ? 'Desmarcar' : 'Concluir'}">
              ${task.done ? '✅' : '⬜'}
            </button>
          </form>
 
          <span class="task-title">${task.title}</span>
 
          <!-- Excluir tarefa -->
          <form action="${pageContext.request.contextPath}/tasks/delete"
                method="post" class="inline-form">
            <input type="hidden" name="id" value="${task.id}">
            <button type="submit" class="btn-delete" title="Excluir">🗑</button>
          </form>
        </li>
      </c:forEach>
 
      <c:if test="${empty tasks}">
        <li class="empty-state">Nenhuma tarefa ainda. Adicione uma acima!</li>
      </c:if>
    </ul>
  </main>
 
  <script src="${pageContext.request.contextPath}/static/app.js"></script>
</body>
</html>
