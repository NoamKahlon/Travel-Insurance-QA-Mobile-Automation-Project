package flows;

import pages.BasePage;
import pages.T012_TravelInsuranceProposalSummaryPage;

import java.util.List;

/**
 * Flow class responsible for handling the Proposal Summary step
 * in the online travel insurance process.
 */
public class T012_TravelInsuranceProposalSummaryFlow {

    // ========== Members ==========
    private final BasePage basePage;

    // ========== Page ==========
    private final T012_TravelInsuranceProposalSummaryPage proposalSummaryPage;

    // ========== Flow Dependencies ==========
    private final T011_TravelInsuranceHealthDeclarationFlow healthDeclarationFlow;

    /**
     * Constructor
     * @param basePage Shared base page for web interaction
     */
    public T012_TravelInsuranceProposalSummaryFlow(BasePage basePage) {
        this.basePage = basePage;

        this.proposalSummaryPage = new T012_TravelInsuranceProposalSummaryPage(basePage);
        this.healthDeclarationFlow = new T011_TravelInsuranceHealthDeclarationFlow(basePage);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Proposal Summary step through all prior flow steps.
     */
    public void navigateToProposalSummaryStep() throws Exception {
        healthDeclarationFlow.navigateToHealthDeclarationStep();
        healthDeclarationFlow.clickNoButton();
        healthDeclarationFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies the summary details displayed on the proposal summary page.
     */
    public void verifySummaryDetails() {
        String passengersNumber = proposalSummaryPage.getPassengersNumber();
        System.out.println("👥 Number of passengers: " + passengersNumber);

        String destination = proposalSummaryPage.getDestination();
        System.out.println("🌍 Destination: " + destination);

        String dates = proposalSummaryPage.getDates();
        System.out.println("📅 Travel dates: " + dates);

        List<String> allCoverages = proposalSummaryPage.getAllPassengerCoverages();
        System.out.println("📋 Coverages: " + String.join(", ", allCoverages));

        String allParagraphsText = proposalSummaryPage.getSpecificPassengerCoveragesTexts();
        System.out.println("📄 Accordion Content:\n" + allParagraphsText);
    }

    /**
     * Verifies the color state of the current and previous steps in the wizard.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        healthDeclarationFlow.verifyCurrentAndPreviousStepsByColor();
    }

    // ========== Actions ==========

    /**
     * Clicks the continue button on the summary step.
     */
    public void clickContinue() throws Exception {
        healthDeclarationFlow.clickContinue();
    }

    /**
     * Verifies visibility of navigation buttons ('Back' and 'Continue').
     */
    public void verifyNavigationButtonsAreVisible() {
        healthDeclarationFlow.verifyNavigationButtonsAreVisible();
    }

    /**
     * Logs the final insurance price, if available.
     */
    public void showFinalPrice() {
        healthDeclarationFlow.showFinalPrice();
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("8/9","סיכום ההצעה");
    }
}
