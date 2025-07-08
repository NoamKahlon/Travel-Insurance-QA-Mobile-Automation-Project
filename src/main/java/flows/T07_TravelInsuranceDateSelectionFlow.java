package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T07_TravelInsuranceDateSelectionPage;

/**
 * Flow class for handling the travel insurance date selection step.
 */
public class T07_TravelInsuranceDateSelectionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T07_TravelInsuranceDateSelectionPage dateSelectionPage;
    private final T06_TravelInsuranceDestinationSelectionFlow destinationSelectionFlow;

    // ========== Constructor ==========
    public T07_TravelInsuranceDateSelectionFlow(BasePage basePage) {
        this.basePage = basePage;
        this.dateSelectionPage = new T07_TravelInsuranceDateSelectionPage(basePage);
        this.destinationSelectionFlow = new T06_TravelInsuranceDestinationSelectionFlow(basePage);
    }

    // ========== Actions ==========

    public void navigateToDateSelectionStep() throws Exception {
        destinationSelectionFlow.navigateToDestinationSelectionStep();
        destinationSelectionFlow.clickEuropeCheckBox();
        dateSelectionPage.clickContinueButton();
    }

    public void pickDates() {
        dateSelectionPage.pickDates();
    }

    public void clickContinueButton() {
        dateSelectionPage.clickContinueButton();
    }

    // ========== Verifications ==========

    public void verifyDateSelection() {

        // ✅ Start date
        boolean isStartVisible = dateSelectionPage.verifyStartDateFieldDisplayed();
        if (!isStartVisible) {
            throw new AssertionError("❌ Start date field is NOT visible");
        }
        Allure.step("✅ Start date field is visible");

        // ✅ End date
        boolean isEndVisible = dateSelectionPage.verifyEndDateFieldDisplayed();
        if (!isEndVisible) {
            throw new AssertionError("❌ End date field is NOT visible");
        }
        Allure.step("✅ End date field is visible");

        // ✅ Back button
        boolean backButtonVisible = dateSelectionPage.isBackButtonVisible();
        if (!backButtonVisible) {
            throw new AssertionError("❌ 'Back' button is missing");
        }
        Allure.step("✅ 'Back' button is visible");

        // ✅ Continue button
        boolean continueButtonVisible = dateSelectionPage.isContinueButtonVisible();
        if (!continueButtonVisible) {
            throw new AssertionError("❌ 'Continue' button is missing");
        }
        Allure.step("✅ 'Continue' button is visible");
    }

    public void verifyCurrentAndPreviousStepsByColor() {
        destinationSelectionFlow.verifyCurrentAndPreviousStepsByColor();
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("2/9", "מתי נוסעים?");
    }
}
