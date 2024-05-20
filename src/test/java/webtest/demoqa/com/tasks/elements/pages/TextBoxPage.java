package webtest.demoqa.com.tasks.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
public class TextBoxPage {

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
        this.driver = driver;
    }
    public void setUserName(String name){
        driver.findElement(userName).sendKeys(name);
    }
    public void setUserEmail(String name){
        driver.findElement(userEmail).sendKeys(name);
    }
    public void setCurrentAddress(String address){
        driver.findElement(currentAddress).sendKeys(address);
    }
    public void setPermanentAddress(String addres){
        driver.findElement(permanentAddress).sendKeys(addres);
    }
    public void clickSubmit(){
        driver.findElement(submit).click();
    }
    public String getResultName(){
        return driver.findElement(resultName).getText();
    }
    public String getResultEmail(){
        return driver.findElement(resulEmail).getText();
    }
    public String getResultCurrentAddress(){
        return driver.findElement(resultCurrentAddress).getText();
    }
    public String getResultPresentAddress(){
        return driver.findElement(resultPresentAddress).getText();
    }
    public String getEmailAttribute(String att){
        return driver.findElement(userEmail).getDomAttribute(att);
    }
    public boolean isNamePresent() {
        try {
            driver.findElement(resultName);
            return true; // Element found
        } catch (NoSuchElementException e) {
            return false; // Element not found
        }
    }
    public boolean isCurrAddressPresent() {
        try {
            driver.findElement(resultCurrentAddress);
            return true; // Element found
        } catch (NoSuchElementException e) {
            return false; // Element not found
        }
    }

}
