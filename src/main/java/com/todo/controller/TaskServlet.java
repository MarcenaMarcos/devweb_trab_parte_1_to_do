package com.todo.controller;
 
import com.todo.model.Task;
import com.todo.model.TaskDAO;
import com.todo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
@WebServlet("/tasks/*")
public class TaskServlet extends HttpServlet {
 
    private final TaskDAO dao = new TaskDAO();
 
    // GET /tasks → lista tarefas do usuário logado
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        User user = (User) req.getSession().getAttribute("loggedUser");
 
        // Cache: página dinâmica sensível — nunca cachear
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
 
        try {
            List<Task> tasks = dao.findByUser(user.getId());
            req.setAttribute("tasks", tasks);
            req.getRequestDispatcher("/WEB-INF/views/tasks.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
 
    // POST /tasks/add | /tasks/done | /tasks/delete
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        User user     = (User) req.getSession().getAttribute("loggedUser");
        String action = req.getPathInfo(); // "/add", "/done", "/delete"
 
        try {
            switch (action == null ? "" : action) {
                case "/add" -> {
                    String title = req.getParameter("title");
                    if (title != null && !title.isBlank()) {
                        dao.insert(user.getId(), title.trim());
                    }
                }
                case "/done" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    dao.toggleDone(id, user.getId());
                }
                case "/delete" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    dao.delete(id, user.getId());
                }
            }
            resp.sendRedirect(req.getContextPath() + "/tasks");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
