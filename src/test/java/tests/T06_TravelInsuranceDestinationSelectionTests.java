package tests;

import flows.T06_TravelInsuranceDestinationSelectionFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T06_TravelInsuranceDestinationSelectionTests extends BaseTest {

    private T06_TravelInsuranceDestinationSelectionFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T06_TravelInsuranceDestinationSelectionFlow(basePage);
    }

    @Test(description = "TC-043 - Verify online travel insurance quote flow is accessible and displayed correctly")
    public void verifyDestinationSelection() throws Exception {
        flow.navigateToDestinationSelectionStep();
        flow.verifyDestinationSelectionStep();
       // flow.verifyCurrentAndPreviousStepsByColor();
        flow.verifyCurrentStep();
    }
}
