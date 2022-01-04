package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.AutomateBookShelves;

public class TestCases extends Base {

	AutomateBookShelves ha = new AutomateBookShelves();

	@BeforeTest
	public void invokeBrowser() {
		logger = report.createTest("Executing Test Cases");

		ha.invokeBrowser();
		reportPass("Browser is Invoked");
	}

	@Test(priority = 1, groups = {"UICheck","Regression Test"})
	public void bookOutstation() throws Exception {

		ha.displayBookshelves();
		ha.obtainMenu();
		ha.giftCard();
		Thread.sleep(5000);
		reportPass("All Test Cases Passed Successfully");
	}


	@AfterTest
	public void closeBrowser() {
		ha.endReport();
		ha.closeBrowser();
	}

}
