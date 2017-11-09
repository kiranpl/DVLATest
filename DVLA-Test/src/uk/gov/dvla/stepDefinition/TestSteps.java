package uk.gov.dvla.stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;

import uk.gov.dvla.pageObjects.ConfirmVehiclePage;
import uk.gov.dvla.pageObjects.EnterRegistrationPage;
import uk.gov.dvla.pageObjects.StartPage;
import uk.gov.dvla.selenium.Browser;

public class TestSteps {
	
	@Given("^user enters car registration number (.*)$")
	public void user_enters_car_registration_number(String registration) throws Throwable {
		StartPage startPage = new StartPage();
		EnterRegistrationPage enterReg = new EnterRegistrationPage();
		
		Browser browser = new Browser();
		browser.openURL("https://www.gov.uk/get-vehicle-information-from-dvla");
		startPage.navigateToEnterRegPage();
		enterReg.enterRegistration(registration);
	}

	@When("^he clicks Continue button$")
	public void he_clicks_Continue_button() throws Throwable {
		EnterRegistrationPage enterReg = new EnterRegistrationPage();
		enterReg.clickContinue();
	}

	@Then("^he should see car make (.*) and model (.*)$")
	public void he_should_see_car_make_and_model(String make, String color) throws Throwable {
		ConfirmVehiclePage confirmVehicle = new ConfirmVehiclePage();
		confirmVehicle.verifyCarDetails(make, color);
	}
}
