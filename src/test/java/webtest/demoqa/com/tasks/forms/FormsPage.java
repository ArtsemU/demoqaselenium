package webtest.demoqa.com.tasks.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FormsPage {
    WebDriver driver;
    private By titlePage = By.xpath("//h1[@class='text-center']");
    private By fname = By.xpath("//input[@id='firstName']");
    private By lname = By.xpath("//input[@id='lastName']");
    private By email = By.xpath("//input[@id='userEmail']");
    private By rbMale = By.xpath("//input[@value='Male']/following-sibling::label");
    private By rbFemale = By.xpath("//input[@value='Female']");
    private By rbOther = By.xpath("//input[@value='Other']");
    private By mobNum = By.xpath("//input[@id='userNumber']");
    private By dob = By.xpath("//input[@id='dateOfBirthInput']");
    private By subject = By.xpath("//input[@id='subjectsInput']");
    private By hobbySport = By.xpath("//label[@for='hobbies-checkbox-1']");
    private By hobbyReadding = By.xpath("//label[@for='hobbies-checkbox-2']");
    private By hobbyMusic = By.xpath("//label[@for='hobbies-checkbox-3']");
    private By upload = By.id("uploadPicture");
    private By currAddress = By.id("currentAddress");
    private By state = By.xpath("//div[@id='state']//input");
    private By city = By.xpath("//div[@id='city']//input");

    public FormsPage(WebDriver driver){
        this.driver = driver;
    }

    public void setFName(String name){
        driver.findElement(fname).sendKeys(name);
    }
    public void setLName(String name){
        driver.findElement(lname).sendKeys(name);
    }
    public void setEmail(String mail){
        driver.findElement(email).sendKeys(mail);
    }
    public void setMobNum(String num){
        driver.findElement(mobNum).sendKeys(num);
    }
    public void setGenderMale(){
        driver.findElement(rbMale).click();
    }
    public void setDob(){
        driver.findElement(dob).clear();
        driver.findElement(dob).sendKeys("01MAY10");
        driver.findElement(dob).sendKeys(Keys.ENTER);
    }
    public void setSubject(String sbj){
        driver.findElement(subject).sendKeys(sbj);
        driver.findElement(subject).sendKeys(Keys.ENTER);
    }
    public void setHobby(){
        driver.findElement(hobbySport).click();
        driver.findElement(hobbyReadding).click();
    }
    public void uploadPicture(String pic){
        driver.findElement(upload).sendKeys(pic);
    }
    public void setCurAddress(String address){
        driver.findElement(currAddress).clear();
        driver.findElement(currAddress).sendKeys(address);
    }
    public void setState(String stateValue){
        driver.findElement(state).sendKeys(stateValue);
        driver.findElement(state).sendKeys(Keys.ENTER);
    }
    public void setCity(String cityValue){
        driver.findElement(city).sendKeys(cityValue);
        driver.findElement(city).sendKeys(Keys.ENTER);
    }


}
