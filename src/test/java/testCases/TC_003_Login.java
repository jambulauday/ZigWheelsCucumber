package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import Utilities.ExcelReader;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_003_Login extends BaseClass{
	
	HomePage homepage;
	LoginPage loginpage;
	Set<String> windowIds;
	List<String> windowIdsList;	
    
	@Test(priority=1)
	public void navigateToHomePage() {
		
		homepage=new HomePage(driver);
		homepage.clickLogo();	 
		BaseClass.getLogger().info("Navigated to Home Page");
	}
    
	@Test(priority=2)
	public void clickOnLogin() {
	    
		BaseClass.getLogger().info("Trying to login in google");
		homepage=new HomePage(driver);
		homepage.clickLogin();		
		homepage.clickGoogle();
		
	}
	@Test(priority=3,dependsOnMethods="navigateToHomePage")
	public void driverSwitchToGoogle() {
		BaseClass.getLogger().info("Switching Driver to Google");
		
		
		loginpage=new LoginPage(driver);	    
		windowIds=driver.getWindowHandles();
		windowIdsList=new ArrayList<>(windowIds);			
		
		driver.switchTo().window(windowIdsList.get(1));	
		
		System.out.println(driver.getTitle());
	}
    
	@Test(priority=4,dependsOnMethods="driverSwitchToGoogle",invocationCount=2)
	//@Parameters({"index"})
	public void enteringInvalidEmail() throws IOException, InterruptedException {
		ExcelReader excel=new ExcelReader();
		BaseClass.getLogger().info("Entering invalid credentials");

		try {
		loginpage.clickAnotherAcc();
		}catch(Exception e) {}
		
		//Getting email from excel file
		String email=excel.dataTestNG();	
		
		loginpage.setEmail(email);
		loginpage.clickNext();
		//Thread.sleep(10000);
		
		String errormsg=loginpage.getErrorMessage();
		System.out.println(errormsg);
	}
}
