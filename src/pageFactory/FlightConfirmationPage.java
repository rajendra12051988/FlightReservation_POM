package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmationPage {
WebDriver driver;
	
	private Logger logger = Logger.getLogger("flightReservation");
	
	
	@FindBy(xpath="//font[@size='+1']")
	private WebElement confirmationText;
	
	public FlightConfirmationPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getBookingConfirmationText(){
		logger.info("Fetching Booking Confirmation Text...");
		highlightElement(confirmationText);
		String bookingConfirmationText = confirmationText.getText().trim();
		logger.info("Fetched Booking Confirmation Text...");
		return bookingConfirmationText;
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;');", element);
	}
	
}
