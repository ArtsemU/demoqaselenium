package webtest.demoqa.com.tasks.forms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.enums.Gender;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class FormsTest {
    WebDriver driver;
    FormsPage formsPage;
    SubmittedForm sbmForm;
    JavascriptExecutor jse;
    private final String PATH = "C:\\Work\\selenium\\demoqaselenium\\src\\Upload\\testFile1.txt";
    private final String address = "Hello World!\nHave a good day!";
    private final String TITLE = "Thanks for submitting the form";
    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/automation-practice-form");
        formsPage = new FormsPage(driver);
        jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,600)");
        sbmForm = new SubmittedForm(driver);

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
        formsPage.setGenderMale(Gender.MALE);
        formsPage.setMobNum("1234567890");
        formsPage.setDob();
        formsPage.setSubject("English");
        jse.executeScript("window.scrollBy(0,600)");
        formsPage.setHobby();
        formsPage.uploadPicture(PATH);
        formsPage.setCurAddress(address);
        formsPage.setState("NCR");
        formsPage.setCity("Noida");
        formsPage.submit();
        assertEquals(TITLE, sbmForm.getTitle(), String.format("Expected title: %s, but received: %s", TITLE, sbmForm.getTitle()));
        assertEquals("FirstName LastName", sbmForm.getSubmittedName(), "Expected name: FirstName LastName, but received: " + sbmForm.getSubmittedName());
        assertEquals("email@gmail.com", sbmForm.getSubmittedEmail(), "Expected email: email@gmail.com, but received: " + sbmForm.getSubmittedEmail());
        assertEquals(Gender.MALE.getValue(), sbmForm.getGender(), String.format("Expected gender: %s, but received: %s", Gender.MALE.getValue(), sbmForm.getGender()));


    }
}
