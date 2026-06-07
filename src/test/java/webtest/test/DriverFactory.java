package webtest.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add any Chrome-specific options here
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Add any Firefox-specific options here
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    public static WebDriver getDriver() {
        // Default to Chrome if no browser is specified
        return getDriver("chrome");
    }

    // -----------------------------
    // ANDROID CHROME (Appium)
    // -----------------------------
    public static WebDriver getAndroidChromeDriver() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setAutomationName("UiAutomator2");
        options.withBrowserName("Chrome");

        URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");

        return new AndroidDriver(appiumServer, options);
    }
}
