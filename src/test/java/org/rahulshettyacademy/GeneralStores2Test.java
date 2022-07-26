package org.rahulshettyacademy;
import java.io.IOException;
import TestUtils.BaseTest;
import Utilities.Reusable;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class GeneralStores2Test extends BaseTest {

	@Test(groups= {"Regression"})
	public void SecondTest() throws IOException, InterruptedException
	{
		
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Mamtha Shanmugam");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		Reusable r = new Reusable();
		r.scrollIntoView(driver, "Austria");
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Austria']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		int prodListSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		Double sum=  0.0;
		for(int i=0; i<prodListSize; i++)
		{
			String amtStr =  driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getAttribute("text");
		    String sub = amtStr.substring(1);
		    System.out.println(sub);
		    Double d =   convertToDouble(sub);
		    sum = sum + d;
		    
		    
		}
		
		String TotalStr = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getAttribute("text");
		String SubTotalStr = TotalStr.substring(1);
		Double Tot = convertToDouble(SubTotalStr);
		
		System.out.println(sum);
		System.out.println(Tot);
		
		Assert.assertEquals(sum, Tot);
		
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		WebElement ele2 =driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		//r.longPressAction(driver, ele2);
		//String alert =driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
		
		//Assert.assertEquals(alert, "Terms Of Conditions");
		
		//driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(3000);	
		
		// Will get all the handles of the app
		Set<String> Handles = driver.getContextHandles();
		
		for(String Handle: Handles)
		{
			
			System.out.println(Handle);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");  //Changing the context from Native to -Work with Selenium
		Thread.sleep(2000);
		driver.findElement(By.name("q")).sendKeys("Kendra Lust");
	    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");   //Changing the context back to native - Work with Appium
		Thread.sleep(5000);
		
		}
	
	
	
//	private By ByXPath(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	public Double convertToDouble(String s)
	{
		return Double.parseDouble(s);
		
	}

}
