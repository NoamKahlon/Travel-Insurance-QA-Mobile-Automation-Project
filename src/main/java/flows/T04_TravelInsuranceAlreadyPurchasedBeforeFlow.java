package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T04_TravelInsuranceAlreadyPurchasedBeforePage;

public class T04_TravelInsuranceAlreadyPurchasedBeforeFlow {

    // ========= Members =========
    private final BasePage basePage;
    private final T04_TravelInsuranceAlreadyPurchasedBeforePage t04TravelInsuranceAlreadyPurchasedBeforePage;
    private final T01_TravelInsuranceIntroFlow introFlow;

    // ========= Constructor =========
    public T04_TravelInsuranceAlreadyPurchasedBeforeFlow(BasePage basePage) {
        this.basePage = basePage;
        this.t04TravelInsuranceAlreadyPurchasedBeforePage = new T04_TravelInsuranceAlreadyPurchasedBeforePage(basePage);
        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage);
    }

    // ========= Actions =========
    public void navigateToAlreadyPurchasedBeforeStep() throws Exception {
        introFlow.navigateToIntroPage();
        introFlow.clickContinueButton();
        introFlow.clickContinueButton();
        t04TravelInsuranceAlreadyPurchasedBeforePage.clickAlreadyPurchasedOption();
        Allure.step("✅ Clicked on 'Already Purchased' option");
    }

    // ========= Verifications =========
    public void verifyAlreadyPurchasedInsuranceDetails() {
        boolean isHappyTextDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isHappyTextDisplayed();
        Allure.step("✅ 'Happy To See You Again' text is visible: " + isHappyTextDisplayed);

        boolean isPhoneDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isPhoneFieldDisplayed();
        Allure.step("✅ Phone field is visible: " + isPhoneDisplayed);

        boolean isIdDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isIdFieldDisplayed();
        Allure.step("✅ ID field is visible: " + isIdDisplayed);
    }
}
