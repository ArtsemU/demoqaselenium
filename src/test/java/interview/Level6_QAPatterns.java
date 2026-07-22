package interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LEVEL 6 — QA-Specific Java Patterns
 *
 * These tasks combine core Java with patterns you use every day as a QA:
 * Page Object data, test data builders, parameterisation, response parsing.
 *
 * Interview tip: when asked "show me some code you write at work",
 * these patterns are exactly what you should reach for.
 */
public class Level6_QAPatterns {

    // ── Pattern 1: Builder — construct complex test data cleanly ───────────
    // "How do you build test data in your tests?" → Builder pattern
    static class User {
        private final String username;
        private final String email;
        private final String role;
        private final boolean active;

        private User(Builder b) {
            this.username = b.username;
            this.email    = b.email;
            this.role     = b.role;
            this.active   = b.active;
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', email='%s', role='%s', active=%s}",
                    username, email, role, active);
        }

        static class Builder {
            private String username = "default_user";
            private String email    = "default@test.com";
            private String role     = "viewer";
            private boolean active  = true;

            Builder username(String v) { this.username = v; return this; }
            Builder email(String v)    { this.email    = v; return this; }
            Builder role(String v)     { this.role     = v; return this; }
            Builder inactive()         { this.active   = false; return this; }

            User build() { return new User(this); }
        }
    }

    // ── Pattern 2: Data-Driven table parsing ──────────────────────────────
    // Simulate parsing a web table (like you do with Selenium WebElement rows)
    static class TableRow {
        final String name;
        final int    age;
        final String dept;

        TableRow(String name, int age, String dept) {
            this.name = name; this.age = age; this.dept = dept;
        }

        @Override
        public String toString() {
            return String.format("[%s | %d | %s]", name, age, dept);
        }
    }

    public static List<TableRow> parseTable(List<List<String>> rawRows) {
        return rawRows.stream()
                .map(row -> new TableRow(row.get(0), Integer.parseInt(row.get(1)), row.get(2)))
                .collect(Collectors.toList());
    }

    public static List<TableRow> filterByDept(List<TableRow> rows, String dept) {
        return rows.stream()
                .filter(r -> r.dept.equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }

    // ── Pattern 3: Simple JSON-like response validator ─────────────────────
    // Simulate what you do after a RestAssured call: validate fields in a Map
    static class ApiResponse {
        private final Map<String, Object> body;
        private final int statusCode;

        ApiResponse(int statusCode, Map<String, Object> body) {
            this.statusCode = statusCode;
            this.body       = body;
        }

        public void assertStatus(int expected) {
            if (statusCode != expected)
                throw new AssertionError("Status: expected " + expected + " but got " + statusCode);
            System.out.println("  Status " + statusCode + " — OK");
        }

        public void assertField(String key, Object expected) {
            Object actual = body.get(key);
            if (!Objects.equals(actual, expected))
                throw new AssertionError("Field '" + key + "': expected '" + expected + "' but got '" + actual + "'");
            System.out.println("  Field '" + key + "' = '" + actual + "' — OK");
        }

        public boolean hasField(String key) { return body.containsKey(key); }
    }

    // ── Pattern 4: Retry mechanism ────────────────────────────────────────
    // Flaky tests? Wrap the action in a retry — interviewers love this.
    @FunctionalInterface
    interface TestAction {
        void run() throws Exception;
    }

    public static void withRetry(int maxAttempts, long delayMs, TestAction action) {
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                action.run();
                System.out.println("  Succeeded on attempt " + (attempt + 1));
                return;
            } catch (Exception e) {
                attempt++;
                System.out.println("  Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt < maxAttempts) {
                    try { Thread.sleep(delayMs); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                }
            }
        }
        throw new RuntimeException("Action failed after " + maxAttempts + " attempts");
    }

    // ── Pattern 5: Test data from enum ────────────────────────────────────
    // Parameterised test scenarios — much cleaner than raw Object[][]
    enum LoginScenario {
        VALID_USER   ("admin",  "admin123",  true,  200),
        WRONG_PASS   ("admin",  "wrong",     false, 401),
        EMPTY_USER   ("",       "pass",      false, 400),
        UNKNOWN_USER ("ghost",  "pass",      false, 404);

        final String username;
        final String password;
        final boolean expectSuccess;
        final int     expectedStatus;

        LoginScenario(String user, String pass, boolean success, int status) {
            this.username       = user;
            this.password       = pass;
            this.expectSuccess  = success;
            this.expectedStatus = status;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== LEVEL 6: QA Patterns ===\n");

        // Builder
        System.out.println("Pattern 1 - Builder:");
        User admin = new User.Builder().username("admin").email("admin@co.com").role("admin").build();
        User guest = new User.Builder().username("guest").inactive().build();
        System.out.println("  " + admin);
        System.out.println("  " + guest);

        // Table parsing
        System.out.println("\nPattern 2 - Table parsing:");
        List<List<String>> raw = Arrays.asList(
                Arrays.asList("Alice", "30", "QA"),
                Arrays.asList("Bob",   "25", "Dev"),
                Arrays.asList("Carol", "28", "QA")
        );
        List<TableRow> rows = parseTable(raw);
        rows.forEach(r -> System.out.println("  " + r));
        System.out.println("  QA team: " + filterByDept(rows, "QA"));

        // API response validator
        System.out.println("\nPattern 3 - API response validator:");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id", 1);
        body.put("status", "active");
        body.put("username", "admin");
        ApiResponse response = new ApiResponse(200, body);
        response.assertStatus(200);
        response.assertField("status", "active");
        response.assertField("username", "admin");

        // Retry
        System.out.println("\nPattern 4 - Retry:");
        int[] callCount = {0};
        withRetry(3, 10, () -> {
            callCount[0]++;
            if (callCount[0] < 3) throw new RuntimeException("element not found yet");
        });

        // Enum test data
        System.out.println("\nPattern 5 - Login scenarios:");
        for (LoginScenario s : LoginScenario.values()) {
            System.out.printf("  %-14s user=%-7s → success=%s, expectedStatus=%d%n",
                    s.name(), s.username, s.expectSuccess, s.expectedStatus);
        }
    }
}
