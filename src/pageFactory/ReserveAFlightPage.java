package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReserveAFlightPage {
WebDriver driver;
	
	private Logger logger = Logger.getLogger("flightReservation");
	
	@FindBy(name="reserveFlights")
	private WebElement continue2;
	
	
	public ReserveAFlightPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPresenceOfReseveFlightsBtn(){
		logger.info("Verifying presence of 'Reserve Flights'...");
		if(continue2.isDisplayed()){
			logger.info("Verified presence of 'Reserve Flights'...");
			return true;
		}else{
			logger.info("Verified presence of 'Reserve Flights'...");
			return false;
		}
	}
	
	public void clickOnReserveFlight(){
		logger.info("Clicking on 'Reserve Flights'...");
		highlightElement(continue2);
		continue2.click();
		logger.info("Clicked on 'Reserve Flights'...");
		
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;');", element);
	}
	
}
