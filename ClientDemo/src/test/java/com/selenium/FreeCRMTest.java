package com.selenium;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Selenium.BaseClass;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.pageObjects.FreCRMCreateNewContact;
import com.pageObjects.FreeCRMLoginPage;

public class FreeCRMTest extends BaseClass{

	
	@Test
	public void LoginFreeCRM() throws IOException, InterruptedException {
		logger = extent.createTest("Login to FreeCRM with Chrome Browser");
		logger.log(Status.INFO, MarkupHelper.createLabel("Login to FIORI with Chrome test case started", color.GREEN));	

		//logger.log(Status.INFO, "Login to OTM with Chrome test case started");
		logger.assignCategory("POC Testing");
		
		driver = initializeDriver();
		FreeCRMLoginPage pgLogin = new FreeCRMLoginPage(driver, logger, prop);
		pgLogin.login();

	}
	
	@Test
	public void createNewContact() throws InterruptedException {
		logger = extent.createTest("Create New Contact");
		logger.log(Status.INFO, MarkupHelper.createLabel("Create New Contact test case started", color.GREEN));	

		//logger.log(Status.INFO, "Login to OTM with Chrome test case started");
		logger.assignCategory("POC Testing");
		
		FreCRMCreateNewContact pgCreateNewContact = new FreCRMCreateNewContact(driver, logger, prop);
		pgCreateNewContact.addnewContact();
	
		
	}
}
