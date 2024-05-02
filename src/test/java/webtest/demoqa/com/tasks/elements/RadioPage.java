package webtest.demoqa.com.tasks.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioPage {
    WebDriver driver;
    public RadioPage(WebDriver driver){
        this.driver = driver;
    }
    By title = By.xpath("//h1[@class='text-center']");
    By yesRadio = By.xpath("//input[@id='yesRadio']");
    By yesRadioLabel = By.xpath("//label[@for='yesRadio']");
    By impRadioLabel = By.xpath("//label[@for='impressiveRadio']");
    By noRadioLabel = By.xpath("//label[@for='noRadio']");
    By successText = By.xpath("//span[@class='text-success']");
    public void clickOnYesRadio(){
        driver.findElement(yesRadio).click();
    }
    public void clickOnYesLabel(){
        driver.findElement(yesRadioLabel).click();
    }
    public void clickOnImprRadio(){
        driver.findElement(impRadioLabel).click();
    }
    public String getSuccessText(){
        return driver.findElement(successText).getText();
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }
    public boolean isNoAvailable(){
        return driver.findElement(noRadioLabel).isEnabled();
    }
    public boolean isYesAvailable(){
        return driver.findElement(yesRadioLabel).isEnabled();
    }

    public void clickOnNo(){
        driver.findElement(noRadioLabel).click();
    }

}
