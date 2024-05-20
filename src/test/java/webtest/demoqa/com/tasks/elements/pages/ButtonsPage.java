package webtest.demoqa.com.tasks.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {
    private WebDriver driver;
    private Actions action;
    private final By doubleMeButton = By.xpath("//button[@id='doubleClickBtn']");
    private final By rightMeButton = By.xpath("//button[@id='rightClickBtn']");
    private final By clickMe = By.xpath("//button[text()='Click Me']");
    private final By doubleClickMessage = By.xpath("//p[@id='doubleClickMessage']");
    private final By rightClickMessage = By.xpath("//p[@id='rightClickMessage']");
    private final By dynamicClickMessage = By.xpath("//p[@id='dynamicClickMessage']");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
    }
    public void clickOnDouble(){
        action.doubleClick(driver.findElement(doubleMeButton)).perform();
    }
    public void clickOnRight(){
        action.contextClick(driver.findElement(rightMeButton)).perform();
    }
    public void clickMe(){
        driver.findElement(clickMe).click();
    }

    public String getDoubleClickMessage(){
        return driver.findElement(doubleClickMessage).getText();
    }
    public String getRightClickMessage(){
        return driver.findElement(rightClickMessage).getText();
    }
    public String getDynamicClickMessage(){
        return driver.findElement(dynamicClickMessage).getText();
    }
}
