package by.htp.selenium.mailtest.factory;

import by.htp.selenium.mailtest.model.User;

public class UserFactory {
	
	public static User createValidUser(){
		return new User("sofa_test", "7313494test", "mail");
	}
	
	public static User createInvalidUser(){
		return new User("sofa_test", "zzz", "mail");
	}

}
