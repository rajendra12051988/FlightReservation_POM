package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindFlightsPage {
	
WebDriver driver;
	
	private Logger logger = Logger.getLogger("flightReservation");
	
	
	@FindBy(xpath="//input[@name='tripType'][@value='oneway']")
	private WebElement Oneway;
	
	@FindBy(name="fromPort")
	private WebElement departureFrom;
	
	@FindBy(name="toPort")
	private WebElement arrivalTo;
	
	@FindBy(xpath="//input[@name='servClass'][@value='First']")
	private WebElement firstClass;
	
	@FindBy(name="airline")
	private WebElement airLinePreference;
	
	@FindBy(name="findFlights")
	private WebElement continue1;
	
	public FindFlightsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectOneWayTripType(){
		logger.info("Clicking on 'Oneway' trip type...");
		highlightElement(Oneway);
		Oneway.click();
		logger.info("Clicked on 'Oneway' trip type...");
		
	}
	
	public void selectFlyFrom(String flyFrom){
		logger.info("Selecting '"+flyFrom+"' from the 'Departure From' drop down...");
		highlightElement(departureFrom);
		Select departFrom = new Select(departureFrom); 
		departFrom.selectByVisibleText(flyFrom);
		logger.info("Selected '"+flyFrom+"' from the 'Departure From' drop down...");
	}
	
	
	public void selectFlyTo(String flyTo){
		logger.info("Selecting '"+flyTo+"' from the 'Arrival To' drop down...");
		highlightElement(arrivalTo);
		Select arriTo = new Select(arrivalTo); 
		arriTo.selectByVisibleText(flyTo);
		logger.info("Selected '"+flyTo+"' from the 'Arrival To' drop down...");
	}
	
	public void selectClassPreference(){
		logger.info("Clicking on 'First Class'...");
		highlightElement(firstClass);
		firstClass.click();
		logger.info("Clicked on 'First Class'...");
	}
	
	public void clickOnFindFlights(){
		logger.info("Clicking on 'Find Flights'...");
		highlightElement(continue1);
		continue1.click();
		logger.info("Clicked on 'Find Flights'...");
	}
	
	public void selectAirlinePreference(String airline){
		logger.info("Selecting '"+airline+"' from the 'Airline Preference' drop down...");
		highlightElement(airLinePreference);
		Select airlineDropDown = new Select(airLinePreference); 
		airlineDropDown.selectByVisibleText(airline);
		logger.info("Selected '"+airline+"' from the 'Airline Preference' drop down...");
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;');", element);
	}
	
}
