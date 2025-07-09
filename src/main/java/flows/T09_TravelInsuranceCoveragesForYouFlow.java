package flows;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.T09_TravelInsuranceCoveragesForYouPage;
//import pages.T09_TravelInsuranceCoveragesForYouPage;

import java.util.ArrayList;
import java.util.List;

import static utils.TravelInsuranceData.EXPECTED_COVERAGE_TITLES;

/**
 * Flow class for the "Coverages For You" step in the travel insurance wizard.
 * Handles validations, price checks, and UI navigation actions.
 */
public class T09_TravelInsuranceCoveragesForYouFlow {

    private final BasePage basePage;
    private final T09_TravelInsuranceCoveragesForYouPage coveragesForYouPage;
    private final T08_TravelInsurancePassengersDetailsFlow passengersDetailsFlow;

    /**
     * Constructor
     */
    public T09_TravelInsuranceCoveragesForYouFlow(BasePage basePage) {
        this.basePage = basePage;
        this.coveragesForYouPage = new T09_TravelInsuranceCoveragesForYouPage(basePage);
        this.passengersDetailsFlow = new T08_TravelInsurancePassengersDetailsFlow(basePage);
    }

    // ========== Navigation ==========

    public void navigateToCoveragesForYouStep() throws Exception {
        passengersDetailsFlow.navigateToPassengersDetailsStep();
        passengersDetailsFlow.fillPassengerDetailsForm();
        passengersDetailsFlow.clickContinue();
    }

    // ========== Verifications ==========

    public void verifyCoverages() {
        Allure.step("Verify all expected coverage titles are present", () -> {
            List<String> expectedTitles = EXPECTED_COVERAGE_TITLES;
            List<WebElement> titleElements = coveragesForYouPage.getCoverageTitles();
            List<String> actualTitles = new ArrayList<>();

            for (WebElement element : titleElements) {
                actualTitles.add(element.getText().trim());
            }

            for (int i = 0; i < expectedTitles.size(); i++) {
                String expected = expectedTitles.get(i);
                String actual = actualTitles.size() > i ? actualTitles.get(i) : "Missing";
                assert expected.equals(actual) :
                        String.format("❌ Mismatch at index %d: Expected '%s' but found '%s'", i, expected, actual);
            }
        });
    }

    public void verifyFinalPriceVisible() throws InterruptedException {
      Thread.sleep(4000);
        Allure.step("Verify 'Final Price' field is visible", () -> {
            assert coveragesForYouPage.isFinalPriceVisible() : "❌ Final Price field is NOT visible!";
        });
    }

    public boolean isFinalPriceVisible() throws InterruptedException {
      Thread.sleep(3000);
        By priceContainer = By.cssSelector("span.finalPriceValue");

        try {
            WebElement container = basePage.getElement(priceContainer);
            String display = container.getCssValue("display");
            System.out.println("📦 display של price-container: " + display);
            return "flex".equalsIgnoreCase(display);
        } catch (Exception e) {
            return false;
        }
    }


//    public void verifyPriceUpdatedAfterAddingCoverage() throws Exception {
//        Allure.step("Verify price is updated after selecting medical coverage", () -> {
//            double priceBefore = coveragesForYouPage.getFinalPriceValue();
//            coveragesForYouPage.addBaggageCoverage();
//            Thread.sleep(1000);
//            double priceAfter = coveragesForYouPage.getFinalPriceValue();
//            assert priceBefore != priceAfter :
//                    String.format("❌ Price did not change! Before: %s | After: %s", priceBefore, priceAfter);
//        });
//    }

    public void verifyPriceUpdatedAfterAddingCoverage() throws Exception {
        Allure.step("Verify price is updated after selecting medical coverage", () -> {
            double priceBefore = coveragesForYouPage.getFinalPriceValue();
            Allure.step("💰 Price before: " + priceBefore);

            coveragesForYouPage.addMedicalCoverage();
            Thread.sleep(1000);

            double priceAfter = coveragesForYouPage.getFinalPriceValue();
            Allure.step("💰 Price after: " + priceAfter);

            assert priceBefore != priceAfter :
                    String.format("❌ Price did not change! Before: %s | After: %s", priceBefore, priceAfter);
        });
    }

    public void showFinalPrice() {
        Allure.step("Show and verify final price text is not empty", () -> {
            String priceText = coveragesForYouPage.getFinalPriceText();

            Allure.step("💵 Final Price: " + priceText);

            assert !priceText.isEmpty() : "❌ Final Price text is empty!";
        });
    }


    public void verifyNavigationButtonsAreVisible() {
        Allure.step("Verify Back and Continue buttons are visible", () -> {
            assert coveragesForYouPage.isBackButtonVisible() : "❌ Back button is NOT visible!";
            assert coveragesForYouPage.isContinueButtonVisible() : "❌ Continue button is NOT visible!";
        });
    }

    public void verifyCurrentAndPreviousStepsByColor() {
        passengersDetailsFlow.verifyCurrentAndPreviousStepsByColor();
    }

    // ========== Actions ==========

    public void chooseMedicalCoverage() {
        Allure.step("Select Medical Coverage", () -> {
            coveragesForYouPage.addMedicalCoverage();
        });
    }

    public void addBaggageCoverage() {
        Allure.step("Add Baggage Coverage", () -> {
            coveragesForYouPage.addBaggageCoverage();
        });
    }

    public void clickContinue() {
        Allure.step("Click 'Continue' button on Coverages step", () -> {
            coveragesForYouPage.clickContinue();
        });
    }

    public void verifyCurrentStep() throws InterruptedException {
        basePage.verifyCurrentStep("4/9", "כיסויים בשבילך");
    }
}
