package StepDefinitions;

import Factory.DriverFactory;
import Pages.GooglePage;
import Pages.TeknasyonMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class GoogleSteps {

    ApplicationHooks applicationHooks = new ApplicationHooks();
    GooglePage googlePage = new GooglePage();
    TeknasyonMainPage teknasyonMainPage = new TeknasyonMainPage();


    @Given("I open the page")
    public void iOpenThePage() {
        applicationHooks.getProperty();
        String Url = applicationHooks.properties.getProperty("url");
        System.out.println("url is: " + Url);
        DriverFactory.getDriver().get(Url);
    }


    @Then("I check the page title is correct")
    public void iCheckThePageTitleIsCorrect() {
        applicationHooks.getProperty();
        String expectedTitle = applicationHooks.properties.getProperty("urlTitle");
        String actualTitle = googlePage.getGooglePageTitle();
        System.out.println("actual : " + actualTitle);
        Assert.assertTrue(actualTitle.equals(expectedTitle));
    }

    @Then("I search for word {string}")
    public void iSearchForWord(String searchFor) {
        googlePage.searchForText(searchFor);
    }

    @And("I verify that {string} is listed")
    public void iVerifyThatIsListed(String website) {
        googlePage.isListed("https://" + website);
    }

    @Then("I click {string} and check if the title is {string}")
    public void iClickWebsiteAndCheckIfTheTitleIs(String website, String title) {
        googlePage.clickFoundResultFor("https://" + website);
        teknasyonMainPage.checkTitle(title);
    }
}
