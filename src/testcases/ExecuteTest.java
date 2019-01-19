package testcases;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import excelInputAndOutput.ExcelInteraction;
import pageFactory.BookAFlightPage;
import pageFactory.FindFlightsPage;
import pageFactory.FlightConfirmationPage;
import pageFactory.LoginPage;
import pageFactory.ReserveAFlightPage;
import utility.Constant;


public class ExecuteTest {
	ExcelInteraction excel;
	WebDriver driver;
	LoginPage loginPage;
    FindFlightsPage findFlightsPage;
    ReserveAFlightPage reserveAFlightPage;
    BookAFlightPage bookAFlightPage;
    FlightConfirmationPage flightConfirmationPage;
	ExtentReports extent;
	ExtentTest extentLogger;
	ExtentHtmlReporter htmlReporter;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browser) throws IOException{
		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",Constant.chromeDriverPath);
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", Constant.geckoDriverPath);
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		
		loginPage = new LoginPage(driver);
		findFlightsPage = new FindFlightsPage(driver);
		reserveAFlightPage = new ReserveAFlightPage(driver);
		bookAFlightPage = new BookAFlightPage(driver);
		flightConfirmationPage = new FlightConfirmationPage(driver);
		excel = new ExcelInteraction();
		
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(new File(Constant.extentReportPath));
		
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report : Flight Reservation(POM)");
		htmlReporter.config().setReportName("E2E Testing of Flight Reservation Using Page Object Model");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent.attachReporter(htmlReporter);
		
		// Provide System Information
		extent.setSystemInfo("Project Name", "Flight Reservation(POM)");
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		InetAddress myHost = InetAddress.getLocalHost();
		extent.setSystemInfo("Host Name", myHost.getHostName());
		
	}
	
	
	@BeforeMethod
	public void getTestMethodName(Method method){
		extentLogger = extent.createTest(method.getName());
	}
	
	@Test(priority=0)
	public void launchApplication(){
		
		try {
			String url = excel.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 0);
			String expTitle = excel.getCellData(Constant.filePath, Constant.fileName, "InvokeApplication", 1, 1);
			driver.get(url);
			Assert.assertEquals(driver.getTitle(), expTitle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
	}
	
	@Test(priority=1)
	public void login(){
		
		try {
			String username = excel.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 0);
			String password = excel.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 1);
			String expTitle = excel.getCellData(Constant.filePath, Constant.fileName, "Login", 1, 2);
			
			// Enter UserName
			loginPage.enterUserName(username);
			// Enter Password
			loginPage.enterPassword(password);
			// Click on 'Sign-In'
			loginPage.clickOnSignIn();
			
			// Verify Page Title
			Assert.assertEquals(driver.getTitle(),expTitle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority=2)
	public void enterFlightDetails(){
		try {
			String flyfrom = excel.getCellData(Constant.filePath, Constant.fileName, "FlightDetails", 1, 0);
			String flyto = excel.getCellData(Constant.filePath, Constant.fileName, "FlightDetails", 1, 1);
			String airline = excel.getCellData(Constant.filePath, Constant.fileName, "FlightDetails", 1, 2);
			// Select Trip Type
			findFlightsPage.selectOneWayTripType();
			// Select Departure From
			findFlightsPage.selectFlyFrom(flyfrom);
			// Select Arrival To
			findFlightsPage.selectFlyTo(flyto);
			// Select Class Preference
			findFlightsPage.selectClassPreference();
			// Select Airline Preference
			findFlightsPage.selectAirlinePreference(airline);
			// Click on 'CONTINUE1'
			findFlightsPage.clickOnFindFlights();
			
			// Verify Element Present
			Assert.assertTrue(reserveAFlightPage.verifyPresenceOfReseveFlightsBtn());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test(priority=3)
	public void reserveAFlight(){
		try{
			// Click on 'CONTINUE2'
			reserveAFlightPage.clickOnReserveFlight();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test(priority=4)
	public void enterPersonalDetails(){
		try {
			String firstname = excel.getCellData(Constant.filePath, Constant.fileName, "PersonalDetails", 1, 0);
			String lastname = excel.getCellData(Constant.filePath, Constant.fileName, "PersonalDetails", 1, 1);
			String ccnumber = excel.getCellData(Constant.filePath, Constant.fileName, "PersonalDetails", 1, 2);
			String expText = excel.getCellData(Constant.filePath, Constant.fileName, "PersonalDetails", 1, 3);
			// Enter First Name
			bookAFlightPage.enterFirstName(firstname);
			// Enter Last Name
			bookAFlightPage.enterLastName(lastname);
			// Enter Credit Number
			bookAFlightPage.enterCreditCardNumber(ccnumber);
			// Click on 'SECURE PURCHASE'
			bookAFlightPage.clickOnBuyFlights();
		
			// Verify Booking Confirmation Text
			Assert.assertTrue(flightConfirmationPage.getBookingConfirmationText().contains(expText));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@AfterMethod
	public void generateReport(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.SUCCESS){
			extentLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" got passed", ExtentColor.GREEN));
			String screenShotPath = getScreenShot(result.getName());
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}else if(result.getStatus() == ITestResult.FAILURE){
			extentLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" got failed due to "+result.getThrowable(), ExtentColor.RED));
			String screenShotPath = getScreenShot(result.getName());
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}else if(result.getStatus() == ITestResult.SKIP){
			extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" got skipped as it was not ready to be executed ", ExtentColor.YELLOW));
		}
	}
	
	private String getScreenShot(String screenShotName) throws IOException{
		TakesScreenshot srcShot = (TakesScreenshot)driver;
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss").format(new Date());
		String destination = System.getProperty("user.dir")+"//screenshots//"+screenShotName+"_"+timeStamp+".png";
		File destFile = new File(destination);
		Files.copy(srcFile,destFile);
		return destination;
	}
	
	@AfterTest
	public void closeDriver(){
		extent.flush();
		driver.quit();
	}

}
