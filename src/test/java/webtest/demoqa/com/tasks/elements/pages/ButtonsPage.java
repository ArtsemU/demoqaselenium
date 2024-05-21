package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(ButtonsPage.class);
    private final By doubleMeButton = By.xpath("//button[@id='doubleClickBtn']");
    private final By rightMeButton = By.xpath("//button[@id='rightClickBtn']");
    private final By clickMe = By.xpath("//button[text()='Click Me']");
    private final By doubleClickMessage = By.xpath("//p[@id='doubleClickMessage']");
    private final By rightClickMessage = By.xpath("//p[@id='rightClickMessage']");
    private final By dynamicClickMessage = By.xpath("//p[@id='dynamicClickMessage']");

    public ButtonsPage(WebDriver driver) {
        super(driver);
        logger.info("ButtonsPage initialized");
    }

    public void clickOnDouble() {
        logger.info("Double clicking");
        WebElement element = waitForElementToBeClickable(doubleMeButton);
        new Actions(driver).doubleClick(element).perform();
    }

    public void clickOnRight() {
        logger.info("Right clicking");
        WebElement element = waitForElementToBeClickable(rightMeButton);
        new Actions(driver).contextClick(element).perform();
    }

    public void clickMe() {
        logger.info("Click on element");
        WebElement element = waitForElementToBeClickable(clickMe);
        element.click();
    }

    public String getDoubleClickMessage() {
        return waitForElementToBeVisible(doubleClickMessage).getText();
    }

    public String getRightClickMessage() {
        return waitForElementToBeVisible(rightClickMessage).getText();
    }

    public String getDynamicClickMessage() {
        return waitForElementToBeVisible(dynamicClickMessage).getText();
    }
}
