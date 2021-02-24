package gradle.cucumber;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class StartingSteps extends BaseSteps{
    private AppiumDriverLocalService localService;
    private final static int port = 4723;
    private final static String nodePath = "/usr/local/bin/node";
    private final static String appiumJSPath = "/usr/local/bin/appium";
    private final static String logFilePath = "build/appium";
    private final static String ipAddress = "0.0.0.0";
    protected static final String appiumHubUrl = "http://localhost:4723/wd/hub";

    @Before
    public void startAppiumServer() throws MalformedURLException {
        localService = AppiumDriverLocalService.buildService(new
                AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodePath))
                .withAppiumJS(new File(appiumJSPath))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(logFilePath))
        );
        localService.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        URL url = new URL(appiumHubUrl);
        capabilities.setCapability(PLATFORM_NAME, "Android");
        capabilities.setCapability(DEVICE_NAME, "Pixel4");
        capabilities.setCapability(AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(APP, "/Users/FadyK/Workspaces/Experiments/Oreilly/AppiumBookTutorial/apps/Quikr.apk");

        driver = new AndroidDriver<AndroidElement>(url, capabilities);
    }

    @After
    public void stopAppiumServer() {
        driver.quit();
        localService.stop();
    }
}
