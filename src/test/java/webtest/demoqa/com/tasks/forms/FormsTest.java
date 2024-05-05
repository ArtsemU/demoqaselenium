package webtest.demoqa.com.tasks.forms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FormsTest {
    WebDriver driver;
    FormsPage formsPage;
    JavascriptExecutor jse;
    private final String PATH = "C:\\Work\\selenium\\demoqaselenium\\src\\Upload\\testFile1.txt";
    private String address = "Hello World!\nHave a good day!";
    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/automation-practice-form");
        formsPage = new FormsPage(driver);
        jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,600)");
    }
    @AfterEach
    public void close(){
        if(driver != null){
            //driver.quit();
            System.out.println(driver);
        }
    }

    @Test
    @DisplayName("Test : fillin form")
    public void testFillinForm(){
        formsPage.setFName("FirstName");
        formsPage.setLName("LastName");
        formsPage.setEmail("email@gmail.com");
        formsPage.setGenderMale();
        formsPage.setMobNum("1234567890");
        formsPage.setDob();
        formsPage.setSubject("English");
        jse.executeScript("window.scrollBy(0,600)");
        formsPage.setHobby();
        formsPage.uploadPicture(PATH);
        formsPage.setCurAddress(address);
        formsPage.setState("NCR");
        formsPage.setCity("Noida");
    }
}
