package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T01_TravelInsuranceIntroPage;

public class T01_TravelInsuranceIntroFlow {

    private final BasePage basePage;
    private final T01_TravelInsuranceIntroPage introPage;

    public T01_TravelInsuranceIntroFlow(BasePage basePage) {
        this.basePage = basePage;
        this.introPage = new T01_TravelInsuranceIntroPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates to the travel insurance page by scrolling to the section,
     * clicking the relevant button, and switching to the new browser tab.
     */
    public void navigateToIntroPage() throws InterruptedException {
        Thread.sleep(1000);

//        Allure.step("🔹 Scroll to Travel Insurance button", () -> {
//            introPage.scrollToTravelInsuranceButton();
//        });
        Thread.sleep(1000);

        Allure.step("🔹 Click on Online Travel Insurance button", () -> {
            introPage.clickOnOnlineTravelInsuranceButton();
        });

//        Allure.step("🔹 Switch to new tab", () -> {
//            basePage.switchToNewTab();
//        });
    }

    /**
     * Clicks the continue button to proceed to the next step.
     */
    public void clickContinueButton() throws InterruptedException {
        Thread.sleep(1000);

        Allure.step("🔹 Scroll to Continue button", () -> {
            introPage.scrollToTravelContinueButton();
        });
        Thread.sleep(1000);

        Allure.step("🔹 Click on Continue button", () -> {
            introPage.clickOnContinueButton();
        });
    }

}
