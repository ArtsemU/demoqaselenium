package webtest.demoqa.com.tasks.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.io.*;
// ---------------


public class UploadDownloadPage {
    private WebDriver webDriver;
    By uploadFile = By.xpath("//input[@id='uploadFile']");
    By uploadedFile = By.xpath("//p[@id='uploadedFilePath']");
    By downloadFile = By.xpath("//a[@id='downloadButton']");
    private final String FOLDER_PATH = "C:\\Users\\48452\\Downloads";
    private final String UPLOAD_FILE = "sampleFile.jpeg";

    public UploadDownloadPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void uploadFile(String path){
        webDriver.findElement(uploadFile).sendKeys(path);
    }
    public String getUploadedFile(){
        return webDriver.findElement(uploadedFile).getText();
    }
    public String getUploadFileName(){
        String[] arr = getUploadedFile().split("\\\\");
        return arr[arr.length - 1];
    }
    public String getDownloadLink(){
        String downloadLink = webDriver.findElement(downloadFile).getAttribute("href");
        return downloadLink;
    }
    public void download(){
        webDriver.findElement(downloadFile).click();
        File downloadedFile = new File("src/Download" + File.separator + "filename.jpeg"); // Change filename.ext to the actual filename
        if(downloadedFile.exists()) {
            System.out.println("File downloaded successfully to: " + downloadedFile.getAbsolutePath());
        } else {
            System.out.println("File download failed or not found.");
        }
    }

    public boolean isFileExist(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File file = new File(FOLDER_PATH, UPLOAD_FILE);

        // Check if the file exists
        if(file.exists()) {
            System.out.println("File exists in the folder.");
            return true;
        } else {
            System.out.println("File does not exist in the folder.");
            return false;
        }
    }
    public void removeFile() {
        // Create a File object with the path to the file
        File file = new File(FOLDER_PATH, UPLOAD_FILE);

        // Check if the file exists
        if (file.exists()) {
            // If the file exists, delete it
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist in the folder.");
        }
    }
}
