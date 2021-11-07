package Pages;

import Factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
    WebDriver driver = DriverFactory.getDriver();

    private By SearchArea = By.name("q");

    public String getGooglePageTitle(){ return DriverFactory.getDriver().getTitle(); }
    public WebElement findWebSiteOnResults(String wepSiteToFind) {
        By siteToCheck = By.xpath("//cite[contains(text(), '" + wepSiteToFind + "')]");
        return driver.findElement(siteToCheck);
    }


    public GooglePage searchForText(String searchText) {
        WebElement searchFor = driver.findElement(SearchArea);
        searchFor.sendKeys(searchText);
        searchFor.sendKeys(Keys.ENTER);
        return this;
    }

    public GooglePage isListed(String website){
        WebElement siteGiven = findWebSiteOnResults(website);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(siteGiven));
        return this;
    }

    public GooglePage clickFoundResultFor(String website){
        WebElement foundResult = findWebSiteOnResults(website);
        foundResult.click();
        return this;
    }


}
