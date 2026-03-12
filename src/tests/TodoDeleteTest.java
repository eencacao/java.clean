package tests;

public class TodoDeleteTest {

    static void testDelete() {
        var uc = TodoUseCaseTest.makeUC();
        uc.create("bye");
        assert  uc.delete(1) : "delete ok";
        assert !uc.delete(1) : "second delete fails";
        System.out.println("PASS testDelete");
    }
}
