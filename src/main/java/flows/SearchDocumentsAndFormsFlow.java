package flows;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SearchDocumentsAndFormsPage;
import utils.SearchDocumentsAndFormsData;

import java.util.List;

/**
 * Flow class for handling the 'Documents and Forms' search functionality.
 * Uses Allure for reporting instead of LoggerUtils.
 */
public class SearchDocumentsAndFormsFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final SearchDocumentsAndFormsPage searchDocumentsAndFormsPage;

    // ========== Constructor ==========
    public SearchDocumentsAndFormsFlow(BasePage basePage) {
        this.basePage = basePage;
        this.searchDocumentsAndFormsPage = new SearchDocumentsAndFormsPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Scrolls to the 'Documents and Forms' section, clicks it,
     * and selects the 'Car' subject from the dropdown.
     */
    public void scrollClickAndSelectCarSubject() throws Exception {
        searchDocumentsAndFormsPage.scrollToDocumentsAndForms();
        Allure.step("✅ Scrolled to Documents and Forms section");

        searchDocumentsAndFormsPage.clickDocumentsAndForms();
        Allure.step("✅ Clicked on Documents and Forms");

        searchDocumentsAndFormsPage.selectSubjectCarFromDropDownMenu();
        Allure.step("✅ Selected subject: Car");

        searchDocumentsAndFormsPage.selectGeneralFromTypeDropDownMenu();
        Allure.step("✅ Selected Type: General");
    }

    /**
     * Fills a free-text search, executes it, and verifies the results table and headers.
     */
    public void performFreeTextSearchAndVerifyResults() throws Exception {
        scrollClickAndSelectCarSubject();

        searchDocumentsAndFormsPage.fillTextField();
        Allure.step("✅ Entered free text search value");

        searchDocumentsAndFormsPage.clickSearch();
        Allure.step("✅ Clicked Search button");

        List<WebElement> tableSearchResult = searchDocumentsAndFormsPage.getTableSearchResult();
        if (tableSearchResult.isEmpty()) {
            throw new AssertionError("❌ No search results found. Table size is 0.");
        } else {
            Allure.step("✅ Documents loaded successfully. Found " + tableSearchResult.size() + " results");
        }

        verifyTableHeaders();
    }

    /**
     * Performs a search using type selection, then clears the form
     * and verifies that the table is empty.
     */
    public void performSearchAndClearAndVerifyTableIsEmpty() throws Exception {
        scrollClickAndSelectCarSubject();

        searchDocumentsAndFormsPage.selectGeneralFromTypeDropDownMenu();
        Allure.step("✅ Selected General type");

        searchDocumentsAndFormsPage.clickSearch();
        Allure.step("✅ Clicked Search button");

        searchDocumentsAndFormsPage.clickClear();
        Allure.step("✅ Clicked Clear button");

        verifyTableIsEmpty();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the table headers match the expected values.
     */
    public void verifyTableHeaders() {
        List<WebElement> headerElements = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerElements);
        List<String> expectedHeaders = SearchDocumentsAndFormsData.expectedHeaders;

        if (actualHeaders.equals(expectedHeaders)) {
            Allure.step("✅ Table headers match expected: " + String.join(", ", actualHeaders));
        } else {
            throw new AssertionError("❌ Table headers do not match!\nExpected: " + expectedHeaders + "\nActual: " + actualHeaders);
        }
    }

    /**
     * Verifies that the table headers are empty after clearing the search.
     */
    public void verifyTableIsEmpty() {
        List<WebElement> headerElements = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerElements);

        if (actualHeaders.isEmpty()) {
            Allure.step("✅ Table headers are empty after clearing, as expected");
        } else {
            throw new AssertionError("❌ Table headers weren't empty after clearing. Actual: " + actualHeaders);
        }
    }
}
