package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.SearchDocumentsAndFormsData;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDocumentsAndFormsPage {

    private final BasePage basePage;

    public SearchDocumentsAndFormsPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////

    private final By documentsAndFormsBtn = By.xpath("//img[@alt='חיפוש טפסים ומסמכים']/ancestor::a");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By resetButtonLoc = By.cssSelector("#table_form > div:nth-child(4) > button:nth-child(2)");
    private final By primary = By.id("primary");
    private final By secondary = By.id("secondary");
    private final By freeTextField = By.id("opentext");
    private final By listOfDocumentsSearched = By.cssSelector(".table__row:not(.row--hide)");
    private final By headers = By.cssSelector("div.table__item");

    // ==============================================
    //                    ACTIONS
    // ==============================================

    //////////// CLICK ////////////

    public void clickDocumentsAndForms() throws Exception {
        basePage.forceClick(documentsAndFormsBtn);
    }

    public void clickSearch() {
        basePage.click(searchButton);
    }

    public void clickClear() {
        basePage.click(resetButtonLoc);
    }

    //////////// SET ////////////

    public void fillTextField() {
        basePage.sendKeys(freeTextField, SearchDocumentsAndFormsData.insurance);
    }

    public void selectSubjectCarFromDropDownMenu() {
        basePage.chooseOptionByIndexInMobileDropdown(primary, 1);
        //basePage.chooseValueFromDropDownMenu(primary, 1);
    }

    public void selectGeneralFromTypeDropDownMenu() {
        basePage.chooseOptionByIndexInMobileDropdown(secondary, 1);
        //basePage.chooseValueFromDropDownMenu(secondary, 1);
    }

    //////////// SCROLL ////////////

    public void scrollToDocumentsAndForms() throws Exception {
         basePage.scrollToElement(documentsAndFormsBtn);
    }

    // ==============================================
    //                     GET
    // ==============================================

//    public List<WebElement> getSearchResultDocument() {
//
//        System.out.println(".table__row not hide " + basePage.getElements(listOfDocumentsSearched));
//        System.out.println(".table__row " + basePage.getElements(By.cssSelector(".table__row")));
//
//        return basePage.getElements(listOfDocumentsSearched);
//    }
    public List<WebElement> getSearchResultDocument() {
        By headers = By.cssSelector(".table__row-header .table__item");
        By.cssSelector(".table__row");

        List<WebElement> allRows = basePage.getElements(By.cssSelector(".table__row"));

        System.out.println("🔍 Found " + allRows.size() + " .table__row elements");

        List<WebElement> visibleRows = allRows.stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());

        for (WebElement row : allRows) {
            System.out.println("Row: isDisplayed=" + row.isDisplayed() + " | Text=\"" + row.getText().trim() + "\"");
        }

        System.out.println("✅ Returning " + visibleRows.size() + " visible .table__row elements");

        return visibleRows;
    }

    public List<WebElement> getTableSearchResult() {
        List<WebElement> results = getSearchResultDocument();
        return results.size() <= 1 ? Collections.emptyList() : results;
    }

    public List<WebElement> getTableHeader() {
        List<WebElement> rows = getTableSearchResult();
        if (rows.isEmpty()) return Collections.emptyList();
        return rows.get(0).findElements(headers);
    }

    public List<String> getWebElementListTexts(List<WebElement> webElementList) {
        return webElementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
