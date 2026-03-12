package infrastructure;

import entities.Todo;
import interfaces.TodoRepository;
import java.util.*;

public class MemoryRepo implements TodoRepository {
    private final Map<Integer, Todo> store = new LinkedHashMap<>();
    private int counter = 0;

    public List<Todo> getAll() { return new ArrayList<>(store.values()); }

    public Optional<Todo> getById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public Todo save(String title) {
        int id = ++counter;
        Todo todo = Todo.create(id, title);
        store.put(id, todo);
        return todo;
    }

    public Optional<Todo> update(int id, String title, boolean done) {
        if (!store.containsKey(id)) return Optional.empty();
        Todo updated = store.get(id).withUpdate(title, done);
        store.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(int id) {
        return store.remove(id) != null;
    }
}
