package Ufinance.hybrid.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import Ufinance.hybrid.generic.Xls_Reader;
import Ufinance.hybrid.seleniumcore.ActionDriver;

public class ExecuteBasedOnXLData extends ActionDriver
{
	public Properties xpath;
	public static boolean isxpathLoaded;
	public String projectPath = System.getProperty("user.dir");

	public ExecuteBasedOnXLData(WebDriver driver1) {
		super(driver1);
	}
	
	public void LoadUIMaps() //throws IOException
	{
		FileInputStream ip;
		if (!isxpathLoaded) {
			log.info("Loading the UIMaps property file");
			xpath = new Properties();
			String path = projectPath
					+ "\\src\\Ufinance\\hybrid\\uimaps\\xpath.properties";
			File file = new File(path);
			try {
				ip = new FileInputStream(file);
				xpath.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("path not loaded properly");
				e.printStackTrace();
			} 
			log.info("UImaps property file loaded");
			isxpathLoaded = true;
		}
	}
	
	public enum actions
	{
		click,
		input,
		verifyElementPresence,
		verifyTextOnElement,
		verifyTitle,
		navigateBack,
		javascript,
		waitforElement,
		 doubleclick,
		 javascriptclick,
		moveToElementClick
		
	}
	
	public void execute(String xlsxPath, String testcaseName )
	{
		//Xls_Reader xls = new Xls_Reader( projectPath + xlsxPath);
		Xls_Reader xls = new Xls_Reader(xlsxPath);
		LoadUIMaps();
		int rows = xls.getRowCount(testcaseName);
		
		for (int i = 2; i <= rows; i++) 
		{
			String flag = xls.getCellData(testcaseName, "flag", i);
			String action = xls.getCellData(testcaseName, 1, i);
			String element = xls.getCellData(testcaseName, 2, i);
			String elementDescription = xls.getCellData(testcaseName, 3, i);
			String value = xls.getCellData(testcaseName, 4, i);
			String verifyText = xls.getCellData(testcaseName, 5, i);
			
			System.out.println(flag + "---" + action + "---" + element + "---" + elementDescription + "---" + value + "---" + verifyText);
			
			String locatorValue = xpath.getProperty(element);
			String locator = "";
			if (!element.isEmpty()) {
				String[] s1 = element.split("_");
				if (s1.length > 0) {
					locator = s1[1];
				}
			}
			if(flag.equalsIgnoreCase("yes"))
			{
				
				switch (actions.valueOf(action)) {
				case click:
					System.out.println(actionStatus);
					click(locator, locatorValue);
					System.out.println("After click "+actionStatus);
					if(actionStatus){
						boolean status = xls.setCellData(testcaseName, "Result", i, "pass");
						System.out.println("Is looged the in xl ? " +status);
					}
					else
						xls.setCellData(testcaseName, "Result", i, "fail");
					break;
				case input:
					input(locator, locatorValue, value);
					if(actionStatus)
						xls.setCellData(testcaseName, "Result", i, "pass");
					else
						xls.setCellData(testcaseName, "Result", i, "fail");
					break;
				case verifyTextOnElement:
					verifyTextOnElement(locator, locatorValue, verifyText);
					if(actionStatus)
						xls.setCellData(testcaseName, "Result", i, "pass");
					else
						xls.setCellData(testcaseName, "Result", i, "fail");
					break;
				case navigateBack:
					navigateBack();
					if(actionStatus)
						xls.setCellData(testcaseName, "Result", i, "pass");
					else
						xls.setCellData(testcaseName, "Result", i, "fail");
					break;
				case waitforElement:
					waitforElement();
					if(actionStatus)
						xls.setCellData(testcaseName, "Result",i, "pass");
					else
					  xls.setCellData(testcaseName, "Result",i,"Fail");
				case  doubleclick:
					doubleclick(locator,locatorValue); 
					if(actionStatus)
					xls.setCellData(testcaseName, "Result", i, "pass");
					else
						xls.setCellData(testcaseName, "Result",i, "Fail");
				case javascriptclick:
					javascriptclick(locator,locatorValue);
					if(actionStatus)
						xls.setCellData(testcaseName, "Result",i, "Pass");
					else
						xls.setCellData(testcaseName, "Result", i, "Fail");
				case moveToElementClick:
				 moveToElementClick(locator,locatorValue);
				 if(actionStatus)
					 xls.setCellData(testcaseName, "Result", i, "pass");
				 else
					 xls.setCellData(testcaseName, "Result",i, "Fail");
					
				default:
				
					break;
				}
			}
		}
	}

}
