package webtest.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestWaits {

    private WebDriver driver;
    private String baseUrl;
    private WebElement element;

    @BeforeAll
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://www.google.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void implicit() {


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        WebElement added = driver.findElement(By.id("box0"));
        //File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        assertEquals("redbox", added.getDomAttribute("class"));
        //assertTrue.assertEquals("redbox", added.getDomAttribute("class"));
    }
    @Test
    public void explicit() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement revealed = driver.findElement(By.id("revealed"));
        driver.findElement(By.id("reveal")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> revealed.isDisplayed());

        revealed.sendKeys("Displayed");
        assertEquals("Displayed", revealed.getDomProperty("value"));
    }


}
