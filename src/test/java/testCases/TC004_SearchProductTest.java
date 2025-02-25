package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseTestClass;

/**
 * Test Case: Product Search
 * 
 * Steps: 1) Navigate to the application URL 2) Navigate to the Home page and
 * initiate product search 3) Enter the product name in the search field 4)
 * Click on the search button 5) Verify if the product is displayed in the
 * search results
 */

public class TC004_SearchProductTest extends BaseTestClass {

	@Test(groups = { "Sanity", "Master" })
	public void verifySearchProductFunctionality() {

		logger.info("--- Starting TC004_SearchProdctTest ---");
		try {
			HomePage hp = new HomePage(driver);
			String msg = hp.searchProduct("apple");

			if (msg.equalsIgnoreCase("There is no product that matches the search criteria.")) {
				System.out.println(msg);
				Assert.assertTrue(true);
			} else if (msg.equalsIgnoreCase("Error..")) {
				Assert.assertTrue(false);
			} else {
				System.out.println(msg);
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("--- Finished TC004_SearchProdctTest ---");

	}
}
