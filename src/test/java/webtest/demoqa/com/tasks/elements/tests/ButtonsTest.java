package webtest.demoqa.com.tasks.elements.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webtest.demoqa.com.tasks.elements.pages.ButtonsPage;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class ButtonsTest {
    private WebDriver driver;
    private ButtonsPage buttonsPage;
    private JavascriptExecutor jse;
    private final String doubleClickText = "You have done a double click";
    private final String rightClickText = "You have done a right click";
    private final String dynamicClickText = "You have done a dynamic click";
    private static final Logger logger = LogManager.getLogger(ButtonsTest.class);

    @BeforeEach
    public  void setUp(){
        logger.info("Starting setup test method");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/buttons");
        jse = (JavascriptExecutor)driver;
        buttonsPage = new ButtonsPage(driver);
        jse.executeScript("window.scrollBy(0,100)");
    }
    @AfterEach
    public void quit(){
        logger.info("Quit");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Test : double click on a button")
    public void testLeftClick(){
        logger.info("Test started : double click on a button");
        buttonsPage.clickOnDouble();
        assertEquals(buttonsPage.getDoubleClickMessage(), doubleClickText, "Expected text for double click");
    }
    @Test
    @DisplayName("Test : right click")
    public void testRightClick(){

        buttonsPage.clickOnRight();
        assertEquals(buttonsPage.getRightClickMessage(), rightClickText, "Expected text for right click");
    }
    @Test
    @DisplayName("Test : click me")
    public void testClickMe(){
        buttonsPage.clickMe();
        assertEquals(buttonsPage.getDynamicClickMessage(), dynamicClickText, "Expected text for dynamic click");
    }
}
