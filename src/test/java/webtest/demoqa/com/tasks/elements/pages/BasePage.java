package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("BasePage initialized");
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        logger.info("Web element is clickable" + locator.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        logger.info("Web element is visible" + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
