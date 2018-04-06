package Ufinance.hybrid.testscripts.bed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Ufinance.hybrid.seleniumcore.ActionDriver;

public class TestBed {
	
	public static Logger log = Logger.getLogger(TestBed.class.getName());
	public static boolean isInitialized;
	public static Properties config;
	public WebDriver driver;
	public String projectPath = System.getProperty("user.dir");
	public static ActionDriver driver1;
	
	public enum browserName
	{
		firefox,
	
		chrome
	}
	
	@BeforeSuite
	public void initializeLogs() throws IOException
	{
		
		if (!isInitialized) 
		{
			BasicConfigurator.configure();
			DOMConfigurator.configure("Log4j.xml");
			log.info("Log initialization is done");
			isInitialized = true;
			
			log.info("Loading the config property file");
			config = new Properties();
			String path = projectPath + "\\src\\Ufinance\\hybrid\\config\\config.properties";
			File file = new File(path);
			FileInputStream ip = new FileInputStream(file);
			
			config.load(ip);
			log.info("config property file loaded");
		}
	}
	
	@BeforeClass
	public void OpenBrowser() {
		switch (browserName.valueOf(config.getProperty("browser"))) {
		case firefox:
			System.out.println("Opening firefox");
			log.info("Opening the firefox");
			System.setProperty("webdriver.firefox.marionette", projectPath
					+ "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox is opened and ready to load the url");

			break;
		
		case chrome:
			log.info("Opening the chrome");
			System.out.println("Opening Chrome");
			System.setProperty("webdriver.chrome.driver", projectPath
					+ "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			Dimension d = new Dimension(480,320);
			driver.manage().window().setSize(d);
			log.info("Chrome is opened and ready to load the url");
			break;

		default:
			break;
		}
		
		log.info("Doing browser setting");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicitTimeOut")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(config.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("browser setting is done");
		
		log.info("Loading the url");
		driver.get(config.getProperty("url"));
		log.info("Completed the loading url");
		
		driver1 = new ActionDriver(driver);
	}

	@AfterClass
	public void CloseBrowser()
	{
		if(driver != null)
		{
			/*log.info("Closing the browser");
			driver.close();
			log.info("Browser is closed");
		*/}
		
	}

}
