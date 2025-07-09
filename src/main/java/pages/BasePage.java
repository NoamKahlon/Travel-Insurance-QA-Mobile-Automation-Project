package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BasePage {

    private final WebDriver driver;
    private final int secondsToWait;

    // ========= LOCATORS =========
    private final By steps = By.cssSelector("div.step");
    private final By cookieCloseButton = By.id("acceptButton");
    private final By finalPriceField = By.cssSelector(".finalPriceValue");

    // ========= CONSTANTS =========
    private final String baseUrl = "https://www.bth.co.il/";

    // ========= CONSTRUCTOR =========
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.secondsToWait = 30;
    }

    //////// ACTIONS ////////

    public void click(By locator) {
        Allure.step("Click on: " + locator, () -> {
            WebElement element = waitForElementToBeClickable(locator);
            element.click();
        });
    }

    public void openButtonOnclickUrlInSameTab(By locator) {
        try {
            Allure.step("Open onclick URL in same tab for: " + locator, () -> {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));

                String onclick = element.getAttribute("onclick");
                if (onclick != null && onclick.contains("window.open")) {
                    String url = onclick.substring(onclick.indexOf("'") + 1, onclick.lastIndexOf("'"));
                    driver.get(url);
                } else {
                    try {
                        element.click();
                    } catch (ElementClickInterceptedException e) {
                        element = waitForElementToBeClickable(locator);
                        element.click();
                    }
                }
            });
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Failed to click or open URL for: " + locator, e);
        }
    }

    public void forceClick(By locator) {
        Allure.step("Force click on: " + locator, () -> {
            WebElement element = waitForElementToBeClickable(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        });
    }

    public void clickCheckbox(By locator) {
        Allure.step("Click checkbox: " + locator, () -> {
            WebElement checkbox = waitForElementToBeClickable(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        });
    }

    public void sendKeys(By locator, CharSequence... keys) {
        Allure.step("Send keys to: " + locator, () -> {
            WebElement element = waitForElementToAppear(locator);
            element.sendKeys(keys);
        });
    }

    public void pickDate(By locator, String date) {
        Allure.step("Pick date '" + date + "' for: " + locator, () -> {
            waitForElementToAppear(locator);
            WebElement input = getElement(locator);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].removeAttribute('readonly'); arguments[0].value = arguments[1];",
                    input, date
            );
        });
    }

    public void chooseOptionByIndexInMobileDropdown(By selectLocator, int index) {
        Allure.step("Choose option at index " + index + " from mobile dropdown: " + selectLocator, () -> {
            waitForElementToAppear(selectLocator);
            WebElement dropdown = getElement(selectLocator);
            dropdown.click();
            List<WebElement> options = driver.findElements(By.xpath("//option"));
            if (options.size() <= index) {
                throw new IllegalArgumentException("Dropdown has only " + options.size() + " options. Can't select index " + index);
            }
            options.get(index).click();
        });
    }

    public void chooseValueFromDropDownMenuSafe(By locator, String visibleText) {
        Allure.step("Choose value '" + visibleText + "' from dropdown: " + locator, () -> {
            waitForElementToAppear(locator);
            WebElement dropDown = getElement(locator);
            new Select(dropDown).selectByVisibleText(visibleText);
        });
    }

    //////// TABS & FRAMES ////////

    public void switchToIframe(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(secondsToWait))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    //////// SCROLL ////////

    public void scrollToElement(By locator) {
        Allure.step("Scroll to element: " + locator, () -> {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", element
            );

            new WebDriverWait(driver, Duration.ofSeconds(secondsToWait))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        });
    }

    //////// VERIFICATIONS ////////

    public String getTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(secondsToWait))
                .until(driver -> !driver.getTitle().trim().isEmpty());
        return driver.getTitle().trim();
    }

    public String getText(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.getText();
    }

    public boolean isDisplayed(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.isDisplayed();
    }

    public String getFinalPriceText() throws InterruptedException {
        Thread.sleep(6000);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String price = (String) js.executeScript(
                    "return document.querySelector('.finalPriceValue')?.textContent.trim() || '';"
            );

            if (price.isEmpty()) {
                throw new RuntimeException("Final price element is empty or not found.");
            }

            return price;
        } catch (Exception e) {
            return "";
        }
    }

    //////// STEP LOGIC ////////

    public String getCurrentStep() {
        By stepIndicatorLocator = By.cssSelector(".step-indicator.mobile > div:first-child");
        By currentStepTextLocator = By.cssSelector(".step-indicator.mobile .step.current");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String stepIndicatorText = wait.until(ExpectedConditions.visibilityOfElementLocated(stepIndicatorLocator)).getText().trim();
        String currentStepText = wait.until(ExpectedConditions.visibilityOfElementLocated(currentStepTextLocator)).getText().trim();

        return "step: " + stepIndicatorText + " current step: " + currentStepText;
    }

    public void verifyCurrentStep(String expectedStepNumber, String expectedStepTitle) throws InterruptedException {
        Thread.sleep(4000);
        String stepInfo = getCurrentStep();

        String stepNumber = stepInfo.split("current step:")[0].replace("step:", "").trim();
        String stepTitle = stepInfo.split("current step:")[1].trim();

        if (!expectedStepNumber.equals(stepNumber)) {
            throw new RuntimeException("Expected step number: " + expectedStepNumber + ", but got: " + stepNumber);
        }

        if (!expectedStepTitle.equals(stepTitle)) {
            throw new RuntimeException("Expected step title: '" + expectedStepTitle + "', but got: '" + stepTitle + "'");
        }

        Allure.step("Step number: " + stepNumber);
        Allure.step("Current step: '" + stepTitle + "'");
    }

    public String getStepBarColorValidationResult() {
        List<WebElement> steps = getElements(this.steps);
        boolean currentStepFound = false;
        boolean allValid = true;
        StringBuilder stepsSummary = new StringBuilder();

        for (WebElement step : steps) {
            String text = step.getText().trim();
            String classValue = step.getAttribute("class").trim();
            String backgroundColor = step.getCssValue("background-color").replace(" ", "");

            if (classValue.contains("current")) {
                currentStepFound = true;
                if (!backgroundColor.contains("31,47,102")) {
                    allValid = false;
                    stepsSummary.append("Current step: ").append(text).append(" color: WRONG (not blue)\n");
                } else {
                    stepsSummary.append("Current step: ").append(text).append(" color: blue\n");
                }
                break;
            } else {
                if (!backgroundColor.contains("96,187,112")) {
                    allValid = false;
                    stepsSummary.append("Completed step: ").append(text).append(" color: WRONG (not green)\n");
                } else {
                    stepsSummary.append("Completed step: ").append(text).append(" color: green\n");
                }
            }
        }

        if (!currentStepFound) {
            allValid = false;
            stepsSummary.append("ERROR: No current step found!\n");
        }

        return (allValid ? "PASS\n" : "FAIL\n") + stepsSummary.toString();
    }

    //////// ELEMENT GETTERS ////////

    public WebElement getElement(By locator) {
        return waitForElementToAppear(locator);
    }

    public List<WebElement> getElements(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(secondsToWait))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public List<WebElement> getElementsSafe(By locator) {
        try {
            waitForElementToAppear(locator);
            return driver.findElements(locator);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    //////// WAITS ////////

    public WebElement waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }

    //////// MISC ////////

    public void closeCookieBannerIfPresent() {
        try {
            waitForElementToAppear(cookieCloseButton);
            click(cookieCloseButton);
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    public void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Test Failed", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillEmail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailAddressLabel_0').value = arguments[0];", "noam123@gmail.com");
    }
}