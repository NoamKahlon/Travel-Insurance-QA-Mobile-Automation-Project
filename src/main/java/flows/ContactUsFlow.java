package flows;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.ContactUsPage;
import utils.ContactUsData;

/**
 * Flow class for automating and verifying the 'Contact Us' form functionality.
 * Uses Allure for reporting instead of LoggerUtils.
 */
public class ContactUsFlow {

    // ========== Members ==========
    private final ContactUsPage contactUsPage;
    private final BasePage basePage;

    /**
     * Constructor
     */
    public ContactUsFlow(BasePage basePage) {
        this.contactUsPage = new ContactUsPage(basePage);
        this.basePage = basePage;
    }

    // ========== Actions ==========

    /**
     * Clicks on the 'Contact Us' button from the navigation or landing page.
     */
    public void clickOnContactUs() {


        basePage.waitForElementToAppear(By.id("topnav-burger"));
        basePage.attachScreenshot();
        basePage.click(By.id("topnav-burger"));


        contactUsPage.clickOnContactUs();
        Allure.step("✅ Clicked on Contact Us");
    }

    /**
     * Fills all form fields and submits the contact form.
     */
    public void fillAndSubmitContactForm() throws InterruptedException {
        contactUsPage.enterFullName();
        contactUsPage.enterPhone();
        contactUsPage.enterEmail();
        contactUsPage.enterSubject();
        contactUsPage.enterMessage();
        contactUsPage.pressSubmit();
        Allure.step("✅ Filled all fields and submitted the form");
    }

    // ========== Verifications ==========

    /**
     * Verifies that all form fields and contact section titles are displayed correctly.
     */
    public void verifyContactUsFormIsDisplayed() {
        assertField("Full Name", contactUsPage.isFullNameFieldDisplayed());
        assertField("Phone", contactUsPage.isPhoneFieldDisplayed());
        assertField("Email", contactUsPage.isEmailFieldDisplayed());
        assertField("Subject", contactUsPage.isSubjectFieldDisplayed());
        assertField("Message", contactUsPage.isMessageFieldDisplayed());

        assertTitle("Sales Center", contactUsPage.getSalesCenterTitleText(), ContactUsData.SALES_CENTER_TITLE);
        assertTitle("Support Center", contactUsPage.getSupportCenterTitleText(), ContactUsData.CLAIMS_CENTER_TITLE);
        assertTitle("Service Center", contactUsPage.getServiceCenterTitleText(), ContactUsData.SERVICE_CENTER_TITLE);
    }

    /**
     * Submits the form without filling any fields and verifies that all validation messages appear.
     */
    public void submitEmptyFormAndVerifyValidationMessages() throws InterruptedException {
        contactUsPage.pressSubmit();
        Allure.step("✅ Submitted empty form");

        String expectedError = ContactUsData.EXPECTED_EMPTY_FIELD_ERROR;
        String[] fieldIds = {"fullname", "phone", "email", "subject", "message"};

        for (String fieldId : fieldIds) {
            By errorLocator = By.id("error-" + fieldId);
            String actualError = basePage.getText(errorLocator);
            if (!expectedError.equals(actualError)) {
                throw new AssertionError("❌ Validation error for " + fieldId + " mismatch. Expected: '" + expectedError + "', got: '" + actualError + "'");
            } else {
                Allure.step("✅ Validation error for " + fieldId + " matches expected: " + actualError);
            }
        }
    }

    /**
     * Verifies the 'Thank You' screen is displayed after successful submission.
     */
    public void verifyThankYouScreenDisplayed() {
        if (!contactUsPage.isThankYouMessageVisible()) {
            throw new AssertionError("❌ Thank you message is NOT visible!");
        } else {
            Allure.step("✅ Thank you message is visible");
        }

        if (!contactUsPage.isBackButtonVisible()) {
            throw new AssertionError("❌ Back to home button is NOT visible!");
        } else {
            Allure.step("✅ Back to home button is visible");
        }
    }

    // ========== Private Helpers ==========

    private void assertField(String name, boolean isDisplayed) {
        if (!isDisplayed) {
            throw new AssertionError("❌ " + name + " field is NOT displayed");
        } else {
            Allure.step("✅ " + name + " field is displayed");
        }
    }

    private void assertTitle(String name, String actual, String expected) {
        if (!actual.trim().equals(expected.trim())) {
            throw new AssertionError("❌ " + name + " title mismatch. Expected: '" + expected + "', got: '" + actual + "'");
        } else {
            Allure.step("✅ " + name + " title matches: " + actual);
        }
    }
}
