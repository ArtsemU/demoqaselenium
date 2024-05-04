package webtest.demoqa.com.tasks.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsPage {
    WebDriver driver;
    private By titlePage = By.xpath("//h1[@class='text-center']");
    private By fname = By.xpath("//input[@id='firstName']");
    private By lname = By.xpath("//input[@id='lastName']");
    private By email = By.xpath("//input[@id='userEmail']");
    private By rbMale = By.xpath("//input[@value='Male']");
    private By rbFemale = By.xpath("//input[@value='Female']");
    private By rbOther = By.xpath("//input[@value='Other']");
    private By mobNum = By.xpath("//input[@id='userNumber']");
    
    public FormsPage(WebDriver driver){
        this.driver = driver;
    }



}
