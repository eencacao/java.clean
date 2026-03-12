package tests;

public class TodoApiWriteTest {

    static void testUpdate() throws Exception {
        var body = "{\"title\":\"updated\",\"completed\":true}";
        var res  = TodoApiTest.put("/todos/1", body);
        assert res.statusCode() == 200 : "PUT /todos/1 want 200";
        assert res.body().contains("updated") : "want updated title";
        System.out.println("PASS PUT /todos/1");
    }

    static void testDelete() throws Exception {
        var res = TodoApiTest.delete("/todos/1");
        assert res.statusCode() == 204 : "DELETE /todos/1 want 204";
        System.out.println("PASS DELETE /todos/1");
    }

    static void testDeleteNotFound() throws Exception {
        var res = TodoApiTest.delete("/todos/1");
        assert res.statusCode() == 404 : "DELETE /todos/1 again want 404";
        System.out.println("PASS DELETE /todos/1 not found");
    }
}
