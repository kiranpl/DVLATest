package uk.gov.dvla.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;

import uk.gov.dvla.selenium.Button;
import uk.gov.dvla.selenium.Label;

public class StartPage {
	private By _start = By.xpath("//a[contains(.,'Start now')]");
	
	public void navigateToEnterRegPage() throws IOException {
		
		Button.click(this._start);
		
		EnterRegistrationPage enterReg = new EnterRegistrationPage();
		
		Label.verifyLabelText(enterReg.getTitleObject(), "Enter the registration number");
	}
}
