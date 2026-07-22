package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL 3 — OOP Concepts
 * Inheritance, interfaces, abstract classes, polymorphism — topics that
 * always come up in Java QA interviews alongside "tell me about OOP".
 *
 * Interview tip: be ready to explain WHY you chose interface vs abstract class.
 * Rule of thumb:
 *   Interface  → defines a CONTRACT (what something can do)
 *   Abstract   → defines a TEMPLATE  (what something IS, with shared behaviour)
 */
public class Level3_OOP {

    // ── Interfaces ──────────────────────────────────────────────────────────
    interface Describable {
        String describe(); // every test entity must be describable
    }

    interface Executable {
        void execute();
        default void logExecution() { // Java 8+ default method
            System.out.println("Executing: " + getClass().getSimpleName());
        }
    }

    // ── Abstract base class ─────────────────────────────────────────────────
    // Shared state + a template method — subclasses only fill in the gaps.
    static abstract class TestStep implements Describable, Executable {
        protected final String name;
        protected boolean passed = false;

        TestStep(String name) { this.name = name; }

        // Template Method pattern: skeleton stays here, details go in subclasses
        public final void run() {
            logExecution();
            execute();
            System.out.println("  Result: " + (passed ? "PASS" : "FAIL"));
        }

        @Override
        public String describe() { return "Step: " + name; }
    }

    // ── Concrete subclasses ─────────────────────────────────────────────────
    static class ClickStep extends TestStep {
        private final String locator;

        ClickStep(String locator) {
            super("Click on " + locator);
            this.locator = locator;
        }

        @Override
        public void execute() {
            System.out.println("  Clicking element: " + locator);
            passed = true; // simulate success
        }
    }

    static class AssertTextStep extends TestStep {
        private final String expected;
        private final String actual;

        AssertTextStep(String expected, String actual) {
            super("Assert text equals '" + expected + "'");
            this.expected = expected;
            this.actual = actual;
        }

        @Override
        public void execute() {
            System.out.println("  Expected: '" + expected + "'  Actual: '" + actual + "'");
            passed = expected.equals(actual);
        }
    }

    // ── Enum (very common interview topic) ─────────────────────────────────
    enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL;

        public boolean isHigherThan(Priority other) {
            return this.ordinal() > other.ordinal();
        }
    }

    // ── Generics ───────────────────────────────────────────────────────────
    // A simple generic pair — interviewers love asking "what are generics for?"
    static class Pair<A, B> {
        final A first;
        final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() { return "(" + first + ", " + second + ")"; }
    }

    // ── Singleton (classic interview pattern) ──────────────────────────────
    // Only one WebDriver instance per test run — classic use-case.
    static class DriverManager {
        private static DriverManager instance;
        private String browserName;

        private DriverManager() {}

        public static DriverManager getInstance() {
            if (instance == null) {
                instance = new DriverManager();
            }
            return instance;
        }

        public void setBrowser(String browser) { this.browserName = browser; }
        public String getBrowser() { return browserName; }
    }

    // ── Demo ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== LEVEL 3: OOP Concepts ===\n");

        // Polymorphism: same type, different behaviour
        List<TestStep> steps = new ArrayList<>();
        steps.add(new ClickStep("#login-button"));
        steps.add(new AssertTextStep("Welcome, Admin", "Welcome, Admin"));
        steps.add(new AssertTextStep("Dashboard", "Home")); // will fail

        System.out.println("--- Running test steps ---");
        for (TestStep step : steps) {
            step.run();
            System.out.println("  " + step.describe());
            System.out.println();
        }

        // Enum usage
        System.out.println("--- Priority ---");
        Priority p = Priority.HIGH;
        System.out.println(p + " is higher than MEDIUM: " + p.isHigherThan(Priority.MEDIUM)); // true
        System.out.println(p + " is higher than CRITICAL: " + p.isHigherThan(Priority.CRITICAL)); // false

        // Generics
        System.out.println("\n--- Pair ---");
        Pair<String, Integer> testCase = new Pair<>("Login test", 42);
        System.out.println(testCase); // (Login test, 42)

        // Singleton
        System.out.println("\n--- Singleton DriverManager ---");
        DriverManager dm1 = DriverManager.getInstance();
        DriverManager dm2 = DriverManager.getInstance();
        dm1.setBrowser("Chrome");
        System.out.println("Same instance: " + (dm1 == dm2));        // true
        System.out.println("Browser from dm2: " + dm2.getBrowser()); // Chrome
    }
}
