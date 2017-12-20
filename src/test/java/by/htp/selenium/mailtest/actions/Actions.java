package by.htp.selenium.mailtest.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import by.htp.selenium.mailtest.driver.DriverSingleton;
import by.htp.selenium.mailtest.model.User;
import by.htp.selenium.mailtest.pages.MainPage;
import by.htp.selenium.mailtest.pages.MessagesPage;
import by.htp.selenium.mailtest.pages.WriteNewMessagePage;
import by.htp.selenium.mailtest.utils.Utils;

public class Actions {
	
	private WebDriver driver;

	public void initBrowser(){
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver(){
		DriverSingleton.closeDriver();
	}
	
	
	public void loginMail(User user){
		driver.manage().deleteAllCookies();

		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.login(user);
	}
	
	public String isAuthorizationIncorrect(User user){
		
		driver.manage().deleteAllCookies();
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.login(user);
		return mainPage.getErrorMessage();
	}
	
	public String isAuthorizationCorrect(User user){
		
		driver.manage().deleteAllCookies();
		
		loginMail(user);
		
		String currentEmail = driver.findElement(By.id("PH_user-email")).getText();
		return currentEmail;	
	}
	
	
	public String sendIncorrectMessage(String recipient, String subject, String message){
	
		WriteNewMessagePage writeNewMessagePage = new WriteNewMessagePage(driver);
		writeNewMessagePage.openPage();
		
		alertWindowHandler();
		
		writeNewMessagePage.filingFields(recipient, subject, message);
		writeNewMessagePage.clickSendButton();
	
		return alertEvent();
		
	}
	
	public String sendCorrectMessage(String recipient, String subject, String message){
		
		WriteNewMessagePage writeNewMessagePage = new WriteNewMessagePage(driver);
		writeNewMessagePage.openPage();
		
		alertWindowHandler();
		
		writeNewMessagePage.filingFields(recipient, subject, message);
		writeNewMessagePage.clickSendButton();
	
		return driver.findElement(By.xpath("//div[@class = 'message-sent__title']")).getText();
		
	}
	
	
	
	public String alertEvent(){
		Utils.sleepThread();
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.dismiss();
		return alertMessage;
		
	}
	
	public void alertWindowHandler(){
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e){
			System.out.println("There isn't any alerts");;
		}
	}
	
	
	
	
}
