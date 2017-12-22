package by.htp.selenium.mailtest.factory.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverCreator extends WebDriverCreator{

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.chrome.driver";
    private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = "C:\\driver\\chromedriver.exe";


    @Override
    public WebDriver factoryMethod() {
    	
        System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_GECKODRIVER_EXE_PATH);
        WebDriver driver = new ChromeDriver();
        
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        return driver;
    }
}