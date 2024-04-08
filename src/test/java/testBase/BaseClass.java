package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pageObjects.HomePage;

public class BaseClass {
	
	
	static public WebDriver driver;
	public Properties p;
	 static Logger logger;
	
	@BeforeTest
	@Parameters({"browser"})	
	public void setUp(String br) throws InterruptedException, IOException {
		
		
		 FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		 p=new Properties();
		 p.load(file);
		
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			System.out.println("Chrome is Launched.");
			break;
 
		case "edge":
			driver = new EdgeDriver();
			System.out.println("Edge is Launched");
			break;
 
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
        String url=p.getProperty("URL");
        driver.get(url);	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	
	
	

}
