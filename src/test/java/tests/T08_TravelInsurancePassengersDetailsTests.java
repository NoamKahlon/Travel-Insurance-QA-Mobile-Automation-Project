package tests;

import flows.T08_TravelInsurancePassengersDetailsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T08_TravelInsurancePassengersDetailsTests extends BaseTest {

    private T08_TravelInsurancePassengersDetailsFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T08_TravelInsurancePassengersDetailsFlow(basePage);
    }

    @Test(description = "TC-045 - Verify Passenger Details UI and Flow")
    public void verifyPassengersDetails() throws Exception {
            flow.navigateToPassengersDetailsStep();
            flow.verifyPassengerDetailsFieldsAreVisible();
            flow.verifyCurrentAndPreviousStepsByColor();
            flow.verifyCurrentStep();
    }
}
