package by.htp.selenium.mailtest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.selenium.mailtest.factory.user.UserFactory;
import by.htp.selenium.mailtest.model.User;
import by.htp.selenium.mailtest.steps.Steps;
import by.htp.selenium.mailtest.utils.Utils;

public class MailRuTest {

	private Steps steps;


	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();

	}

	@Test(enabled = true, description = "possitive")
	public void validLogin() {
		
		User user = UserFactory.createValidUser();
		String enterEmail = Utils.createEmail(user.getLogin(), user.getDomain());
		String newEmail = steps.isAuthorizationCorrect(UserFactory.createValidUser());

		Assert.assertEquals(newEmail, enterEmail);

	}
	
	@Test(enabled = false, description = "negative")
	public void invalidLogin() {

		String error = steps.isAuthorizationIncorrect(UserFactory.createInvalidUser());
		Assert.assertEquals(error, "Неверное имя или пароль");

	}

	@Test(enabled = false,  dependsOnMethods = "validLogin" )
	public void writeMessageIncorrectEmail() {

		String alertMessage = steps.sendIncorrectMessage(Utils.generateInvalidEmail(), "Hello :)", "HELLO WORLD!!!");
		Assert.assertTrue(alertMessage.contains("некорректный адрес получателя"));
	}
	
	@Test(dependsOnMethods = "validLogin")
	public void writeCorrectMessageAmount(){
		Assert.assertTrue(steps.amountMessagesAfterSending());
	}

	
	@Test(enabled = false, dependsOnMethods = "validLogin", description = "negative")
	public void sendMessageWithoutEmail() {
		
		String alertMessage = steps.sendIncorrectMessage("", "", "HELLO WORLD!!!");
		Assert.assertEquals(alertMessage, "Не указан адрес получателя");
	}
	
	@Test(enabled = false, dependsOnMethods = "validLogin", description = "negative")
	public void sendEmptyMessage() {
		
		String alertMessage = steps.sendEmptyMessage(Utils.generateValidEmail(), "Hello :)", "");
		Assert.assertEquals(alertMessage, "Вы уверены, что хотите отправить пустое письмо?");
	}
	
	
	@Test(enabled = false, dependsOnMethods = "validLogin")
	public void sendCorrectMessage() {
		
		String infoMessage = steps.sendIncorrectMessage(Utils.generateValidEmail(), "Hello :)", "HELLO WORLD!!!");
		Assert.assertEquals(infoMessage, "success");
	}
	
	@AfterClass
	public void close(){
		steps.closeDriver();
	}
	
	
	

}
