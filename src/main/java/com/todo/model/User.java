package com.todo.model;
 
/** JavaBean que representa um usuário do sistema. */
public class User {
    private int    id;
    private String username;
    private String password; // hash SHA-256
 
    public User() {}
 
    public User(int id, String username) {
        this.id       = id;
        this.username = username;
    }
 
    // Getters e Setters
    public int    getId()       { return id; }
    public void   setId(int id) { this.id = id; }
 
    public String getUsername()            { return username; }
    public void   setUsername(String u)    { this.username = u; }
 
    public String getPassword()            { return password; }
    public void   setPassword(String p)    { this.password = p; }
}

