package tests;

import flows.T05_TravelInsurancePassengersNotFromIsraelFlow;
import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T05_TravelInsurancePassengersNotFromIsraelTests extends BaseTest {

    private T05_TravelInsurancePassengersNotFromIsraelFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T05_TravelInsurancePassengersNotFromIsraelFlow(basePage);
    }

    @Test(description = "TC-042 - UI - Block flow when not all passengers depart from Israel")
    public void passengersNotFromIsrael() throws Exception {
            flow.navigateToPassengersFromIsraelQuestionStep();
            flow.clickPassengersFromIsraelNoOption();
            flow.isStopProcessErrorDisplayed();
    }
}
