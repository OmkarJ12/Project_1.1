package testCases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verifyLoginFunctionality() {
		logger.info("-- Starting TC001_LoginTest --");
		
		try {
				//homepage actions
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				logger.info("clicked on loging link...");
				
				//loginpage actions
				LoginPage lp =  new LoginPage(driver);
				lp.enterEmailId(prop.getProperty("email"));
				lp.enterPwd(prop.getProperty("password"));
				lp.clickLoginBtn();
				logger.info("Customer details entered..");
				
				//myaccountpage actions
				MyAccountPage mp = new MyAccountPage(driver);
				
				//validation
				Assert.assertTrue(mp.isMyAccTxtVisible(),"Login failed..");
				
			} catch (Exception e) {
				Assert.fail();
			}
	}
}
