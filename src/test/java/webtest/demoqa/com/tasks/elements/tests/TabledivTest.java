package webtest.demoqa.com.tasks.elements.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class TabledivTest extends BaseTest{
    List<Webtable> table;
    TabledivPage tabledivPage;
    private static final Logger logger = LogManager.getLogger(TabledivTest.class);

    @BeforeEach
    public void setUp(){
        logger.info("Setup method BeforeEach");
        super.setUp();
        driver.get("https://demoqa.com/webtables");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        tabledivPage = new TabledivPage(driver);
        jse.executeScript("window.scrollBy(0,300)");
    }

    @Test
    @DisplayName("Test : read default table")
    public void testReadTable(){
        logger.info("Test testReadTable started");
        table = tabledivPage.refreshTable();
        int count = table.size();
        logger.info("Count :" + count);
        assertEquals(count, 3, "Expected value 3 - default numbers rows");
        logger.info("Test testReadTable finished");
    }

    @Test
    @DisplayName("Test : add one row")
    public void testAddNewRow(){
        logger.info("Test testAddNewRow started");
        tabledivPage.fillInRegForm();
        table = tabledivPage.refreshTable();
        int count = table.size();
        logger.info("table size :" + count);
        assertEquals(count, 4, "Expected value 4");
        logger.info("Test testAddNewRow finished");
    }
    @Test
    @DisplayName("Test : remove a row")
    public void testRemoveRow(){
        logger.info("Test testRemoveRow started");
        table = tabledivPage.refreshTable();
        tabledivPage.removeRow(table.get(0).getAction());
        int count = tabledivPage.refreshTable().size();
        logger.info("Count :" + count);
        assertEquals(count, 2, "One row was deleted. Expected in total : 2");
        logger.info("Test testRemoveRow finished");
    }
    @Test
    @DisplayName("Test : update name in first row")
    public void testUpdateCell(){
        logger.info("Test testUpdateCell finished");
        String updatedName = "NewName";
        table = tabledivPage.refreshTable();
        tabledivPage.updateRow(table.get(0).getAction());
        tabledivPage.updateFName(updatedName);
        table = tabledivPage.refreshTable();
        String name = table.get(0).getFirstName();
        logger.info("Updasted name :" + name);
        assertEquals(name, updatedName, "Expected updated First name");
        logger.info("Test testUpdateCell finished");
    }
    @Test
    @DisplayName("Test : sorting by Age")
    public void testSortingByAge(){
        logger.info("Test testSortingByAge started");
        tabledivPage.sortingByAge();
        table = tabledivPage.refreshTable();
        List<Integer> ages = table.stream()
                .map(Webtable::getAge) // Get field value for each object
                .collect(Collectors.toList());
        boolean isSortedAscending = IntStream.range(0, ages.size() - 1)
                .allMatch(i -> ages.get(i) <= ages.get(i + 1));
        assertTrue(isSortedAscending, "Expected True");
        logger.info("Test testSortingByAge finished");
    }
}
