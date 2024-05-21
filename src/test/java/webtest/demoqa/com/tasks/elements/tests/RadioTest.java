package webtest.demoqa.com.tasks.elements.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.elements.pages.RadioPage;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class RadioTest extends BaseTest{
    RadioPage radioPage;
    private static final Logger logger = LogManager.getLogger(CheckBoxTests.class);

    @BeforeEach
    public  void setUp(){
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/radio-button");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        radioPage = new RadioPage(driver);
        jse.executeScript("window.scrollBy(0,200)");
    }

    @Test
    @DisplayName("Test : choose Yes")
    public void testChooseYes(){
        logger.info("Test testChooseYes started");
        assertEquals(radioPage.getTitle(), "Radio Button", "expected Radio Button text");
        radioPage.clickOnYesLabel();
        assertEquals(radioPage.getSuccessText().toLowerCase(), "yes", "expected Yes text");
        logger.info("Test testChooseYes finished");
    }
    @Test
    @DisplayName("Test : choose Impressive")
    public void testChooseImpressive(){
        logger.info("Test testChooseImpressive started");
        assertEquals(radioPage.getTitle(), "Radio Button", "Expected Radio Button text");
        radioPage.clickOnImprRadio();
        assertEquals(radioPage.getSuccessText().toLowerCase(), "impressive", "Expected Yes text");
        logger.info("Test testChooseImpressive finished");
    }
    @Test
    @DisplayName("Test : no is disabled")
    public void testNoDisabled(){
        logger.info("Test testNoDisabled started");
        radioPage.isNoAvailable();
        radioPage.isYesAvailable();
        radioPage.clickOnYesLabel();
        radioPage.isYesAvailable();
        logger.info("Test testNoDisabled finished");
    }
}
