package bddtests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import webtest.demoqa.com.tasks.forms.FormsPage;
import webtest.demoqa.com.tasks.forms.SubmittedForm;
import webtest.enums.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormsSteps {

    private static final Logger logger = LogManager.getLogger(FormsSteps.class);
    private static final String FORM_URL = "https://demoqa.com/automation-practice-form";
    private static final String UPLOAD_PATH = "C:\\Work\\selenium\\demoqaselenium\\src\\Upload\\";

    private final WebDriverContext ctx;

    public FormsSteps(WebDriverContext ctx) {
        this.ctx = ctx;
    }

    @Given("the user opens the practice form page")
    public void openPracticeFormPage() {
        ctx.driver.get(FORM_URL);
        ctx.formsPage = new FormsPage(ctx.driver);
        ctx.submittedForm = new SubmittedForm(ctx.driver);
        JavascriptExecutor jse = (JavascriptExecutor) ctx.driver;
        jse.executeScript("window.scrollBy(0,600)");
        logger.info("Opened practice form page");
    }

    @When("the user enters first name {string} and last name {string}")
    public void enterFirstAndLastName(String firstName, String lastName) {
        ctx.formsPage.setFName(firstName);
        ctx.formsPage.setLName(lastName);
    }

    @When("the user enters email {string}")
    public void enterEmail(String email) {
        ctx.formsPage.setEmail(email);
    }

    @When("the user selects gender {string}")
    public void selectGender(String gender) {
        ctx.formsPage.setGenderMale(Gender.valueOf(gender.toUpperCase()));
    }

    @When("the user enters mobile number {string}")
    public void enterMobileNumber(String mobileNumber) {
        ctx.formsPage.setMobNum(mobileNumber);
    }

    @When("the user sets date of birth to {string}")
    public void setDateOfBirth(String dob) {
        ctx.formsPage.setDob();
    }

    @When("the user selects subject {string}")
    public void selectSubject(String subject) {
        ctx.formsPage.setSubject(subject);
    }

    @When("the user selects hobbies {string} and {string}")
    public void selectHobbies(String firstHobby, String secondHobby) {
        JavascriptExecutor jse = (JavascriptExecutor) ctx.driver;
        jse.executeScript("window.scrollBy(0,600)");
        ctx.formsPage.setHobby();
    }

    @When("the user uploads the file {string}")
    public void uploadFile(String fileName) {
        ctx.formsPage.uploadPicture(UPLOAD_PATH + fileName);
    }

    @When("the user enters current address {string}")
    public void enterCurrentAddress(String address) {
        ctx.formsPage.setCurAddress(address.replace("\\n", "\n"));
    }

    @When("the user selects state {string} and city {string}")
    public void selectStateAndCity(String state, String city) {
        ctx.formsPage.setState(state);
        ctx.formsPage.setCity(city);
    }

    @When("the user submits the form")
    public void submitForm() {
        ctx.formsPage.submit();
    }

    @Then("the confirmation title should be {string}")
    public void verifyTitle(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getTitle());
    }

    @And("the submitted name should be {string}")
    public void verifyName(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getSubmittedName());
    }

    @And("the submitted email should be {string}")
    public void verifyEmail(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getSubmittedEmail());
    }

    @And("the submitted gender should be {string}")
    public void verifyGender(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getGender());
    }

    @And("the submitted mobile number should be {string}")
    public void verifyMobileNumber(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getNumber());
    }

    @And("the submitted date of birth should match {string}")
    public void verifyDob(String expected) {
        Assertions.assertTrue(areDatesEqual(ctx.submittedForm.getDob(), expected),
                String.format("Expected dob: %s, but received: %s", expected, ctx.submittedForm.getDob()));
    }

    @And("the submitted subject should be {string}")
    public void verifySubject(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getSbj());
    }

    @And("the submitted hobbies should be {string}")
    public void verifyHobbies(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getHobby());
    }

    @And("the submitted file name should be {string}")
    public void verifyFileName(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getFileName());
    }

    @And("the submitted address should be {string}")
    public void verifyAddress(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getAddress());
    }

    @And("the submitted state and city should be {string}")
    public void verifyStateCity(String expected) {
        Assertions.assertEquals(expected, ctx.submittedForm.getStateCity());
    }

    private static boolean areDatesEqual(String displayedDateString, String inputDateString) {
        SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd MMM,yyyy");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("ddMMMyyyy");
        try {
            Date displayedDate = displayDateFormat.parse(displayedDateString);
            Date inputDate = inputDateFormat.parse(inputDateString);
            return displayedDate.equals(inputDate);
        } catch (ParseException e) {
            logger.error("Error parsing the dates: " + e.getMessage());
            return false;
        }
    }
}
