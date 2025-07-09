package pages;

import org.openqa.selenium.By;

public class T03_TravelInsurancePassengersFromIsraelQuestionPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By questionHeader = By.xpath("//h1[contains(.,'האם כל הנוסעים יוצאים מישראל')]");
    private final By yesOption = By.cssSelector("div[data-f='yes']");
    private final By noOption = By.cssSelector("div[data-f='no']");

    //////////// CONSTRUCTOR ////////////
    public T03_TravelInsurancePassengersFromIsraelQuestionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void clickYesButton() {
        basePage.click(yesOption);
    }

    //////////// GETTERS ////////////
    public String getQuestionText() {
        return basePage.getText(questionHeader);
    }

    //////////// VALIDATIONS ////////////
    public boolean isYesOptionDisplayed() {
        return basePage.isDisplayed(yesOption);
    }

    public boolean isNoOptionDisplayed() {
        return basePage.isDisplayed(noOption);
    }

}
