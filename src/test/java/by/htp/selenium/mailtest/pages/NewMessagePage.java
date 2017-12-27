package by.htp.selenium.mailtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.selenium.mailtest.utils.Utils;

public class NewMessagePage extends AbstractPage{
	
	private final String BASE_URL = "https://e.mail.ru/compose/?1513570232116";
	
	private final String BUTTON_SEND_XPATH = "//span[contains(text(), 'Отправить')]";
	private final String FIELD_RECIPIENT_XPATH = "//textarea[@data-original-name = 'To']";
	private final String FIELD_SUBJECT_XPATH = "//input[@name = 'Subject']";
	private final String FIELD_MESSAGE_BODY_XPATH = "//tr/td/iframe";

	@FindBy (xpath = BUTTON_SEND_XPATH)
	private WebElement sendButton;
	
	@FindBy (xpath = FIELD_RECIPIENT_XPATH)
	private WebElement recipientField;
	
	@FindBy (xpath = FIELD_SUBJECT_XPATH)
	private WebElement subjectField;
	
	
	public NewMessagePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}
	
	
	public void filingFieldsAndClickSend(String recipient, String subject, String message){
		
//		Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
//		wait.until(ExpectedConditions.alertIsPresent());
		
		Utils.sleepThread();
		recipientField.sendKeys(recipient);
		
		Utils.sleepThread();
		subjectField.sendKeys(subject);
	
		Utils.sleepThread();
		messageFrame(message);

		Utils.sleepThread();
		sendButton.click();

	}
	
	
	private void messageFrame(String message){	
		driver.switchTo().frame(driver.findElement(By.xpath(FIELD_MESSAGE_BODY_XPATH)));
		driver.findElement(By.tagName("body")).sendKeys(message);
		driver.switchTo().defaultContent();
	}
	
	
	

}
