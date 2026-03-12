package adapters;

import entities.Todo;
import java.util.List;

public class JsonHelper {

    public static String toJson(Todo t) {
        return String.format(
            "{\"id\":%d,\"title\":\"%s\","
            + "\"completed\":%b,\"created_at\":\"%s\"}",
            t.id, escape(t.title), t.completed, t.createdAt
        );
    }

    public static String toJsonArray(List<Todo> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(toJson(list.get(i)));
        }
        return sb.append("]").toString();
    }

    public static String parseTitle(String body) {
        int s = body.indexOf("\"title\"");
        if (s < 0) return "";
        int q1 = body.indexOf("\"", s + 7);
        int q2 = body.indexOf("\"", q1 + 1);
        return q1 < 0 || q2 < 0 ? "" : body.substring(q1 + 1, q2);
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
