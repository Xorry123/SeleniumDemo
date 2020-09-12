package com.Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.Utility;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;


public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static Utility util;
	public static ExtentTest logger;
	public static Properties prop;
	public static String driverPath; 
	
	public ExtentColor color;
	
	@BeforeSuite
	public void configExtentReports() {

		//for providing reports in a path this class is used
		//String path = System.getProperty("user.dir")+"\\reports\\index.html";
		String path = System.getProperty("user.dir")+"/reports/index.html";
		
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//configuration settings
		reporter.config().setReportName("POC Automation Results");
		reporter.config().setDocumentTitle("Test Execution Report");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setCSS("css-string");

		
		
		
		// main class 
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rohit Alhat");
		extent.setSystemInfo("Project", "POC Web Automation");
	    extent.setSystemInfo("OS","Windows 10");

		

	}

	@AfterSuite
	public void tearDown() throws IOException {

		/*
		 * if(result.getStatus()==ITestResult.SUCCESS) { String
		 * temp=Utility.getScreenshot(driver);
		 * logger.pass(result.getMethod().getMethodName(),MediaEntityBuilder.
		 * createScreenCaptureFromPath(temp).build()); }
		 * 
		 * if(result.getStatus()==ITestResult.FAILURE) { String
		 * temp=Utility.getScreenshot(driver);
		 * logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.
		 * createScreenCaptureFromPath(temp).build());
		 * 
		 * 
		 * }
		 */
		extent.flush();
		//driver.quit();
		
		
	}
	
	
	public WebDriver initializeDriver() throws IOException {
		
		String filePath = System.getProperty("user.dir") +"\\src\\main\\java\\com\\Selenium\\data.properties" ;
		prop = new Properties();
		FileInputStream fis =new FileInputStream(filePath);
		
		prop.load(fis);
		String browserName=prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			driverPath= System.getProperty("user.dir") + "\\Drivers";
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\ralhat\\Desktop\\Rohit\\Selenium\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", driverPath +"\\chromedriver.exe");
			
			//System.setProperty("webdriver.ie.driver", "C:\\Users\\ralhat\\Desktop\\Rohit\\Selenium\\Drivers\\IEDriverServer.exe");
			driver = new ChromeDriver();
			
		}else {
			driverPath= System.getProperty("user.dir") + "\\Drivers";
			System.setProperty("webdriver.gecko.driver", driverPath +"\\geckodriver.exe");
			
			//System.setProperty("webdriver.ie.driver", "C:\\Users\\ralhat\\Desktop\\Rohit\\Selenium\\Drivers\\IEDriverServer.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		
	}

	
	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts =(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		//String path = System.getProperty("user.dir")+ "\\Screenshot\\" + System.currentTimeMillis()+".png";
		String path ="./reports/Screenshot/" + System.currentTimeMillis()+".png";

		File destination = new File(path);
		try {
			FileUtils.copyFile(src,destination);
		}catch(IOException e) {
			System.out.println("Capture Failed:- " +e.getMessage());
		}
		//System.out.println(path);		
		return path;	

		
	}

	
	
	public void myLogger(String decsription,ExtentTest logger,String status) {
		 switch(status){    
		    //case statements within the switch block  
		    case "PASS": 
		    	try {
					//logger.log(Status.PASS, MarkupHelper.createLabel("URL Launched in Chrome Browser", color.GREEN));	
					
					logger.pass(decsription,MediaEntityBuilder.createScreenCaptureFromPath("."+getScreenshot(driver)).build());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		    	break;    
		    case "FAIL":
		    	try {
					//logger.log(Status.FAIL, MarkupHelper.createLabel("URL Launched in Chrome Browser", color.RED));	

					logger.fail(decsription,MediaEntityBuilder.createScreenCaptureFromPath("."+getScreenshot(driver)).build());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		    case "INFO":
		    	try {

					logger.info(decsription);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}	
		    
		    
		 }
		
		
		
		
		
		
		
		/*if(status == "PASS") {
			try {
				//logger.log(Status.PASS, MarkupHelper.createLabel("URL Launched in Chrome Browser", color.GREEN));	
				
				logger.pass(text,MediaEntityBuilder.createScreenCaptureFromPath("."+getScreenshot(driver)).build());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else {
			try {
				//logger.log(Status.FAIL, MarkupHelper.createLabel("URL Launched in Chrome Browser", color.RED));	

				logger.fail(text,MediaEntityBuilder.createScreenCaptureFromPath("."+getScreenshot(driver)).build());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
			
*/		
		
	}
	
	public void waitTillElementVisible(String xpathvalue) {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathvalue)));
	}
	
public void waitTillElementVisibleByName(By name) {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(name));
		
	}
	
	public void clickButton(WebDriver driver,By name) {
		try {
			driver.findElement(name).click();
			myLogger("SuccessFully Clicked on Button ", logger,"PASS");
			Thread.sleep(2000);
		}catch(Exception e) {
			myLogger("Exception Occured " +e.getMessage(), logger.log(Status.FATAL, e),"PASS");

		}
	}
	
	
	public void enterData(WebDriver driver,By name,String data) throws InterruptedException {
		driver.findElement(name).sendKeys(data);
		myLogger("SuccessFully Entered Data:- " + data, logger,"PASS");
		Thread.sleep(2000);
	}
	
	public boolean elementExists(WebDriver driver,By name) {
		
		
		try {
		   driver.findElement(name);
		   
		   return true;
		} catch (NoSuchElementException e) {
			myLogger("NoSuchElementException :- " + e.getMessage(), logger,"FAIL");

		   return false;
		}
		
	}
	
	public static void highlight(WebDriver driver,By name) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String bgcolor = driver.findElement(name).getCssValue("backgroundColor");
		for(int i=0;i<10;i++) {
			
			//changeColor(driver, "rgb(255,69,0)", name);
			changeColor(driver, "rgb(0,200,0)", name);
			changeColor(driver, bgcolor, name);
		}
		
	
		
		
	}
	public static void changeColor(WebDriver driver,String color,By name) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '" +color+"'" ,driver.findElement(name) );
		try {
			Thread.sleep(20);
		}catch(InterruptedException e) {
			
		}
		
		
		
		
	}
	

	
	
}
