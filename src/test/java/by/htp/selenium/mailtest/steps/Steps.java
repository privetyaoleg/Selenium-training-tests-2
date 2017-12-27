package by.htp.selenium.mailtest.steps;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.selenium.mailtest.driver.DriverSingleton;
import by.htp.selenium.mailtest.init.Init;
import by.htp.selenium.mailtest.model.User;
import by.htp.selenium.mailtest.pages.MainPage;
import by.htp.selenium.mailtest.pages.MessagesPage;
import by.htp.selenium.mailtest.pages.NewMessagePage;
import by.htp.selenium.mailtest.utils.Utils;

public class Steps {

	private WebDriver driver;

	public void initBrowser() {
		Init.init();
		driver = Init.driver;
	}

	public void closeDriver() {
		driver.quit();
	}

	public void loginMail(User user) {
		driver.manage().deleteAllCookies();

		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.login(user);
	}

	public String isAuthorizationIncorrect(User user) {

		driver.manage().deleteAllCookies();
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.login(user);
		Utils.sleepThread();
		return mainPage.getErrorMessage();
	}

	public String isAuthorizationCorrect(User user) {

		driver.manage().deleteAllCookies();

		loginMail(user);
		Utils.sleepThread();
		String currentEmail = driver.findElement(By.id("PH_user-email")).getText();
		return currentEmail;
	}

	public String sendIncorrectMessage(String recipient, String subject, String message) {

		filingFields(recipient, subject, message);
		return getAlertMessageAndEsc();
	}

	public String sendCorrectMessage(String recipient, String subject, String message) {

		filingFields(recipient, subject, message);
		return driver.findElement(By.xpath("//div[@class = 'message-sent__title']")).getText();
	}

	public String sendEmptyMessage(String recipient, String subject, String message){
		filingFields(recipient, subject, message);
		Utils.sleepThread();
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='popup__desc']"));
		String result = null;
		if(!list.isEmpty()){
			for (WebElement webElement : list) {
				if(webElement.getText().length() != 0){
					result = webElement.getText();
				}
			}
		}
		
		driver.switchTo().defaultContent();
		return result;
	}

	public String getAlertMessageAndEsc() {
		try {
			Utils.sleepThread();
			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			alert.dismiss();
			return alertMessage;
		} catch (NoAlertPresentException e) {
			return "success";
		}

	}
	
	public boolean amountMessagesAfterSending(){
		
		MessagesPage messagesPage = new MessagesPage(driver);
		int amountBefore = Utils.getIntFromString(messagesPage.getAmountSentMessages());
		filingFields("alenaapina@mail.ru", "Hello :)", "Hellooooooo...");
		Utils.sleepThread();
		int amountAfter = Utils.getIntFromString(messagesPage.getAmountSentMessages());

		return amountBefore + 1 == amountAfter;
	}

	public void tryAlertMessageAndOk() {
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e) {
			System.out.println("There isn't any alerts");
		}
	}

	private void filingFields(String recipient, String subject, String message) {

		NewMessagePage writeNewMessagePage = new NewMessagePage(driver);
		writeNewMessagePage.openPage();

		tryAlertMessageAndOk();

		writeNewMessagePage.filingFieldsAndClickSend(recipient, subject, message);
	}

}
