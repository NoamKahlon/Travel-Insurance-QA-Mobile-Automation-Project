package flows;

import io.qameta.allure.Allure;
import pages.BasePage;
import pages.T08_TravelInsurancePassengersDetailsPage;

import static utils.TravelInsuranceData.*;

/**
 * Flow class for handling passenger details input during the travel insurance flow.
 */
public class T08_TravelInsurancePassengersDetailsFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T08_TravelInsurancePassengersDetailsPage passengersDetailsPage;
    private final T07_TravelInsuranceDateSelectionFlow dateSelectionFlow;

    /**
     * Constructor
     */
    public T08_TravelInsurancePassengersDetailsFlow(BasePage basePage) {
        this.basePage = basePage;
        this.passengersDetailsPage = new T08_TravelInsurancePassengersDetailsPage(basePage);
        this.dateSelectionFlow = new T07_TravelInsuranceDateSelectionFlow(basePage);
    }

    // ========== Navigation ==========

    public void navigateToPassengersDetailsStep() throws Exception {
        Thread.sleep(4000);
        dateSelectionFlow.navigateToDateSelectionStep();
        Thread.sleep(4000);
        dateSelectionFlow.pickDates();
        Thread.sleep(4000);
        dateSelectionFlow.clickContinueButton();
    }

    // ========== Actions ==========

    public void clickContinue() {
        Allure.step("Click 'Continue' on Passenger Details", () -> {
            dateSelectionFlow.clickContinueButton();
        });
    }

    public void fillPassengerDetailsForm() throws InterruptedException {
       Thread.sleep(4000);
        Allure.step("Fill passenger details form", () -> {
            passengersDetailsPage.selectMaleGender();
            passengersDetailsPage.enterPhoneNumber(PHONE);
            passengersDetailsPage.enterFirstNameEnglish(FIRST_NAME_EN);
            passengersDetailsPage.enterLastNameEnglish(LAST_NAME_EN);
            passengersDetailsPage.enterFirstNameHebrew(FIRST_NAME_HE);
            passengersDetailsPage.enterLastNameHebrew(LAST_NAME_HE);
            passengersDetailsPage.enterIdNumber(ID_NUMBER);
            passengersDetailsPage.enterBirthDate(BIRTH_DATE);
            passengersDetailsPage.enterEmail(EMAIL);
        });
    }

    // ========== Verifications ==========

    public void verifyCurrentAndPreviousStepsByColor() {
        dateSelectionFlow.verifyCurrentAndPreviousStepsByColor();
    }

    public void verifyPassengerDetailsFieldsAreVisible() throws InterruptedException {
        Thread.sleep(4000);
        Allure.step("Verify all passenger detail fields are visible", () -> {
            assert passengersDetailsPage.isIdFieldVisible() : "❌ ID field not visible";
            assert passengersDetailsPage.isPhoneFieldVisible() : "❌ Phone field not visible";
            assert passengersDetailsPage.isFirstNameHebrewVisible() : "❌ First Name (HE) field not visible";
            assert passengersDetailsPage.isLastNameHebrewVisible() : "❌ Last Name (HE) field not visible";
            assert passengersDetailsPage.isFirstNameEnglishVisible() : "❌ First Name (EN) field not visible";
            assert passengersDetailsPage.isLastNameEnglishVisible() : "❌ Last Name (EN) field not visible";
            assert passengersDetailsPage.isBirthDateFieldVisible() : "❌ Birth Date field not visible";
            assert passengersDetailsPage.isEmailFieldVisible() : "❌ Email field not visible";
            assert passengersDetailsPage.isGenderFieldVisible() : "❌ Gender field not visible";
            assert passengersDetailsPage.isAddPassengerFieldVisible() : "❌ Add Passenger button not visible";
            assert passengersDetailsPage.isContinueButtonVisible() : "❌ Continue button not visible";
            assert passengersDetailsPage.isBackButtonVisible() : "❌ Back button not visible";
        });
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("3/9", "פרטי הנוסעים");
    }
}
