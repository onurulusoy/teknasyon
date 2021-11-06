package Pages;

import Factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PositionDetailsPage {
    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);


    private By applyPositionButton = By.cssSelector("a[title*='Başvur']");

    public PositionDetailsPage checkPositionTitle(String title){
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement expectedTitle = driver.findElement(By.xpath("//h1[text()='" + title + "']"));
        wait.until(ExpectedConditions.visibilityOf(expectedTitle));
        return this;
    }

    public PositionDetailsPage applyPosition(){
        WebElement theButton = driver.findElement(applyPositionButton);
        wait.until(ExpectedConditions.visibilityOf(theButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", theButton);
        return this;
    }
}
