package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Reusable {

		
	
	public AppiumDriverLocalService service;
	
	
	public List<HashMap<String, String>> getJsonDataFromFile(String jsonFilePath) throws IOException
	{
		//System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\testData\\GeneralStores.json"
		//Convert Json data to String Data
		//FileUtils Class is available in Commons.io Dependency
		//ObjectMapper, TypeReference Class are available in Jakson Databind Dependency
		//The FileUtils will convert the Json File Data to Json String Data
		String JsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data = mapper.readValue(JsonContent,   //This piece of code will convert the Json data into 
		new TypeReference<List<HashMap<String, String>>>(){
			
		});
		
        return data;
		
	}	
	
	// Reusable LongPressActions 
	public void longPressAction(AndroidDriver driver,WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
	    		 ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
	    		"duration",2000 ));
	}
	
	
	
	
	
	
	public void scrollIntoView(AndroidDriver driver,String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ text +"\"))"));
	}
	

	
	
	
	public void scrollToEndAction(AndroidDriver driver)
	{
		boolean Scrollmore;
	     do {
	    Scrollmore = (Boolean)  ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture",
	    		 ImmutableMap.of("left", 100, "top", 100, "width", 200, "height",200, "direction","down","percent",3.0));
	     }while(Scrollmore);
	}
	
	
	public void swipeGesture(AndroidDriver driver, WebElement ele, String direction)
	{
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", 
		        ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
			    		    "direction",direction,                                  //direction of the swipe
			    		    "percent",0.75 ));  
	}
	
	
	public void DragDropAction(AndroidDriver driver, WebElement source, int x, int y)
	{
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", 
		          ImmutableMap.of("elementId", ((RemoteWebElement)source).getId(),
		  	    		          "endX",x,                                  
			   	    		      "endY",y
			   	    		      ));
	}
	
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress,int port)
	{
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\sandeep\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
					.withIPAddress(ipAddress).usingPort(port).build();
				service.start();
				return service;
	}
	
	
	public String getScreenShotPath(String testCaseName,AppiumDriver driver) throws IOException
	{
		//This code does two jobs.
		//Capture the Screenshot and place in a folder
		//Sends the Screenshot path
	    File source  =	driver.getScreenshotAs(OutputType.FILE);
	    String destinationFile = System.getProperty("user.dir")+"\\reports"+ testCaseName+".png";   //Destination folder  
	    FileUtils.copyFile(source, new File(destinationFile));
	    System.out.println("We are in the Screenshot methid");
	    return destinationFile;
	    
	    
	    
	}
	

}
