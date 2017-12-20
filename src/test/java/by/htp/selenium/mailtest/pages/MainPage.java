package by.htp.selenium.mailtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.htp.selenium.mailtest.model.User;
import by.htp.selenium.mailtest.utils.Utils;

public class MainPage extends AbstractPage{
	
	
	private final String BASE_URL = "https://mail.ru/";
	
	private final String FIELD_LOGIN_ID = "mailbox:login";
	private final String FIELD_PASSWORD_ID = "mailbox:password";
	private final String BUTTON_SUBMIT_ID = "mailbox:submit";
	private final String MENU_DOMAIN_ID = "mailbox:domain";
	private final String ERROR_ID = "mailbox:error";
	
	@FindBy (id = FIELD_LOGIN_ID)
	private WebElement loginField;
	
	@FindBy (id = FIELD_PASSWORD_ID)
	private WebElement passwordField;
	
	@FindBy (id = BUTTON_SUBMIT_ID)
	private WebElement submitButton;
	
	@FindBy (id = MENU_DOMAIN_ID)
	private WebElement domainMenu;
	
	@FindBy (id = ERROR_ID)
	private WebElement errorMessage;
	
	
	private WebElement domain;
	
	
	
	
	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);	
	}
	
	public void login(User user){
		
		this.domain = driver.findElement(By.xpath(Utils.xpathByDomen(user.getDomain())));
		
		domainMenu.click();
		this.domain.click();
		loginField.sendKeys(user.getLogin());
		passwordField.sendKeys(user.getPassword());
		submitButton.click();
	}
	
	public String getErrorMessage(){
		return errorMessage.getText();
	}

	@Override
	public void openPage() {
		driver.get(BASE_URL);	
	}

}
