package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T010_TravelInsuranceSpecialCoveragesPage;

import java.util.List;

import static utils.TravelInsuranceData.EXPECTED_COVERAGE_TITLES;

/**
 * Flow class for handling the "Special Coverages" step in the travel insurance wizard.
 * Includes coverage selection, title validation, and navigation handling.
 */
public class T010_TravelInsuranceSpecialCoveragesFlow {

    private final BasePage basePage;

    private final T010_TravelInsuranceSpecialCoveragesPage specialCoveragesPage;

    private final T09_TravelInsuranceCoveragesForYouFlow coveragesForYouFlow;

    public T010_TravelInsuranceSpecialCoveragesFlow(BasePage basePage) {
        this.basePage = basePage;
        this.specialCoveragesPage = new T010_TravelInsuranceSpecialCoveragesPage(basePage);
        this.coveragesForYouFlow = new T09_TravelInsuranceCoveragesForYouFlow(basePage);
    }

    // ========== Navigation ==========

    public void navigateToSpecialCoveragesStep() throws Exception {
        coveragesForYouFlow.navigateToCoveragesForYouStep();
        coveragesForYouFlow.chooseMedicalCoverage();
        coveragesForYouFlow.clickContinue();
    }

    // ========== Verifications ==========

    public void verifyTitleTexts() {
        Allure.step("Verify special coverage titles match expected", () -> {
            List<String> actualTitles = specialCoveragesPage.getActualTitles();
            List<String> expectedTitles = EXPECTED_COVERAGE_TITLES;
            assert actualTitles.equals(expectedTitles) :
                    "❌ Title texts mismatch!\nExpected: " + expectedTitles + "\nActual: " + actualTitles;
        });
    }

    public void verifyCurrentAndPreviousStepsByColor() {
        coveragesForYouFlow.verifyCurrentAndPreviousStepsByColor();
    }

    public void verifyNavigationButtonsAreVisible() {
        coveragesForYouFlow.verifyNavigationButtonsAreVisible();
    }

    public void showFinalPrice() {
        coveragesForYouFlow.showFinalPrice();
    }

    // ========== Actions ==========

    public void addSpecialCoverage() {
        Allure.step("Add 'Extreme Sports' special coverage", () -> {
            specialCoveragesPage.clickExtremeSportsButton();
            specialCoveragesPage.clickCustomerName();
            specialCoveragesPage.clickAddCoverage();
        });
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("5/9","כיסויים מיוחדים");
    }
}
