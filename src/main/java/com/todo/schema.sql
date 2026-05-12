-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS todo_db
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 
USE todo_db;
 
-- Tabela de usuários
CREATE TABLE users (
  id       INT          PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50)  UNIQUE NOT NULL,
  password VARCHAR(64)  NOT NULL  -- hash SHA-256
);
 
-- Tabela de tarefas
CREATE TABLE tasks (
  id         INT          PRIMARY KEY AUTO_INCREMENT,
  user_id    INT          NOT NULL,
  title      VARCHAR(200) NOT NULL,
  done       BOOLEAN      DEFAULT FALSE,
  created_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
 
-- Usuário de teste (senha: hash da senha 123456)
INSERT INTO users (username, password) VALUES
  ('admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
