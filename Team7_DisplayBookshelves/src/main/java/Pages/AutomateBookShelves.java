package Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import Base.Base;
import utils.ReadExcel;

public class AutomateBookShelves extends Base{
	
 static Properties prop = new Properties();
 static Properties pro = new Properties();
	 
	
 
	By bsicon = By.xpath("//*[@id=\"content\"]/div[3]/div/div[3]/a[4]/h4");
	By excos = By.id("filters_availability_In_Stock_Only");
	By storage = By.xpath("//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[2]/div[1]");
	By cost = By.xpath("//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[1]");
	By open = By.id("filters_storage_type_Open");
	By sliderXpath = By.id("price_limit_9000-15999");
	By name = By.xpath("//*[@id=\"content\"]/div[3]/ul/li/div/div[5]/a/div[1]/span");
	By price = By.xpath("//*[@id=\"content\"]/div[3]/ul/li/div/div[5]/a/div[2]/span");
	//By price2= By.xpath("//*[@id=\\\"filters-form\\\"]/div[1]/div/div/ul/li[1]/div[1]");

	By collection = By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[10]/span");
	By close = By.xpath("//*[@id=\"authentication_popup\"]/div[1]/div/div[2]/a[1]");
	By gift = By.xpath("//*[@id=\"footer-links\"]/div[1]/div[3]/ul/li[2]/a");
	By bday = By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/h3");
	By choose = By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button");
	By amt = By.xpath("//*[@id=\"ip_2251506436\"]");
	By next = By.xpath("//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/button");
	By rname = By.id("ip_4036288348");
	By rmail = By.id("ip_137656023");
	By sname = By.id("ip_1082986083");
	By smail = By.id("ip_4081352456");
	By sno = By.id("ip_2121573464");
	By message = By.id("ip_582840596");
	By confirm = By.xpath("//*[@id=\"app-container\"]/div/main/section/section[3]/form/button");
	
	public void displayBookshelves() throws InterruptedException, IOException {
		logger = report.createTest("Diplay bookshelves under 15000.");
		openURL("websiteURLKey");
		driver.findElement(bsicon).click();
		driver.findElement(excos).click();
		Actions action = new Actions(driver);
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cost));
		action.moveToElement(driver.findElement(cost)).perform();
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(close));
		driver.findElement(close).click();*/
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(sliderXpath));
		driver.findElement(sliderXpath).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(close));
		driver.findElement(close).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(storage));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,25)", "");
		action.moveToElement(driver.findElement(storage)).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(open));
		reportPass("All filters are set");
		driver.findElement(open).click();
		Thread.sleep(2000);
		
		List<WebElement> prices = driver.findElements(price);
		List<WebElement> names = driver.findElements(name);
		//List<WebElement> votes = driver.findElements(vote);
		OutputStream output=new FileOutputStream("C:\\Hackathon\\Team7_DisplayBookshelves\\src\\main\\java\\config\\output.properties");
		for (int i = 0; i <3; i++) {
			
			System.out.println(names.get(i).getText() + " - "
					+ prices.get(i).getText());
			String n=names.get(i).getText();
			String p=prices.get(i).getText();
			prop.setProperty("Name and price of bookshelves-", n+"-"+p);
			prop.store(output, "");
		}
		
		reportPass("Name and Prices of the Bookshelves are Obtained");
	}
	
	public void obtainMenu() throws InterruptedException, IOException {
		
		logger = report.createTest("Obtain all menu and submenus in Collection.");
		openURL("websiteURLKey");
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collection));
		action.moveToElement(driver.findElement(collection)).perform();
		
		OutputStream collection=new FileOutputStream("C:\\Hackathon\\Team7_DisplayBookshelves\\src\\main\\java\\config\\collectionop.properties");
		System.out.println("****** Menu and sublists under collections tab ******");
		pro.setProperty("*", "****** Menu and sublists under collections tab ******");
		pro.store(collection, "");
		
		System.out.println("");
		//OutputStream collection=new FileOutputStream("C:\\Hackathon\\DisplayBookshelves\\Display Bookshelves\\src\\main\\java\\config\\collectionop.properties");
		for (int i = 1; i < 4; i++) {
			WebElement headerList = driver
					.findElement(By.xpath("(//ul[@class='inline-list left'])[10]/li[" + i + "]/div[1]/a[1]"));
			wait.until(ExpectedConditions.visibilityOf(headerList));
			System.out.println(headerList.getText());
			String h=headerList.getText();
			pro.setProperty("*", h);
			pro.store(collection, "");
			System.out.println("----------------");
			pro.setProperty("*", "--------------------------");
			pro.store(collection, "");
			for (int j = 1; j < 7; j++) {
				WebElement subList = driver.findElement(By
						.xpath("(//ul[@class='inline-list left'])[10]/li[" + i + "]/ul[1]/li[" + j + "]/a[1]/span[1]"));
				wait.until(ExpectedConditions.visibilityOf(subList));
				System.out.println(subList.getText());
				String s=subList.getText();
				pro.setProperty("*", s);
				pro.store(collection, "");
				
				
				
			}
			System.out.println("----------------");
			pro.setProperty("*", "--------------------------");
			pro.store(collection, "");
			System.out.println("");
		}
		reportPass("All menus and Submenus are Obtained Successfully");
	}
	
	public void giftCard() throws Exception {
		logger = report.createTest("Fill wrong details and Capture Error.");
		driver.findElement(gift).click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(bday)).perform();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(choose));
		driver.findElement(choose).click();
		//driver.findElement(amt).sendKeys("4000");
		driver.findElement(amt).sendKeys(ReadExcel.read(1, 0));
		driver.findElement(next).click();
		
		driver.findElement(rname).sendKeys(ReadExcel.read(1, 1));
		driver.findElement(rmail).sendKeys(ReadExcel.read(1, 2));
		driver.findElement(sname).sendKeys(ReadExcel.read(1, 3));
		driver.findElement(smail).sendKeys(ReadExcel.read(1, 4));
		driver.findElement(sno).sendKeys(ReadExcel.read(1, 5));
		driver.findElement(message).sendKeys(ReadExcel.read(1, 6));
		
		reportPass("Wrong details are filled");
		driver.findElement(confirm).click();
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")
				+ "/Screenshot/Error.png");
		Files.copy(srcFile, destFile);
		reportPass("Error Message is Captured");
		System.out.println("Error Captured Successfully");
	}
}
