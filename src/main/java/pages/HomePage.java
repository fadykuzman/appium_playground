package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(id = "citySpinner")
    private WebElement cityDropdown;


    public HomePage(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
