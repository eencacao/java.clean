package usecases;

import entities.Todo;
import interfaces.TodoRepository;
import java.util.Optional;

public class TodoWriteUseCase extends TodoUseCase {

    public TodoWriteUseCase(TodoRepository repo) { super(repo); }

    public Optional<Todo> update(int id, String t, boolean done) {
        return repo.update(id, t, done);
    }

    public boolean delete(int id) { return repo.delete(id); }
}
