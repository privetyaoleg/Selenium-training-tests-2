package by.htp.selenium.mailtest.factory.driver;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {

    protected WebDriver driver;

    public abstract WebDriver factoryMethod();
}
