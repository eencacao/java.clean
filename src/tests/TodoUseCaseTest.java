package tests;

import infrastructure.MemoryRepo;
import usecases.TodoWriteUseCase;

public class TodoUseCaseTest {

    public static TodoWriteUseCase makeUC() {
        return new TodoWriteUseCase(new MemoryRepo());
    }

    static void testCreate() {
        var uc   = makeUC();
        var todo = uc.create("buy milk");
        assert todo.id == 1 : "id should be 1";
        assert "buy milk".equals(todo.title) : "wrong title";
        assert !todo.completed : "should not be completed";
        System.out.println("PASS testCreate");
    }

    static void testGetAll() {
        var uc = makeUC();
        assert uc.getAll().isEmpty() : "should start empty";
        uc.create("a");
        uc.create("b");
        assert uc.getAll().size() == 2 : "should have 2";
        System.out.println("PASS testGetAll");
    }

    static void testGetById() {
        var uc = makeUC();
        uc.create("find me");
        assert uc.getById(1).isPresent() : "should find id=1";
        assert uc.getById(99).isEmpty()  : "id=99 not found";
        System.out.println("PASS testGetById");
    }

    static void testUpdate() {
        var uc = makeUC();
        uc.create("old");
        var r = uc.update(1, "new", true);
        assert r.isPresent()                : "update ok";
        assert "new".equals(r.get().title)  : "title updated";
        assert r.get().completed            : "completed=true";
        assert uc.update(99, "x", false).isEmpty() : "99 not found";
        System.out.println("PASS testUpdate");
    }
}
