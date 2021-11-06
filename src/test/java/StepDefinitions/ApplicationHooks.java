package StepDefinitions;

import Factory.DriverFactory;
import Utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties properties;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        properties = configReader.initialize_properties();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = properties.getProperty("browserName");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);

    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
