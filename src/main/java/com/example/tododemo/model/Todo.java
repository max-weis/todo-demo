package com.example.tododemo.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duedate", nullable = false)
    private Instant dueDate;

    @Column(name = "done", nullable = false)
    private boolean done;

    public Todo() {
    }

    public Todo(String title, String description, Instant dueDate, boolean done) throws Exception {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
    }

    public Todo(int id, String title, String description, Instant dueDate, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toJSON() {
        try {
            // Zeit muss korrekt konvertiert werden, sonst gibt es Fehler bei den Tests
            ObjectMapper om = new ObjectMapper();
            om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}