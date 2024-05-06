package webtest.demoqa.com.tasks.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SubmittedForm {
    WebDriver driver;
    private final By header = By.xpath("//div[@class='modal-header']/div");
    private final By submittedName = By.xpath("//td[text()='Student Name']/following-sibling::td");
    private final By submittedEmail = By.xpath("//td[text()='Student Email']/following-sibling::td");
    private final By submittedGender = By.xpath("//td[text()='Gender']/following-sibling::td");
    private final By submittedMobile = By.xpath("//td[text()='Mobile']/following-sibling::td");
    private final By submittedDob = By.xpath("//td[text()='Date of Birth']/following-sibling::td");
    private final By submittedSubjects = By.xpath("//td[text()='Subjects']/following-sibling::td");
    private final By submittedHobbies = By.xpath("//td[text()='Hobbies']/following-sibling::td");
    private final By submittedPicture = By.xpath("//td[text()='Picture']/following-sibling::td");
    private final By submittedAddress = By.xpath("//td[text()='Address']/following-sibling::td");
    private final By submittedStateCity = By.xpath("//td[text()='State and City']/following-sibling::td");
    public SubmittedForm(WebDriver driver){
        this.driver = driver;
    }
    public String getTitle(){
        return driver.findElement(header).getText();
    }
    public String getSubmittedName(){
        return driver.findElement(submittedName).getText();
    }

}
