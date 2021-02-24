package gradle.cucumber;

import io.appium.java_client.MobileBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LandingPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HomePageSteps extends BaseSteps {


    protected URL url;
    WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {


    }

    @When("I launch Quikr App")
    public void i_launch_quikr_app() {
        wait = new WebDriverWait(driver, 60);
    }

    @When("I choose to log in using Google")
    public void i_choose_to_log_in_using_google() {
        By google = MobileBy.id("com.quikr:id/sign_in_button");

        wait.until(ExpectedConditions.visibilityOfElementLocated(google)).click();
    }
    @Then("I see account picker screen with my email address {string}")
    public void i_see_account_picker_screen_with_my_email_address(String expectedEmail) throws InterruptedException {

        WebElement element = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.id("com.google.android.gms:id/account_name")
                        ));

        String actualEmail = element.getText();
        System.out.println(actualEmail);
        assertThat(expectedEmail, equalTo(actualEmail));
    }


    @When("I choose {string} as my city")
    public void i_choose_as_my_city(String city) {


        new LandingPage(driver).skipToHomePage();
        By cancel = By.id("android:id/button1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancel)).click();
        By deny = By.id("com.android.permissioncontroller:id/permission_deny_button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deny)).click();
        By later = By.xpath("//android.widget.Button[@text='LATER']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(later)).click();
        By dismiss = By.xpath("//android.widget.TextView[@text='Dismiss']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dismiss)).click();
        //android.widget.TextView
        By citySelector = By.id("com.quikr:id/citySpinner");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(citySelector));
        element.click();
        // android.widget.FrameLayout
        // android.widget.TextView
        // com.quikr:id/search_ET
        By cityLocator = By.xpath(String.format("//android.widget.TextView[@text='%s']", city));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityLocator)).click();
    }
    @When("I search for {string} under Used Cars")
    public void i_search_for_under_used_cars(String carType) {
        By carsCard = By.xpath("//android.widget.TextView[@text='Cars']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(carsCard)).click();
        By usedCars = By.xpath("//android.widget.TextView[@text='USED CARS']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usedCars)).click();
        By searchBar = By.id("com.quikr:id/cnb_hp_choose_et");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).click();
        By searchField = By.id("com.quikr:id/cnb_search_text_et");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.pressKey(new KeyEvent(AndroidKey.H));
        driver.pressKey(new KeyEvent(AndroidKey.O));
        driver.pressKey(new KeyEvent(AndroidKey.N));
        driver.pressKey(new KeyEvent(AndroidKey.D));
        driver.pressKey(new KeyEvent(AndroidKey.A));
        driver.pressKey(new KeyEvent(AndroidKey.SPACE));
        driver.pressKey(new KeyEvent(AndroidKey.C));
        driver.pressKey(new KeyEvent(AndroidKey.I));
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.Y));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    @Then("I should see the first car result as {string}")
    public void i_should_see_the_first_car_result_as(String string) {
        By searchResults = MobileBy.className("android.widget.TextView");
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResults));
        assertThat(elements.get(0).getText(), containsString(string));
    }

    private void extractPageSource(String pageSource) {
        File file = new File("/Users/FadyK/Workspaces/Experiments/Oreilly/AppiumBookTutorial/src/test/resources/source.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(pageSource.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
