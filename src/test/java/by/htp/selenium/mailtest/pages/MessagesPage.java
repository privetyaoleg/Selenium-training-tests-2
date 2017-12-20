package by.htp.selenium.mailtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagesPage extends AbstractPage{
	
	private final String BASE_URL = "https://e.mail.ru/messages/inbox/";
	
	private final String BUTTON_WRITE_NEW_MESSAGE_XPATH = "//span[contains(text(), 'Написать письмо')]";
	
	@FindBy (xpath = BUTTON_WRITE_NEW_MESSAGE_XPATH)
	private WebElement buttonNewMessage;
	
	
	
	public MessagesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.get(BASE_URL);		
	}
	
	public void clickOnWriteNewMessage(){		
		buttonNewMessage.click();
	}
	
	
}
