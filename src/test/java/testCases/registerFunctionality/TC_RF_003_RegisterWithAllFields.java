package testCases.registerFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC_RF_003_RegisterWithAllFields extends BaseTestClass {

	@Test
	public void registerWithAllFields() {
		logger.info("==> Starting TC_RF_003_RegisterWithAllFields <==");
//        logger.info("==> Total driver instances at START: <=="+getDriverInstanceCount());

		try {
			// Navigate to Registration Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			AccountRegisterPage arp = hp.clickRegister();                        //page chaining

			// Fill Registration Form
			logger.info("Entering details in registration form...");
			AccountSuccessPage asp = arp.accountRegisterFullProcess(randomeStringFirstName(), randomeStringLastName(), emailGenerator(), randomePassword());
		
			// Validate Account Creation
			String successMsg = asp.getConfirmationMsg();
			logger.debug("Fetched success message: " + successMsg);

			Assert.assertEquals(successMsg, "Your Account Has Been Created!",
					"Test failed: Expected 'Your Account Has Been Created!' but found '" + successMsg + "'");

			MyAccountPage map = asp.clickContinueBtn();
			logger.info("Account details submitted...");

			// Validate My Account Page Header
			logger.info("On account success page...");
			Assert.assertTrue(map.isMyAccTxtVisible(),
					"Test failed: Expected My Account header to be visible, but it was not.");

			logger.info("Account successfully registered and My Account page verified.");

		} catch (Exception e) {
			logger.error("Test execution failed due to an exception: " + e.getMessage(), e);
			Assert.fail("Test failed due to an exception.");
		}

//        logger.info("==> Total driver instances at END: <=="+getDriverInstanceCount());
		logger.info("==> Finished execution of TC_RF_003_RegisterWithAllFields <==");
	}
}
