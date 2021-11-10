package StepDefinitions;

import Factory.DriverFactory;
import Pages.ApplicationFormPage;
import Pages.PositionDetailsPage;
import Pages.TeknasyonCareerPage;
import Pages.TeknasyonMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeknasyonSteps {

    TeknasyonMainPage teknasyonMainPage = new TeknasyonMainPage();
    TeknasyonCareerPage teknasyonCareerPage = new TeknasyonCareerPage();
    PositionDetailsPage positionDetailsPage = new PositionDetailsPage();
    ApplicationFormPage applicationFormPage = new ApplicationFormPage();

    ApplicationHooks applicationHooks = new ApplicationHooks();

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);

    @Then("I scroll to the bottom of the page and click {string}")
    public void iScrollToTheBottomOfThePageAndClickToTheSubMenuItem(String item) {
        WebElement subMenuItem = teknasyonMainPage.findSubMenuItems(item);
        wait.until(ExpectedConditions.visibilityOf(subMenuItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subMenuItem);
    }

    @Then("I check the description of the Kariyer page")
    public void iCheckTheDescriptionOfTheKariyerPage() {
        teknasyonCareerPage.checkDescription();
    }

    @Then("I verify that the {string} position exists")
    public void iVerifyThatThePositionExists(String position) {
        teknasyonCareerPage.verifyPositionExists(position);
    }

    @Then("I click on the {string} position and check the title")
    public void iClickOnThePositionAndCheckTheTitle(String position) {
        teknasyonCareerPage.navigateToThePositionAncClick(position);
        positionDetailsPage.checkPositionTitle(position);
    }

    @And("I click apply button")
    public void iClickApplyButton() {
        positionDetailsPage.applyPosition();
    }

    @Then("I check if there is any warning on the application form page")
    public void iCheckIfThereIsAnyWarningOnTheApplicationFormPage() {
        Assert.assertFalse(applicationFormPage.isThereAnyWarning());
    }

    @Then("I verify and click {string} button")
    public void iVerifyAndClickButton(String button) {
        applicationFormPage.verifyButton(button);
        applicationFormPage.applyButton(button);
    }

    @Then("I check {string} button is present")
    public void iCheckButtonIsPresent(String button) {
        applicationFormPage.verifyButton(button);
    }

    @Then("I fill {string} as {string}")
    public void iFillAs(String placeHolder, String input) {
        applicationFormPage.fillForm(placeHolder, input);
    }

    @Then("I upload my CV")
    public void iUploadMyCV() {
        applicationHooks.getProperty();
        String CVName = applicationHooks.properties.getProperty("CVName");
        applicationFormPage.uploadFile(CVName);
    }

    @Then("I verify that I have the recaptcha")
    public void iVerifyThatIHaveTheRecaptcha() {
        applicationFormPage.verifyRecaptcha();
    }

    @Then("I check all the checkboxes")
    public void iCheckAllTheCheckboxes() {
        applicationFormPage.checkTheBoxes();
    }

    @And("I see the warning texts are gone")
    public void iSeeTheWarningTextsAreGone() {
        applicationFormPage.isWarningGone();
    }

    @Then("I see the successful icon and text")
    public void iSeeTheSuccessfulIconAndText() {
        applicationFormPage.seeSuccessfulIcon();
        applicationFormPage.seeSuccessfulText();
    }
}
