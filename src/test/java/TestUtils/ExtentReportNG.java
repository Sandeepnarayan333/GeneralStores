package TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public ExtentReportNG() {
		// TODO Auto-generated constructor stub
	}
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject()
	{
		//ExtentReports & ExtentSparkReporter
		
				String path = System.getProperty("user.dir")+"\\reports\\index.html";   //Creating a path where the test file to be stored.
				System.out.println(path);
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);              //ExtentSparkreporter is helper class for Extent Reports
				reporter.config().setReportName("Web Automation Results");           
				reporter.config().setDocumentTitle("Test Results");
				
				extent = new ExtentReports();    //main Class
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Sandeep Kumar N");
				return extent;
	}

}
