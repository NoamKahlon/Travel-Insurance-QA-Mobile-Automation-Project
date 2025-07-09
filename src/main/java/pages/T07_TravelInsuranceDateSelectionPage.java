package pages;

import org.openqa.selenium.By;

public class T07_TravelInsuranceDateSelectionPage {

    private final BasePage basePage;

    public T07_TravelInsuranceDateSelectionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////

    private final By continueButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");
    private final By startDateField = By.id("startDate");
    private final By endDateField = By.id("endDate");
    private final By nextStepButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");
    private final By previousStepButton = By.cssSelector(".sc-papXJ.iGkwwW.goback");

    //////////// ACTIONS ////////////

    public void clickContinueButton() {
        basePage.forceClick(nextStepButton);
    }

    public void pickDates() {
        basePage.sendKeys(startDateField, "01/10/2025");
        basePage.sendKeys(endDateField, "01/11/2025");
    }

    //////////// VALIDATIONS ////////////
    ///
    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(previousStepButton);
    }

    public boolean verifyStartDateFieldDisplayed() {
        return basePage.isDisplayed(startDateField);
    }

    public boolean verifyEndDateFieldDisplayed() {
        return basePage.isDisplayed(endDateField);
    }
}
