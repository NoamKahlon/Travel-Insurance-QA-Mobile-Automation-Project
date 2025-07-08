package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class BaseTest {

    public AndroidDriver driver;
    protected BasePage basePage;

    @BeforeMethod
    public void setUp() throws Exception {
        Runtime.getRuntime().exec("adb shell settings put secure default_input_method com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME");
        Thread.sleep(1000); // זמן למעבר המקלדת

        // סגירת אפליקציית כרום מהניסיון הקודם
        Runtime.getRuntime().exec("adb shell am force-stop com.android.chrome");
        Thread.sleep(1000);

        // הגדרת קונפיגורציה ל-Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:deviceName", "Android Device");
        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:fullReset", false);
        capabilities.setCapability("appium:appPackage", "com.android.chrome");
        capabilities.setCapability("appium:appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("appium:newCommandTimeout", 120);
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:forceAppLaunch", true);
        capabilities.setCapability("appium:chromedriverAutodownload", true);
        //capabilities.setCapability("appium:chromedriverExecutableDir", "C:\\Users\\Noam\\chromedriver-win64");

        // אתחול הדרייבר
        driver = new AndroidDriver(new URL("http://localhost:4723"), capabilities);

        // טען את האתר
        driver.get("https://www.bth.co.il/");
        Thread.sleep(6000); // זמן טעינה אופציונלי

        // המתן ש-WebView ייטען
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(d -> driver.getContextHandles().size() > 1);

        // עבור ל-WebView
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            if (context.toLowerCase().contains("webview") || context.toLowerCase().contains("chromium")) {
                driver.context(context);
                break;
            }
        }

        // אתחול basePage
        basePage = new BasePage(driver);
        basePage.waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not take screenshot: " + e.getMessage());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
