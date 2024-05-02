package webtest.demoqa.com.tasks.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.enums.CheckBoxElements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckBoxTests {
    WebDriver driver;
    CheckBoxPage checkBoxPage;
    JavascriptExecutor jse;

    private boolean isInElementEnum(List<WebElement> elements){
        if(elements.isEmpty()){
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
                System.out.println("Missing element : " + element.getText());
                return false;
            }
        }
        return true;
    }
    private boolean isReact(List<WebElement> elements){
        if(elements.size() != 1){
            return false;
        }
        return elements.get(0).getText().equals(CheckBoxElements.REACT.getValue());
    }

    @BeforeEach
    public  void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/checkbox");
        jse = (JavascriptExecutor)driver;
        checkBoxPage = new CheckBoxPage(driver);
    }

    @AfterEach
    public void close(){
        driver.quit();
    }

    @Test
    @DisplayName("Test : Choose All")
    public void testChooseAll(){
        //checkBoxPage = new CheckBoxPage(driver);
        jse.executeScript("window.scrollBy(0,300)");
        checkBoxPage.clickOnHome();
        checkBoxPage.returnResultList();
        List<WebElement> list = checkBoxPage.returnResultList();
        assertTrue(isInElementEnum(list), "Some element is missing");
    }

    @Test
    @DisplayName("Test : Expand root element and choose Documents")
    public void testExpandRootChooseDocuments(){
        //checkBoxPage = new CheckBoxPage(driver);
        jse.executeScript("window.scrollBy(0,400)");
        checkBoxPage.expandHomeRoot();
        checkBoxPage.clickOnDocuments();
        List<WebElement> list = checkBoxPage.returnResultList();
        assertTrue(isInElementEnum(list), "Some element is missing");
    }

    @Test
    @DisplayName("Test : Expand root and choose React")
    public void testExpandRootChooseReact(){
        //checkBoxPage = new CheckBoxPage(driver);
        jse.executeScript("window.scrollBy(0,400)");
        checkBoxPage.expandHomeRoot();
        checkBoxPage.expandDocumentsRoot();
        checkBoxPage.expandWorkSpace();
        checkBoxPage.chooseReact();
        List<WebElement> list = checkBoxPage.returnResultList();
        assertTrue(isReact(list), "No such element");
    }
}
