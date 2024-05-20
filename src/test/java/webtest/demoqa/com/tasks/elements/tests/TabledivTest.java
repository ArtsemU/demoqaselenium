package webtest.demoqa.com.tasks.elements.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webtest.demoqa.com.tasks.elements.pages.TabledivPage;
import webtest.test.Webtable;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TabledivTest {
    WebDriver driver;
    List<Webtable> table;
    TabledivPage tabledivPage;
    JavascriptExecutor jse;

    @BeforeEach
    public  void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/webtables");
        jse = (JavascriptExecutor)driver;
        tabledivPage = new TabledivPage(driver);
        jse.executeScript("window.scrollBy(0,300)");
    }
    @AfterEach
    public void close(){
        driver.quit();
    }

    @Test
    @DisplayName("Test : read default table")
    public void testReadTable(){
        table = tabledivPage.refreshTable();
        int count = table.size();
        assertEquals(count, 3, "Expected value 3 - default numbers rows");    }

    @Test
    @DisplayName("Test : add one row")
    public void testAddNewRow(){
        tabledivPage.fillInRegForm();
        table = tabledivPage.refreshTable();
        int count = table.size();
        assertEquals(count, 4, "Expected value 4");
    }
    @Test
    @DisplayName("Test : remove a row")
    public void testRemoveRow(){
        table = tabledivPage.refreshTable();
        tabledivPage.removeRow(table.get(0).getAction());
        assertEquals(tabledivPage.refreshTable().size(), 2, "One row was deleted. Expected in total : 2");
    }
    @Test
    @DisplayName("Test : update name in first row")
    public void testUpdateCell(){
        String updatedName = "Mikasa";
        table = tabledivPage.refreshTable();
        tabledivPage.updateRow(table.get(0).getAction());
        tabledivPage.updateFName(updatedName);
        table = tabledivPage.refreshTable();
        assertEquals(table.get(0).getFirstName(), updatedName, "Expected updated First name");
    }
    @Test
    @DisplayName("Test : sorting by Age")
    public void testSortingByAge(){
        tabledivPage.sortingByAge();
        table = tabledivPage.refreshTable();
        List<Integer> ages = table.stream()
                .map(Webtable::getAge) // Получение значения поля age для каждого объекта
                .collect(Collectors.toList());
        boolean isSortedAscending = IntStream.range(0, ages.size() - 1)
                .allMatch(i -> ages.get(i) <= ages.get(i + 1));
        assertTrue(isSortedAscending, "Expected True");
    }
}
