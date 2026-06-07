package webtest.demoqa.com.tasks.elements.tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import webtest.test.DriverFactory;

import java.net.MalformedURLException;

public class AndroidTest {

    @Test
    public void androidTest() throws MalformedURLException, InterruptedException {
        WebDriver driver = DriverFactory.getAndroidChromeDriver();
        driver.get("https://google.com");

        Thread.sleep(5000);

        driver.quit();
    }
}
