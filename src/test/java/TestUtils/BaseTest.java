package TestUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Utilities.Reusable;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends Reusable{
	
	
	public AndroidDriver driver;
	  
	public BaseTest()
	{
	
	}
	

 
	//Connect the mobile over Wifi cmd is        adb connect 192.168.31.140:5555
	 @BeforeClass(alwaysRun=true)              //Runs before every class in the no matter what
	 public  void AppiumConfigure() throws IOException, InterruptedException
	  {
          
		     startServer();
			Thread.sleep(3000);
			
		  FileInputStream fis = new FileInputStream("\\Users\\sandeep\\eclipse-workspace\\AppiumFrameworkDesign\\global.properties");
		 
		  Properties prop = new Properties();
//		  String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
//		  System.out.println(ipAddress);
		  prop.load(fis);
		//  String port = prop.getProperty("port");
		  String App = (String) prop.get("GeneralStores");
		  
		 // service = startAppiumServer(ipAddress,Integer.parseInt(port));
		  
		  
		  UiAutomator2Options options = new UiAutomator2Options();
		  options.setDeviceName("Android Device");
		  //options.setChromedriverExecutable("\\Users\\sandeep\\Downloads\\chromedriver_win32"); 
		  options.setApp(App);
		   driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		   System.out.println("Yes");
		 
		  
		  
		  
		    
		    
	  }
	  
	  
	  
	 @AfterClass(alwaysRun=true)
	  public  void tearDown()   // Used to stop the server after the test execution
	  {
		     driver.quit();
			 stopServer();
			  
	  }
	 
		
		
	  
	  @SuppressWarnings("deprecation")
	public static void startServer() {
		    Runtime runtime = Runtime.getRuntime();
		    try {
		        runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
		        Thread.sleep(10000);
		    } catch (IOException | InterruptedException e) {
		        e.printStackTrace();
		    }
		}

		@SuppressWarnings("deprecation")
		public static void stopServer() {
		    Runtime runtime = Runtime.getRuntime();
		    try {
		        runtime.exec("taskkill /F /IM node.exe");
		        runtime.exec("taskkill /F /IM cmd.exe");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
//	  
	  
	  
	  


	}


