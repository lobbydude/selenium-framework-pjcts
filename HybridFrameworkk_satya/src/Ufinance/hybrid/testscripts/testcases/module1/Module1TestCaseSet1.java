package Ufinance.hybrid.testscripts.testcases.module1;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import Ufinance.hybrid.executor.ExecuteBasedOnXLData;
import Ufinance.hybrid.testscripts.bed.TestBed;

public class Module1TestCaseSet1 extends TestBed 
{
	Logger log = Logger.getLogger("Module1TestCaseSet1");
	public Properties or;
	public static boolean isORLoaded;
	
	

/*	
	//@Test(priority=1)
	@Test
	public void login()
	{
		log.info("Started executing the Testcase1");
		// Selenium is started
		ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
		//exe.execute(config.getProperty("pathOfXlsx"), "login");
		exe.execute(config.getProperty("pathOfXlsx"),"login");
		
		
		log.info("Completed execution of the Testcase1 of Module1 and Ste1");
	}
//@Test(priority=2)
	@Test
  public void UnitManagement()
  {
	  log.info("Started executing the Testcase1");
		// Selenium is started
		ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
		//exe.execute(config.getProperty("pathOfXlsx"), "login");
		exe.execute(config.getProperty("pathOfXlsx"),"UnitManagement");
		
		
		log.info("Completed execution of the Testcase1 of Module1 and Ste1");  
  
  }*/
/*
@Test
public void FinancialTemplate()
{
	  log.info("Started executing the Testcase1");
		// Selenium is started
		ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
		//exe.execute(config.getProperty("pathOfXlsx"), "login");
		exe.execute(config.getProperty("pathOfXlsx"),"FinancialTemplate");
		
		
		log.info("Completed execution of the Testcase1 of Module1 and Ste1");  
}*/

/*	@Test

	public void ProductFloders()
	{
		  log.info("Started executing the Testcase1");
			// Selenium is started
			ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
			//exe.execute(config.getProperty("pathOfXlsx"), "login");
			exe.execute(config.getProperty("pathOfXlsx"),"ProductFloders");
			
			
			log.info("Completed execution of the Testcase1 of Module1 and Ste1");  
	}*/
	
@Test
	public void itemFloder()
	{
		  log.info("Started executing the Testcase1");
			// Selenium is started
			ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
			//exe.execute(config.getProperty("pathOfXlsx"), "login");
			exe.execute(config.getProperty("pathOfXlsx"),"itemFloder");
			
			
			log.info("Completed execution of the Testcase1 of Module1 and Ste1");  
	}
	/*
	@Test
	public void UserAndGroups()
	{
		log.info("Started executing the Testcase1");
		// Selenium is started
		ExecuteBasedOnXLData exe = new ExecuteBasedOnXLData(driver);
		//exe.execute(config.getProperty("pathOfXlsx"), "login");
		exe.execute(config.getProperty("pathOfXlsx"),"UserAndGroups");
		
	}*/
}


  