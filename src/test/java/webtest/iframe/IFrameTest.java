package webtest.iframe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.forms.FormsPage;
import webtest.demoqa.com.tasks.forms.SubmittedForm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class IFrameTest {
    private WebDriver driver;
    private JavascriptExecutor jse;
    private IFramePage framePage;
    Properties scripts;
    private static String BASEURL = "https://csreis.github.io/tests/cross-site-iframe.html";
    private WebElement frame;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); // Initialize WebDriver for each test
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASEURL);
        framePage = new IFramePage(driver);
        jse = (JavascriptExecutor) driver;
        scripts = new Properties();
        frame = framePage.getiFrame();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Load the script properties
        try (FileInputStream fis = new FileInputStream("src/test/resources/scripts.properties")) {
            scripts.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initListener();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            printMessage("Test finished");
            //driver.quit(); // Ensure the WebDriver is properly closed after each test
        }
    }


    @Test
    public void testOMwithJson() throws JsonProcessingException {
        printMessage("Test 3 : ");
        Client client = new Client("123", "John Doe", 30);
        String jsonClient = objectMapper.writeValueAsString(client);
        //printMessage(json);
        System.out.println("Init object : " + jsonClient);
        System.out.println("----------------");
        sendMessageToiFrame(jsonClient);
        sleep(1);
        String receivedJson = getReceivedMessage();

    }


    public String getReceivedMessage() {
        String ss = "";
        String ss2 = "";
        ss = (String) jse.executeScript("return window.receivedMessage;");
        ss2 = (String) jse.executeScript("return window.testMsg;");
        System.out.println("ss : " + ss);
        System.out.println("ss2 : " + ss2);
        return ss;
    }
    public void initListener() {
        framePage.switchToFrame();
        addMessageListenerToIframe();
        framePage.switchToMainWindow();
    }
    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMessage(String message) {
        // Get the JavaScript code for printing to console
        String script = scripts.getProperty("anotherScript");
        // Execute the JavaScript snippet with the message as an argument
        jse.executeScript(script, message);
    }

    public void sendMessageToiFrame() {
        String script = scripts.getProperty("testMessageScript");
        jse.executeScript(script, "Hello!");
    }
    public void sendMessageToiFrame(String message) {
        String script = scripts.getProperty("testMessageScript");
        jse.executeScript(script, message);
    }

    public void addMessageListenerToIframe() {
        String script = scripts.getProperty("testListener");
        // Execute the script inside the iframe
        jse.executeScript(script);
    }
}
