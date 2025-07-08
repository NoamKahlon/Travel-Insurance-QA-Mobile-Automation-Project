package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T03_TravelInsurancePassengersFromIsraelQuestionPage;

public class T03_TravelInsurancePassengersFromIsraelQuestionFlow {

    // ========= Members =========
    private final BasePage basePage;
    private final T03_TravelInsurancePassengersFromIsraelQuestionPage passengersFromIsraelQuestionPage;
    private final T02_TravelInsurancePurchasedBeforeQuestionFlow purchasedBeforeQuestionFlow;

    // ========= Constructor =========
    public T03_TravelInsurancePassengersFromIsraelQuestionFlow(BasePage basePage) {
        this.basePage = basePage;
        this.passengersFromIsraelQuestionPage = new T03_TravelInsurancePassengersFromIsraelQuestionPage(basePage);
        this.purchasedBeforeQuestionFlow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage);
    }

    // ========= Actions =========
    public void navigateToPassengersFromIsraelQuestionStep() throws Exception {
        purchasedBeforeQuestionFlow.navigateToPurchasedInsuranceBeforeQuestionStep();
        purchasedBeforeQuestionFlow.clickFirstTimeButton();
    }

    public void clickPassengersFromIsraelOption() {
        passengersFromIsraelQuestionPage.clickYesButton();
    }

    // ========= Verifications =========
    public void verifyPassengersFromIsraelQuestion() throws Exception {
        String questionText = passengersFromIsraelQuestionPage.getQuestionText();
        Allure.step("✅ Found question text: " + questionText);

        boolean isYesOptionVisible = passengersFromIsraelQuestionPage.isYesOptionDisplayed();
        Allure.step("✅ 'Yes' option is visible: " + isYesOptionVisible);

        boolean isNoOptionVisible = passengersFromIsraelQuestionPage.isNoOptionDisplayed();
        Allure.step("✅ 'No' option is visible: " + isNoOptionVisible);
    }
}
