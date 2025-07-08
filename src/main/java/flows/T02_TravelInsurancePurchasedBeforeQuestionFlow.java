package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T02_TravelInsurancePurchasedBeforeQuestionPage;
import utils.TravelInsuranceData;

/**
 * Flow class for handling the 'Have you purchased insurance before?' question step
 * in the travel insurance wizard.
 */
public class T02_TravelInsurancePurchasedBeforeQuestionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T02_TravelInsurancePurchasedBeforeQuestionPage purchasedBeforeQuestionPage;
    private final T01_TravelInsuranceIntroFlow introFlow;

    /**
     * Constructor
     * @param basePage shared BasePage instance
     */
    public T02_TravelInsurancePurchasedBeforeQuestionFlow(BasePage basePage) {
        this.basePage = basePage;
        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage);
        this.purchasedBeforeQuestionPage = new T02_TravelInsurancePurchasedBeforeQuestionPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates from the intro step to the 'Have you purchased insurance before?' step.
     */
    public void navigateToPurchasedInsuranceBeforeQuestionStep() throws Exception {
        introFlow.navigateToIntroPage();
        introFlow.clickContinueButton(); // From intro
        introFlow.clickContinueButton(); // From quote/intro section
    }

    /**
     * Clicks on the 'First time' option button.
     */
    public void clickFirstTimeButton() {
        purchasedBeforeQuestionPage.clickFirstTimeOption();
    }

    /**
     * Clicks on the 'Already purchased' option button.
     */
    public void clickAlreadyPurchasedButton() {
        purchasedBeforeQuestionPage.clickAlreadyPurchasedOption();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the question text and both answer options are visible and correct.
     */
    public void verifyInsurancePurchasedQuestion() {
        Allure.step("Verify question text contains expected text", () -> {
            String question = purchasedBeforeQuestionPage.getQuestionText();
            if (!question.contains(TravelInsuranceData.DID_YOU_PURCHASE_INSURANCE_TEXT)) {
                throw new AssertionError("❌ Wrong question text: " + question);
            }
        });

        Allure.step("Verify 'First time' option is visible", () -> {
            if (!purchasedBeforeQuestionPage.isFirstTimeOptionVisible()) {
                throw new AssertionError("❌ 'First time' option is NOT visible");
            }
        });

        Allure.step("Verify 'Already purchased' option is visible", () -> {
            if (!purchasedBeforeQuestionPage.isAlreadyPurchasedOptionVisible()) {
                throw new AssertionError("❌ 'Already purchased' option is NOT visible");
            }
        });
    }
}
