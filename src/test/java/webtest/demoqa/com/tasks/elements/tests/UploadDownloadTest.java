package webtest.demoqa.com.tasks.elements.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.elements.pages.UploadDownloadPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class UploadDownloadTest {
    private WebDriver driver;
    private UploadDownloadPage uploadDownloadPage;
    private JavascriptExecutor jse;
    private final String PATH = "C:\\Work\\selenium\\testone\\src\\Upload\\testFile1.txt";

    @BeforeEach
    public  void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/upload-download");
        jse = (JavascriptExecutor)driver;
        uploadDownloadPage = new UploadDownloadPage(driver);
    }
    @AfterEach
    public void close(){
        uploadDownloadPage.removeFile();
        driver.quit();
    }
    @Test
    @DisplayName("Test : Upload file")
    public void testUploadFile(){
        uploadDownloadPage.uploadFile(PATH);
        assertTrue(PATH.endsWith(uploadDownloadPage.getUploadFileName()), "Expected file name");
    }
    @Test
    @DisplayName("Test : Download file v1")
    public void testDwl(){
        uploadDownloadPage.download();
        assertTrue(uploadDownloadPage.isFileExist(), "Expected that file exist");
    }
}
