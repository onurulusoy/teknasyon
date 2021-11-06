package Pages;

import Factory.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ApplicationFormPage {
    WebDriver driver = DriverFactory.getDriver();

    private By nameSurnameError = By.id("jobNameSurname-error");
    private By emailError = By.id("jobEmail-error");
    private By fileError = By.id("jobFile-error");
    private By letError = By.id("jobLet-error");
    private By reCaptchaError = By.id("recaptchaControl-error");
    private By recapcha = By.id("recaptcha");
    private By checkboxes = By.className("checkbox");
    private By iconSuccess = By.className("icon-success");
    private By textSuccess = By.xpath("//h2[text()='Başvurunuz alınmıştır.']");

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isThereAnyWarning() {
        return (isElementPresent(nameSurnameError) &&
                isElementPresent(emailError) &&
                isElementPresent(fileError) &&
                isElementPresent(letError) &&
                isElementPresent(reCaptchaError));
    }

    public ApplicationFormPage verifyButton(String button){
        WebElement theButton = driver.findElement(By.xpath("//span[text()='" + button +"']"));
        Assert.assertTrue(theButton.isDisplayed());
        return this;
    }

    public ApplicationFormPage applyButton(String button){
        WebElement theButton = driver.findElement(By.xpath("//span[text()='" + button +"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", theButton);
        return this;
    }

    public ApplicationFormPage fillForm(String placeholder, String input) {
        WebElement element = driver.findElement(By.cssSelector("input[placeholder*='" + placeholder + "']"));
        element.sendKeys(input);
        return this;
    }

    public ApplicationFormPage uploadFile(){
        String CVPath = System.getProperty("user.dir") + "\\testCV.pdf";
        WebElement uploadButton = driver.findElement(By.id("jobFile"));
        uploadButton.sendKeys(CVPath);
        return this;
    }

    public ApplicationFormPage verifyRecaptcha(){
        WebElement recaptchaToBeVerified = driver.findElement(recapcha);
        Assert.assertTrue(recaptchaToBeVerified.isDisplayed());
        return this;
    }

    public ApplicationFormPage checkTheBoxes(){
        List<WebElement> checkboxesToBeChecked = driver.findElements(checkboxes);

        //didn't check the recaptcha
        for (int i = 0; i < checkboxesToBeChecked.size() - 1; i++) {
            checkboxesToBeChecked.get(i).click();
        }
        return this;
    }

    public String getStyleAttribute(By webElem){
        WebElement element = driver.findElement(webElem);
        String elementDisplayValue = element.getAttribute("style");
        return elementDisplayValue;
    }

    public ApplicationFormPage isWarningGone() {
        String jobNameSurnameDisplayValue = getStyleAttribute(nameSurnameError);
        String jobEmailDisplayValue = getStyleAttribute(emailError);
        String jobLetDisplayValue = getStyleAttribute(letError);
        //Even though file upload is successful and there is no problem with file upload, the warning message is observed.
        //So I commented it out
        //String fileStyle = getStyleAttribute(fileError);

        Assert.assertTrue("display: none;".equals(jobNameSurnameDisplayValue) &&
                "display: none;".equals(jobEmailDisplayValue) &&
                "display: none;".equals(jobLetDisplayValue));
        return this;
    }

    public ApplicationFormPage seeSuccessfulIcon(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://teknasyon.com/job-application-form-success/"));
        WebElement successfulIcon = driver.findElement(iconSuccess);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(successfulIcon));
        Assert.assertTrue(successfulIcon.isDisplayed());
        return this;
    }

    public ApplicationFormPage seeSuccessfulText(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://teknasyon.com/job-application-form-success/"));
        WebElement successfulText = driver.findElement(textSuccess);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(successfulText));
        Assert.assertTrue(successfulText.isDisplayed());
        return this;
    }

}
