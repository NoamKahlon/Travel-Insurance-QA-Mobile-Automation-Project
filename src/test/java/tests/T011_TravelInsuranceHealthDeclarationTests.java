package tests;

import flows.T011_TravelInsuranceHealthDeclarationFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T011_TravelInsuranceHealthDeclarationTests extends BaseTest{

    private T011_TravelInsuranceHealthDeclarationFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T011_TravelInsuranceHealthDeclarationFlow(basePage);
    }

    @Test(description = "TC-049 - Verify online health insurance flow with coverage and health declaration")
    public void verifyOnlineHealthInsurance() throws Exception {
            flow.navigateToHealthDeclarationStep();
            flow.verifyNavigationButtonsAreVisible();
            flow.showFinalPrice();
            flow.verifyCurrentStep();
    }
}
