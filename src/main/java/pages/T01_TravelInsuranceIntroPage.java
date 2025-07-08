package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class T01_TravelInsuranceIntroPage {

    BasePage basePage;

    //////////// LOCATORS ////////////               sc-papXJ jyxUhb procceed
    ///
    ///
    ///
    ///
    private final By continueButton = By.cssSelector("button.sc-papXJ.jyxUhb.procceed");
//    private final By continueButton = By.cssSelector("button.sc-papXJ.jJyxUhB.proceed");
    //private final By continueButton = By.cssSelector("button.sc-papXJ");
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");
    private final By travelInsuranceButton = By.id("abroadplus");
    //private final By travelInsuranceButton = By.xpath("//div[@class='hero__insurance-footer'])[3]");
    //private final By travelInsuranceButton = By.xpath("(//div[@class='hero__insurance-footer'])[3]");


    //////////// CONSTRUCTOR ////////////
    public T01_TravelInsuranceIntroPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void scrollToTravelInsuranceButton() {
        basePage.scrollToElement(travelInsuranceButton);
    }

    public void clickOnOnlineTravelInsuranceButton() {
        basePage.openButtonOnclickUrlInSameTab(travelInsuranceButton);
    }

    public void clickOnContinueButton() {
        basePage.click(continueButton);
    }

    //////////// VALIDATIONS ////////////
    public boolean isQuoteIntroSectionVisible() {
        return basePage.isDisplayed(quoteIntroBlock);
    }

    public void scrollToTravelContinueButton() {
        basePage.scrollToElement(continueButton);
    }
}
