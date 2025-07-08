package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T013_TravelInsurancePaymentAndConfirmationPage;

/**
 * Flow class for the Payment and Confirmation step in the travel insurance wizard.
 * Responsible for navigating from the summary step and verifying all required fields for credit card and contact info.
 */
public class T013_TravelInsurancePaymentAndConfirmationFlow {

    // ========== Members ==========
    private final BasePage basePage;

    private final T013_TravelInsurancePaymentAndConfirmationPage paymentAndConfirmationPage;
    private final T012_TravelInsuranceProposalSummaryFlow proposalSummaryFlow;

    // ========== Constructor ==========
    public T013_TravelInsurancePaymentAndConfirmationFlow(BasePage basePage) {
        this.basePage = basePage;

        this.paymentAndConfirmationPage = new T013_TravelInsurancePaymentAndConfirmationPage(basePage);
        this.proposalSummaryFlow = new T012_TravelInsuranceProposalSummaryFlow(basePage);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Payment and Confirmation step by going through the proposal summary flow.
     */
    public void navigateToPaymentAndConfirmationStep() throws Exception {
        proposalSummaryFlow.navigateToProposalSummaryStep();
        proposalSummaryFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies that all credit card holder personal details fields are visible.
     */
    public void verifyCreditCardHolderDetails() {
        paymentAndConfirmationPage.verifyCardHolderFirstNameFieldIsDisplayed();
        Allure.step("✅ First name field is displayed");

        paymentAndConfirmationPage.verifyCardHolderLastNameFieldIsDisplayed();
        Allure.step("✅ Last name field is displayed");

        paymentAndConfirmationPage.verifyCardHolderEmailFieldIsDisplayed();
        Allure.step("✅ Email field is displayed");

        paymentAndConfirmationPage.verifyNumberOfPaymentsFieldIsDisplayed();
        Allure.step("✅ Number of payments field is displayed");
    }

    /**
     * Verifies that all credit card payment fields inside the iframe are visible.
     */
    public void verifyCreditCardDetails() {
        paymentAndConfirmationPage.switchToIframe();

        paymentAndConfirmationPage.verifyCardNumberFieldIsDisplayed();
        Allure.step("✅ Card number field is displayed");

        paymentAndConfirmationPage.verifyCardExpiryYearFieldIsDisplayed();
        Allure.step("✅ Expiry year field is displayed");

        paymentAndConfirmationPage.verifyCardHolderIdFieldIsDisplayed();
        Allure.step("✅ Card holder ID field is displayed");

        paymentAndConfirmationPage.switchToDefaultContent();
    }

    /**
     * Verifies that address, contact and confirmation checkboxes are displayed.
     */
    public void verifyAddressAndContactDetails() {
        paymentAndConfirmationPage.verifyCityFieldIsDisplayed();
        Allure.step("✅ City field is displayed");

        paymentAndConfirmationPage.verifyStreetFieldIsDisplayed();
        Allure.step("✅ Street field is displayed");

        paymentAndConfirmationPage.verifyHouseNumberFieldIsDisplayed();
        Allure.step("✅ House number field is displayed");

        paymentAndConfirmationPage.verifyApartmentNumberFieldIsDisplayed();
        Allure.step("✅ Apartment number field is displayed");

        paymentAndConfirmationPage.verifyPostalCodeFieldIsDisplayed();
        Allure.step("✅ Postal code field is displayed");

        paymentAndConfirmationPage.verifyInsuranceDeclarationCheckboxIsDisplayed();
        Allure.step("✅ Insurance declaration checkbox is displayed");

        paymentAndConfirmationPage.verifyTermsAndConditionsCheckboxIsDisplayed();
        Allure.step("✅ Terms and conditions checkbox is displayed");
    }

    /**
     * Validates current and previous steps in the progress bar by color.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        proposalSummaryFlow.verifyCurrentAndPreviousStepsByColor();
    }

    /**
     * Verifies visibility of 'Back' navigation button.
     */
    public void verifyBackButtonIsVisible() {
        boolean backButtonVisible = paymentAndConfirmationPage.verifyBackButton();
        Allure.step(backButtonVisible
                ? "✅ 'Back' button is visible"
                : "❌ 'Back' button is missing");
    }

    /**
     * Verifies visibility of 'Pay' button inside iframe.
     */
    public void verifyPayButtonIsVisible() {
        paymentAndConfirmationPage.switchToIframe();
        boolean payButtonVisible = paymentAndConfirmationPage.verifyPayButton();
        Allure.step(payButtonVisible
                ? "✅ 'Pay' button is visible"
                : "❌ 'Pay' button is missing");
        paymentAndConfirmationPage.switchToDefaultContent();
    }

    /**
     * Logs the final price display on screen.
     */
    public void showFinalPrice() {
        proposalSummaryFlow.showFinalPrice();
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("9/9","תשלום ואישור");
    }
}
