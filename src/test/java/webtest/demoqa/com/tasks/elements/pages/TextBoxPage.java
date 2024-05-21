package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
public class TextBoxPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(TextBoxPage.class);
    WebDriver driver;
    By userName = By.xpath("//input[@id='userName']");
    By userEmail = By.xpath("//input[@id='userEmail']");
    By currentAddress = By.xpath("//textarea[@id='currentAddress']");
    By permanentAddress = By.xpath("//textarea[@id='permanentAddress']");
    By submit = By.xpath("//button[@id='submit']");
    By resultName = By.xpath("//p[@id='name']");
    By resulEmail = By.xpath("//p[@id='email']");
    By resultCurrentAddress = By.xpath("//p[@id='currentAddress']");
    By resultPresentAddress = By.xpath("//p[@id='permanentAddress']");

    public TextBoxPage(WebDriver driver){
        super(driver);
        logger.info("TextBoxPage initialized");
    }
    public void setUserName(String name){
        waitForElementToBeClickable(userName).sendKeys(name);
        logger.info("Set user name as :" + name);
    }
    public void setUserEmail(String mail){
        waitForElementToBeClickable(userEmail).sendKeys(mail);
        logger.info("Set user email as :" + mail);
    }
    public void setCurrentAddress(String address){
        waitForElementToBeClickable(currentAddress).sendKeys(address);
        logger.info("Set user Current address as :" + address);
    }
    public void setPermanentAddress(String addres){
        waitForElementToBeClickable(permanentAddress).sendKeys(addres);
        logger.info("Set user permanent address as :" + addres);
    }
    public void clickSubmit(){
        waitForElementToBeClickable(submit).click();
        logger.info("Click on Submit");
    }
    public String getResultName(){
        return waitForElementToBeVisible(resultName).getText();
    }
    public String getResultEmail(){
        return waitForElementToBeVisible(resulEmail).getText();
    }
    public String getResultCurrentAddress(){
        return waitForElementToBeVisible(resultCurrentAddress).getText();
    }
    public String getResultPresentAddress(){
        return waitForElementToBeVisible(resultPresentAddress).getText();
    }
    public String getEmailAttribute(String att){
        logger.info("Get arribute value for :" + att);
        return waitForElementToBeVisible(userEmail).getDomAttribute(att);
    }
    public boolean isNamePresent() {
        try {
            logger.info("Check Name available");
            waitForElementToBeVisible(resultName);
            return true; // Element found
        } catch (NoSuchElementException e) {
            logger.error("Name was not found");
            return false; // Element not found
        }
    }
    public boolean isCurrAddressPresent() {
        try {
            logger.info("Check Address available");
            waitForElementToBeVisible(resultCurrentAddress);
            return true; // Element found
        } catch (NoSuchElementException e) {
            logger.error("Address was not found");
            return false; // Element not found
        }
    }

}
