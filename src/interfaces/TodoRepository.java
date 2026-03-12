package interfaces;

import entities.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    List<Todo> getAll();
    Optional<Todo> getById(int id);
    Todo save(String title);
    Optional<Todo> update(int id, String title, boolean completed);
    boolean delete(int id);
}
