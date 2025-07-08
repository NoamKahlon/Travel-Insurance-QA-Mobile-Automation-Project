package tests;

import flows.ContactUsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTests extends BaseTest {

    private ContactUsFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        this.flow = new ContactUsFlow(basePage);
        flow.clickOnContactUs();
    }

    @Test(description = "TC-094 - Verify that Contact Us page loads with form fields visible")
    public void test_TC094_ContactUsPageIsDisplayed() {
            flow.verifyContactUsFormIsDisplayed();
    }

    @Test(description = "TC-095 - Verify validation messages appear when Contact Us form is submitted empty")
    public void test_TC095_ContactFormValidationOnEmptySubmit() throws InterruptedException {
            flow.verifyContactUsFormIsDisplayed();
            flow.submitEmptyFormAndVerifyValidationMessages();
    }

    @Test(description = "TC-096 - Verify successful submission of Contact Us form and display of thank you screen")
    @Description("Submit form with valid details and verify thank you message and Back to Home button are displayed")
    public void test_TC096_SuccessfulContactFormSubmission() throws InterruptedException {
            flow.verifyContactUsFormIsDisplayed();
            flow.fillAndSubmitContactForm();
            flow.verifyThankYouScreenDisplayed();
    }
}
