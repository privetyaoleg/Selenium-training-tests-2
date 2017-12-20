package by.htp.selenium.mailtest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.selenium.mailtest.actions.Actions;
import by.htp.selenium.mailtest.factory.UserFactory;
import by.htp.selenium.mailtest.model.User;
import by.htp.selenium.mailtest.utils.Utils;

public class MailRuTest {

	private Actions actions;


	@BeforeMethod(description = "Init browser")
	public void setUp() {
		actions = new Actions();
		actions.initBrowser();

	}

	@Test(enabled = true, description = "possitive")
	public void validLogin() {
		
		User user = UserFactory.createValidUser();
		String enterEmail = Utils.createEmail(user.getLogin(), user.getDomain());
		String newEmail = actions.isAuthorizationCorrect(UserFactory.createValidUser());

		Assert.assertEquals(newEmail, enterEmail.toString());

	}
	
	@Test(enabled = true, description = "negative")
	public void invalidLogin() {

		
		String error = actions.isAuthorizationIncorrect(UserFactory.createInvalidUser());

		Assert.assertTrue(error.trim().equalsIgnoreCase("Неверное имя или пароль"));

	}

	@Test(enabled = true , dependsOnMethods = "validLogin" )
	public void writeMessageIncorrectEmail() {

		String alertMessage = actions.sendIncorrectMessage(Utils.generateInvalidEmail(), "Hello :)", "HELLO WORLD!!!");

		System.out.println(alertMessage);

		Assert.assertTrue(alertMessage.contains("некорректный адрес"));
	}

	
	@Test(dependsOnMethods = "validLogin", description = "negative")
	public void sendMessageWithoutEmail() {
		
		String alertMessage = actions.sendIncorrectMessage("", "", "HELLO WORLD!!!");

		Assert.assertTrue(alertMessage.contains("Не указан адрес получателя"));
	}
	
	@Test(dependsOnMethods = "validLogin", description = "negative")
	public void sendEmptyMessage() {
		
		String alertMessage = actions.sendIncorrectMessage(Utils.generateValidEmail(), "Hello :)", "");

		Assert.assertTrue(alertMessage.equals("Вы уверены, что хотите отправить пустое письмо?"));
	}
	
	
	@Test(dependsOnMethods = "validLogin")
	public void writeMessage() {
		
		String infoMessage = actions.sendCorrectMessage(Utils.generateValidEmail(), "Hello :)", "HELLO WORLD!!!");

		Assert.assertTrue(infoMessage.contains("отправлено"));
	}
	
	
	

}
