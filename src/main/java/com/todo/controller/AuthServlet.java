package com.todo.controller;
 
import com.todo.model.TaskDAO;
import com.todo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
 
@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthServlet extends HttpServlet {
 
    private final TaskDAO dao = new TaskDAO();
 
    // GET /login → exibe o formulário de login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        if ("/logout".equals(req.getServletPath())) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
 
        // Lê cookie de "lembrar usuário"
        String savedUser = "";
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if ("savedUsername".equals(c.getName())) {
                    savedUser = c.getValue();
                }
            }
        }
        req.setAttribute("savedUsername", savedUser);
 
        // Cache: página de login não deve ser cacheada
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
 
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
 
    // POST /login → valida credenciais
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        String username  = req.getParameter("username");
        String password  = req.getParameter("password");
        String remember  = req.getParameter("remember"); // "on" ou null
 
        try {
            String hash = sha256(password);
            User user   = dao.findByCredentials(username, hash);
 
            if (user == null) {
                req.setAttribute("error", "Usuário ou senha inválidos.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                return;
            }
 
            // Cria sessão e armazena usuário logado
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedUser", user);
            session.setMaxInactiveInterval(30 * 60); // 30 minutos
 
            // Cookie "lembrar usuário" (7 dias) ou remove
            Cookie cookie = new Cookie("savedUsername",
                                       "on".equals(remember) ? username : "");
            cookie.setMaxAge("on".equals(remember) ? 7 * 24 * 3600 : 0);
            cookie.setHttpOnly(true);
            cookie.setPath(req.getContextPath() + "/");
            resp.addCookie(cookie);
 
            resp.sendRedirect(req.getContextPath() + "/tasks");
 
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
    }
 
    private String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
