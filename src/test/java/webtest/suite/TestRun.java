package webtest.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import webtest.demoqa.com.tasks.elements.tests.ButtonsTest;
import webtest.demoqa.com.tasks.elements.tests.RadioTest;

@Suite
@SelectClasses({
    RadioTest.class,
    ButtonsTest.class
})
public class TestRun {
}
