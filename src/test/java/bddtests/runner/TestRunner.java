package bddtests.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bddtests")
@ConfigurationParameter(
        key   = "cucumber.glue",
        value = "bddtests"
)
@ConfigurationParameter(
        key   = "cucumber.plugin",
        value = "pretty, html:target/cucumber-report-forms.html"
)
public class TestRunner {}
