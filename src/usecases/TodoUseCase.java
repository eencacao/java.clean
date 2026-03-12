package usecases;

import entities.Todo;
import interfaces.TodoRepository;
import java.util.List;
import java.util.Optional;

public class TodoUseCase {
    protected final TodoRepository repo;

    public TodoUseCase(TodoRepository repo) { this.repo = repo; }

    public List<Todo> getAll() { return repo.getAll(); }

    public Optional<Todo> getById(int id) { return repo.getById(id); }

    public Todo create(String title) { return repo.save(title); }
}
