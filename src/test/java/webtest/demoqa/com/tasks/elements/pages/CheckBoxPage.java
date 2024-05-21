package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CheckBoxPage.class);
    private final By title = By.xpath("//h1[@class='text-center']");
    private final By expandAll = By.xpath("//button[@title='Expand all']");
    private final By collapseAll = By.xpath("//button[@title='Collapse all']");
    private final By home = By.xpath("//input[@id='tree-node-home']/following-sibling::span[@class='rct-checkbox']");
    private final By documents = By.xpath("//span[text()='Documents']");
    private final By react = By.xpath("//span[text()='React']");
    private final By resultList = By.xpath("//div[@id='result']//span[@class='text-success']");
    private final By homeRoot = By.xpath("//span[text()='Home']/parent::label/preceding-sibling::button");
    private final By desktopRoot = By.xpath("//span[text()='Desktop']/parent::label/preceding-sibling::button");
    private final By documentsRoot = By.xpath("//span[text()='Documents']/parent::label/preceding-sibling::button");
    private final By downloadsRoot = By.xpath("//span[text()='Downloads']/parent::label/preceding-sibling::button");
    private final By workspaceRoot = By.xpath("//span[text()='WorkSpace']/parent::label/preceding-sibling::button");

    public CheckBoxPage(WebDriver driver) {
        super(driver);
        logger.info("CheckBoxPage initialized");
    }

    public void clickOnHome() {
        logger.info("Click on Home");
        waitForElementToBeClickable(home).click();
    }

    public void clickOnExpandAll() {
        logger.info("Click on Expand All");
        waitForElementToBeClickable(expandAll).click();
    }

    public void expandHomeRoot() {
        logger.info("Click on Expand home root");
        waitForElementToBeClickable(homeRoot).click();
    }

    public void clickOnDocuments() {
        logger.info("Click on Documents");
        waitForElementToBeClickable(documents).click();
    }

    public void expandDocumentsRoot() {
        logger.info("Click on Expand Documents root");
        waitForElementToBeClickable(documentsRoot).click();
    }

    public void expandWorkSpace() {
        logger.info("Click on Expand WorkSpace");
        waitForElementToBeClickable(workspaceRoot).click();
    }

    public void chooseReact() {
        logger.info("Click on a root");
        waitForElementToBeClickable(react).click();
    }

    public List<WebElement> getResultList() {
        logger.info("Get a result List");
        return waitForElementToBeVisible(resultList).findElements(resultList);
    }

    public String getTitle() {
        logger.info("Get a text");
        return waitForElementToBeVisible(title).getText();
    }
}
