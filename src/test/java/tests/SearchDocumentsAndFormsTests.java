package tests;

import flows.SearchDocumentsAndFormsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchDocumentsAndFormsTests extends BaseTest {

    private SearchDocumentsAndFormsFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        this.flow = new SearchDocumentsAndFormsFlow(basePage);
    }

    @Test(description = "TC-058 - Verify free text document search returns correct results")
    public void test_TC058_FreeTextDocumentSearch() throws Exception {
            flow.performFreeTextSearchAndVerifyResults();
    }

    @Test(description = "TC-059 - Verify clearing document search fields clears the search results")
    public void test_TC059_DocumentSearchAndClear() throws Exception {
            flow.performSearchAndClearAndVerifyTableIsEmpty();
    }
}
