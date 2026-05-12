package com.todo.model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.util.DBConnection;
 
/** DAO — toda lógica de acesso ao banco fica aqui. */
public class TaskDAO {
 
    // ── Autenticação ──────────────────────────────────────────────────────
 
    public User findByCredentials(String username, String hashSenha)
            throws SQLException {
        String sql = "SELECT id, username FROM users " +
                     "WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, hashSenha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"));
                }
            }
        }
        return null;
    }
 
    // ── CRUD de Tarefas ───────────────────────────────────────────────────
 
    public List<Task> findByUser(int userId) throws SQLException {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task t = new Task();
                    t.setId(rs.getInt("id"));
                    t.setUserId(rs.getInt("user_id"));
                    t.setTitle(rs.getString("title"));
                    t.setDone(rs.getBoolean("done"));
                    t.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(t);
                }
            }
        }
        return list;
    }
 
    public void insert(int userId, String title) throws SQLException {
        String sql = "INSERT INTO tasks (user_id, title) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.executeUpdate();
        }
    }
 
    public void toggleDone(int taskId, int userId) throws SQLException {
        String sql = "UPDATE tasks SET done = NOT done " +
                     "WHERE id = ? AND user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }
 
    public void delete(int taskId, int userId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ? AND user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }
}

