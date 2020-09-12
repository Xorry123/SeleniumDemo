package com.pageObjects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.Selenium.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class FreeCRMLoginPage extends BaseClass {

	WebDriver driver;
	ExtentTest logger;
	ITestResult result;
	Properties prop;
	
	
	
	public FreeCRMLoginPage(WebDriver driver,ExtentTest logger,Properties prop){
		this.driver=driver;
		this.logger=logger;
		this.prop=prop;
	}
	
	By userName = By.xpath("//input[@name='email']");
	By password = By.xpath("//input[@name='password']");
	By signInButton = By.xpath("//div[text()='Login']");
	
	public void login() throws InterruptedException, IOException {
		String url = prop.getProperty("FreeCRMURL");
		myLogger("Login to FreeCRM method started", logger, "INFO");
		
		driver.get(url);
		String strtitle=driver.getTitle();
		System.out.println(strtitle);
		driver.manage().window().maximize();
		
		
		highlight(driver, userName);
		driver.findElement(userName).sendKeys("rohitacetester@gmail.com"); 
		
		Thread.sleep(500);
		highlight(driver, password);

		driver.findElement(password).sendKeys("Razor@0909"); 
		Thread.sleep(500);
		logger.pass("Login Credentials Entered SuccessFully",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		Thread.sleep(500);
		highlight(driver, signInButton);

		driver.findElement(signInButton).click();
		System.out.println(driver.getTitle());
		if(strtitle.contentEquals("Cogmento CRM")) {
			myLogger("Login SuccessFull", logger,"PASS");
			
		}else {
			myLogger("Login Failed", logger,"FAIL");
		}
		
		
	}
}
