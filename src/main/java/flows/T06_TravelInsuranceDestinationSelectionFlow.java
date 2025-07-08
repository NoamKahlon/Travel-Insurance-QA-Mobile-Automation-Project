package flows;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.T06_TravelInsuranceDestinationSelectionPage;

import java.util.List;

/**
 * Flow class for handling the destination selection step
 * in the travel insurance wizard.
 */
public class T06_TravelInsuranceDestinationSelectionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T06_TravelInsuranceDestinationSelectionPage destinationSelectionPage;
    private final T03_TravelInsurancePassengersFromIsraelQuestionFlow passengersFromIsraelQuestionFlow;

    /**
     * Constructor
     * @param basePage Shared BasePage instance
     */
    public T06_TravelInsuranceDestinationSelectionFlow(BasePage basePage) {
        this.basePage = basePage;
        this.destinationSelectionPage = new T06_TravelInsuranceDestinationSelectionPage(basePage);
        this.passengersFromIsraelQuestionFlow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates to the destination selection step by answering previous steps.
     */
    public void navigateToDestinationSelectionStep() throws Exception {
        passengersFromIsraelQuestionFlow.navigateToPassengersFromIsraelQuestionStep();
        passengersFromIsraelQuestionFlow.clickPassengersFromIsraelOption();
    }

    /**
     * Clicks on the 'Europe' destination checkbox.
     */
    public void clickEuropeCheckBox() {
        destinationSelectionPage.clickEuropeCheckBox();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the destination step UI elements are present and functional.
     */
    public void verifyDestinationSelectionStep() {
        List<WebElement> destinations = destinationSelectionPage.getDestinationOptions();
        boolean hasOptions = !destinations.isEmpty();
        Allure.step(
                hasOptions
                        ? "✅ Destination options loaded successfully (" + destinations.size() + " options)"
                        : "❌ No destination options found"
        );

        boolean titleDisplayed = destinationSelectionPage.isWhereAreYouGoingTitleDisplayed();
        Allure.step(
                titleDisplayed
                        ? "✅ 'Where are you going?' title is displayed"
                        : "❌ 'Where are you going?' title is NOT displayed"
        );

        boolean tooltipVisible = destinationSelectionPage.isTooltipIconVisible();
        Allure.step(
                tooltipVisible
                        ? "✅ Tooltip icon is visible"
                        : "❌ Tooltip icon is missing"
        );

        boolean backButtonVisible = destinationSelectionPage.isBackButtonVisible();
        Allure.step(
                backButtonVisible
                        ? "✅ 'Back' button is visible"
                        : "❌ 'Back' button is missing"
        );

        boolean continueButtonVisible = destinationSelectionPage.isContinueButtonVisible();
        Allure.step(
                continueButtonVisible
                        ? "✅ 'Continue' button is visible"
                        : "❌ 'Continue' button is missing"
        );
    }

    /**
     * Verifies the visual color status of the step bar (completed vs. current).
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        String result = basePage.getStepBarColorValidationResult();
        boolean isValid = result.startsWith("PASS");
        String summary = result.replaceFirst("PASS\n|FAIL\n", "");

        Allure.step("Step validation:\n" + summary);
        if (!isValid) {
            throw new AssertionError("Step bar validation failed:\n" + summary);
        }
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("1/9", "לאן נוסעים?");
    }
}
