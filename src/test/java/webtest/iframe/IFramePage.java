package webtest.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IFramePage {
    WebDriver driver;

    private By button1 = By.xpath("//button[text()='Go cross-site (simple page)']");
    private By button2 = By.xpath("//button[text()='Go cross-site (complex page)']");
    private By button3 = By.xpath("//button[text()='Go same-site']");
    private By frameWindow = By.xpath("//iframe[@id='frame1']");
    private By windowTitle = By.xpath("//h1");

    public IFramePage (WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getiFrame() {
        return driver.findElement(frameWindow);
    }

    public void clickOnButton1() {
        driver.findElement(button1).click();
    }
    public void clickOnButton2() {
        driver.findElement(button2).click();
    }
    public void clickOnButton3() {
        driver.findElement(button3).click();
    }

    public void switchToFrame() {
        WebElement iframeElement = driver.findElement(frameWindow);
        driver.switchTo().frame(iframeElement);
    }

    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
    }

}
