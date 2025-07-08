package tests;

import flows.T010_TravelInsuranceSpecialCoveragesFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T010_TravelInsuranceSpecialCoveragesTests extends BaseTest {

    private T010_TravelInsuranceSpecialCoveragesFlow flow;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        flow = new T010_TravelInsuranceSpecialCoveragesFlow(basePage);
    }

    @Test(description = "Verify the full flow and UI behavior of the 'Coverage Calculation' step in online travel insurance")
    public void verifySpecialCoverages() throws Exception {
            flow.navigateToSpecialCoveragesStep();
            flow.verifyTitleTexts();
            flow.showFinalPrice();
            flow.verifyNavigationButtonsAreVisible();
            flow.verifyCurrentStep();
    }
}
