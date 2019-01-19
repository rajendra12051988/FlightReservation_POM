package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	private Logger logger = Logger.getLogger("flightReservation");
	
	@FindBy(name="userName")
	private WebElement userName;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement signIn;
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterUserName(String username){
		logger.info("Entering '"+username+"' in the 'UserName' edit field...");
		highlightElement(userName);
		userName.sendKeys(username);
		logger.info("Entered '"+username+"' in the 'UserName' edit field...");
	}
	
	
	public void enterPassword(String pwd){
		logger.info("Entering '"+pwd+"' in the 'Password' edit field...");
		highlightElement(password);
		password.sendKeys(pwd);
		logger.info("Entered '"+pwd+"' in the 'Password' edit field...");
	}
	
	public void clickOnSignIn(){
		logger.info("Clicking on 'SignIn'...");
		highlightElement(signIn);
		signIn.click();
		logger.info("Clicked on 'SignIn'...");
	}
	
	
	private void highlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;');", element);
	}

}
