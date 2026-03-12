package adapters;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("restriction")
public class HttpHelper {

    public static void send(HttpExchange ex, int code, String body)
            throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json");
        ex.sendResponseHeaders(code, bytes.length);
        ex.getResponseBody().write(bytes);
        ex.getResponseBody().close();
    }

    public static String readBody(HttpExchange ex) throws IOException {
        InputStream is = ex.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    public static int extractId(String path) {
        String[] parts = path.split("/");
        if (parts.length < 3) return -1;
        try { return Integer.parseInt(parts[2]); }
        catch (NumberFormatException e) { return -1; }
    }

    public static boolean parseCompleted(String body) {
        return body.contains("\"completed\":true");
    }
}
