package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {
    
	WebDriver driverARP;
	JavascriptExecutor jse;
	
	
	//constructor
	public AccountRegisterPage(WebDriver driver) {
	      super(driver);
	      driverARP = driver;
	      jse = (JavascriptExecutor) driver;
	}
	
	
	//locators
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstNameInput;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastNameInput;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailInput;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pwdInput;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement privacyRadioBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement continueBtn;
	
	@FindBy(id = "input-newsletter")
	WebElement newsLetterBtn;
	
	
	
	//action methods
	public void enterFirstNameInput(String fName) {
		firstNameInput.sendKeys(fName);
	}

	public void enterLastNameInput(String lName) {
		lastNameInput.sendKeys(lName);
	}
	
	public void enterEmailInput(String email) {
		emailInput.sendKeys(email);
	}
	
	public void enterPwdInput(String pwd) {
		pwdInput.sendKeys(pwd);
	}
	
	public void clickPrivacyBtn() {
//		jse.executeScript("arguments[0].scrollIntoView(true);", privacyRadioBtn);
		jse.executeScript("arguments[0].click()", privacyRadioBtn);
//		privacyRadioBtn.click();
	}
	
	public void clickContinueBtn() {
//		continueBtn.click();
		continueBtn.submit();
	}
	
	public void agreeForNewsLetter() {
		jse.executeScript("arguments[0].scrollIntoView(true);", newsLetterBtn );
		jse.executeScript("arguments[0].click()", newsLetterBtn);
//		newsLetterBtn.click();
	}
	
	public AccountSuccessPage accountRegisterFullProcess(String fname, String lname, String email, String pwd) {
	
			enterFirstNameInput(fname);
			enterLastNameInput(lname);
			enterEmailInput(email);
			enterPwdInput(pwd);
			
			agreeForNewsLetter();
			clickPrivacyBtn();
			clickContinueBtn();
		
			return new AccountSuccessPage(driverARP);          //page obj chaining
	}
}
