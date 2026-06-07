package webtest.demoqa.com.tasks.elements.cucumber;

import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webtest.test.DriverFactory;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final WebDriverContext ctx;

    public Hooks(WebDriverContext ctx) {
        this.ctx = ctx;
    }

    @Before  // аналог BaseTest.setUp() + ButtonsTest.setUp()
    public void setUp() {
        ctx.driver = DriverFactory.getDriver();
        ctx.driver.manage().window().maximize();
        logger.info("Driver initialized and browser window maximized.");
    }

    @After   // аналог BaseTest.tearDown()
    public void tearDown() {
        if (ctx.driver != null) {
            ctx.driver.quit();
        }
        logger.info("Driver quit and browser window closed.");
    }
}
