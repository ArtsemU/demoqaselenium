package webtest.demoqa.com.tasks.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsPage {
    WebDriver driver;
    private By title = By.xpath("//h1[@class='text-center']");
    
    public FormsPage(WebDriver driver){
        this.driver = driver;
    }



}
