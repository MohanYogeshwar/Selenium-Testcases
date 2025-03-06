package io.testing.tables.datatables;

import java.io.File;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.math.RoundingMode; 
import java.text.DecimalFormat;
import javax.swing.JFrame;


import org.slf4j.*;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.Gson;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.testing.tables.datatables.Fppom;
import io.testing.tables.datatables.Fpsd;
//import io.cucumber.messages.types.Timestamp;
import model.Patient;
import model.PatientList;

import java.sql.Timestamp;

public class Fpsd extends Fpbasecls {
//	String featurePath = FileSystems.getDefault().getPath("Ncc.feature").normalize().toAbsolutePath().toString();
    
	
	private static final Logger logger = LoggerFactory.getLogger(Fpsd.class);
	public static WebDriver driver;
	FileInputStream ft;
	Properties Test;
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	
	@Before
	public void initialsetup() throws IOException {
		
//	    String absolutePath = FileSystems.getDefault().getPath("Case.properties").normalize().toAbsolutePath().toString();
//	    System.out.println(absolutePath);
//		ft = new FileInputStream(absolutePath);
//		Test = new Properties();
//	    Test.load(ft);
	    String driverPath = FileSystems.getDefault().getPath("chromedriver.exe").normalize().toAbsolutePath().toString();
	    System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver","C:\\Automation_Testing\\Scripts\\Codes\\chromedriver.exe ");
		   
		   driver=new ChromeDriver();
		   driver.manage().window().maximize();
			   driver.get("https://cleargrc-qa.azurewebsites.net/");
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			  
		
			   
			   
		}	
	
	

	@BeforeSuite
	
	@Given("user can access the Clear GRC login page")
	public void user_can_access_the_clear_grc_login_page() {
		PageFactory.initElements(driver, Fppom.class);
		System.out.println("Enter the Target location successfully");
	    
	}
   
	@When("user enters the valid emial Id")
	public void user_enters_the_valid_emial_id() {
	    
	}

	

	
	
	@When("user enters the valid password in respective field from the input sheet")
	public void user_enters_the_valid_password_in_respective_field_from_the_input_sheet(IDataReader casedata) throws InterruptedException, IOException {
		
	       // Excel Report
		    XSSFWorkbook workbook=new XSSFWorkbook();
			XSSFSheet sheet=workbook.createSheet("ForgetPassword");
			XSSFFont font = workbook.createFont();
			//Calendar cal = Calendar.getInstance();
		    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			

			Timestamp timestamp;
			String excelFilePath = "C:\\Automation_Testing\\Reports\\";

				
		    XSSFCellStyle styll=workbook.createCellStyle();			 
			sheet.createRow(0);
			font.setBold(true);
			styll.setFont(font);
			XSSFCell test = sheet.getRow(0).createCell(0);
			test.setCellValue("TESTCASE NUMBER");
			test.setCellStyle(styll);
			XSSFCell Desc = sheet.getRow(0).createCell(1);
			Desc.setCellValue("FIELDS");
			Desc.setCellStyle(styll);
			XSSFCell inp = sheet.getRow(0).createCell(2);
			inp.setCellValue("INPUT VALUE");
			inp.setCellStyle(styll);	
			XSSFCell time = sheet.getRow(0).createCell(3);
			time.setCellValue("TIMESTAMP");
			time.setCellStyle(styll);
			XSSFCell stat = sheet.getRow(0).createCell(4);
			stat.setCellValue("STATUS");
			stat.setCellStyle(styll);
			XSSFCell res = sheet.getRow(0).createCell(5);
			res.setCellValue("REASON FOR FAILURE");
			res.setCellStyle(styll);
			
		   
	

//		List<Patient> patientList = new ArrayList<>();
//		System.out.println("ExcelData before :::"+ new Gson().toJson(((ExcelDataReader) casedata).getAllRows()));
//		
//		
//
//		for(Map<String,String> row : ((ExcelDataReader) casedata).getAllRows()) {	
//			
//			patientList.add(new Patient(row.get("Email"),row.get("Password")));
//		}
//		System.out.println("ExcelData after:::"+ new Gson().toJson(patientList));
//		
		   
		   
//		
//	    int total=((ExcelDataReader) casedata).getAllRows().size();
//	    System.out.println(total);
			int count=0;
			int row_cell=1;
//	
//		if(total!=0) {
//		    for(Patient row: patientList) 
		    {
		    	
		    	// row creation
		    	count=count+1;
		    	sheet.createRow(row_cell);
		    	sheet.getRow(row_cell).createCell(0).setCellValue("***********Forget password '"+count+"'***************");
		    	row_cell=row_cell+1;
		    	
		    	
		    	
		    	
		    	//Enter Email
		    	WebElement email=driver.findElement(By.id("Email"));
//		    	email.sendKeys(row.getEmail());
			    email.sendKeys("msubraman@asdas.asd");
		    	sheet.createRow(row_cell);
			    sheet.getRow(row_cell).createCell(0).setCellValue("TC001");
			    sheet.getRow(row_cell).createCell(1).setCellValue("Email");
			    sheet.getRow(row_cell).createCell(2).setCellValue("msubraman@asdas.asd");
				sheet.getRow(row_cell).createCell(4).setCellValue("Pass");
				timestamp = new Timestamp(System.currentTimeMillis());
	            sheet.getRow(row_cell).createCell(3).setCellValue(sdf1.format(timestamp));
				row_cell=row_cell+1;
				
				//Enter Password
				WebElement password=driver.findElement(By.id("Password"));
//				password.sendKeys(row.getPassword());
			    password.sendKeys("Pwd4ClearGRC!");
			    sheet.createRow(row_cell);
			    sheet.getRow(row_cell).createCell(0).setCellValue("TC002");
			    sheet.getRow(row_cell).createCell(1).setCellValue("Password");
			    sheet.getRow(row_cell).createCell(2).setCellValue("Pxxxxxxxx!");
				sheet.getRow(row_cell).createCell(4).setCellValue("Pass");
				timestamp = new Timestamp(System.currentTimeMillis());
	            sheet.getRow(row_cell).createCell(3).setCellValue(sdf1.format(timestamp));
				row_cell=row_cell+1;
				
				//Click Submit
				driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();				
                System.out.println("Error thrown successfully");
                Thread.sleep(4000);
				
               
				if(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible fade show'] | //div[contains(text(),'Please provide a valid email address.')]")).isDisplayed()){
					// Click Forget password 
					driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]")).click();
					Thread.sleep(2000);
					
					//Enter valid email address
					WebElement email1=driver.findElement(By.id("Email"));
				    email1.sendKeys("msubramani@anadata.com");
					
				//Submit
				 
                driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();     
                Thread.sleep(1000);
//                driver.findElement(By.xpath("(//button[@class='ajs-button btn ci-btn-primary'])[2]")).click();
//                Thread.sleep(3000);
//                String msg= driver.findElement(By.id("toast-container")).getText();
                
                
				//screenshot               
                File imgsrc=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                System.out.println(imgsrc.getCanonicalPath());
                System.out.println(imgsrc.getAbsolutePath());
                timestamp = new Timestamp(System.currentTimeMillis());
                String imgname="C:\\Automation_Testing\\Scripts\\Codes\\Createuser\\Screenshots\\ForgetPassword-" + sdf1.format(timestamp) + ".png";
                File imgdesn= new File(imgname);           
                org.openqa.selenium.io.FileHandler.copy(imgsrc,imgdesn);
                
                try {//div[contains(text(),'validation Failed')]
                	                driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
                	
                			    	driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed();
                			    	sheet.createRow(row_cell);
                                    sheet.getRow(row_cell).createCell(0).setCellValue("***********Forget Password Works Successfully.***************");
                			    	XSSFCellStyle styl=workbook.createCellStyle(); 
                 	        	    styl.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                 	        	    styl.setFillPattern(FillPatternType.DIAMONDS);
                 	        	    XSSFCell celll = sheet.getRow(row_cell).createCell(4);
                 	        	    font.setBold(true);
                 	        	    styl.setFont(font);
                 	        	    celll.setCellValue("Passed"); 
                 	        	    celll.setCellStyle(styl);
                                    row_cell=row_cell+2;
                				    sheet.createRow(row_cell);		        
                		        	    
                				        
                			    }
                			    catch(Exception e){                			    	
//                			    	driver.findElement(By.xpath("//div[contains(text(),'validation Failed')]")).isDisplayed();
                			    	 sheet.createRow(row_cell);
                	                 // hyperlink
                	                 
                	                CreationHelper createHelper = workbook.getCreationHelper();
                	                XSSFCellStyle hlinkstyle = workbook.createCellStyle();
                	                XSSFFont hlinkfont = workbook.createFont();
                	                hlinkfont.setUnderline(XSSFFont.U_SINGLE);
                	                hlinkfont.setColor(IndexedColors.BLUE.index);
                	                hlinkstyle.setFont(hlinkfont);
                	                XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.URL);
                	                XSSFCell cell;
                	               
                	                
                	                cell = sheet.createRow(row_cell).createCell((short) 3);
                	                cell.setCellValue("ScreenShot");
                	                link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.FILE);
                	               
                	                imgname=imgname.replace("\\", "/");
                	                link.setAddress(imgname);                
                	                cell.setHyperlink(link);
                	                cell.setCellStyle(hlinkstyle);
                		                
                			       
                				        //System.out.println(driver.getCurrentUrl());
                				       
                		        	    sheet.getRow(row_cell).createCell(0).setCellValue("***********Forget Password does not work successfully.***************");               		        	    
                		        	    sheet.getRow(row_cell).createCell(5).setCellValue("");               		        	    
                		        	    XSSFCellStyle styl=workbook.createCellStyle(); 
                			            styl.setFillBackgroundColor(IndexedColors.RED.getIndex()); 
                			            styl.setFillPattern(FillPatternType.DIAMONDS);		            
                						XSSFCell celll = sheet.getRow(row_cell).createCell(4);
                						font.setBold(true);
                						styl.setFont(font);
                				        celll.setCellValue("FAILED"); 
                				        celll.setCellStyle(styl);
                				        row_cell=row_cell+2;
                				       
              		        	
                		        }}
//				else {
//				    	sheet.createRow(row_cell);
//					    sheet.getRow(row_cell).createCell(0).setCellValue("***********Forget Password does not work successfully.***************");               		        	    
//		        	    sheet.getRow(row_cell).createCell(5).setCellValue("Invalid Input values");               		        	    
//		        	    XSSFCellStyle styl=workbook.createCellStyle(); 
//			            styl.setFillBackgroundColor(IndexedColors.RED.getIndex()); 
//			            styl.setFillPattern(FillPatternType.DIAMONDS);		            
//						XSSFCell celll = sheet.getRow(row_cell).createCell(4);
//						font.setBold(true);
//						styl.setFont(font);
//				        celll.setCellValue("FAILED"); 
//				        celll.setCellStyle(styl);
//				        row_cell=row_cell+2;
//					
//				}


				
				
				 
//	}
		    timestamp = new Timestamp(System.currentTimeMillis());
            excelFilePath = excelFilePath + "ForgetPassword- " + sdf1.format(timestamp) + ".xlsx";
		    File file =new File(excelFilePath);
			FileOutputStream fos=new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
			
			System.out.println("Output Report path:" +excelFilePath);
			JOptionPane.showMessageDialog(null, "Execution Completed Successfully. Reports saved in " +excelFilePath, "Note",JOptionPane.INFORMATION_MESSAGE);
		}
//		else {
//			JOptionPane.showMessageDialog(null, "Execution Failed. Unable to read Input data.", "Error",JOptionPane.ERROR_MESSAGE);	
//		}
	}
	
	@Then("user clicks submit button then it takes to homepage")
	public void user_clicks_submit_button_then_it_takes_to_homepage() {
	    
	}

	@Given("user should click the Admin button")
	public void user_should_click_the_admin_button() {
	    
	}

	@Given("user could see all the options in this button")
	public void user_could_see_all_the_options_in_this_button() {
	    
	}

	@When("user clicks the User option")
	public void user_clicks_the_user_option() {
	   
	}

	@Then("it takes the user to user page.")
	public void it_takes_the_user_to_user_page() {
	    
	}

	@Given("user click the add user button")
	public void user_click_the_add_user_button() {
	    
	}

	@Then("User should fill all the details of user")
	public void user_should_fill_all_the_details_of_user() {
	    
	}

	@Then("the user click the submit button")
	public void the_user_click_the_submit_button() {
	   
	}

	@Then("user click the Ok button")
	public void user_click_the_ok_button() {
	  
	}

	@Then("new user has created")
	public void new_user_has_created() {
	   
	}
	
	@DataTableType
	public IDataReader excelToDataTable(Map<String, String> entry) { // [Excel= <fileName>, Location=<FileLocation> ..]
		ExcelConfiguration config = new ExcelConfiguration.ExcelConfigurationBuilder()
				.setFileName(entry.get("Excel"))
				.setFileLocation(entry.get("Location"))
				.setSheetName(entry.get("Sheet"))
				.setIndex(Integer.valueOf(entry.getOrDefault("Index", "0")))
				.build();
		return new ExcelDataReader(config);
				
	}
	
	
}


