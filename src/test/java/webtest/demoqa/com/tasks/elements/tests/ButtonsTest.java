package webtest.demoqa.com.tasks.elements.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import webtest.demoqa.com.tasks.elements.pages.ButtonsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonsTest extends BaseTest {
    private ButtonsPage buttonsPage;
    private static final Logger logger = LogManager.getLogger(ButtonsTest.class);
    private static final String DOUBLE_CLICK_TEXT = "You have done a double click";
    private static final String RIGHT_CLICK_TEXT = "You have done a right click";
    private static final String DYNAMIC_CLICK_TEXT = "You have done a dynamic click";

    @BeforeEach
    public void setUp() {
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/buttons");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        buttonsPage = new ButtonsPage(driver);
        jse.executeScript("window.scrollBy(0,100)");
    }

    @Test
    @DisplayName("Test: Double click on a button")
    public void testDoubleClickButton() {
        logger.info("Test testDoubleClickButton started");
        buttonsPage.clickOnDouble();
        assertEquals(DOUBLE_CLICK_TEXT, buttonsPage.getDoubleClickMessage(), "Expected text for double click");
        logger.info("Test testDoubleClickButton finished");
    }

    @Test
    @DisplayName("Test: Right click on a button")
    public void testRightClickButton() {
        logger.info("Test testRightClickButton started");
        buttonsPage.clickOnRight();
        assertEquals(RIGHT_CLICK_TEXT, buttonsPage.getRightClickMessage(), "Expected text for right click");
        logger.info("Test testRightClickButton finished");
    }

    @Test
    @DisplayName("Test: Click on a button")
    public void testClickButton() {
        logger.info("Test testClickButton started");
        buttonsPage.clickMe();
        assertEquals(DYNAMIC_CLICK_TEXT, buttonsPage.getDynamicClickMessage(), "Expected text for dynamic click");
        logger.info("Test testClickButton finished");
    }
}
