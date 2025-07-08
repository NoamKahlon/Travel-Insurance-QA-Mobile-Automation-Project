
package tests;

import flows.T02_TravelInsurancePurchasedBeforeQuestionFlow;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.databind.deser.Deserializers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T02_TravelInsurancePurchasedBeforeQuestionTests extends BaseTest {

    private T02_TravelInsurancePurchasedBeforeQuestionFlow flow;

    @BeforeMethod
    public void setup() throws Exception {
        super.setUp();
        flow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage);
    }

    @Test(description = "TC-039 - Verify previous insurance question appears")
    public void previousInsuranceQuestion() throws Exception {
        flow.navigateToPurchasedInsuranceBeforeQuestionStep();
        flow.verifyInsurancePurchasedQuestion();
    }
}
