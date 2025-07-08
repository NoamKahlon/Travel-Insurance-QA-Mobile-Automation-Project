package tests;

import flows.T013_TravelInsurancePaymentAndConfirmationFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T013_TravelInsurancePaymentAndConfirmationTests extends BaseTest{

    private T013_TravelInsurancePaymentAndConfirmationFlow flow;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        flow = new T013_TravelInsurancePaymentAndConfirmationFlow(basePage);
    }

    @Test(description = "Verify summary and payment section in online travel insurance quote for a soldier")
    public void paymentAndConfirmation() throws Exception {
            flow.navigateToPaymentAndConfirmationStep();
            flow.verifyCreditCardHolderDetails();
            flow.verifyCreditCardDetails();
            flow.verifyAddressAndContactDetails();
            flow.verifyBackButtonIsVisible();
            flow.verifyPayButtonIsVisible();
            flow.showFinalPrice();
            flow.verifyCurrentStep();

    }
}
