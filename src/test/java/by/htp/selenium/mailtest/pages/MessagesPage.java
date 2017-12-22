package by.htp.selenium.mailtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagesPage extends AbstractPage{
	
	private final String BASE_URL = "https://e.mail.ru/messages/inbox/";
	
	private final String BUTTON_WRITE_NEW_MESSAGE_XPATH = "//span[contains(text(), 'Написать письмо')]";
	private final String BUTTON_SENT_MESSAGES = "//div[@data-id='500000']/a";
	
	@FindBy (xpath = BUTTON_WRITE_NEW_MESSAGE_XPATH)
	private WebElement buttonNewMessage;
	
	@FindBy (xpath = BUTTON_SENT_MESSAGES)
	private WebElement sentMessages;

	
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
	
	public String getAmountSentMessages(){
		Actions builder = new Actions(driver);
	    builder.moveToElement(sentMessages).build().perform();
		String amountSentMessages = sentMessages.getAttribute("data-title");
	
		System.out.println("data-title!!!!!!!!!!!!!!" + amountSentMessages + "   ");
		return amountSentMessages;
	}
	
	
}
