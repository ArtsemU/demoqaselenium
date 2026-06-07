package webtest.demoqa.com.tasks.elements.cucumber.runner;

import org.junit.platform.suite.api.*;
import org.junit.platform.suite.api.SelectClasspathResource;
@Suite
@IncludeEngines("cucumber") // другие варианты: "junit-jupiter", "junit-vintage", "spock"
@SelectClasspathResource("features") // другие варианты: "features/smoke", "features/regression", конкретный файл: @SelectFile("src/test/resources/login.feature")
@ConfigurationParameter(
        key   = "cucumber.glue",
        //value = "com.example.steps"
        value = "webtest.demoqa.com.tasks.elements.cucumber" // можно указать несколько пакетов через запятую: "package1, package2"
)
@ConfigurationParameter(
        key   = "cucumber.plugin",
        value = "pretty, html:target/cucumber-report.html" // другие варианты:
        // json:target/cucumber.json,
        // junit:target/cucumber.xml,
        // io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm
)
// дополнительные параметры которые можно добавить:
// @ConfigurationParameter(key = "cucumber.filter.tags", value = "@smoke") // фильтр по тегам
// @ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "true") // параллельный запуск
public class TestRunner {}