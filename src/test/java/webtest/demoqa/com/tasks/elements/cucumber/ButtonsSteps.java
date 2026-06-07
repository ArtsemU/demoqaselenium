package webtest.demoqa.com.tasks.elements.cucumber;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import webtest.demoqa.com.tasks.elements.pages.ButtonsPage;


public class ButtonsSteps {

    private static final Logger logger = LogManager.getLogger(ButtonsSteps.class);
    private final WebDriverContext ctx;
    private String lastClickType; // запоминаем тип клика для шага Then

    public ButtonsSteps(WebDriverContext ctx) {
        this.ctx = ctx;
    }

    // --- Background steps ---

    @Given("the user opens the page {string}")
    public void openPage(String url) {
        ctx.driver.get(url);
        ctx.buttonsPage = new ButtonsPage(ctx.driver);
        logger.info("Setup method Before");
    }

    @Given("the page is scrolled down by {int} pixels")
    public void scrollDown(int pixels) {
        JavascriptExecutor jse = (JavascriptExecutor) ctx.driver;
        jse.executeScript("window.scrollBy(0," + pixels + ")");
    }

    // --- When steps ---

    @When("the user performs a double click on the button")
    public void doubleClick() {
        logger.info("Test Double click started");
        ctx.buttonsPage.clickOnDouble();
        lastClickType = "double";
    }

    @When("the user performs a right click on the button")
    public void rightClick() {
        logger.info("Test Right click started");
        ctx.buttonsPage.clickOnRight();
        lastClickType = "right";
    }

    @When("the user performs a dynamic click on the button")
    public void dynamicClick() {
        logger.info("Test Dynamic click started");
        ctx.buttonsPage.clickMe();
        lastClickType = "dynamic";
    }

    // --- Then step (один на все три сценария) ---

    @Then("the message {string} is displayed")
    public void verifyMessage(String expected) {
        String actual;

        if ("double".equals(lastClickType)) {
            actual = ctx.buttonsPage.getDoubleClickMessage();
        } else if ("right".equals(lastClickType)) {
            actual = ctx.buttonsPage.getRightClickMessage();
        } else if ("dynamic".equals(lastClickType)) {
            actual = ctx.buttonsPage.getDynamicClickMessage();
        } else {
            throw new IllegalStateException("Unknown click type: " + lastClickType);
        }
        Assertions.assertEquals(expected, actual, "Message text mismatch");
        logger.info("Test finished. Message verified: " + actual);
    }
}
