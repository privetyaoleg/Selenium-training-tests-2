package by.htp.selenium.mailtest.utils;

import java.util.Random;

public class Utils {

	public static String createEmail(String username, String domain) {
		StringBuilder enterEmail = new StringBuilder();
		enterEmail.append(username).append("@").append(domain).append(".ru");
		return enterEmail.toString();
	}
	
	public static String xpathByDomen(String domain){
		
		StringBuilder xpathDomain = new StringBuilder();
		xpathDomain.append("//option[contains(text(), '").append(domain).append("')]");
		return xpathDomain.toString();
		
	}
	
	public static String generateValidEmail(){
		
		StringBuilder randomEmail = new StringBuilder();
		Random random = new Random();
		String[] domains = {"mail.ru", "bk.ru", "yandex.ru", "tut.by", "list.ru", "gmail.com"};
		Random randomDomains = new Random();
		randomEmail.append("privet").append(random.nextInt(100)).append("@").append(domains[randomDomains.nextInt(6)]);

		return randomEmail.toString();	
	}
	
	public static String generateInvalidEmail(){
		StringBuilder randomEmail = new StringBuilder();
		Random random = new Random();
		String[] domains = {"mail.ru", "bk.ru", "yandex.ru", "tut.by", "list.ru", "gmail.com"};
		Random randomDomains = new Random();
		randomEmail.append("test").append(random.nextInt(100)).append("XXX").append(domains[randomDomains.nextInt(6)]);

		return randomEmail.toString();	
	}
	
	public static int getIntFromString(String str){
		
    	StringBuilder xx = new StringBuilder();
    	char[] ar = str.toCharArray();
    	
    	for (char c : ar) {
			if(c >= 48 && c<=57){
				xx.append(c);
			}
		}
    	
    	return Integer.parseInt(xx.toString());
	}

	
	public static void sleepThread(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
