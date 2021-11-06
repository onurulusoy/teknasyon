Feature: Teknasyon
  Scenario: Job Application
    Given I open the page
    Then I check the page title is correct
    Then I search for word "Teknasyon"
    And I verify that "teknasyon.com" is listed
    Then I click "teknasyon.com" and check if the title is "Teknasyon"
    Then I scroll to the bottom of the page and click "Kariyer"
    Then I check the description of the Kariyer page
    Then I verify that the "Test Otomasyon Mühendisi" position exists
    Then I click on the "Test Otomasyon Mühendisi" position and check the title
    And I click apply button
    Then I check if there is any warning on the application form page
    Then I verify and click "Başvur" button
    Then I check "CV Yükle" button is present
    Then I fill "Adınızı soyadınızı giriniz." as "Test User"
    Then I fill "E-postanızı giriniz." as "testuser@gmail.com"
    Then I upload my CV
    Then I verify that I have the recaptcha
    Then I check all the checkboxes
    And I see the warning texts are gone
    Then I verify and click "Başvur" button
    #Manual intervention is required before the last step because of reCaptcha
    Then I see the successful icon and text
