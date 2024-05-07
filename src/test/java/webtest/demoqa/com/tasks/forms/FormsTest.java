package webtest.demoqa.com.tasks.forms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.enums.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class FormsTest {
    WebDriver driver;
    FormsPage formsPage;
    SubmittedForm sbmForm;
    JavascriptExecutor jse;
    private final String PATH = "C:\\Work\\selenium\\demoqaselenium\\src\\Upload\\";
    private final String FILE_NAME = "testFile1.txt";
    private final String address = "Hello World!\nHave a good day!";
    private final String TITLE = "Thanks for submitting the form";
    private final String NCR = "NCR";
    private final String NOIDA = "Noida";
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
        formsPage.uploadPicture(PATH + FILE_NAME);
        formsPage.setCurAddress(address);
        formsPage.setState(NCR);
        formsPage.setCity(NOIDA);
        formsPage.submit();
        assertEquals(TITLE, sbmForm.getTitle(), String.format("Expected title: %s, but received: %s", TITLE, sbmForm.getTitle()));
        assertEquals("FirstName LastName", sbmForm.getSubmittedName(), "Expected name: FirstName LastName, but received: " + sbmForm.getSubmittedName());
        assertEquals("email@gmail.com", sbmForm.getSubmittedEmail(), "Expected email: email@gmail.com, but received: " + sbmForm.getSubmittedEmail());
        assertEquals(Gender.MALE.getValue(), sbmForm.getGender(), String.format("Expected gender: %s, but received: %s", Gender.MALE.getValue(), sbmForm.getGender()));
        assertEquals("1234567890", sbmForm.getNumber(), String.format("Expected number: %s, but received: %s", "1234567890", sbmForm.getNumber()));
        assertTrue(areDatesEqual(sbmForm.getDob(), "01MAY2010"), String.format("Expected dob: %s, but received: %s", "01MAY2010", sbmForm.getDob()));
        assertEquals("English", sbmForm.getSbj(), String.format("Expected subject: %s, but received: %s", "English", sbmForm.getSbj()));
        assertEquals("Sports, Reading", sbmForm.getHobby(), String.format("Expected Hobbies: %s, but received: %s", "Sports, Reading", sbmForm.getHobby()));
        assertEquals(FILE_NAME, sbmForm.getFileName(), String.format("Expected File: %s, but received: %s", FILE_NAME, sbmForm.getFileName()));
        assertEquals("Hello World! Have a good day!", sbmForm.getAddress(), String.format("Expected Address: %s, but received: %s", "Hello World! Have a good day!", sbmForm.getAddress()));
        assertEquals(NCR + " " + NOIDA, sbmForm.getStateCity(), String.format("Expected State and City: %s, but received: %s", NCR + " " + NOIDA, sbmForm.getStateCity()));
    }
    public static boolean areDatesEqual(String displayedDateString, String inputDateString) {
        SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd MMM,yyyy");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("ddMMMyyyy");

        try {
            // Parse the displayed date string
            Date displayedDate = displayDateFormat.parse(displayedDateString);

            // Parse the input date string
            Date inputDate = inputDateFormat.parse(inputDateString);

            // Compare the parsed dates
            return displayedDate.equals(inputDate);
        } catch (ParseException e) {
            System.out.println("Error parsing the dates: " + e.getMessage());
            return false;
        }
    }
}
