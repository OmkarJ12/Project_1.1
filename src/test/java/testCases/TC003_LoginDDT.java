package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTestClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void loginDDT(String email, String pwd, String expRes) {		
	
		logger.info("-- TC003_LoginDDT Starting --");
		
		try {
		//homepage actions
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginpage actions
		LoginPage lp =  new LoginPage(driver);
		lp.enterEmailId(email);
		lp.enterPwd(pwd);
		lp.clickLoginBtn();
		
		//myaccountpage actions
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage =  mp.isMyAccTxtVisible();
		
		/*
		 * data is valid -  login success - test pass - logout
		 *				    login failed -  test fail 
		 * 
		 * data is invalid -  login success - test fail - logout
		 * 					  login fail -  test pass 
		 */
		
		//for - data is valid condition
		if(expRes.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				mp.clickLogoutLnk();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}

		//for - data is invalid condition
		if(expRes.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				mp.clickLogoutLnk();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("-- TC003_LoginDDT Ending --");
	}
}
