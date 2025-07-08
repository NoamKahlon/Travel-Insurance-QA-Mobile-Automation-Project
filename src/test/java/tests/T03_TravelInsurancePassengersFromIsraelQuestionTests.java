package tests;

import flows.T03_TravelInsurancePassengersFromIsraelQuestionFlow;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T03_TravelInsurancePassengersFromIsraelQuestionTests extends BaseTest {

    private T03_TravelInsurancePassengersFromIsraelQuestionFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage);
    }


    @Test(description = "TC-040 - Verify passengers from Israel question appears")
    public void verifyPassengersFromIsraelQuestion() throws Exception {
        flow.navigateToPassengersFromIsraelQuestionStep();
        flow.verifyPassengersFromIsraelQuestion();
    }
}
