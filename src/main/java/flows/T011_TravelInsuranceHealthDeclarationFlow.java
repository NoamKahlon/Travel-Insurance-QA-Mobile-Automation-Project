package flows;

import org.openqa.selenium.By;
import pages.BasePage;
import pages.T011_TravelInsuranceHealthDeclarationPage;

/**
 * Flow class for the Health Declaration step in the travel insurance wizard.
 * Manages navigation, option selection, and verifications.
 */
public class T011_TravelInsuranceHealthDeclarationFlow {

    // ========= Members =========
    private final BasePage basePage;
    private final T011_TravelInsuranceHealthDeclarationPage healthDeclarationPage;
    private final T010_TravelInsuranceSpecialCoveragesFlow specialCoveragesFlow;

    // ========= Locators =========
    private final By continueButton = By.cssSelector("button.sc-papXJ.jyxUhb.procceed");

    /**
     * Constructor
     * @param basePage shared BasePage instance
     */
    public T011_TravelInsuranceHealthDeclarationFlow(BasePage basePage) {
        this.basePage = basePage;
        this.healthDeclarationPage = new T011_TravelInsuranceHealthDeclarationPage(basePage);
        this.specialCoveragesFlow = new T010_TravelInsuranceSpecialCoveragesFlow( basePage);
    }

    // ========= Navigation =========

    /**
     * Navigates to the Health Declaration step by completing all prior flows.
     */
    public void navigateToHealthDeclarationStep() throws Exception {
        specialCoveragesFlow.navigateToSpecialCoveragesStep();
        Thread.sleep(7000);
        specialCoveragesFlow.addSpecialCoverage();
        basePage.click(continueButton);
    }

    // ========= Actions =========

    /**
     * Clicks the 'No' option in the health declaration.
     */
    public void clickNoButton() {
        healthDeclarationPage.clickNoButton();
    }

    /**
     * Clicks the 'Continue' button to proceed from the health declaration step.
     */
    public void clickContinue() throws Exception {
        Thread.sleep(1500); // Optionally replace with explicit wait
        basePage.click(continueButton);
    }

    // ========= Verifications =========

    /**
     * Verifies step bar colors for completed and active steps.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        basePage.getStepBarColorValidationResult();
    }

    /**
     * Verifies that navigation buttons ('Back' and 'Continue') are visible.
     */
    public void verifyNavigationButtonsAreVisible() {
        specialCoveragesFlow.verifyNavigationButtonsAreVisible();
    }

    /**
     * Displays and verifies the final price.
     */
    public void showFinalPrice() {
        specialCoveragesFlow.showFinalPrice();
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("6/9","הצהרת בריאות");
    }
}
