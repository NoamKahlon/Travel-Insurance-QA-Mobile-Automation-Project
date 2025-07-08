package tests;

import flows.CheckingTheExistenceOfCarInsuranceFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckingTheExistenceOfCarInsuranceTests extends BaseTest {

    private CheckingTheExistenceOfCarInsuranceFlow flow;

    @BeforeMethod
    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.flow = new CheckingTheExistenceOfCarInsuranceFlow(basePage);
        flow.navigateToInsuranceCheckSection();
    }

    @Test(description = "TC-054 - Verify error message is shown for uninsured vehicle")
    public void verifyInvalidCarInsurance() throws InterruptedException {
            flow.searchInsurance(false);
            flow.verifyInsuranceNotFoundError();
    }

    @Test(description = "TC-055 - Verify valid car insurance exists")
    public void verifyValidCarInsurance() throws InterruptedException {
            flow.searchInsurance(true);
            flow.verifyInsuranceFoundSuccess();

    }
}
