package tests;

import flows.T012_TravelInsuranceProposalSummaryFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T012_TravelInsuranceProposalSummaryTests extends BaseTest {

    private T012_TravelInsuranceProposalSummaryFlow flow;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        this.flow = new T012_TravelInsuranceProposalSummaryFlow(basePage);
    }

    @Test(description = "TC-050 - Verify full online travel insurance flow for a soldier: destinations, coverages, personal details, and summary.")
    public void verifyProposalSummary() throws Exception {
            flow.navigateToProposalSummaryStep();
            flow.verifySummaryDetails();
            flow.verifyNavigationButtonsAreVisible();
            flow.showFinalPrice();
            flow.verifyCurrentStep();

    }
}
