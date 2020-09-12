package com.pageObjects;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;

import com.Selenium.BaseClass;
import com.aventstack.extentreports.ExtentTest;

public class FreCRMCreateNewContact extends BaseClass {

	
	WebDriver driver;
	ExtentTest logger;
	ITestResult result;
	Properties prop;
	
	
	public FreCRMCreateNewContact(WebDriver driver,ExtentTest logger,Properties prop){
		this.driver=driver;
		this.logger=logger;
		this.prop=prop;
	}
	
	By ContactsLink = By.xpath("//span[text()='Contacts']");
	By newButton =	By.xpath("//button[@class='ui linkedin button']/i[@class='edit icon']");
	
	By headerText = By.xpath("//div[@class='ui header item mb5 light-black']");
	By firstName =	By.xpath("//input[@name='first_name']"); 
	By lastName =	By.xpath("//input[@name='last_name']");
	By companyName = By.xpath("//div[@name='company']//input[@class='search']");
	By addElement = By.xpath("///div[@class='selected item addition']/span[contains(text(),'Add')]");
	By tagsName =	By.xpath("//label[@for=\"tags\"]//div//input");
	
	By EmailAddress =	By.xpath("//input[@placeholder=\"Email address\"]");
	By categoryDropDownClick = By.xpath("//div[@name=\"category\"]//i[@class=\"dropdown icon\"]");
	By selectCategory = By.xpath("//span[@class=\"text\" and text()='Contact']");
	By statusDropDownClick = By.xpath("//div[@name=\"status\"]//i[@class=\"dropdown icon\"]");
	By selectStatus = By.xpath("//span[@class=\"text\" and text()='New']");
	By description = By.xpath("//textarea[@name=\"description\"]");
	By timeZoneDropDownClick = By.xpath("//div[@name='timezone']//i[@class='dropdown icon']");
	By selectTimezone = By.xpath("//span[@class=\"text\" and text()='Asia/Dili']");
	
	By address = By.xpath("//input[@name='address']");
	By city = By.xpath("//input[@name='city']");
	By phoneCountryDropDown = By.xpath("//div[@name='hint']//i[@class='dropdown icon']");
	By enterPhineCountry = By.xpath("//div[@name='hint']//input");
	By selectPhoneCountry = By.xpath("//div[@class='visible menu transition']//div");

	By phoneNumber = By.xpath("//input[@name='value' and @placeholder=\"Number\"]");
	By position = By.xpath("//input[@name='position']");
	
	By saveButton =	By.xpath("//button[@class='ui linkedin button']/i[@class='save icon']");
	By cancelButton =	By.xpath("//button[@class='ui linkedin button']/i[@class='cancel icon']");
	
	public void addnewContact() throws InterruptedException {
		
		myLogger("Add New Contact to FreeCRM method started", logger, "INFO");
		Thread.sleep(1000);
		highlight(driver, ContactsLink);
		clickButton(driver, ContactsLink);
		clickButton(driver, newButton);
		highlight(driver, firstName);
		enterData(driver, firstName, "Alpha");
		enterData(driver, lastName, "AlphaTest");
		highlight(driver, companyName);
		enterData(driver, companyName, "Pokemon");
		highlight(driver, companyName);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		
		
		enterData(driver, tagsName, "POC Testing");
		highlight(driver, tagsName);

		act.sendKeys(Keys.DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
//		if (elementExists(driver, addElement)) {
//			highlight(driver, addElement);
//			clickButton(driver, addElement);
//		}
		//driver.findElement(tagsName).sendKeys(Keys.ENTER);
		enterData(driver, EmailAddress, "A@B.com");
		clickButton(driver, categoryDropDownClick);
		clickButton(driver, selectCategory);
		
		clickButton(driver, statusDropDownClick);
		clickButton(driver, selectStatus);
		
		enterData(driver, description, "New Contact Creation");
		
		clickButton(driver, timeZoneDropDownClick);
		clickButton(driver, selectTimezone);
		
		enterData(driver, address, "49/3,Parade Road");
		enterData(driver, city,"Houston");
		
		clickButton(driver, phoneCountryDropDown);
		enterData(driver, enterPhineCountry, "India");
		
		List<WebElement> countries =  driver.findElements(selectPhoneCountry);		
		for (WebElement country : countries) {
		    if (country.getText().equalsIgnoreCase("India")) {
		    	country.click();
		        break;
		    }
		}
		
		
		enterData(driver, phoneNumber, "1313131313");
		enterData(driver, position, "Trainer");
		
		//clickButton(driver, saveButton);
		
		
	}

	
	
}
