package TestUtils;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.Reusable;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestNgListeners extends BaseTest implements ITestListener{

	
	AppiumDriver driver;
	ExtentTest test;
	ExtentReports extent =ExtentReportNG.getReporterObject();
	
	public TestNgListeners() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		//add the Extent report create code here
		test = extent.createTest(result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	//Add the screenshot code here		
		System.out.println("We are in TestFailure");
		test.fail(result.getThrowable());
		
		try {
			
			//To get the driver from result - veryImportant
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());    
			
		    
			
		    }catch (Exception el) {
			// TODO: handle exception
			el.printStackTrace();
			
		                      }
		
		try {
			
			//Test to get the screenshot from the path
			test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
			
			
		    }catch (Exception e) {                                      
			// TODO: handle exception
			e.printStackTrace();
		                         }
		
		
		}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush();  //On completion of complete 
		
	}

}
