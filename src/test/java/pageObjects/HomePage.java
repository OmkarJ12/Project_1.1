package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccDrpDwn ;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	//action methods
	public void clickMyAccount() {
		myAccDrpDwn.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}
