package webtest.suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("webtest.demoqa.com.tasks.elements.tests")
@IncludeTags("test")
public class DemoSuite {
}

