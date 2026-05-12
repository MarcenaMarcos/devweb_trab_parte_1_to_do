package com.todo.controller;
 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
 
/**
 * Filtro de segurança: bloqueia qualquer acesso a /tasks/*
 * sem usuário autenticado na sessão.
 */
@WebFilter("/tasks/*")
public class AuthFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest  req  = (HttpServletRequest)  request;
        HttpServletResponse resp = (HttpServletResponse) response;
 
        HttpSession session = req.getSession(false);
        boolean loggedIn    = session != null &&
                              session.getAttribute("loggedUser") != null;
 
        if (loggedIn) {
            chain.doFilter(request, response); // permite continuar
        } else {
            // Redireciona para o login com mensagem
            resp.sendRedirect(req.getContextPath() +
                              "/login?msg=Faça+login+para+continuar");
        }
    }
}

