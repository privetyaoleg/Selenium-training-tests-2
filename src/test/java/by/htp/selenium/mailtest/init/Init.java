package by.htp.selenium.mailtest.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Init {

	public static WebDriver driver;
	public static Properties properties;
	public static Properties webDriverProperties;

	public static void init() {
		try {
			properties = readProperties("src/test/java/example/test/resources/props.properties");
			webDriverProperties = readProperties("src/test/java/example/test/resources/webdrv.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setProperty("webdriver.base.url", webDriverProperties.getProperty("baseurl"));
		String baseUrl = webDriverProperties.getProperty("baseurl");
		if (baseUrl == null) {
			baseUrl = "https://mail/ru";
		}

		String browser = webDriverProperties.getProperty("browser");

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", webDriverProperties.getProperty("chromeDrv"));
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.firefox.driver", webDriverProperties.getProperty("firefoxDrv"));
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", webDriverProperties.getProperty("ieDrv"));
			driver = new InternetExplorerDriver();
			break;
		default:
			System.setProperty("webdriver.firefox.driver", webDriverProperties.getProperty("firefoxDrv"));
			driver = new FirefoxDriver();
			break;
		}
		settingDriver();

	}

	private static Properties readProperties(String path) throws IOException {
		File props = new File(path);
		FileInputStream in = new FileInputStream(props);
		Properties properties = new Properties();
		properties.load(in);
		return properties;
	}

	private static void settingDriver() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

}