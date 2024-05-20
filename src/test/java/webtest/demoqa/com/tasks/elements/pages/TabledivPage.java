package webtest.demoqa.com.tasks.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webtest.test.Webtable;

import java.util.ArrayList;
import java.util.List;

public class TabledivPage {
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
        this.driver = driver;
    }

    public void sortingByAge(){
        driver.findElement(headerAge).click();
    }
    public void removeRow(WebElement cell){
        cell.findElement(actionDelete).click();
    }
    public void updateRow(WebElement cell){
        cell.findElement(actionUpdate).click();
    }
    public void openClosePopup(){
        driver.findElement(addRow).click();
        System.out.println("Popup title : " + driver.findElement(regFormTitle).getText());
        driver.findElement(regClose).click();
    }
    public void updateFName(String name){
        driver.findElement(inputFName).clear();
        driver.findElement(inputFName).sendKeys(name);
        driver.findElement(regSubmit).click();
    }
    public void fillInRegForm(){
        driver.findElement(addRow).click();
        driver.findElement(inputFName).sendKeys("John");
        driver.findElement(inputLName).sendKeys("Doe");
        driver.findElement(inputLName).sendKeys("Doe");
        driver.findElement(inputEmail).sendKeys("John.Doe@google.com");
        driver.findElement(inputAge).sendKeys("22");
        driver.findElement(inputSalary).sendKeys("3700");
        driver.findElement(inputDepartment).sendKeys("QA Department");
        driver.findElement(regSubmit).click();
    }
    public List<Webtable> refreshTable() {
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
        return webtables.size();
    }

}

