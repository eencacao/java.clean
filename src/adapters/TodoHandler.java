package adapters;

import com.sun.net.httpserver.HttpExchange;
import entities.Todo;
import java.io.IOException;
import java.util.Optional;

@SuppressWarnings("restriction")
public class TodoHandler extends TodoReadHandler {

    public TodoHandler(usecases.TodoWriteUseCase uc) { super(uc); }

    public void handle(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path   = ex.getRequestURI().getPath();
        if (path.matches("/todos/?")) {
            if ("GET".equals(method))       handleGetAll(ex);
            else if ("POST".equals(method)) handlePost(ex);
            else HttpHelper.send(ex, 405, "{\"error\":\"not allowed\"}");
        } else {
            int id = HttpHelper.extractId(path);
            if (id < 0) {
                HttpHelper.send(ex, 400, "{\"error\":\"invalid id\"}");
                return;
            }
            handleByMethod(ex, method, id);
        }
    }

    private void handleByMethod(HttpExchange ex, String m, int id)
            throws IOException {
        if ("GET".equals(m)) {
            handleGet(ex, id);
        } else if ("PUT".equals(m)) {
            String body  = HttpHelper.readBody(ex);
            String t     = JsonHelper.parseTitle(body);
            boolean done = HttpHelper.parseCompleted(body);
            Optional<Todo> r = uc.update(id, t, done);
            if (r.isEmpty())
                HttpHelper.send(ex, 404, "{\"error\":\"not found\"}");
            else
                HttpHelper.send(ex, 200, JsonHelper.toJson(r.get()));
        } else if ("DELETE".equals(m)) {
            if (!uc.delete(id))
                HttpHelper.send(ex, 404, "{\"error\":\"not found\"}");
            else
                HttpHelper.send(ex, 204, "");
        } else {
            HttpHelper.send(ex, 405, "{\"error\":\"not allowed\"}");
        }
    }
}
