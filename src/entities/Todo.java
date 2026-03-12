package entities;

import java.time.Instant;

public class Todo {
    public final int id;
    public final String title;
    public final boolean completed;
    public final String createdAt;

    public Todo(int id, String title, boolean completed, String createdAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    public static Todo create(int id, String title) {
        return new Todo(id, title, false, Instant.now().toString());
    }

    public Todo withUpdate(String title, boolean completed) {
        return new Todo(this.id, title, completed, this.createdAt);
    }
}
