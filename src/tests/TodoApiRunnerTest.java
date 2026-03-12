package tests;

import com.sun.net.httpserver.HttpServer;
import infrastructure.MemoryRepo;
import usecases.TodoWriteUseCase;
import adapters.TodoHandler;
import adapters.HttpHelper;
import java.net.InetSocketAddress;

@SuppressWarnings("restriction")
public class TodoApiRunnerTest {

    static HttpServer startServer() throws Exception {
        MemoryRepo       repo    = new MemoryRepo();
        TodoWriteUseCase uc      = new TodoWriteUseCase(repo);
        TodoHandler      handler = new TodoHandler(uc);
        HttpServer       server  = HttpServer.create(
            new InetSocketAddress(18080), 0
        );
        server.createContext("/todos", ex -> {
            try { handler.handle(ex); }
            catch (Exception e) {
                HttpHelper.send(ex, 500, "{\"error\":\"server error\"}");
            }
        });
        server.start();
        return server;
    }

    static void testGetAll() throws Exception {
        var res = TodoApiTest.get("/todos");
        assert res.statusCode() == 200 : "GET /todos want 200";
        assert res.body().contains("[]") : "want empty array";
        System.out.println("PASS GET /todos");
    }

    static void testCreate() throws Exception {
        var res = TodoApiTest.post("/todos", "{\"title\":\"test\"}");
        assert res.statusCode() == 201 : "POST /todos want 201";
        assert res.body().contains("test") : "want title in response";
        System.out.println("PASS POST /todos");
    }

    static void testGetNotFound() throws Exception {
        var res = TodoApiTest.get("/todos/99");
        assert res.statusCode() == 404 : "GET /todos/99 want 404";
        System.out.println("PASS GET /todos/99 not found");
    }
}
