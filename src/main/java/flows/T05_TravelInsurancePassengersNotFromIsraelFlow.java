package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T05_TravelInsurancePassengersNotFromIsraelPage;

/**
 * Flow class for handling the case where passengers are not departing from Israel.
 * Covers the redirection logic, error handling, and validation of stop messages.
 */
public class T05_TravelInsurancePassengersNotFromIsraelFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T05_TravelInsurancePassengersNotFromIsraelPage passengersNotFromIsraelPage;

    private final T01_TravelInsuranceIntroFlow introFlow;
    private final T02_TravelInsurancePurchasedBeforeQuestionFlow purchasedBeforeQuestionFlow;
    private final T03_TravelInsurancePassengersFromIsraelQuestionFlow passengersFromIsraelQuestionFlow;

    /**
     * Constructor
     * @param basePage Shared base page instance
     */
    public T05_TravelInsurancePassengersNotFromIsraelFlow(BasePage basePage) {
        this.basePage = basePage;

        this.passengersNotFromIsraelPage = new T05_TravelInsurancePassengersNotFromIsraelPage(basePage);

        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage);
        this.purchasedBeforeQuestionFlow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage);
        this.passengersFromIsraelQuestionFlow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates from the intro to the 'Are all passengers departing from Israel?' step.
     */
    public void navigateToPassengersFromIsraelQuestionStep() throws Exception {
        passengersFromIsraelQuestionFlow.navigateToPassengersFromIsraelQuestionStep();
    }

    /**
     * Selects the 'No' option for the 'Are passengers from Israel' question.
     */
    public void clickPassengersFromIsraelNoOption() {
        passengersNotFromIsraelPage.clickPassengersAreNotFromIsrael();
        Allure.step("✅ Clicked on 'No' for passengers from Israel");
    }

    // ========== Verifications ==========

    /**
     * Verifies that the stop-process error message is displayed after selecting 'No'.
     */
    public void isStopProcessErrorDisplayed() {
        boolean errorDisplayed = passengersNotFromIsraelPage.isStopProcessErrorDisplayed();
        if (!errorDisplayed) {
            throw new AssertionError("❌ Error message is missing");
        }
        Allure.step("✅ Error message is visible");
    }

    /**
     * Verifies that the 'Back to homepage' button is displayed on the stop screen.
     */
    public void isBackToHomeButtonDisplayed() {
        boolean backButtonDisplayed = passengersNotFromIsraelPage.isBackToHomeButtonDisplayed();
        if (!backButtonDisplayed) {
            throw new AssertionError("❌ 'Back to homepage' button is missing");
        }
        Allure.step("✅ 'Back to homepage' button is visible");
    }
}
