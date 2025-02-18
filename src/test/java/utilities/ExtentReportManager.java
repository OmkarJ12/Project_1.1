package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTestClass;

public class ExtentReportManager implements ITestListener{

	 public ExtentSparkReporter sparkReporter;   //ui of the report
	 public ExtentReports extent;   // populate common info on the report
	 public ExtentTest test;  // creating test case entries in the report and update status of the test methods
	 
	 String repName;
	 public void onStart(ITestContext context) {
		 
		 //date format method 
			/*
			 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); 
			 * Date dt = new Date();
			 * String currentDateTimeStamp = df.format(dt);
			 */
		 //instead we can also do all the above steps in sigle line
		 String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 
		 //adding above timeStamp in report name
		 repName = "Test-Report" + currentDateTimeStamp +".html";
		 
//		 sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myreports.html");  //
		 sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		 
		 sparkReporter.config().setDocumentTitle("Opencart Automation Report");      //title of report
		 sparkReporter.config().setReportName("Opencart Functional Testing");       //name of the report
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 
		 extent.setSystemInfo("Application", "opencart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		 String os = context.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating system", os);
		 
		 String browser = context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		 if(!includeGroups.isEmpty()) {
			 extent.setSystemInfo("Groups", includeGroups.toString());
		 }
		 
	 }
	 
	 public void onTestSuccess(ITestResult result) {
		 test = extent.createTest(result.getTestClass().getName());     
		 test.assignCategory(result.getMethod().getGroups());  					//to display groups in reports
		 test.log(Status.PASS,result.getName()+"got successfully executed..");   //update status pass/fail/skip
		 
	 }
	 
	 public void onTestFailure(ITestResult result) {
		 test = extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		
		 test.log(Status.FAIL,result.getName()+"got failed..");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		 try {
			 String imgPath = new BaseTestClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
		 }catch(IOException e1) {
			 e1.printStackTrace();
		 }
	 }
	 
	 public void onTestSkipped(ITestResult result) {

		 test = extent.createTest(result.getTestClass().getName()); 
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP,result.getName()+"got skipped..");
		 test.log(Status.INFO,result.getThrowable().getMessage());
	 }
	 
	 public void onFinish(ITestContext context) {
		 extent.flush();             // will right all the above thing in actual report file
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		
		 /*
		 //for sending automatic report via email
		 try {
			 
			 URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName); 
			 
			 //create the email msg
			 ImageHtmlEmail email = new ImageHtmlEmail();
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.googlemail.com");
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("omkarjadhav2245@gmail.com", "password"));
			 email.setSSLOnConnect(true);
			 email.setFrom("omkarjadhav2245@gmail.com");       //sender
			 email.setSubject("Test Results");
			 email.setMsg("Please find the attached testing report..");
			 email.addTo("ojadhav245@gmail.com");             //receiver
			 email.attach(url, "extent report", "please check report..");
			 email.send();
		  }catch(Exception e1) {
			 e1.printStackTrace();
		 }
		 */
	 }
}
