package webtest.demoqa.com.tasks.elements.tests;

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

public class RadioTest {
    WebDriver driver;
    RadioPage radioPage;
    JavascriptExecutor jse;

    @BeforeEach
    public  void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/radio-button");
        jse = (JavascriptExecutor)driver;
        radioPage = new RadioPage(driver);
    }

    @AfterEach
    public void close(){
        //driver.quit();
    }
    @Test
    @DisplayName("Test : choose Yes")
    public void testChooseYes(){
        assertEquals(radioPage.getTitle(), "Radio Button", "expected Radio Button text");
        jse.executeScript("window.scrollBy(0,200)");
        //radioPage.clickOnYesRadio();
        radioPage.clickOnYesLabel();
        assertEquals(radioPage.getSuccessText().toLowerCase(), "yes", "expected Yes text");
    }
    @Test
    @DisplayName("Test : choose Impressive")
    public void testChooseImpressive(){
        assertEquals(radioPage.getTitle(), "Radio Button", "expected Radio Button text");
        jse.executeScript("window.scrollBy(0,200)");
        radioPage.clickOnImprRadio();
        assertEquals(radioPage.getSuccessText().toLowerCase(), "impressive", "expected Yes text");
    }
    @Test
    @DisplayName("Test : no is disabled")
    public void testNoDisabled(){
        radioPage.isNoAvailable();
        radioPage.isYesAvailable();
        radioPage.clickOnYesLabel();
        radioPage.isYesAvailable();
    }
}
