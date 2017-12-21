package by.htp.selenium.mailtest.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
		filingFields(recipient, subject, message);
		return getAlertMessageAndEsc();
		
	}
	
	public String sendCorrectMessage(String recipient, String subject, String message){
		
		filingFields(recipient, subject, message);
		return driver.findElement(By.xpath("//div[@class = 'message-sent__title']")).getText();
		
	}
	
	public String sendEmptyMessage(String recipient, String subject, String message){
		filingFields(recipient, subject, message);
		String result = driver.findElement(By.xpath("//div[@class='popup__desc']")).getText();
		
		driver.switchTo().defaultContent();
		return result;
	}
	
	
	
	public String getAlertMessageAndEsc(){
		try {
			Utils.sleepThread();
			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			alert.dismiss();
			return alertMessage;
		} catch (NoAlertPresentException e){
			return "success";
		}
		
		
	}
	
	public void tryAlertMessageAndOk(){
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e){
			System.out.println("There isn't any alerts");;
		}
	}
	
	private void filingFields(String recipient, String subject, String message){
		
		WriteNewMessagePage writeNewMessagePage = new WriteNewMessagePage(driver);
		writeNewMessagePage.openPage();
		
		tryAlertMessageAndOk();
		
		writeNewMessagePage.filingFields(recipient, subject, message);
		writeNewMessagePage.clickSendButton();
	}
	
	
	
	
}
