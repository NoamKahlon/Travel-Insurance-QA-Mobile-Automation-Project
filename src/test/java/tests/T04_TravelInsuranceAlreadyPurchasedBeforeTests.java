package tests;

import flows.T04_TravelInsuranceAlreadyPurchasedBeforeFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T04_TravelInsuranceAlreadyPurchasedBeforeTests extends BaseTest {

    private T04_TravelInsuranceAlreadyPurchasedBeforeFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T04_TravelInsuranceAlreadyPurchasedBeforeFlow(basePage);
    }

    @Test(description = "TC-041 - UI - Verify quote suggestion flow with previous insurance selected")
    public void verifyAlreadyPurchasedBefore() throws Exception {
        flow.navigateToAlreadyPurchasedBeforeStep();
        flow.verifyAlreadyPurchasedInsuranceDetails();

    }
}
