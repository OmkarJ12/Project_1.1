package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSuccessPage extends BasePage {
	
	WebDriver driveASP;
	
	//constructor
	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		driveASP = driver;
	}

	//locators
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement successMsg;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement continueBtn;
	
	
	//action methods
	public String getConfirmationMsg() {
		try {
			return (successMsg.getText());
		}catch (Exception e) {
			return (e.getMessage());
		}
	}
	
	public MyAccountPage clickContinueBtn() {
		continueBtn.click();
		return new MyAccountPage(driveASP);
	}
}
