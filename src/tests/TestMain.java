package tests;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class TestMain {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Unit Tests ===");
        runUnit();
        System.out.println("=== Functional Tests ===");
        runFunctional();
    }

    static void runUnit() {
        try {
            TodoUseCaseTest.testCreate();
            TodoUseCaseTest.testGetAll();
            TodoUseCaseTest.testGetById();
            TodoUseCaseTest.testUpdate();
            TodoDeleteTest.testDelete();
            System.out.println("All unit tests passed.");
        } catch (AssertionError e) {
            System.err.println("FAIL: " + e.getMessage());
            System.exit(1);
        }
    }

    static void runFunctional() throws Exception {
        HttpServer srv = TodoApiRunnerTest.startServer();
        Thread.sleep(100);
        try {
            TodoApiRunnerTest.testGetAll();
            TodoApiRunnerTest.testCreate();
            TodoApiRunnerTest.testGetNotFound();
            TodoApiWriteTest.testUpdate();
            TodoApiWriteTest.testDelete();
            TodoApiWriteTest.testDeleteNotFound();
            System.out.println("All functional tests passed.");
        } finally {
            srv.stop(0);
        }
    }
}
