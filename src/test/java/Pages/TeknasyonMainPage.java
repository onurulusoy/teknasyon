package Pages;

import Factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeknasyonMainPage {
    WebDriver driver = DriverFactory.getDriver();

    private By SearchArea = By.name("q");

    public TeknasyonMainPage checkTitle(String title){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs(title));
        return this;
    }

    public WebElement findSubMenuItems(String subMenuItem) {
        WebElement menuItem = driver.findElement(By.xpath("//ul[@class='sub-menu']//a[text()='" + subMenuItem + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuItem);

        return menuItem;
    }
}
