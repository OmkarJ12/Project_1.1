package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseTestClass {

	public static WebDriver driver;
	private static int driverInstanceCount = 0;
	public Logger logger;                        //log4j
	public Properties prop;
	
	Faker faker = new Faker();

	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("Windows")String os, @Optional("edge")String br) throws IOException {
	
		//loading config.properties file
		try {
		FileReader file =  new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		}catch(IOException e) {
			System.out.println("Failed to load config.properties file.."+e.getMessage());
		}
		
		//for keeping check on driver instance creation for better optimization
		if(driver==null) {                           //ensure driver is not recreated
		
			//for remote execution(selenium grid)
			if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				
				//for os 
				if(os.equalsIgnoreCase("Windows")) {
					cap.setPlatform(Platform.WIN11);
				}
				else if(os.equalsIgnoreCase("mac")){
					cap.setPlatform(Platform.MAC);
				}
				else if(os.equalsIgnoreCase("linux")){
					cap.setPlatform(Platform.LINUX);
				}
				else {
					System.out.println("no matching os..");
					return;
				}
				
				//for browser
				switch (br) {
				case "chrome" : cap.setBrowserName("chrome");break;
				case "edge" : cap.setBrowserName("MicrosoftEdge");break;
				case "firefox" : cap.setBrowserName("firefox");break;
				default : System.out.println("no matching browser..");return;
				}
				
				//creating driver
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			}
			
			//for local execution
			if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
				//crossbrowser setup code
				switch(br.toLowerCase()) {
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name..");return;
				}
			}
			 driverInstanceCount++; // Increment instance count
           System.out.println("WebDriver instance created. Total Instances: " + driverInstanceCount);
		}
			
		//logger code for logging error etc
		logger =  LogManager.getLogger(this.getClass());  //log4j2
		
		//creating driver instance
//		driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//reading value from config.properties file
		driver.get(prop.getProperty("appURL1"));
		driver.manage().window().maximize();
	}
	
	public static int getDriverInstanceCount() {
        return driverInstanceCount;
    }

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}

	/*
	 * public String randomeString() { String generatedString =
	 * RandomStringUtils.randomAlphabetic(5);
	 * 
	 * return generatedString; }
	 * 
	 * public String randomeNumeric(){ String generatedNumber =
	 * RandomStringUtils.randomNumeric(10); return generatedNumber; }
	 * 
	 * public String randomeAlphaNumeric() { return
	 * randomeString()+"@"+randomeNumeric(); }
	 */
	String fname;
	String lname;
	
	public String randomeStringFirstName() {
		fname = faker.name().firstName();
		System.out.println("Firstname: "+fname);
		return fname;
	}

	public String randomeStringLastName() {
		lname = faker.name().lastName();
		System.out.println("Lastname: "+lname);
		return lname;
	}
	
	public String emailGenerator() {
		String email = fname+lname+"@myorg.com";
		System.out.println("email: "+email);
		return email;
	}
	
	public String randomePassword() {
		String pwd = faker.internet().password();
		System.out.println("pwd: "+pwd);
		return pwd;
	}
	
	public String captureScreen(String tname) throws IOException {
		 String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 
		 TakesScreenshot takesSS = (TakesScreenshot)driver;
		 File srcFile = takesSS.getScreenshotAs(OutputType.FILE);
		 
		 String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+currentDateTimeStamp;
		 File targetFile = new File(targetFilePath);
		 
		 srcFile.renameTo(targetFile);
		 
		 return targetFilePath;
	}
}
