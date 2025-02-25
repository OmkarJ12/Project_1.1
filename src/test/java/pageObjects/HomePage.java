package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	WebDriver driverHP;
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
		driverHP = driver;
	}
	
	
	//locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccDrpDwn ;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBox;
	
	@FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
	WebElement searchIcon;
	
	@FindBy(xpath = "//div[@class='col-sm-6 text-end']")
	WebElement productListText;
	
	@FindBy(xpath = "//div[@class='col']//p")
	WebElement noProductText;
	
	
	
	//action methods
	public void clickMyAccount() {
		myAccDrpDwn.click();
	}
	
	public AccountRegisterPage clickRegister() {
		lnkRegister.click();
		return new AccountRegisterPage(driverHP);                    //page object chaining
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void enterProductNameToSearch(String productName) {
		searchBox.clear();
		searchBox.sendKeys(productName);
	}
	
	public void clickSearch() {
		searchIcon.click();
	}
	
	public String searchProduct(String prodName) throws InterruptedException {
		searchBox.clear();
		searchBox.sendKeys(prodName);
		Thread.sleep(3000);
		searchIcon.click();
		try {
			if(noProductText.isDisplayed()) {
				Thread.sleep(3000);
				return noProductText.getText();
			}else {
				Thread.sleep(3000);
				return productListText.getText();
			}
		}catch(Exception e) {
			Thread.sleep(3000);
			return "Error..";
		}
	}
}
