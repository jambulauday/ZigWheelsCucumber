package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UsedCarspage;
import testBase.BaseClass;

public class TC_002_CarDetails extends BaseClass{
	
	@Test(priority=1)
	public void moveToUsedCars() {
		
		 HomePage hp=new HomePage(driver);
		 hp.clickLogo();
		 hp.moveToUsedCars();
		}
		
	@Test(priority=2,dependsOnMethods="moveToUsedCars")
		public void selectCity(){
			 HomePage hp=new HomePage(driver);
			 hp.selectCity();
		}
		
	@Test(priority=3,dependsOnMethods="selectCity")
		public void popularCars() {
		  UsedCarspage ucp =new UsedCarspage(driver);
		  List<WebElement>popularCars=ucp.ListOfPopularcars();
		  for(WebElement x:popularCars) {
			  System.out.println(x.getText());
		  }
		}
}
