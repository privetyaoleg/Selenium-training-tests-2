package by.htp.selenium.mailtest.driver;

import org.openqa.selenium.WebDriver;

import by.htp.selenium.mailtest.factory.driver.ChromeDriverCreator;
import by.htp.selenium.mailtest.factory.driver.FirefoxDriverCreator;
import by.htp.selenium.mailtest.factory.driver.OperaDriverCreator;
import by.htp.selenium.mailtest.factory.driver.WebDriverCreator;

public class DriverSingleton {

		
	private static WebDriver driver;
   
    private DriverSingleton(){};


    public static WebDriver getDriver(){
        if (null == driver){        
            WebDriverCreator creator = new ChromeDriverCreator(); 
            driver = creator.factoryMethod();
        }

        return driver;
    }

    
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
    
}
