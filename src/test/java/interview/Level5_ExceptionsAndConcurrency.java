package interview;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LEVEL 5 — Exceptions & Basic Concurrency
 *
 * Exception hierarchy, checked vs unchecked, custom exceptions,
 * and thread-safety topics that QA engineers hit when tests run in parallel.
 */
public class Level5_ExceptionsAndConcurrency {

    // ── Custom exceptions ───────────────────────────────────────────────────
    // Checked exception (caller must handle or declare)
    static class ElementNotFoundException extends Exception {
        ElementNotFoundException(String locator) {
            super("Element not found: " + locator);
        }
    }

    // Unchecked exception (extends RuntimeException — no need to declare)
    static class TestConfigException extends RuntimeException {
        TestConfigException(String message) {
            super("Config error: " + message);
        }
    }

    // ── Task 1: Exception chaining ──────────────────────────────────────────
    // Wrap a low-level exception in a meaningful test exception
    public static String readConfig(String key) {
        try {
            if (key == null) throw new NullPointerException("key is null");
            if (key.isBlank()) throw new IllegalArgumentException("key is blank");
            return "value_of_" + key;
        } catch (Exception e) {
            throw new TestConfigException("Failed to read key '" + key + "': " + e.getMessage());
        }
    }

    // ── Task 2: try-with-resources ─────────────────────────────────────────
    // Always close resources — interviewers ask "how do you handle resource leaks?"
    static class FakeDbConnection implements AutoCloseable {
        FakeDbConnection() { System.out.println("  DB connection opened"); }

        public String query(String sql) { return "result of: " + sql; }

        @Override
        public void close() { System.out.println("  DB connection closed automatically"); }
    }

    public static void tryWithResourcesDemo() {
        try (FakeDbConnection conn = new FakeDbConnection()) {
            System.out.println("  " + conn.query("SELECT 1"));
        } // close() called automatically even if an exception is thrown
    }

    // ── Task 3: finally vs return ──────────────────────────────────────────
    // Classic trap: what prints when there's both return and finally?
    public static String finallyTrap() {
        try {
            return "from try";     // this executes first...
        } finally {
            System.out.println("  finally always runs!");  // ...then this
            // if you put a RETURN here it would OVERRIDE the try return — big trap!
        }
    }

    // ── Task 4: Thread-safety with AtomicInteger ────────────────────────────
    // Without AtomicInteger, a shared counter in parallel tests produces wrong results.
    // AtomicInteger operations are atomic (compare-and-swap under the hood).
    static class TestCounter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() { count.incrementAndGet(); }
        public int get() { return count.get(); }
    }

    public static void atomicDemo() throws InterruptedException {
        TestCounter counter = new TestCounter();
        int threadCount = 10;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            pool.submit(() -> {
                counter.increment();
                latch.countDown();
            });
        }

        latch.await(); // wait for all threads to finish
        pool.shutdown();
        System.out.println("  Expected: 10,  Actual: " + counter.get()); // always 10
    }

    // ── Task 5: Callable + Future ───────────────────────────────────────────
    // Run a "test" in a thread pool and get the result back — pattern used
    // in parallel test frameworks (TestNG parallel="methods").
    public static void callableFutureDemo() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> testTask = () -> {
            Thread.sleep(100); // simulate test execution time
            return "Test passed on thread: " + Thread.currentThread().getName();
        };

        Future<String> future = executor.submit(testTask);
        System.out.println("  Doing other work while test runs...");
        System.out.println("  Result: " + future.get()); // blocks until done
        executor.shutdown();
    }

    // ── Task 6: volatile vs synchronized ───────────────────────────────────
    // volatile  → guarantees visibility (no caching in CPU registers)
    // synchronized → guarantees visibility + atomicity (only one thread at a time)
    static class Flag {
        // volatile ensures all threads see the latest value
        private volatile boolean running = true;

        public void stop() { running = false; }
        public boolean isRunning() { return running; }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== LEVEL 5: Exceptions & Concurrency ===\n");

        System.out.println("Task 1 - Custom exception + chaining:");
        try {
            System.out.println(readConfig("browser")); // ok
            System.out.println(readConfig(null));       // throws
        } catch (TestConfigException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        System.out.println("\nTask 2 - try-with-resources:");
        tryWithResourcesDemo();

        System.out.println("\nTask 3 - finally trap:");
        String result = finallyTrap();
        System.out.println("  Return value: " + result); // "from try"

        System.out.println("\nTask 4 - AtomicInteger (thread-safe counter):");
        atomicDemo();

        System.out.println("\nTask 5 - Callable + Future:");
        callableFutureDemo();

        System.out.println("\nTask 6 - volatile flag (explained in comments above)");
        Flag flag = new Flag();
        System.out.println("  isRunning: " + flag.isRunning()); // true
        flag.stop();
        System.out.println("  isRunning: " + flag.isRunning()); // false
    }
}
