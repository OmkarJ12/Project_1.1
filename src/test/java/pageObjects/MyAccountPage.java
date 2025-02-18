package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[normalize-space()='My Account']")
	WebElement myAccountPageTxt;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoutLnk;
	
	public boolean isMyAccTxtVisible() {
		try {
		return myAccountPageTxt.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogoutLnk() {
		logoutLnk.click();
	}
}
