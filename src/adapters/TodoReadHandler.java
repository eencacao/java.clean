package adapters;

import com.sun.net.httpserver.HttpExchange;
import entities.Todo;
import usecases.TodoWriteUseCase;
import java.io.IOException;
import java.util.Optional;

@SuppressWarnings("restriction")
class TodoReadHandler {
    protected final TodoWriteUseCase uc;

    TodoReadHandler(TodoWriteUseCase uc) { this.uc = uc; }

    void handleGet(HttpExchange ex, int id) throws IOException {
        Optional<Todo> t = uc.getById(id);
        if (t.isEmpty()) {
            HttpHelper.send(ex, 404, "{\"error\":\"not found\"}");
        } else {
            HttpHelper.send(ex, 200, JsonHelper.toJson(t.get()));
        }
    }

    void handleGetAll(HttpExchange ex) throws IOException {
        HttpHelper.send(ex, 200, JsonHelper.toJsonArray(uc.getAll()));
    }

    void handlePost(HttpExchange ex) throws IOException {
        String body  = HttpHelper.readBody(ex);
        String title = JsonHelper.parseTitle(body);
        if (title.isEmpty()) {
            HttpHelper.send(ex, 400, "{\"error\":\"title required\"}");
            return;
        }
        HttpHelper.send(ex, 201, JsonHelper.toJson(uc.create(title)));
    }
}
