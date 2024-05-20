package webtest.demoqa.com.tasks.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxPage {
    WebDriver driver;
    By title = By.xpath("//h1[@class='text-center']");
    By expandAll = By.xpath("//button[@title='Expand all']");
    By collapseAll = By.xpath("//button[@title='Collapse all']");
    By home = By.xpath("//input[@id='tree-node-home']/following-sibling::span[@class='rct-checkbox']");
    By documents = By.xpath("//span[text()='Documents']");
    By react = By.xpath("//span[text()='React']");
    By resultList = By.xpath("//div[@id='result']//span[@class='text-success']");
    By homeRoot = By.xpath("//span[text()='Home']/parent::label/preceding-sibling::button");
    By desktopRoot = By.xpath("//span[text()='Desktop']/parent::label/preceding-sibling::button");
    By documentsRoot = By.xpath("//span[text()='Documents']/parent::label/preceding-sibling::button");
    By downloadsRoot = By.xpath("//span[text()='Downloads']/parent::label/preceding-sibling::button");
    By workspaceRoot = By.xpath("//span[text()='WorkSpace']/parent::label/preceding-sibling::button");

    public CheckBoxPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOnHome(){
        driver.findElement(home).click();
    }
    public void clickOnExpandAll(){
        driver.findElement(expandAll).click();
    }
    public void expandHomeRoot(){
        driver.findElement(homeRoot).click();
    }
    public void clickOnDocuments(){
        driver.findElement(documents).click();
    }
    public void expandDocumentsRoot(){
        driver.findElement(documentsRoot).click();
    }
    public void expandWorkSpace(){
        driver.findElement(workspaceRoot).click();
    }
    public void chooseReact(){
        driver.findElement(react).click();
    }
    public List<WebElement> returnResultList(){
        List<WebElement> result;
        result = driver.findElements(resultList);
        return result;
    }

}
