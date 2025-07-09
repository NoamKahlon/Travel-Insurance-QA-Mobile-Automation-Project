package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.CheckingTheExistenceOfCarInsurancePage;
import utils.CheckingTheExistenceOfCarInsuranceData;

/**
 * Flow class for verifying car insurance existence using license number and date.
 * Uses Allure for reporting.
 */
public class CheckingTheExistenceOfCarInsuranceFlow {

    // ========== Members ==========
    private final CheckingTheExistenceOfCarInsurancePage insurancePage;

    /**
     * Constructor
     * @param basePage shared BasePage
     */
    public CheckingTheExistenceOfCarInsuranceFlow(BasePage basePage) {
        this.insurancePage = new CheckingTheExistenceOfCarInsurancePage(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates to the insurance check section and clicks the entry point.
     */
    public void navigateToInsuranceCheckSection() throws Exception {
       Thread.sleep(7500);
        insurancePage.scrollToCheckingTheExistenceOfCarInsurance();
        Allure.step("✅ Scrolled to insurance check section");

        insurancePage.clickCheckingTheExistenceOfCarInsurance();
        Allure.step("✅ Clicked insurance check button");
    }

    /**
     * Inputs license number (valid/invalid) and date, then triggers search.
     * @param useValidNumber true for valid license, false for uninsured license
     */
    public void searchInsurance(boolean useValidNumber) throws InterruptedException {
        if (useValidNumber) {
            insurancePage.enterLicenseNumber();
            Allure.step("✅ Entered valid license number");
        } else {
            insurancePage.enterInvalidLicenseNumber();
            Allure.step("✅ Entered invalid/uninsured license number");
        }

        insurancePage.enterDate();
        Allure.step("✅ Entered date");

        insurancePage.clickSearch();
        Allure.step("✅ Clicked search button");
    }

    // ========== Verifications ==========

    /**
     * Verifies error message text and styling for invalid license.
     */
    public void verifyInsuranceNotFoundError() {
        String expected = CheckingTheExistenceOfCarInsuranceData.INVALID_LICENSE_ERROR_TEXT;
        String actual = insurancePage.getErrorText();

        if (!expected.equals(actual)) {
            throw new AssertionError("❌ Expected error: '" + expected + "' but got: '" + actual + "'");
        } else {
            Allure.step("✅ Error text matches expected: " + actual);
        }

        if (!insurancePage.isErrorTextRed()) {
            throw new AssertionError("❌ Error text is NOT red");
        } else {
            Allure.step("✅ Error text is red as expected");
        }
    }

    /**
     * Verifies success message text and styling for valid license.
     */
    public void verifyInsuranceFoundSuccess() {
        String expected = CheckingTheExistenceOfCarInsuranceData.VALID_LICENSE_SUCCESS_TEXT;
        String actual = insurancePage.getSuccessText();

        if (!expected.equals(actual)) {
            throw new AssertionError("❌ Expected success: '" + expected + "' but got: '" + actual + "'");
        } else {
            Allure.step("✅ Success text matches expected: " + actual);
        }

        if (!insurancePage.isSuccessTextGreen()) {
            throw new AssertionError("❌ Success text is NOT green");
        } else {
            Allure.step("✅ Success text is green as expected");
        }
    }
}
