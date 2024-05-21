package webtest.demoqa.com.tasks.elements.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import webtest.demoqa.com.tasks.elements.pages.CheckBoxPage;
import webtest.enums.CheckBoxElements;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckBoxTests extends BaseTest {
    private CheckBoxPage checkBoxPage;
    private static final Logger logger = LogManager.getLogger(CheckBoxTests.class);
    private boolean isInElementEnum(List<WebElement> elements) {
        logger.info("Check if <element> present in the Enum list");
        if (elements.isEmpty()) {
            return false;
        }

        for (WebElement element : elements) {
            boolean found = false;
            for (CheckBoxElements e : CheckBoxElements.values()) {
                if (e.getValue().equals(element.getText())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                logger.error("Missing element : " + element.getText());
                return false;
            }
        }
        return true;
    }

    private boolean isReact(List<WebElement> elements) {
        if (elements.size() != 1) {
            logger.error("Element size is incorrect");
            return false;
        }
        logger.info("Return true if element equals");
        return CheckBoxElements.REACT.getValue().equals(elements.get(0).getText());
    }

    @BeforeEach
    public void setUp() {
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/checkbox");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        checkBoxPage = new CheckBoxPage(driver);
        jse.executeScript("window.scrollBy(0,400)");
    }

    @Test
    @DisplayName("Test : Choose All")
    public void testChooseAll() {
        logger.info("Test testChooseAll started");
        checkBoxPage.clickOnHome();
        List<WebElement> list = checkBoxPage.getResultList();
        assertTrue(isInElementEnum(list), "Some element is missing");
        logger.info("Test testChooseAll finished");
    }

    @Test
    @DisplayName("Test : Expand root element and choose Documents")
    public void testExpandRootChooseDocuments() {
        logger.info("Test testExpandRootChooseDocuments started");
        checkBoxPage.expandHomeRoot();
        checkBoxPage.clickOnDocuments();
        List<WebElement> list = checkBoxPage.getResultList();
        assertTrue(isInElementEnum(list), "Some element is missing");
        logger.info("Test testExpandRootChooseDocuments finished");
    }

    @Test
    @DisplayName("Test : Expand root and choose React")
    public void testExpandRootChooseReact() {
        logger.info("Test testExpandRootChooseReact started");
        checkBoxPage.expandHomeRoot();
        checkBoxPage.expandDocumentsRoot();
        checkBoxPage.expandWorkSpace();
        checkBoxPage.chooseReact();
        List<WebElement> list = checkBoxPage.getResultList();
        assertTrue(isReact(list), "No such element");
        logger.info("Test testExpandRootChooseReact finished");
    }
}