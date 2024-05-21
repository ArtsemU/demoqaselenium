package webtest.demoqa.com.tasks.elements.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.io.*;
// ---------------


public class UploadDownloadPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(UploadDownloadPage.class);
    private WebDriver webDriver;
    By uploadFile = By.xpath("//input[@id='uploadFile']");
    By uploadedFile = By.xpath("//p[@id='uploadedFilePath']");
    By downloadFile = By.xpath("//a[@id='downloadButton']");
    private final String FOLDER_PATH = "C:\\Users\\48452\\Downloads";
    private final String UPLOAD_FILE = "sampleFile.jpeg";

    public UploadDownloadPage(WebDriver driver) {
        super(driver);
        logger.info("UploadDownloadPage initialized");
    }
    public void uploadFile(String path){
        logger.info("Up;oad a file with path :" + path);
        waitForElementToBeVisible(uploadFile).sendKeys(path);
    }
    public String getUploadedFile(){
        return waitForElementToBeVisible(uploadedFile).getText();
    }
    public String getUploadFileName(){
        logger.info("Get uploaded file name");
        String[] arr = getUploadedFile().split("\\\\");
        return arr[arr.length - 1];
    }
    public String getDownloadLink(){
        logger.info("Get download link");
        return waitForElementToBeVisible(downloadFile).getAttribute("href");
    }
    public void download(){
        logger.info("Down;oad a file");
        waitForElementToBeClickable(downloadFile).click();
        File downloadedFile = new File("src/Download" + File.separator + "filename.jpeg"); // Change filename.ext to the actual filename
        if(downloadedFile.exists()) {
            logger.info("File downloaded successfully to: " + downloadedFile.getAbsolutePath());
        } else {
            logger.error("File download failed or not found.");
        }
    }

    public boolean isFileExist(){
        logger.info("Check is a file exist");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        File file = new File(FOLDER_PATH, UPLOAD_FILE);

        // Check if the file exists
        if(file.exists()) {
            logger.info("File exists in the folder");
            return true;
        } else {
            logger.info("File does not exist in the folder");
            return false;
        }
    }
    public void removeFile() {
        logger.info("Remove a file");
        // Create a File object with the path to the file
        File file = new File(FOLDER_PATH, UPLOAD_FILE);

        // Check if the file exists
        if (file.exists()) {
            // If the file exists, delete it
            if (file.delete()) {
                logger.info("File deleted successfully.");
            } else {
                logger.error("Failed to delete the file.");
            }
        } else {
            logger.info("File does not exist in the folder.");
        }
    }
}
