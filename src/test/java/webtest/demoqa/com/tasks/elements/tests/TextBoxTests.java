package webtest.demoqa.com.tasks.elements.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.elements.pages.TextBoxPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static webtest.test.Screenshots.takeScreenShot;


public class TextBoxTests {

    static WebDriver driver;
    TextBoxPage textBoxPage;
    JavascriptExecutor jse;

    @BeforeEach
    public  void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/text-box");
        jse = (JavascriptExecutor)driver;
    }

    @AfterEach
    public void close(){
        driver.quit();
    }

    @Test
    @DisplayName("Fill in input fields")
    public void testInput(){
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.setUserName("userName");
        textBoxPage.setUserEmail("user@email.com");
        jse.executeScript("window.scrollBy(0,500)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver, "testInput");
        if(textBoxPage.isNamePresent()){
            assertTrue(textBoxPage.getResultName().contains("userName"), "There is not such text");
            assertTrue(textBoxPage.getResultEmail().contains("user@email.com"), "There is not such text");
        } else {
            fail("Here is no element");
        }
    }

    @Test
    @DisplayName("Fill in text area")
    public void testTextArea(){
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.setCurrentAddress("Wroclaw, Szybka 4F/42");
        textBoxPage.setPermanentAddress("Minsk, Gor street, 4-15");
        jse.executeScript("window.scrollBy(0,500)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver,"testTextArea");
        if (textBoxPage.isCurrAddressPresent()){
            assertTrue(textBoxPage.getResultCurrentAddress().contains("Wroclaw"), "There is not such text");
            assertTrue(textBoxPage.getResultPresentAddress().contains("Minsk"), "There is not such text");
        } else {
            fail("Here is no element");
        }
    }

    @Test
    @DisplayName("Error for wrong email")
    public void testEmailError(){
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.setUserEmail("abc");
        jse.executeScript("window.scrollBy(0,400)");
        textBoxPage.clickSubmit();
        takeScreenShot(driver, "errorEmail");
        assertTrue(textBoxPage.getEmailAttribute("class").contains("field-error"), "There is not such att");
    }
}
