package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAFlightPage {
	
WebDriver driver;
	
	private Logger logger = Logger.getLogger("flightReservation");
	
	@FindBy(name="passFirst0")
	private WebElement firstName;
	
	@FindBy(name="passLast0")
	private WebElement lastName;
	
	@FindBy(name="creditnumber")
	private WebElement creditNumber;
	
	@FindBy(name="buyFlights")
	private WebElement securePurchase;
	
	
	public BookAFlightPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterFirstName(String firstname){
		logger.info("Entering '"+firstname+" in the 'First Name' edit field...");
		highlightElement(firstName);
		firstName.sendKeys(firstname);
		logger.info("Entered '"+firstname+" in the 'First Name' edit field...");
		
	}
	
	public void enterLastName(String lastname){
		logger.info("Entering '"+lastname+" in the 'Last Name' edit field...");
		highlightElement(lastName);
		lastName.sendKeys(lastname);
		logger.info("Entered '"+lastname+" in the 'Last Name' edit field...");
		
	}
	
	public void enterCreditCardNumber(String creditCardNumber){
		logger.info("Entering '"+creditCardNumber+" in the 'Credit Card Number' edit field...");
		highlightElement(creditNumber);
		creditNumber.sendKeys(creditCardNumber);
		logger.info("Entered '"+creditCardNumber+" in the 'Credit Card Number' edit field...");
		
	}
	
	public void clickOnBuyFlights(){
		logger.info("Clicking on 'Buy Flights'...");
		highlightElement(securePurchase);
		securePurchase.click();
		logger.info("Clicked on 'Buy Flights'...");
		
	}
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;');", element);
	}

}
