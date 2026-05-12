# devweb_trab_parte_1_to_do

# 📝 To-Do List MVC - Java Web

Projeto de Gerenciador de Tarefas desenvolvido em Java utilizando o padrão **MVC (Model-View-Controller)**, servlets, JSP e integração com banco de dados MySQL.

---

## 🚀 Funcionalidades

- **Autenticação:** Sistema de login com validação de credenciais.
- **Segurança:** Filtro (`AuthFilter`) que impede o acesso às tarefas sem login ativo.
- **Gerenciamento de Tarefas (CRUD):**
  - Listagem de tarefas específicas por usuário.
  - Adição de novas tarefas.
  - Alternância de status (Concluída/Pendente).
  - Exclusão de tarefas.
- **Persistência:** Integração com banco de dados MySQL via JDBC.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Jakarta Servlet & JSP** (Interface Web)
* **Apache Tomcat 10.1** (Servidor de Aplicação)
* **Maven** (Gerenciamento de Dependências)
* **MySQL** (Banco de Dados)
* **JDBC** (Conexão com Banco)

---

## 🏗️ Estrutura do Projeto (MVC)

* **Model:** Classes `User`, `Task` e `TaskDAO` (Lógica de dados e regras de negócio).
* **View:** Arquivos `.jsp` em `WEB-INF/views/` (Interface do usuário).
* **Controller:** `AuthServlet`, `TaskServlet` e `AuthFilter` (Controle de rotas e segurança).
* **Util:** `DBConnection` (Fábrica de conexões).

---

## ⚙️ Como rodar o projeto

1.  **Banco de Dados:**
    - Crie um banco chamado `todo_db` (ou o nome configurado no seu `DBConnection.java`).
    - Execute os scripts SQL para criar as tabelas `users` e `tasks`.
2.  **Configuração da Conexão:**
    - No arquivo `src/main/java/com/todo/util/DBConnection.java`, ajuste seu usuário e senha do MySQL.
3.  **Execução:**
    - Configure o servidor Tomcat no seu IDE (NetBeans/IntelliJ).
    - Execute o projeto e acesse: `http://localhost:8080/to_do_mvc/login`

---

## 👥 Contribuintes

* Marcos Marcena
* Caio Nascimento
* Jonathan Matos
