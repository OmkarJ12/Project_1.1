package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC001_AccountRegistrationTest extends BaseTestClass {

	@Test(groups = {"Regression","Master"})
	public void verifyAccountRegistration() {
		
		logger.info("-- Starting TC001_AccountRegistrationTest --");
		logger.info("==> Total driver instances at START: <=="+getDriverInstanceCount());
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myaccount link..");

		hp.clickRegister();
		logger.info("Clicked on register link..");
		
		AccountRegisterPage regPage = new AccountRegisterPage(driver);
		
		regPage.enterFirstNameInput(randomeStringFirstName().toUpperCase());
		regPage.enterLastNameInput(randomeStringLastName().toUpperCase());
		regPage.enterEmailInput(emailGenerator());
		regPage.enterPwdInput(randomePassword());
		logger.info("Customer details entered..");
		
		
		regPage.clickPrivacyBtn();
		regPage.clickContinueBtn();
		
		logger.info("Validating expected msg..");
		AccountSuccessPage scPage = new AccountSuccessPage(driver);
		String confirmMsg = scPage.getConfirmationMsg();
		if(confirmMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
		}catch(Exception e) {
			Assert.fail();		
		}
		
		logger.info("==> Total driver instances at END: <=="+getDriverInstanceCount());
		logger.info("-- Finished TC001_AccountRegistrationTest --");
	}
}
