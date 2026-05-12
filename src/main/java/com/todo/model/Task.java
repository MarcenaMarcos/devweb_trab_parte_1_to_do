package com.todo.model;
 
import java.sql.Timestamp;
 
/** JavaBean que representa uma tarefa. */
public class Task {
    private int       id;
    private int       userId;
    private String    title;
    private boolean   done;
    private Timestamp createdAt;
 
    public Task() {}
 
    // Getters e Setters
    public int       getId()               { return id; }
    public void      setId(int id)         { this.id = id; }
 
    public int       getUserId()           { return userId; }
    public void      setUserId(int uid)    { this.userId = uid; }
 
    public String    getTitle()            { return title; }
    public void      setTitle(String t)    { this.title = t; }
 
    public boolean   isDone()              { return done; }
    public void      setDone(boolean d)    { this.done = d; }
 
    public Timestamp getCreatedAt()        { return createdAt; }
    public void      setCreatedAt(Timestamp ts) { this.createdAt = ts; }
}
