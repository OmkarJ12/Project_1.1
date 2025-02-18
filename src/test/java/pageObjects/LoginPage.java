package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailInputBox;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pwdInputBox;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginBtn;
	
	public void enterEmailId(String emailId) {
		emailInputBox.clear();
		emailInputBox.sendKeys(emailId);
	}

	public void enterPwd(String pwd) {
		pwdInputBox.clear();
		pwdInputBox.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
}
