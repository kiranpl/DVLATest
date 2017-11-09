package uk.gov.dvla.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;

import uk.gov.dvla.selenium.Button;
import uk.gov.dvla.selenium.Label;
import uk.gov.dvla.selenium.Textbox;

public class EnterRegistrationPage {
	private By _registration = By.xpath("//span[contains(.,'Registration number')]/../../input");
	private By _title = By.xpath("//h1[contains(.,'Enter the registration number')]");
	private By _continue = By.xpath("//button[contains(.,'Continue')]");
	
	public void enterRegistration(String registration) {
		Textbox.typeText(this._registration, registration);
	}
	
	public void clickContinue() throws IOException {
		ConfirmVehiclePage confirmVehicle = new ConfirmVehiclePage();
		Button.click(this._continue);
		Label.verifyLabelText(confirmVehicle.getTitleObject(), "Is this the vehicle you are looking for");
	}
	
	public By getTitleObject() {
		return this._title;
	}
}
