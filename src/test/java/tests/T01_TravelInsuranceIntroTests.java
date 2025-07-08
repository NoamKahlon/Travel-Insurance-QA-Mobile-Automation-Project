package tests;

import flows.T01_TravelInsuranceIntroFlow;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Listeners({AllureTestNg.class})  // ✅ Correct: on the class
public class T01_TravelInsuranceIntroTests extends BaseTest {

    private T01_TravelInsuranceIntroFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T01_TravelInsuranceIntroFlow(basePage);
    }

    @Test(description = "TC-038 - Verify landing on online travel insurance flow")
    @Description("Open insurance flow, verify title and main intro block")
    public void introFlow() throws Exception {
        flow.navigateToIntroPage();
    }
}
