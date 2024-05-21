package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(RadioPage.class);
    By title = By.xpath("//h1[@class='text-center']");
    By yesRadio = By.xpath("//input[@id='yesRadio']");
    By yesRadioLabel = By.xpath("//label[@for='yesRadio']");
    By impRadioLabel = By.xpath("//label[@for='impressiveRadio']");
    By noRadioLabel = By.xpath("//label[@for='noRadio']");
    By successText = By.xpath("//span[@class='text-success']");
    public RadioPage(WebDriver driver){
        super(driver);
        logger.info("RadioPage initialized");
    }
    public void clickOnYesRadio(){
        logger.info("Click on Yes rb");
        waitForElementToBeClickable(yesRadio).click();
    }
    public void clickOnYesLabel(){
        logger.info("Click on Yes leble");
        waitForElementToBeClickable(yesRadioLabel).click();
    }
    public void clickOnImprRadio(){
        logger.info("Click on Impr rb");
        waitForElementToBeClickable(impRadioLabel).click();
    }
    public String getSuccessText(){
        return waitForElementToBeVisible(successText).getText();
    }
    public String getTitle(){
        return waitForElementToBeVisible(title).getText();
    }
    public boolean isNoAvailable(){
        return waitForElementToBeVisible(noRadioLabel).isEnabled();
    }
    public boolean isYesAvailable(){
        return waitForElementToBeVisible(yesRadioLabel).isEnabled();
    }

    public void clickOnNo(){
        waitForElementToBeClickable(noRadioLabel).click();
    }

}
