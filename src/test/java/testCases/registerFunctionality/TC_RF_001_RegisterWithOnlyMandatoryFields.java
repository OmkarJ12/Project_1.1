package testCases.registerFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC_RF_001_RegisterWithOnlyMandatoryFields extends BaseTestClass {

	@Test
	public void verifyAccRegWithOnlyMandatoryFields() {
		logger.info("==> Starting TC_RF_001_RegisterWithOnlyMandatoryFields <==");
//        logger.info("==> Total driver instances at START: <=="+getDriverInstanceCount());

		try {
			// Navigate to Registration Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();

			// Fill Registration Form
			logger.info("Entering details in registration form...");
			AccountRegisterPage arp = new AccountRegisterPage(driver);
			arp.enterFirstNameInput(randomeStringFirstName());
			arp.enterLastNameInput(randomeStringLastName()); // Fixed incorrect method call
			arp.enterEmailInput(emailGenerator());
			arp.enterPwdInput(randomePassword());
			arp.clickPrivacyBtn();

			logger.info("Clicking on Submit button...");
			arp.clickContinueBtn();

			// Validate Account Creation
			AccountSuccessPage asp = new AccountSuccessPage(driver);
			String successMsg = asp.getConfirmationMsg();
			logger.debug("Fetched success message: " + successMsg);

			Assert.assertEquals(successMsg, "Your Account Has Been Created!",
					"Test failed: Expected 'Your Account Has Been Created!' but found '" + successMsg + "'");

			asp.clickContinueBtn();

			// Validate My Account Page Header
			MyAccountPage map = new MyAccountPage(driver);
			Assert.assertTrue(map.isMyAccTxtVisible(),
					"Test failed: Expected My Account header to be visible, but it was not.");

			logger.info("Account successfully registered and My Account page verified.");

		} catch (Exception e) {
			logger.error("Test execution failed due to an exception: " + e.getMessage(), e);
			Assert.fail("Test failed due to an exception.");
		}

//        logger.info("==> Total driver instances at END: <=="+getDriverInstanceCount());
		logger.info("==> Finished execution of TC_RF_001_RegisterWithOnlyMandatoryFields <==");
	}
}
