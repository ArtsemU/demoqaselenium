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
import webtest.demoqa.com.tasks.elements.pages.UploadDownloadPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class UploadDownloadTest extends BaseTest{
    private UploadDownloadPage uploadDownloadPage;
    private static final Logger logger = LogManager.getLogger(UploadDownloadTest.class);
    private final String PATH = "C:\\Work\\selenium\\demoqaselenium\\src\\Upload\\testFile1.txt";

    @BeforeEach
    public void setUp(){
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/upload-download");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        uploadDownloadPage = new UploadDownloadPage(driver);
        jse.executeScript("window.scrollBy(0,100)");
    }

    @Test
    @DisplayName("Test : Upload file")
    public void testUploadFile(){
        logger.info("Test testUploadFile started");
        logger.info("Path :" + PATH);
        uploadDownloadPage.uploadFile(PATH);
        assertTrue(PATH.endsWith(uploadDownloadPage.getUploadFileName()), "Expected file name");
        logger.info("Test testUploadFile finished");
    }
    @Test
    @DisplayName("Test : Download file v1")
    public void testDwl(){
        logger.info("Test testDwl started");
        uploadDownloadPage.download();
        assertTrue(uploadDownloadPage.isFileExist(), "Expected that file exist");
        logger.info("Test testDwl finished");
    }
}
