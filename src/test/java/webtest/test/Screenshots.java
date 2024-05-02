package webtest.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots {
    //public void takeScreenShot(String fileName){
    //    // Version 1 : simple approach how to take a screen
    //    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //    try {
    //        FileHandler.copy(file, new File("screens/" + fileName + ".jpg"));
    //    } catch (IOException e) {
    //        throw new RuntimeException(e);
    //    }
    //}
    public static void takeScreenShot(WebDriver driver, String fileNamePrefix) {
        // Version 2 : simple approach how to take a screen
        // Generate a timestamp for uniqueness
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Create a screenshot object
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination directory for saving screenshots
        String directoryPath = "screenshots/";
        File directory = new File(directoryPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Failed to create directory for screenshots");
            }
        }

        // Define the destination file path with unique timestamp
        String filePath = directoryPath + fileNamePrefix + "_" + timeStamp + ".jpg";
        File destinationFile = new File(filePath);

        // Copy the screenshot to the destination file
        try {
            Files.copy(screenshot.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + filePath, e);
        }
    }
}
