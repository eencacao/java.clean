package adapters;

import com.sun.net.httpserver.HttpServer;
import infrastructure.MemoryRepo;
import usecases.TodoWriteUseCase;
import java.io.IOException;
import java.net.InetSocketAddress;

@SuppressWarnings("restriction")
public class Main {
    public static void main(String[] args) throws IOException {
        MemoryRepo       repo    = new MemoryRepo();
        TodoWriteUseCase uc      = new TodoWriteUseCase(repo);
        TodoHandler      handler = new TodoHandler(uc);
        HttpServer       server  = HttpServer.create(
            new InetSocketAddress(8080), 0
        );
        server.createContext("/todos", ex -> {
            try { handler.handle(ex); }
            catch (Exception e) {
                HttpHelper.send(ex, 500, "{\"error\":\"server error\"}");
            }
        });
        server.start();
        System.out.println("Listening on :8080");
    }
}
