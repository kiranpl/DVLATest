/**
 * 
 */
package uk.gov.dvla.selenium;

import java.io.File;
import java.io.IOException;

/**
 * @author Home
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
	
	private static WebDriver _driver;
	
	public static void openURL(String URL) throws IOException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "lib" + File.separator + "geckodriver.exe");
		_driver = new FirefoxDriver();
		_driver.get(URL);
		Helper.takeScreenshot("OpenURL");
	}
	
	public static WebDriver getDriver() {
		return _driver;
	}

}
