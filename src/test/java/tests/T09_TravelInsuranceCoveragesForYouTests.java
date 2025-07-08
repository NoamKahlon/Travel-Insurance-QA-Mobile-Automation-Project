package tests;

import flows.T09_TravelInsuranceCoveragesForYouFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T09_TravelInsuranceCoveragesForYouTests extends BaseTest {

    T09_TravelInsuranceCoveragesForYouFlow flow;

    @BeforeMethod
    public void setFlow() throws Exception {
        super.setUp();
        this.flow = new T09_TravelInsuranceCoveragesForYouFlow(basePage);
    }

    @Test(description = "TC-046: Stage 4 - Completion in Your Path (UI)")
    public void verifyCoveragesForYou() throws Exception {
            flow.navigateToCoveragesForYouStep();
            flow.verifyCoverages();
            flow.verifyNavigationButtonsAreVisible();
            flow.addBaggageCoverage();
            flow.showFinalPrice();
            flow.verifyCurrentStep();
    }

    @Test(description = "TC-047: Verify the full flow and UI behavior of the 'Coverage Calculation' step in online travel insurance")
    public void verifyCoverageCalculation() throws Exception {
            flow.navigateToCoveragesForYouStep();
            flow.verifyPriceUpdatedAfterAddingCoverage();
            flow.verifyCurrentStep();
    }
}
