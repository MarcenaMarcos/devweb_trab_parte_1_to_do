/**
 * app.js — validações front-end e pequenos ajustes de DOM
 *
 * Cache: este arquivo é estático; configure o servidor para enviar:
 *   Cache-Control: public, max-age=86400
 */
 
// ── Validação do formulário de login ──────────────────────────────────────
const loginForm = document.getElementById('loginForm');
if (loginForm) {
  loginForm.addEventListener('submit', function (e) {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;
 
    if (!username) {
      e.preventDefault();
      showAlert('O campo Usuário não pode estar vazio.', 'error');
      return;
    }
 
    if (password.length < 4) {
      e.preventDefault();
      showAlert('A senha deve ter pelo menos 4 caracteres.', 'error');
      return;
    }
 
    // Sanitização básica: rejeita caracteres perigosos
    const dangerous = /[<>"'%;()&+]/;
    if (dangerous.test(username)) {
      e.preventDefault();
      showAlert('O usuário contém caracteres inválidos.', 'error');
    }
  });
}
 
// ── Validação do formulário de adicionar tarefa ───────────────────────────
const addForm = document.getElementById('addForm');
if (addForm) {
  addForm.addEventListener('submit', function (e) {
    const title = document.getElementById('newTask').value.trim();
    if (!title) {
      e.preventDefault();
      alert('Escreva uma tarefa antes de adicionar.');
    }
  });
}
 
// ── Utilitário de alerta ─────────────────────────────────────────────────
function showAlert(msg, type) {
  // Remove alerta anterior, se existir
  const old = document.querySelector('.alert.js-alert');
  if (old) old.remove();
 
  const div = document.createElement('div');
  div.className = `alert alert-${type} js-alert`;
  div.textContent = msg;
 
  const form = document.querySelector('form');
  form.parentNode.insertBefore(div, form);
}

