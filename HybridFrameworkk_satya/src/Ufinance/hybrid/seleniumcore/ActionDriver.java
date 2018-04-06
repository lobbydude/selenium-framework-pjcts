package Ufinance.hybrid.seleniumcore;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class ActionDriver {

	private WebDriver driver;
	private WebElement element;
	private Actions action;
	public static Logger log = Logger.getLogger("ActionDriver");
	public boolean actionStatus;
	

	public ActionDriver(WebDriver driver1) {
		driver = driver1;
		action=new Actions(driver1);
	}

	public enum locatorName {
		id, name, xpath, className, tagName, linkText, partialLinkText, cssSelector

	}

	public void click(String locator, String locatorValue) 
	{
		element = handleExceptionWhileFindingElement(locator, locatorValue);
		
		if(element != null)
		{
			if(element.isEnabled())
			{
				element.click();
				actionStatus = true;
				log.info("Click is sucessful");
			}
			else
			{
				System.out.println("Element is present but not enabled to click whose locator is ->" + locator + " --> with value -->" + locatorValue);
				log.info("Element is present but not enabled to click whose locator is ->" + locator + " --> with value -->" + locatorValue);
				Assert.assertTrue(element.isEnabled(), "Element is present but not enabled to click whose locator is ->" + locator + " --> with value -->" + locatorValue);
			}
		}
		else
		{
			System.out.println("failed to find an element to click with locator-->" + locator + " with value -->" + locatorValue);
			log.info("failed to find an element to click with locator-->" + locator + " with value -->" + locatorValue);
			try {
				Assert.assertNotNull(element, "failed to find an element to click with locator-->" + locator + " with value -->" + locatorValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doubleclick(String locator,String locatorValue)
    {
           element=handleExceptionWhileFindingElement(locator, locatorValue);
           if(element!=null)
           {
                  if(element.isDisplayed())
                  {
                        //action.doubleClick(element).build().perform();
                        action.doubleClick(element).build().perform();
                        //action.doubleClick(element)
                  }
                  else 
                  {
                        
                        log.info("There is a element but it is not enabled to double click");
                        actionStatus = false;
                        Assert.assertTrue(false, "There is a element but it is not enabled to double click");
                  }
           }
           else
           {
                  
                  log.info("There is no element to double click");
                  actionStatus = false;
                  //Assert.assertTrue(false, "There is no element to double click");
           }
    }
	public void moveToElementClick(String locator,String locatorValue)
    {
           element=handleExceptionWhileFindingElement(locator, locatorValue);
           if(element!=null)
           {
                  if(element.isDisplayed())
                  {
                        action.moveToElement(element).click().build().perform();
                  }
                  else 
                  {
                        
                        log.info("There is a element but it is not enabled to moveTocclick mouse");
                        actionStatus = false;
                        Assert.assertTrue(false, "There is a element but it is not enabled to moveToclick mouse");
                  }
           }
    }

	public void javascriptclick(String locator, String  locatorValue)
    {
    
           
                  element =handleExceptionWhileFindingElement(locator,  locatorValue);
                  
                  if(element != null)
                  {
                        if(element.isEnabled())
                        {
                               
           
                               ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

                                                                        
                                                                        
                               System.out.println("Click is success in JS");
                               log.info("Click is success");
                               
                        }
                        else 
                        {
                               
                               log.info("There is a element but it is not enabled to click");
                               
                               System.out.println("There is a element but it is not enabled to click");
                               
    }
                  }
    }



	public void input(String locator, String locatorValue, String valueToEnter) {

		element = handleExceptionWhileFindingElement(locator, locatorValue);
		
		if(element != null)
		{
			if(element.isEnabled())
			{
				element.sendKeys(valueToEnter);
				actionStatus = true;
				log.info("Entering a text on the webelement is successful");
			}
			else
			{
				System.out.println("Element is present but not enabled to type whose locator is ->" + locator + " --> with value -->" + locatorValue);
				log.info("Element is present but not enabled to type whose locator is ->" + locator + " --> with value -->" + locatorValue);
				Assert.assertTrue(element.isEnabled(), "Element is present but not enabled to type whose locator is ->" + locator + " --> with value -->" + locatorValue);
			}
		}
		else
		{
			System.out.println("failed to find an element to type with locator-->" + locator + " with value -->" + locatorValue);
			log.info("failed to find an element to type with locator-->" + locator + " with value -->" + locatorValue);
			Assert.assertNotNull(element, "failed to find an element to type with locator-->" + locator + " with value -->" + locatorValue);
		}
	
	}
	public void verifyElementPresence(String locator, String locatorValue) {
		
		element = handleExceptionWhileFindingElement(locator, locatorValue);
		
		if(element != null)
		{
			actionStatus = true;
			Assert.assertTrue(element != null, "Expected element is not present");
			log.info("Expected element is present");
		}
		else
		{
			System.out.println("failed to find an exepcted element with locator-->" + locator + " with value -->" + locatorValue);
			log.info("failed to find an expected element with locator-->" + locator + " with value -->" + locatorValue);
			Assert.assertNotNull(element, "failed to find an element with locator-->" + locator + " with value -->" + locatorValue);
		}
		
	}

	public void verifyTextOnElement(String locator, String locatorValue, String expectedText)
	{
		element = handleExceptionWhileFindingElement(locator, locatorValue);
		
		if(element != null)
		{
			String actualText = element.getText();
			if(actualText.equals(expectedText))
			{
				actionStatus = true;
				System.out.println("Text verification on element is done");
				log.info("Text verification on element is done");
			}
			else
			{
				System.out.println("Element is present but there is no expected text on it");
				log.info("Element is present but there is no expected text on it");
				Assert.assertTrue(actualText.equals(expectedText), "Element is present but there is no expected text on it");
			}
			
		}
		else
		{
			System.out.println("failed to find an element to verify text with locator-->" + locator + " with value -->" + locatorValue);
			log.info("failed to find an element to verify text with locator-->" + locator + " with value -->" + locatorValue);
			Assert.assertNotNull(element, "failed to find an element to verify the Text on the element with locator-->" + locator + " with value -->" + locatorValue);
		}
	
	}
	
	public void verifyTitle(String expectedTitle)
	{
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle))
		{
			log.info("Expected title is present");
			actionStatus = true;
		}
		else
			log.info("Expected title is not present");
		Assert.assertTrue(actualTitle.equals(expectedTitle), "Expected title is not present on the page");
	}
	
	public void navigateBack()
	{
		actionStatus = false;
		try {
			driver.navigate().back();
			actionStatus = true;
			log.info("Navigating back is success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Navigating back is failed");
		}
	}
	public void javascript(String ElementLocator, String LocatorValue)
	{
	
		actionStatus=false;
			element =handleExceptionWhileFindingElement(ElementLocator, LocatorValue);
			
			if(element != null)
			{
				if(element.isEnabled())
				{
					
		
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

											
											
					System.out.println("Click is success in JS");
					log.info("Click is success");
					
				}
				else 
				{
					
					log.info("There is a element but it is not enabled to click");
					
					System.out.println("There is a element but it is not enabled to click");
					
	}
			}
	}
	public void waitforElement()
	{
		actionStatus=false;
		try{
			Thread.sleep(5000);
			actionStatus=true;
			log.info("wait for element");
		}
				catch(InterruptedException e){
					log.info("wait failed");
					
				}
			}
		
			
	
	public WebElement handleExceptionWhileFindingElement(String locator, String locatorValue)
	{
		actionStatus = false;
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		WebElement elm = null;
		

		try {
			switch (locatorName.valueOf(locator)) {
			case id:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case name:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case xpath:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case linkText:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case className:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case tagName:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case partialLinkText:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			case cssSelector:
				log.info("Finding an element with element locator -> " + locator + "=" + locatorValue);
				elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				log.info("Finding is successful with element locator -> " + locator + "=" + locatorValue);
				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.info("Failed to Find an element with element locator -> " + locator + "=" + locatorValue);
		}
		
		return elm;
	
	}
}
