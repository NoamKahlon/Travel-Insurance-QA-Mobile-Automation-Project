package tests;

import flows.T07_TravelInsuranceDateSelectionFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T07_TravelInsuranceDateSelectionTests extends BaseTest{

    private T07_TravelInsuranceDateSelectionFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T07_TravelInsuranceDateSelectionFlow(basePage);
    }

    @Test(description = "TC-044 - Verify date selection step in travel insurance quote flow")
    public void verifyDateSelection() throws Exception {
            flow.navigateToDateSelectionStep();
            flow.verifyDateSelection();
            flow.verifyCurrentStep();
    }
}
