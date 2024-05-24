package webtest.demoqa.com.tasks.elements.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.elements.pages.TextBoxPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static webtest.test.Screenshots.takeScreenShot;


public class TextBoxTests extends BaseTest{
    TextBoxPage textBoxPage;
    JavascriptExecutor jse;
    private static final Logger logger = LogManager.getLogger(TextBoxTests.class);

    @BeforeEach
    public void setUp(){
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/text-box");
        jse = (JavascriptExecutor)driver;
        textBoxPage = new TextBoxPage(driver);
        jse.executeScript("window.scrollBy(0,500)");
    }

    @Test
    @DisplayName("Fill in input fields")
    public void testInput(){
        logger.info("Test testInput started");
        textBoxPage.setUserName("userName");
        textBoxPage.setUserEmail("user@email.com");
        jse.executeScript("window.scrollBy(0,500)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver, "testInput");
        if(textBoxPage.isNamePresent()){
            assertTrue(textBoxPage.getResultName().contains("userName"), "There is not such text");
            assertTrue(textBoxPage.getResultEmail().contains("user@email.com"), "There is not such text");
        } else {
            logger.error("There is no element");
            fail("Here is no element");
        }
        logger.info("Test testInput finished");
    }

    @Test
    @Tag("test")
    @DisplayName("Fill in text area")
    public void testTextArea(){
        logger.info("Test testTextArea started");
        textBoxPage.setCurrentAddress("Wroclaw, Szybka 4F/42");
        textBoxPage.setPermanentAddress("Minsk, Gor street, 4-15");
        jse.executeScript("window.scrollBy(0,500)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver,"testTextArea");
        if (textBoxPage.isCurrAddressPresent()){
            assertTrue(textBoxPage.getResultCurrentAddress().contains("Wroclaw"), "There is not such text");
            assertTrue(textBoxPage.getResultPresentAddress().contains("Minsk"), "There is not such text");
        } else {
            logger.error("Here is no element");
            fail("Here is no element");
        }
        logger.info("Test testTextArea finished");
    }

    @Test
    @DisplayName("Error for wrong email")
    public void testEmailError(){
        logger.info("Test testEmailError started");
        textBoxPage.setUserEmail("abc");
        jse.executeScript("window.scrollBy(0,400)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver, "errorEmail");
        String errMessage = textBoxPage.getEmailAttribute("class");
        logger.info("error message :" + errMessage);
        assertTrue(textBoxPage.getEmailAttribute("class").contains("field-error"), "There is not such att");
        logger.info("Test testEmailError finished");
    }
}
