package tests;

import java.net.*;
import java.net.http.*;

public class TodoApiTest {
    static final String BASE = "http://localhost:18080";
    static final HttpClient CLIENT = HttpClient.newHttpClient();

    static HttpResponse<String> get(String path) throws Exception {
        var req = HttpRequest.newBuilder()
            .uri(URI.create(BASE + path))
            .GET().build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
    }

    static HttpResponse<String> post(String path, String body)
            throws Exception {
        var req = HttpRequest.newBuilder()
            .uri(URI.create(BASE + path))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
    }

    static HttpResponse<String> put(String path, String body)
            throws Exception {
        var req = HttpRequest.newBuilder()
            .uri(URI.create(BASE + path))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(body))
            .build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
    }

    static HttpResponse<String> delete(String path) throws Exception {
        var req = HttpRequest.newBuilder()
            .uri(URI.create(BASE + path))
            .DELETE().build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
    }
}
