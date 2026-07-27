# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Selenium WebDriver + Java test automation project (Maven, Java 17, JUnit 5) against the demo site https://demoqa.com/. Tests and page objects live under `src/test/java/webtest/demoqa/com/tasks/`, organized by site feature (`elements`, `forms`, ...). Each feature package follows a Page Object Model: `pages/` holds page classes, `tests/` holds JUnit 5 test classes.

## Commands

Build/compile without running tests:
```
mvn -q -DskipTests test-compile
```

Run a single test class:
```
mvn -q -Dtest=webtest.demoqa.com.tasks.forms.FormsTest test
```

Run a Cucumber BDD runner (JUnit Platform Suite + Cucumber engine):
```
mvn -q -Dtest=bddtests.runner.TestRunner test
```

Run the full test suite:
```
mvn test
```

There are also JUnit Platform `@Suite` classes for grouped runs:
- `webtest.suite.DemoSuite` — selects package `webtest.demoqa.com.tasks.elements.tests`, filtered by tag `"test"`.
- `webtest.suite.TestRun` — selects explicit classes (`RadioTest`, `ButtonsTest`).

Allure results are written to `target/allure-results` (see `src/test/resources/allure.properties`); Cucumber HTML reports are written to `target/cucumber-report*.html` per runner config.

## Architecture

**Driver management**: `webtest.test.DriverFactory` is the intended single source for WebDriver creation — `getDriver()` (defaults to Chrome), `getDriver(String browser)` (`"chrome"`/`"firefox"`), and `getAndroidChromeDriver()` for Appium against `http://127.0.0.1:4723/wd/hub`. `webtest.demoqa.com.tasks.elements.tests.BaseTest` and `bddtests.Hooks` both use it for `@BeforeEach`/`@Before` setup and `@AfterEach`/`@After` teardown (maximize window, quit driver, log via log4j2).

**Not every test uses this factory**: `FormsTest` and `IFrameTest` instantiate `new ChromeDriver()` directly instead of going through `DriverFactory`/`BaseTest`, and both have `driver.quit()` commented out in teardown — be aware of this inconsistency when touching those classes or writing new tests in the same style.

**Page objects**: `webtest.demoqa.com.tasks.elements.pages.BasePage` provides `waitForElementToBeClickable`/`waitForElementToBeVisible` helpers (10s `WebDriverWait`) that feature page classes extend. Other feature packages (`forms`, `iframe`) have their own page classes (`FormsPage`, `SubmittedForm`, `IFramePage`) that don't extend `BasePage` but follow the same constructor-takes-`WebDriver` pattern.

**Two parallel Cucumber/BDD setups exist**, each with its own glue package and runner — don't mix them:
- `bddtests/` (glue `bddtests`, features in `src/test/resources/bddtests/*.feature`, runner `bddtests.runner.TestRunner`) — currently covers the Forms flow, using `WebDriverContext` (a plain field-holder, injected via picocontainer) to share `driver`/page objects across step classes.
- `webtest.demoqa.com.tasks.elements.cucumber` (glue same path, features in `src/test/resources/features/*.feature`, runner `webtest.demoqa.com.tasks.elements.cucumber.runner.TestRunner`) — covers Buttons/API steps, same `WebDriverContext` pattern scoped to that package.

**`src/test/java/interview/`**: standalone Java exercises (Level1 Strings → Level7 Stream Operations) for QA interview prep. Plain classes with `main`/static methods — no JUnit, no Selenium, unrelated to the web test suite. Naming convention when adding a non-stream counterpart to a stream-based method: `<originalMethodName>_withoutStreams`.

**Sandbox areas** (`sandbox/` at repo root, plus `src/test/java/sandbox/` and `src/test/java/webtest/demoqa/com/tasks/sandbox/`): free experimentation space, no constraints on what goes here (see `sandbox/README.md`).

**`scripts.properties`** (`src/test/resources/scripts.properties`): named JavaScript snippets (postMessage/listener scripts) loaded via `Properties` and executed with `JavascriptExecutor` — used by the iframe cross-window messaging tests.

**Logging**: log4j2 config at `src/main/resources/log4j2.xml`, console appender, root level `debug`.
