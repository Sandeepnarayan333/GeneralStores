package PageObjectFactory.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import Utilities.Reusable;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPageObjects extends Reusable{
  
	 AndroidDriver driver;
	
	public LoginPageObjects(AndroidDriver driver) {
	        // TODO Auto-generated constructor stub
		    super();
            this.driver=driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    
	}
		
	
	

	

	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	public WebElement FemaleCheckBox;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	public WebElement MaleCheckBox;
	
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter name here']")
	public WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countryDropDown;
	
//	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Austria']");
//	private WebElement Austria;
//	//driver.findElement(By.xpath("//android.widget.TextView[@text='Austria']")).click();
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement LetsShopButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	
	public void setNameField(String name)
		{                                         
			nameField.sendKeys(name);                    // This is an action Method
			driver.hideKeyboard();
		}
	
	public void setGender(String gender)
	{
		if(gender.contains("Male"))
			MaleCheckBox.click();                        // This is an action Method
		else
			FemaleCheckBox.click();
		
	}
	
	public void setCountry(String CouName)
	{
		countryDropDown.click();
		//Reusable re = new Reusable();
		scrollIntoView(driver, CouName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+ CouName +"']")).click();
	
	}
	
	
	
	public void submitForm()
	{
		LetsShopButton.click();
	}
	
}
