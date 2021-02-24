package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(id = "skip")
    private WebElement skipLink;

    @FindBy(id = "login_register_view")
    private WebElement mobileOrEmailField;

    @FindBy(id = "continue_login")
    private WebElement continueButton;

    @FindBy(id = "fb")
    private WebElement fbButton;

    @FindBy(id = "sign_in_button")
    private WebElement googleButton;

    public LandingPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void skipToHomePage() {
        waitForElementToBeVisible(skipLink);
        skipLink.click();
    }

    public void registerByMobileOrEmail(String mobileOrEmail) {
        mobileOrEmailField.sendKeys(mobileOrEmail);
        continueButton.click();
    }

    public void signInByFB() {
        fbButton.click();
    }

    public void signInByGoogle() {
        googleButton.click();
    }
}
