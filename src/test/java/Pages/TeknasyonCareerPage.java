package Pages;

import Factory.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeknasyonCareerPage {
    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);

    private By description = By.className("b-desc");


    public TeknasyonCareerPage checkDescription(){
        WebElement pageDescription = driver.findElement(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(description));
        Assert.assertTrue(pageDescription.getText().contains("Seni tanımak isteriz!"));
        return this;
    }

    public TeknasyonCareerPage verifyPositionExists(String position){
        WebElement expectedPosition = driver.findElement(By.cssSelector("a[title*='" + position + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", expectedPosition);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(expectedPosition));
        return this;
    }

    public TeknasyonCareerPage navigateToThePositionAncClick(String position){
        WebElement positionToClick = driver.findElement(By.cssSelector("a[title*='" + position + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", positionToClick);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", positionToClick);
        return this;
    }

}
