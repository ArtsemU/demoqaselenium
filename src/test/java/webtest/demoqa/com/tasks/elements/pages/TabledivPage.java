package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webtest.test.Webtable;

import java.util.ArrayList;
import java.util.List;

public class TabledivPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(TabledivPage.class);
    WebDriver driver;
    List<Webtable> webtables;
    By tableCells = By.xpath("//div[@role='gridcell']");
    By tableRows = By.className("rt-tr-group");
    By tableCells2 = By.className("rt-td");
    // popup
    By addRow = By.xpath("//button[@id='addNewRecordButton']");
    By regFormTitle = By.xpath("//div[@id='registration-form-modal']");
    By regSubmit = By.xpath("//button[@id='submit']");
    By regClose = By.xpath("//button[@class='close']");
    By inputFName = By.xpath("//input[@id='firstName']");
    By inputLName = By.xpath("//input[@id='lastName']");
    By inputEmail = By.xpath("//input[@id='userEmail']");
    By inputAge= By.xpath("//input[@id='age']");
    By inputSalary= By.xpath("//input[@id='salary']");
    By inputDepartment= By.xpath("//input[@id='department']");
    By actionDelete = By.xpath("//span[@title='Delete']");
    By actionUpdate = By.xpath("//span[@title='Edit']");
    By headerAge = By.xpath("//div[text()='Age']");

    public TabledivPage(WebDriver driver){
        super(driver);
        logger.info("TabledivPage initialized");
    }

    public void sortingByAge(){
        logger.info("Soring by Age");
        waitForElementToBeClickable(headerAge).click();
    }
    public void removeRow(WebElement cell){
        logger.info("Remove a row");
        waitForElementToBeClickable(actionDelete).click();
    }
    public void updateRow(WebElement cell){
        logger.info("Update a row");
        waitForElementToBeClickable(actionUpdate).click();
    }
    public void openClosePopup(){
        logger.info("Open close method");
        waitForElementToBeClickable(addRow).click();
        waitForElementToBeClickable(regClose).click();
    }
    public void updateFName(String name){
        logger.info("Update FName");
        waitForElementToBeClickable(inputFName).clear();
        waitForElementToBeClickable(inputFName).sendKeys(name);
        waitForElementToBeClickable(regSubmit).click();
    }
    public void fillInRegForm(){
        logger.info("Fill in Reg Form");
        waitForElementToBeClickable(addRow).click();
        waitForElementToBeClickable(inputFName).sendKeys("John");
        waitForElementToBeClickable(inputLName).sendKeys("Doe");
        waitForElementToBeClickable(inputEmail).sendKeys("John.Doe@google.com");
        waitForElementToBeClickable(inputAge).sendKeys("22");
        waitForElementToBeClickable(inputSalary).sendKeys("3700");
        waitForElementToBeClickable(inputDepartment).sendKeys("QA Department");
        waitForElementToBeClickable(regSubmit).click();
        logger.info("Form was filled in and submitted");
    }
    public List<Webtable> refreshTable() {
        logger.info("Refresh a table");
        List<WebElement> rows = driver.findElements(tableRows);
        webtables = new ArrayList<>();
        int rowCount = rows.size();

        for (int i = 0; i < rowCount; i++) {
            WebElement row = rows.get(i);

            List<WebElement> cells = row.findElements(tableCells2);

            try {
                Webtable webtable = new Webtable();
                webtable.setFirstName(cells.get(0).getText());
                webtable.setLastName(cells.get(1).getText());
                webtable.setAge(Integer.parseInt(cells.get(2).getText()));
                webtable.setEmail(cells.get(3).getText());
                webtable.setSalary(Integer.parseInt(cells.get(4).getText()));
                webtable.setDepartment(cells.get(5).getText());
                webtable.setAction(cells.get(6));
                webtables.add(webtable);
            } catch (NumberFormatException e){
                break;
            }
        }
        return webtables;
    }

    public Integer getTableSize(){
        logger.info("Get table size");
        return webtables.size();
    }

}

