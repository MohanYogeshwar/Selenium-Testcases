package io.testing.tables.datatables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CqbaseclsTests {

	public static WebDriver driver;

	public static void press(WebElement element) 
	{
		element.click();
	}  
	public static void sendkeys(WebElement element,String value) 
	{
		element.sendKeys(value);
	} 
	public static void close() {
		driver.close();
	}
	public static void Select(WebElement element) 
	{
		Select s=new Select(element);
		s.selectByVisibleText("3");
	} 

	public static void scroll() 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500);");
		
	}  
	public static void screenshot() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\Admin\\Downloads\\datatable_excel7\\datatable_excel7\\Screenshot\\img1.png");
	    org.openqa.selenium.io.FileHandler.copy(source, destination);
	}
	public static void switchframe(WebElement element) 
	{
		driver.switchTo().frame(element);
	}
	public static void defaultcontent()
	{
		driver.switchTo().defaultContent();
	}

	public static void sleep(int millisecs) throws InterruptedException 
	{
		Thread.sleep(millisecs);

	}

}
