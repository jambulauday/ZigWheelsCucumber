package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UpcomingBikesPage;
import testBase.BaseClass;

public class TC_001_BikeDetails extends BaseClass{
	
	
	@Test(priority=1)
	public void clickOnNewBikes() {
		HomePage hm = new HomePage(driver);
		BaseClass.getLogger().info("Navigated to NewBikes");
		//hm=new HomePage(BaseClass.getDriver());
		hm.moveToNewBikes();
			
	}
	
	@Test(priority=2)
	public void clickOnUpcomingBikes() {
		HomePage hm = new HomePage(driver);
		hm.clickOnUpcomingBikes();
		BaseClass.getLogger().info("Clicked on Upcoming bikes");
	}
	
	@Test(priority=3)
	public void selectHondaBikes() {
		
		 UpcomingBikesPage ubp= new UpcomingBikesPage(driver);
		 ubp.selectManufacturer();
		 BaseClass.getLogger().info("Honda as manufacturer is selected ");
	}
	
	@Test(priority=4,dependsOnMethods="selectHondaBikes")
	public void hondaUpcomingBikes() {
		 UpcomingBikesPage ubp= new UpcomingBikesPage(driver);
		 ubp.clickViewMore();
		List<WebElement> modelName= ubp.listOfModels();
		List<WebElement> price= ubp.priceOfModels();
		List<WebElement> expectedDate= ubp.expectedDateOfRelease();
		
		 for(int i=0;i<modelName.size();i++) {
	    	 
			   String temp=price.get(i).getText();
			   if(!temp.contains("Lakh"))
			   {
				   System.out.println(modelName.get(i).getText()+"    "+price.get(i).getText()+"   "+expectedDate.get(i).getText());
			   }
			   else if(temp.contains("Lakh"))	 
			   {
				   double p=Double.parseDouble(temp.substring(temp.indexOf("Rs")+4,temp.indexOf("Lakh")-1));
			    	if(p<=4.00) {
			    		System.out.println(modelName.get(i).getText()+"    "+price.get(i).getText()+"   "+expectedDate.get(i).getText());
			    		     }
			   	}
		 }
	}
	
	
	
	
	

}
