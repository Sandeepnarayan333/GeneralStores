package org.rahulshettyacademy;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import TestUtils.BaseTest;
import Utilities.Reusable;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjectFactory.android.LoginPageObjects;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;


public class GeneralStores1Test extends BaseTest {
	

	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void FirstTest(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		
		//LoginPageObjects l = new LoginPageObjects(driver);
		
		
		
		
		//l.setGender("Female");  // Setting Gender
	    
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+input.get("sex")+"']")).click();
		
		
		//l.setNameField("Mamtha Shanmugam");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys(input.get("name"));
		driver.hideKeyboard();
		
		//l.setCountry("Austria");
		
         driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		Reusable r = new Reusable();
         r.scrollIntoView(driver, "Austria");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+input.get("country")+"']")).click();
		//l.submitForm();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//String prod = driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan 6 Rings']")).getText();
		//System.out.println(prod);
		
		r.scrollIntoView(driver, "Jordan 6 Rings");
		Thread.sleep(2000);
		
		int items = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0;i<items;i++)
		{
		   String name1 = 	driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
		 
			if(name1.equalsIgnoreCase("Jordan 6 Rings")) 
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		String prodname = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals("Jordan 6 Rings", prodname);
	
		
	}
	
	
	@Test(groups = {"Regression"})
	public void AnotherTest()
	{
		System.out.println("This is the Third Test");
	}
	
	
	//@BeforeMethod                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	public void preSetup() throws InterruptedException
	{
		System.out.println("We are at preSetup");
		Activity act = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		System.out.println("We have crossed Activity");
		Thread.sleep(2000);
		driver.startActivity(act);    // As there is no permission to open the app.it is to be run only in emulator
	
	}
	
		
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> data = getJsonDataFromFile(System.getProperty("user.dir")+ "\\src\\test\\java\\org\\rahulshettyacademy\\testData\\GeneralStores.json");
		return new Object[][] {{data.get(0)}};
		
		//{{Hash}, {Hash}, {Hash}}  -  HashMap List Structure
	}


	
	
}

